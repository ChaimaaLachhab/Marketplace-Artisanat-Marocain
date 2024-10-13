package com.artisanat_backend.controller;

import com.artisanat_backend.dto.request.ProductRequestDto;
import com.artisanat_backend.dto.request.ReviewRequestDto;
import com.artisanat_backend.dto.response.ProductResponseDto;
import com.artisanat_backend.mapper.ProductMapper;
import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Customer;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.service.ProductService;
import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * Récupère tous les produits.
     * @return Liste des produits.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Récupère tous les produits populaires.
     * @return Liste des produits.
     */
    @GetMapping("/rated")
    public ResponseEntity<List<ProductResponseDto>> getTopRatedProducts() {
        List<Product> products = productService.getTopRatedProducts();
        List<ProductResponseDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Récupère tous les produits récemment ajoutés.
     * @return Liste des produits.
     */
    @GetMapping("/recent")
    public ResponseEntity<List<ProductResponseDto>> getRecentlyAddedProducts() {
        List<Product> products = productService.getRecentlyAddedProducts();
        List<ProductResponseDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Récupère tous les produits par Artisan ID.
     * @param artisanId ID de l'artisan.
     * @return Liste des produits.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @GetMapping("/artisan/{artisanId}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByArtisan(@PathVariable Long artisanId) {
        List<Product> products = productService.getProductsByArtisan(artisanId);
        List<ProductResponseDto> productDtos = products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    /**
     * Récupère un produit par son ID.
     * @param id ID du produit.
     * @return Produit demandé.
     */
    @GetMapping("/item/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null
                ? new ResponseEntity<>(productMapper.toDto(product), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Crée un produit avec des fichiers médias attachés.
     * @param productDto Produit à créer.
     * @param attachments Liste des fichiers médias.
     * @return Produit créé.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @PostMapping(value = "/create-with-media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> createProductWithMedia(@RequestPart("product") ProductRequestDto productDto,
                                                                     @RequestPart("attachments") List<MultipartFile> attachments,
                                                                     @AuthenticationPrincipal Artisan artisan) {
        Product createdProduct = productService.createProductWithMedia(productDto, attachments, artisan);
        return new ResponseEntity<>(productMapper.toDto(createdProduct), HttpStatus.CREATED);
    }

    /**
     * Récupère les produits filtrés par critères.
     * @param name Nom du produit (optionnel).
     * @param category Catégorie du produit (optionnelle).
     * @param collection Collection du produit (optionnelle).
     * @param minPrice Prix minimum (optionnel).
     * @param maxPrice Prix maximum (optionnel).
     * @return Liste des produits filtrés.
     */
    @GetMapping("/filter")
    public ResponseEntity<Page<ProductResponseDto>> getFilteredProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Collection collection,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Double rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Product> products = productService.getFilteredProducts(name, category, collection, minPrice, maxPrice, rating, page, size);
        Page<ProductResponseDto> productDtos = products.map(productMapper::toDto);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }


    /**
     * Supprime un produit par son ID.
     * @param id ID du produit.
     * @return Réponse HTTP.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Met à jour un produit.
     * @param productId ID du produit à mettre à jour.
     * @param productDto Produit à mettre à jour.
     * @return Produit mis à jour.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto productDto) {
        Product updatedProduct = productService.updateProduct(productId, productDto);
        return new ResponseEntity<>(productMapper.toDto(updatedProduct), HttpStatus.OK);
    }

    /**
     * Ajoute un nouvel avis à un produit.
     * @param productId ID du produit.
     * @param reviewDto Avis à ajouter.
     * @return Produit mis à jour.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/add-review/{productId}")
    public ResponseEntity<ProductResponseDto> addReviewToProduct(@PathVariable Long productId,
                                                                 @RequestBody ReviewRequestDto reviewDto,
                                                                 @AuthenticationPrincipal Customer customer) {
        Product updatedProduct = productService.addReviewToProduct(productId, reviewDto, customer);
        return new ResponseEntity<>(productMapper.toDto(updatedProduct), HttpStatus.CREATED);
    }

    /**
     * Ajoute un nouvel avis à un produit.
     * @param productId ID du produit.
     * @param newStock Avis à ajouter.
     * @return Produit mis à jour.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PostMapping("/update-stock/{productId}")
    public ResponseEntity<ProductResponseDto> updateProductStock(@PathVariable Long productId,
                                                                 @RequestParam int newStock) {

            Product updatedProduct = productService.updateProductStock(productId, newStock);
        return new ResponseEntity<>(productMapper.toDto(updatedProduct), HttpStatus.CREATED);
    }
}
