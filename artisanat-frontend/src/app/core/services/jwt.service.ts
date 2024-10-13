import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  decodeToken(token: string): any {
    return jwtDecode(token);
  }

  getUserRole(token: string): string | null {
    const decodedToken = this.decodeToken(token);
    return decodedToken?.role || null;
  }

  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  setUserRole(role: string) {
    localStorage.setItem('userRole', role);
  }

  getUserRoleFromStorage(): string | null {
    return localStorage.getItem('userRole');
  }

  getUsernameFromToken(token: string): string | null {
    const decodedToken = this.decodeToken(token);
    return decodedToken?.sub || null;
  }

  removeToken(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('userRole');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAdmin(): boolean {
    const role = this.getUserRoleFromStorage();
    return role === 'ADMIN';
  }

  isCustomer(): boolean {
    const role = this.getUserRoleFromStorage();
    return role === 'CUSTOMER';
  }

  isArtisan(): boolean {
    const role = this.getUserRoleFromStorage();
    return role === 'ARTISAN';
  }
}
