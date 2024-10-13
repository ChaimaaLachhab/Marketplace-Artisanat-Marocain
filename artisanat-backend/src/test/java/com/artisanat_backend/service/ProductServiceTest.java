package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.ProductRequestDto;
import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import com.artisanat_backend.enums.Type;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.exception.ProductNotFoundException;
import com.artisanat_backend.mapper.ProductMapper;
import com.artisanat_backend.model.*;
import com.artisanat_backend.repository.ProductRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private MediaUploadService mediaUploadService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Artisan artisan;
    private List<MultipartFile> attachments;
    private ProductRequestDto productDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        artisan = new Artisan();
        attachments = new ArrayList<>();
        productDto = new ProductRequestDto();
    }

    @Test
    void getAllProducts_ReturnsProductList() {
        List<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(productList, result);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById_ReturnsProduct() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(productId);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void createProductWithMedia_ThrowsException_WhenArtisanNotVerified() {
        artisan.setVerificationStatus(VerificationStatus.PENDING);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.createProductWithMedia(productDto, attachments, artisan)
        );

        assertEquals("Artisan not accepted", exception.getMessage());
    }

    @Test
    void createProductWithMedia_ThrowsException_WhenNoMediaProvided() {
        artisan.setVerificationStatus(VerificationStatus.APPROVED);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.createProductWithMedia(productDto, attachments, artisan)
        );

        assertEquals("No media files provided.", exception.getMessage());
    }

    @Test
    void createProductWithMedia_CreatesProductWithMedia() {
        artisan.setVerificationStatus(VerificationStatus.APPROVED);
        MultipartFile mockImage = mock(MultipartFile.class);
        when(mockImage.getContentType()).thenReturn("image/png");
        attachments.add(mockImage);

        when(productMapper.toEntity(productDto)).thenReturn(product);

        Media mockMedia = new Media();
        when(mediaUploadService.handleMediaUpload(any(MultipartFile.class), any(Product.class)))
                .thenReturn(mockMedia);

        productService.createProductWithMedia(productDto, attachments, artisan);

        assertFalse(product.getMedia().isEmpty());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProduct_SavesAndReturnsUpdatedProduct() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProduct(productId, productDto);

        assertNotNull(result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProductStock_ThrowsException_WhenNewStockIsNegative() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Exception exception = assertThrows(RuntimeException.class, () ->
                productService.updateProductStock(productId, -1)
        );

        assertEquals("Stock cannot be negative", exception.getMessage());
    }

    @Test
    void addReviewToProduct_AddsReviewSuccessfully() {
        Long productId = 1L;
        Customer customer = new Customer();
        ReviewRequestDto reviewDto = new ReviewRequestDto();
        Review review = new Review();


        Product product = new Product();
        product.setReviews(new ArrayList<>());

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(reviewService.addReview(productId, reviewDto, customer)).thenReturn(review);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.addReviewToProduct(productId, reviewDto, customer);

        assertNotNull(result);
        assertTrue(result.getReviews().contains(review));
    }


    @Test
    void deleteProduct_CallsDeleteOnRepository() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
