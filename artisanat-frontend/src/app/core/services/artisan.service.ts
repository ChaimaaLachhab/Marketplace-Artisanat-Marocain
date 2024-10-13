import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ArtisanResponseDto} from "../dtos/response/artisan-response-dto";
import {ArtisanRequestDto} from "../dtos/request/artisan-request-dto";
import {VerificationStatus} from "../enums/verification-status.enum";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ArtisanService {
  private apiUrl = `${environment.apiUrl}/artisans`;

  constructor(private http: HttpClient) { }

  getAllArtisans(): Observable<ArtisanResponseDto[]> {
    return this.http.get<ArtisanResponseDto[]>(`${this.apiUrl}/all`);
  }

  getArtisanById(): Observable<ArtisanResponseDto> {
    return this.http.get<ArtisanResponseDto>(`${this.apiUrl}/details`);
  }

  updateArtisan(artisanDTO: ArtisanRequestDto, userPhoto?: File): Observable<ArtisanResponseDto> {
    const formData = new FormData();
    formData.append('artisan', new Blob([JSON.stringify(artisanDTO)], { type: 'application/json' }));
    if (userPhoto) {
      formData.append('userPhoto', userPhoto);
    }

    return this.http.put<ArtisanResponseDto>(`${this.apiUrl}/update`, formData);
  }

  getPendingArtisans(): Observable<ArtisanResponseDto[]> {
    return this.http.get<ArtisanResponseDto[]>(`${this.apiUrl}/pending`);
  }

  verifyArtisan(id: number, status: VerificationStatus): Observable<ArtisanResponseDto> {
    const params = { status: status };
    return this.http.post<ArtisanResponseDto>(`${this.apiUrl}/verify/${id}`, null, { params });
  }
}
