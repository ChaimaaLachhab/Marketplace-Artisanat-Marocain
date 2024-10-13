import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {OrderResponseDto} from "../dtos/response/order-response-dto";
import {Status} from "../enums/status.enum";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private orderResponse!: OrderResponseDto;
  private apiUrl = `${environment.apiUrl}/orders`;

  constructor(private http: HttpClient) { }


  setOrderResponse(orderResponse: OrderResponseDto): void {
    this.orderResponse = orderResponse;
  }

  getOrderResponse(): OrderResponseDto {
    return this.orderResponse;
  }

  clearOrderResponse(): void {
    this.orderResponse = {} as OrderResponseDto;
  }

  getAllOrders(): Observable<OrderResponseDto[]> {
    return this.http.get<OrderResponseDto[]>(this.apiUrl);
  }

  getOrderById(id: number): Observable<OrderResponseDto> {
    return this.http.get<OrderResponseDto>(`${this.apiUrl}/${id}`);
  }

  getOrdersByCustomer(): Observable<OrderResponseDto[]> {
    return this.http.get<OrderResponseDto[]>(`${this.apiUrl}/customer`);
  }

  getOrdersByStatus(status: Status): Observable<OrderResponseDto[]> {
    return this.http.get<OrderResponseDto[]>(`${this.apiUrl}/status/${status}`);
  }

  createOrder(location: string, point?: number): Observable<OrderResponseDto> {
    const params = new HttpParams()
      .set('point', point ? point.toString() : '')
      .set('location', location);

    return this.http.post<OrderResponseDto>(`${this.apiUrl}/create`, null, { params });
  }


  updateOrderStatus(orderId: number, status: Status): Observable<OrderResponseDto> {
    const params = new HttpParams()
      .set('orderId', orderId.toString())
      .set('status', status);

    return this.http.post<OrderResponseDto>(`${this.apiUrl}/update`, null, { params });
  }
}
