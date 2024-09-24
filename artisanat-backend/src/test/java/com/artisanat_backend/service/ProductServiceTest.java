package com.artisanat_backend.service;

import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    private MediaService mediaService;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Artisan artisan;
    private List<MultipartFile> attachments;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        artisan = new Artisan();
        attachments = new ArrayList<>();
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
    void getProductById_ReturnsNull_WhenProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product result = productService.getProductById(productId);

        assertNull(result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void createProductWithMedia_ThrowsException_WhenArtisanNotVerified() {
        artisan.setVerificationStatus(VerificationStatus.PENDING);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.createProductWithMedia(product, attachments, artisan)
        );

        assertEquals("Artisan not accepted", exception.getMessage());
    }

    @Test
    void createProductWithMedia_ThrowsException_WhenNoMediaProvided() {
        artisan.setVerificationStatus(VerificationStatus.APPROVED);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productService.createProductWithMedia(product, attachments, artisan)
        );

        assertEquals("No media files provided.", exception.getMessage());
    }

    @Test
    void createProductWithMedia_CreatesProductWithMedia() {
        artisan.setVerificationStatus(VerificationStatus.APPROVED);
        MultipartFile mockImage = mock(MultipartFile.class);
        when(mockImage.getContentType()).thenReturn("image/png");
        attachments.add(mockImage);

        Media mockMedia = new Media();
        when(mediaService.handleMediaUpload(any(MultipartFile.class), any(Product.class)))
                .thenReturn(mockMedia);

        productService.createProductWithMedia(product, attachments, artisan);

        assertFalse(product.getMedia().isEmpty());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProduct_SavesAndReturnsProduct() {
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.updateProduct(product);

        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct_CallsDeleteOnRepository() {
        Long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void getTopRatedProducts_ReturnsTopRatedList() {
        List<Product> topRatedProducts = new ArrayList<>();
        when(productRepository.findTopRatedProducts()).thenReturn(topRatedProducts);

        List<Product> result = productService.getTopRatedProducts();

        assertNotNull(result);
        assertEquals(topRatedProducts, result);
        verify(productRepository, times(1)).findTopRatedProducts();
    }

    @Test
    void getRecentlyAddedProducts_ReturnsRecentlyAddedList() {
        List<Product> recentlyAddedProducts = new ArrayList<>();
        when(productRepository.findRecentlyAddedProducts()).thenReturn(recentlyAddedProducts);

        List<Product> result = productService.getRecentlyAddedProducts();

        assertNotNull(result);
        assertEquals(recentlyAddedProducts, result);
        verify(productRepository, times(1)).findRecentlyAddedProducts();
    }

}
