import { Component } from '@angular/core';
import {NgIf} from "@angular/common";
import {CustomerComponent} from "./customer/customer.component";
import {ArtisanComponent} from "./artisan/artisan.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgIf,
    CustomerComponent,
    ArtisanComponent,
    RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  showCustomerForm = true;

  toggleForm(showCustomer: boolean): void {
    this.showCustomerForm = showCustomer;
  }
}
