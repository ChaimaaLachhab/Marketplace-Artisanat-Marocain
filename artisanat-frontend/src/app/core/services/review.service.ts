import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ReviewResponseDto} from "../dtos/response/review-response-dto";
import {ReviewRequestDto} from "../dtos/request/review-request-dto";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = `${environment.apiUrl}/reviews`;

  constructor(private http: HttpClient) { }

  getAllReviews(): Observable<ReviewResponseDto[]> {
    return this.http.get<ReviewResponseDto[]>(`${this.apiUrl}/all`);
  }

  getAllReviewsByProductId(productId: number): Observable<ReviewResponseDto[]> {
    return this.http.get<ReviewResponseDto[]>(`${this.apiUrl}/by-product/${productId}`);
  }

  getReviewById(id: number): Observable<ReviewResponseDto> {
    return this.http.get<ReviewResponseDto>(`${this.apiUrl}/${id}`);
  }

  addReview(productId: number, reviewDto: ReviewRequestDto): Observable<ReviewResponseDto> {
    return this.http.post<ReviewResponseDto>(`${this.apiUrl}/add-review/${productId}`, reviewDto);
  }

  updateReview(id: number, reviewDto: ReviewRequestDto): Observable<ReviewResponseDto> {
    return this.http.put<ReviewResponseDto>(`${this.apiUrl}/update/${id}`, reviewDto);
  }

  deleteReview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
