import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {ProductResponseDto} from "../dtos/response/product-response-dto";
import {ProductRequestDto} from "../dtos/request/product-request-dto";
import {Category} from "../enums/category.enum";
import {Collection} from "../enums/collection.enum";
import {ReviewRequestDto} from "../dtos/request/review-request-dto";
import {Product} from "../models/product.model";
import {ArtisanRequestDto} from "../dtos/request/artisan-request-dto";
import {ArtisanResponseDto} from "../dtos/response/artisan-response-dto";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = `${environment.apiUrl}/products`;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<ProductResponseDto[]> {
    return this.http.get<ProductResponseDto[]>(`${this.apiUrl}/all`);
  }

  getTopRatedProducts(): Observable<ProductResponseDto[]> {
    return this.http.get<ProductResponseDto[]>(`${this.apiUrl}/rated`);
  }

  getRecentlyAddedProducts(): Observable<ProductResponseDto[]> {
    return this.http.get<ProductResponseDto[]>(`${this.apiUrl}/recent`);
  }

  getProductsByArtisan(artisanId: number): Observable<ProductResponseDto[]> {
    return this.http.get<ProductResponseDto[]>(`${this.apiUrl}/artisan/${artisanId}`);
  }

  getProductById(id: number): Observable<ProductResponseDto> {
    return this.http.get<ProductResponseDto>(`${this.apiUrl}/item/${id}`);
  }

  createProductWithMedia(productDto: ProductRequestDto, attachments: File[]): Observable<ProductResponseDto> {
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify(productDto)], { type: 'application/json' }));
    attachments.forEach(file => formData.append('attachments', file));

    return this.http.post<ProductResponseDto>(`${this.apiUrl}/create-with-media`, formData);
  }

  getFilteredProducts(name?: string, category?: string, collection?: string, minPrice?: number, maxPrice?: number, rating?: number): Observable<ProductResponseDto[]> {
    let params: any = {};
    if (name) params.name = name;
    if (category) params.category = category;
    if (collection) params.collection = collection;
    if (minPrice) params.minPrice = minPrice;
    if (maxPrice) params.maxPrice = maxPrice;
    if (maxPrice) params.rating = rating;

    return this.http.get<ProductResponseDto[]>(`${this.apiUrl}/filter`, { params });
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  updateProduct(productId: number, productDto: ProductRequestDto): Observable<ProductResponseDto> {
    return this.http.put<ProductResponseDto>(`${this.apiUrl}/update/${productId}`, productDto);
  }

  addReviewToProduct(productId: number, reviewDto: ReviewRequestDto): Observable<ProductResponseDto> {
    return this.http.post<ProductResponseDto>(`${this.apiUrl}/add-review/${productId}`, reviewDto);
  }
}
