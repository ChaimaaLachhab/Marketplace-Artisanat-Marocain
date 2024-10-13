import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from "../../../environments/environment";
import { JwtService } from "./jwt.service";
import { Router } from "@angular/router";
import { Role } from "../enums/role.enum";
import {LoginUserDto} from "../dtos/login-user.dto";
import {LoginResponse} from "../dtos/login-response.dto";
import {CustomerRequestDto} from "../dtos/request/customer-request-dto";
import {CustomerResponseDto} from "../dtos/response/customer-response-dto";
import {ArtisanResponseDto} from "../dtos/response/artisan-response-dto";
import {ArtisanRequestDto} from "../dtos/request/artisan-request-dto";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiUrl = `${environment.apiUrl}/auth`;
  private currentUserSubject = new BehaviorSubject<any>(this.decodeToken(this.jwtService.getToken()));

  currentUser$ = this.currentUserSubject.asObservable();

  constructor(
    private http: HttpClient,
    private jwtService: JwtService,
    private router: Router
  ) {}

  register(customerDTO: CustomerRequestDto): Observable<CustomerResponseDto> {
    return this.http.post<CustomerResponseDto>(`${this.apiUrl}/signup`, customerDTO);
  }

  addArtisan(artisanDTO: ArtisanRequestDto): Observable<ArtisanResponseDto> {
    return this.http.post<ArtisanResponseDto>(`${this.apiUrl}/add-artisan`, artisanDTO);
  }

  authenticate(loginUserDto: LoginUserDto): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginUserDto).pipe(
      tap((loginResponse: LoginResponse) => {
        if (loginResponse) {
          this.jwtService.saveToken(loginResponse.token);
          this.currentUserSubject.next(this.decodeToken(loginResponse.token));
        } else {
          console.error('No login response received');
        }
      })
    );
  }

  logout(): void {
    this.jwtService.removeToken();
    this.currentUserSubject.next(null);
    this.router.navigate(['/auth/login']);
  }

  isLoggedIn(): boolean {
    return !!this.currentUserSubject.value;
  }

  hasRole(role: Role): boolean {
    const currentUserRole = this.getCurrentUserRole();
    return currentUserRole === role;
  }

  getCurrentUserRole(): Role | null {
    const token = this.jwtService.getToken();
    if (token) {
      return this.jwtService.getUserRole(token) as Role || null;
    }
    return null;
  }

  private decodeToken(token: string | null): any {
    if (token) {
      return this.jwtService.decodeToken(token);
    }
    return null;
  }
}
