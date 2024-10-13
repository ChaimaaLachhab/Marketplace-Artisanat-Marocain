import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment";
import {User} from "../models/user.model";
import {UserDTO} from "../dtos/user.dto";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/admins`;

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/all`);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/details/${id}`);
  }

  updateUser(id: number, user: User): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }
}
