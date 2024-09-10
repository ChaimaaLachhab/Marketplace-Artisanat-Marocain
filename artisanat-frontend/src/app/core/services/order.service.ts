import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../models/order.model';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = `${environment.apiUrl}/orders`;

  constructor(private http: HttpClient) { }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl).pipe(
      catchError(this.handleError)
    );
  }

  getOrderById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  getOrdersByCustomer(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/customer`).pipe(
      catchError(this.handleError)
    );
  }

  getOrdersByStatus(status: string): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/status/${status}`).pipe(
      catchError(this.handleError)
    );
  }

  createOrderForSingleProduct(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiUrl}/single`, order).pipe(
      catchError(this.handleError)
    );
  }

  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.apiUrl}/bulk`, order).pipe(
      catchError(this.handleError)
    );
  }

  applyDiscountAndFinalizeOrder(orderId: number, loyaltyPoints: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/apply-discount/${orderId}`, {}, {
      params: { loyaltyPoints: loyaltyPoints.toString() }
    }).pipe(
      catchError(this.handleError)
    );
  }

  deleteOrder(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'Une erreur inconnue est survenue!';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur côté client : ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Erreur côté serveur : ${error.status} - ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
