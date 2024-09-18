import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {environment} from "../../../environments/environment";
import {Cart} from "../models/cart.model";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiUrl = `${environment.apiUrl}/carts`;

  constructor(private http: HttpClient) { }

  addProductToCart(cartId: number, productId: number): Observable<string> {
    const url = `${this.apiUrl}/${cartId}/add/${productId}`;
    return this.http.post<string>(url, {}).pipe(
      catchError(this.handleError)
    );
  }

  removeProductFromCart(cartId: number, productId: number): Observable<string> {
    const url = `${this.apiUrl}/${cartId}/remove/${productId}`;
    return this.http.delete<string>(url).pipe(
      catchError(this.handleError)
    );
  }

  calculateCartTotal(cartId: number): Observable<number> {
    const url = `${this.apiUrl}/${cartId}/total`;
    return this.http.get<number>(url).pipe(
      catchError(this.handleError)
    );
  }

  clearCart(cartId: number): Observable<string> {
    const url = `${this.apiUrl}/${cartId}/clear`;
    return this.http.delete<string>(url).pipe(
      catchError(this.handleError)
    );
  }

  getCart(cartId: number): Observable<Cart> {
    const url = `${this.apiUrl}/${cartId}`;
    return this.http.get<Cart>(url).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      errorMessage = `Server-side error: ${error.status} - ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
