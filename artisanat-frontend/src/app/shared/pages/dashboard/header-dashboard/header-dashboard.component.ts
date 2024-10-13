import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {JwtService} from "../../../../core/services/jwt.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-header-dashboard',
  standalone: true,
  imports: [
    MatIcon,
    NgIf
  ],
  templateUrl: './header-dashboard.component.html',
  styleUrl: './header-dashboard.component.css'
})
export class HeaderDashboardComponent {
  @Input() pageTitle: string = 'Dashboard';

  constructor(private router: Router, private authService: AuthenticationService, private  jwtService: JwtService) {}

  onCreateNewProduct() {
    this.router.navigate(['/dashboard/products/form'])
  }

  onLogout() {
    this.authService.logout()
  }

  isArtisan(): boolean {
    return this.jwtService.isArtisan()
  }

  navigateTo(path: string): void {
    this.router.navigate([`/dashboard/users/${path}`]);
  }
}
