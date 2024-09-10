import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Loyalty } from '../models/loyalty.model';
import { Order } from '../models/order.model';
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoyaltyService {

  private apiUrl = `${environment.apiUrl}/loyalty`;

  constructor(private http: HttpClient) { }

  // Obtenir les informations de fidélité par ID
  getLoyaltyById(id: number): Observable<Loyalty> {
    return this.http.get<Loyalty>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Ajouter de nouveaux points de fidélité pour un client
  addPointsToCustomerLoyalty(customerId: number, points: number): Observable<string> {
    const url = `${this.apiUrl}/add-points?customerId=${customerId}&points=${points}`;
    return this.http.post<string>(url, {}).pipe(
      catchError(this.handleError)
    );
  }

  // Calculer les points basés sur une commande
  calculatePoints(order: Order): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/calculate-points`, order).pipe(
      catchError(this.handleError)
    );
  }

  // Calculer la réduction basée sur les points
  calculateDiscount(points: number, totalAmount: number): Observable<number> {
    const url = `${this.apiUrl}/calculate-discount?points=${points}&totalAmount=${totalAmount}`;
    return this.http.get<number>(url).pipe(
      catchError(this.handleError)
    );
  }

  // Mettre à jour les points de fidélité d'un client
  updateLoyaltyPoints(customerId: number, usedPoints: number): Observable<string> {
    const url = `${this.apiUrl}/${customerId}/update-points?usedPoints=${usedPoints}`;
    return this.http.put<string>(url, {}).pipe(
      catchError(this.handleError)
    );
  }

  // Gestion des erreurs
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Server-side error: ${error.status} - ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
