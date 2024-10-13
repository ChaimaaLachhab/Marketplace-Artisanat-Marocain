import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {CustomerResponseDto} from "../dtos/response/customer-response-dto";
import {CustomerRequestDto} from "../dtos/request/customer-request-dto";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = `${environment.apiUrl}/customers`;

  constructor(private http: HttpClient) { }

  getAllCustomers(): Observable<CustomerResponseDto[]> {
    return this.http.get<CustomerResponseDto[]>(`${this.apiUrl}/all`);
  }

  getCustomerById(): Observable<CustomerResponseDto> {
    return this.http.get<CustomerResponseDto>(`${this.apiUrl}/details`);
  }

  updateCustomer(customerDTO: CustomerRequestDto, userPhoto?: File): Observable<CustomerResponseDto> {
    const formData = new FormData();
    formData.append('customer', new Blob([JSON.stringify(customerDTO)], { type: 'application/json' }));
    if (userPhoto) {
      formData.append('userPhoto', userPhoto);
    }

    return this.http.put<CustomerResponseDto>(`${this.apiUrl}/update`, formData);
  }

  getLoyaltyPointsById(customerId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${customerId}/loyalty-points`);
  }

  getLoyaltyPoints(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/loyalty-points`);
  }
}
