package com.artisanat_backend.controller;

import com.artisanat_backend.model.Artisan;
import com.artisanat_backend.model.Product;
import com.artisanat_backend.service.ProductService;
import com.artisanat_backend.enums.Category;
import com.artisanat_backend.enums.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Récupère tous les produits.
     * @return Liste des produits.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Récupère tous les produits popular.
     * @return Liste des produits.
     */
    @GetMapping("/rated")
    public ResponseEntity<List<Product>> getTopRatedProducts() {
        List<Product> products = productService.getTopRatedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Récupère tous les produits recament ajouté.
     * @return Liste des produits.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @GetMapping("/recent")
    public ResponseEntity<List<Product>> getRecentlyAddedProducts() {
        List<Product> products = productService.getRecentlyAddedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Récupère un produit par son ID.
     * @param id ID du produit.
     * @return Produit demandé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Crée un produit avec des fichiers médias attachés.
     * @param product Produit à créer.
     * @param attachments Liste des fichiers médias.
     * @return Produit créé.
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'ARTISAN')")
    @PostMapping(value = "/create-with-media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProductWithMedia(@RequestPart("product") Product product,
                                                          @RequestPart("attachments") List<MultipartFile> attachments,
                                                          @AuthenticationPrincipal Artisan artisan) {
        Product createdProduct = productService.createProductWithMedia(product, attachments, artisan);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
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
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'ARTISAN')")
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> getFilteredProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Collection collection,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice) {

        List<Product> products = productService.getFilteredProducts(name, category, collection, minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Supprime un produit par son ID.
     * @param id ID du produit.
     * @return Réponse HTTP.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Met à jour un produit.
     * @param product Produit à mettre à jour.
     * @return Produit mis à jour.
     */
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}
