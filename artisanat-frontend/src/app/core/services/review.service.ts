import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";  // Modifiez selon votre structure de projet

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private apiUrl = `${environment.apiUrl}/reviews`;

  constructor(private http: HttpClient) { }

  getAllReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.apiUrl}/all`);
  }

  getReviewById(id: number): Observable<Review> {
    return this.http.get<Review>(`${this.apiUrl}/${id}`);
  }

  addReview(review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.apiUrl}/add`, review);
  }

  updateReview(id: number, updatedReview: Review): Observable<Review> {
    return this.http.put<Review>(`${this.apiUrl}/update/${id}`, updatedReview);
  }

  deleteReview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
