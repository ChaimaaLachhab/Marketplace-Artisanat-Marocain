import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {AdminResponseDto} from "../dtos/response/admin-response-dto";
import {AdminRequestDto} from "../dtos/request/admin-request-dto";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = `${environment.apiUrl}/admins`;

  constructor(private http: HttpClient) { }

  getAllAdmins(): Observable<AdminResponseDto[]> {
    return this.http.get<AdminResponseDto[]>(`${this.apiUrl}/all`);
  }

  getAdminById(): Observable<AdminResponseDto> {
    return this.http.get<AdminResponseDto>(`${this.apiUrl}/details`);
  }

  updateAdmin(adminDTO: AdminRequestDto, userPhoto?: File): Observable<AdminResponseDto> {
    const formData = new FormData();
    formData.append('admin', new Blob([JSON.stringify(adminDTO)], { type: 'application/json' }));
    if (userPhoto) {
      formData.append('userPhoto', userPhoto);
    }

    return this.http.put<AdminResponseDto>(`${this.apiUrl}/update`, formData);
  }

  deleteAdmin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
