import {Component, OnInit} from '@angular/core';
import {DividerModule} from "primeng/divider";
import {NgIf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {CustomerService} from "../../../../core/services/customer.service";
import {CustomerResponseDto} from "../../../../core/dtos/response/customer-response-dto";

@Component({
  selector: 'app-icon-profile',
  standalone: true,
  imports: [
    DividerModule,
    NgIf,
    MatIcon
  ],
  templateUrl: './icon-profile.component.html',
  styleUrl: './icon-profile.component.css'
})
export class IconProfileComponent implements OnInit{
  userDetails: CustomerResponseDto | undefined;
  isNightModeActive: boolean = false;

  constructor(private router: Router, private authService: AuthenticationService, private customerService: CustomerService) {}

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(customerInfos => {
      this.userDetails = customerInfos;
    });
  }

  isLogin(): boolean{
    return  this.authService.isLoggedIn()
  }

  display() {
    this.isNightModeActive = !this.isNightModeActive;
  }

  editProfile(): void {
    this.router.navigate(['/profile'])
  }

  login(): void {
    this.router.navigate(['/auth/login'])
  }

  register(): void {    this.router.navigate(['/auth/login'])
    this.router.navigate(['/auth/register'])
  }

  logout(): void {
    this.authService.logout()
  }
}
