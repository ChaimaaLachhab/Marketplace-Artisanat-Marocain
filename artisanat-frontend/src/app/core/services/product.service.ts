import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {Product} from "../models/product.model";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = `${environment.apiUrl}/products`;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/all`);
  }

  getTopRatedProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/rated`);
  }

  getRecentlyAddedProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/recent`);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  createProductWithMedia(product: Product, attachments: File[]): Observable<Product> {
    const formData: FormData = new FormData();
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
    attachments.forEach((file, index) => formData.append(`attachments`, file, file.name));

    return this.http.post<Product>(`${this.apiUrl}/create-with-media`, formData);
  }

  getFilteredProducts(name?: string, category?: string, collection?: string, minPrice?: number, maxPrice?: number): Observable<Product[]> {
    let params: any = {};
    if (name) params.name = name;
    if (category) params.category = category;
    if (collection) params.collection = collection;
    if (minPrice) params.minPrice = minPrice;
    if (maxPrice) params.maxPrice = maxPrice;

    return this.http.get<Product[]>(`${this.apiUrl}/filter`, { params });
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/update`, product);
  }
}
