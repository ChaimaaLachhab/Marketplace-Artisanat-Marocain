import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CartResponseDto} from "../dtos/response/cart-response-dto";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = `${environment.apiUrl}/carts`;

  constructor(private http: HttpClient) {}

  addProductToCart(productId: number, quantity: number): Observable<CartResponseDto> {
    const params = new HttpParams().set('quantity', quantity.toString());
    return this.http.post<CartResponseDto>(`${this.apiUrl}/add-product/${productId}`, null, { params });
  }

  removeProductFromCart(productId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/remove-product/${productId}`);
  }

  calculateCartTotal(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/total`);
  }

  clearCart(): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/clear`);
  }

  getCart(): Observable<CartResponseDto> {
    return this.http.get<CartResponseDto>(`${this.apiUrl}/get-cart`);
  }
}
