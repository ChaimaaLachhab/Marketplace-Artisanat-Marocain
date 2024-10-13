package com.artisanat_backend.service;

import com.artisanat_backend.dto.request.ProductRequestDto;
import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.enums.Collection;
import com.artisanat_backend.enums.Type;
import com.artisanat_backend.enums.VerificationStatus;
import com.artisanat_backend.exception.ProductNotFoundException;
import com.artisanat_backend.mapper.ProductMapper;
import com.artisanat_backend.model.*;
import com.artisanat_backend.enums.Category;
import com.artisanat_backend.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MediaUploadService mediaUploadService;
    private final ReviewService reviewService;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper, MediaUploadService mediaUploadService, @Lazy ReviewService reviewService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.mediaUploadService = mediaUploadService;
        this.reviewService = reviewService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getTopRatedProducts() {
        return productRepository.findTopRatedProducts();
    }

    public List<Product> getRecentlyAddedProducts() {
        return productRepository.findRecentlyAddedProducts();
    }

    public List<Product> getProductsByArtisan(Long artisanId) {
        return productRepository.findAllByArtisanId(artisanId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProductWithMedia(ProductRequestDto productDto, List<MultipartFile> attachments, Artisan artisan) {

        Product product = productMapper.toEntity(productDto);

        if (artisan.getVerificationStatus().equals(VerificationStatus.PENDING)) {
            throw new IllegalArgumentException("Artisan not accepted");
        }

        if (attachments.isEmpty()) {
            throw new IllegalArgumentException("No media files provided.");
        }

        List<MultipartFile> photos = new ArrayList<>();
        MultipartFile video = null;

        for (MultipartFile file : attachments) {
            String fileType = file.getContentType();
            if (fileType != null && fileType.startsWith("video")) {
                if (video != null) {
                    throw new IllegalArgumentException("Only one video is allowed.");
                }
                video = file;
            } else if (fileType != null && fileType.startsWith("image")) {
                photos.add(file);
            } else {
                throw new IllegalArgumentException("Invalid file type.");
            }
        }

        if (video != null && photos.size() > 9) {
            throw new IllegalArgumentException("With a video, only up to 10 photos are allowed.");
        }

        if (video == null && photos.size() > 10) {
            throw new IllegalArgumentException("Cannot upload more than 10 photos.");
        }

        List<Media> mediaList = new ArrayList<>();
        if (video != null) {
            Media videoMedia = mediaUploadService.handleMediaUpload(video, product);
            videoMedia.setType(Type.VIDEO);
            mediaList.add(videoMedia);
        }

        for (MultipartFile file : photos) {
            Media media = mediaUploadService.handleMediaUpload(file, product);
            media.setType(Type.PHOTO);
            mediaList.add(media);
        }

        product.setMedia(mediaList);
        product.setArtisan(artisan);

        return productRepository.save(product);
    }


    public Page<Product> getFilteredProducts(String name, Category category, Collection collection, Float minPrice, Float maxPrice, Double rating, int page, int size) {
        BooleanBuilder predicate = buildPredicate(name, category, collection, minPrice, maxPrice, rating);
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(predicate, pageable);
    }

    private BooleanBuilder buildPredicate(String name, Category category, Collection collection, Float minPrice, Float maxPrice, Double rating) {
        QProduct product = QProduct.product;
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null && !name.isEmpty()) {
            builder.and(product.name.containsIgnoreCase(name));
        }

        if (category != null) {
            builder.and(product.category.eq(category));
        }

        if (collection != null) {
            builder.and(product.collection.eq(collection));
        }

        if (minPrice != null) {
            builder.and(product.price.goe(minPrice));
        }

        if (maxPrice != null) {
            builder.and(product.price.loe(maxPrice));
        }

        if (rating != null) {
            builder.and(product.rating.eq(rating));
        }

        return builder;
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long productId, ProductRequestDto productDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        productMapper.partialUpdate(productDto, existingProduct);

        return productRepository.save(existingProduct);
    }

    public Product addReviewToProduct(Long productId, ReviewRequestDto reviewDto, Customer customer) {
        Review review = reviewService.addReview(productId, reviewDto, customer);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        product.getReviews().add(review);
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProductStock(Long productId, int newStock) {
        if (newStock < 0) {
            throw new RuntimeException("Stock cannot be negative");
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        product.setStock(product.getStock() + newStock);
        return productRepository.save(product);
    }

    public void updateProductRating(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.calculateAndPersistRating();
        productRepository.save(product);
    }

}
