import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Order} from "../models/order.model";
import {LoyaltyResponseDto} from "../dtos/response/loyalty-response-dto";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoyaltyService {
  private apiUrl = `${environment.apiUrl}/loyalty`;

  constructor(private http: HttpClient) { }

  getLoyaltyById(id: number): Observable<LoyaltyResponseDto> {
    return this.http.get<LoyaltyResponseDto>(`${this.apiUrl}/${id}`);
  }

  addPointsToCustomerLoyalty(customerId: number, points: number): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/add-points?customerId=${customerId}&points=${points}`, null);
  }

  calculatePoints(order: Order): Observable<number> {
    return this.http.post<number>(`${this.apiUrl}/calculate-points`, order);
  }

  calculateDiscount(points: number, totalAmount: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/calculate-discount?points=${points}&totalAmount=${totalAmount}`);
  }

  updateLoyaltyPoints(customerId: number, usedPoints: number): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/${customerId}/update-points?usedPoints=${usedPoints}`, null);
  }
}
