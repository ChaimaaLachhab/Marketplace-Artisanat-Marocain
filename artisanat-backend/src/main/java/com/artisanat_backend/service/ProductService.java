package com.artisanat_backend.service;

import com.artisanat_backend.enums.Collection;
import com.artisanat_backend.enums.Type;
import com.artisanat_backend.model.Media;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.enums.Category;
import com.artisanat_backend.model.QProduct;
import com.artisanat_backend.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MediaService mediaService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getTopRatedProducts() {
        return productRepository.findTopRatedProducts();
    }

    public List<Product> getRecentlyAddedProducts() {
        return productRepository.findRecentlyAddedProducts();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product createProductWithMedia(Product product, List<MultipartFile> attachments) {
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
            } else if (fileType != null && (fileType.startsWith("image"))) {
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
            Media videoMedia = mediaService.handleMediaUpload(video, product);
            videoMedia.setType(Type.VIDEO);
            mediaList.add(videoMedia);
        }

        for (MultipartFile file : photos) {
            Media media = mediaService.handleMediaUpload(file, product);
            media.setType(Type.PHOTO);
            mediaList.add(media);
        }

        product.setMedia(mediaList);
        return productRepository.save(product);
    }


    public List<Product> getFilteredProducts(String name, Category category, Collection collection, Float minPrice, Float maxPrice) {
        BooleanBuilder predicate = buildPredicate(name, category, collection, minPrice, maxPrice);
        return (List<Product>) productRepository.findAll(predicate);
    }

    private BooleanBuilder buildPredicate(String name, Category category, Collection collection, Float minPrice, Float maxPrice) {
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

        return builder;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
