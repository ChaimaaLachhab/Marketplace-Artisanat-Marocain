import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { CartService } from "../../../../core/services/cart.service";
import { OrderService } from "../../../../core/services/order.service";
import { DividerModule } from "primeng/divider";
import {CurrencyPipe, DecimalPipe} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-billing-details',
  standalone: true,
  imports: [
    DividerModule,
    CurrencyPipe,
    DecimalPipe,
    FormsModule
  ],
  templateUrl: './billing-details.component.html',
  styleUrls: ['./billing-details.component.css']
})
export class BillingDetailsComponent implements OnInit {
  total!: number;
  location: string = 'RABAT';
  point: number = 0;
  discount!: number;
  finalAmount!: number;

  constructor(
    private router: Router,
    private cartService: CartService,
    private orderService: OrderService
  ) {}

  ngOnInit(): void {
    this.cartService.calculateCartTotal().subscribe((data) => {
      this.total = data;
      this.calculateDiscount(); // Calculer le rabais lors de l'initialisation
    });
  }

  calculateDiscount(): void {
    if (this.point) {
      this.discount = this.point * 0.1;
    } else {
      this.discount = 0;
    }
    this.finalAmount = this.total - this.discount;
  }

  placeOrder(): void {
    this.orderService.createOrder(this.location, this.point).subscribe(
      (response) => {
        console.log('Order created successfully:', response);
        this.orderService.setOrderResponse(response);
        this.router.navigate(['/checkout-success']);
      },
      (error) => {
        console.error('Error creating order:', error);
      }
    );
  }
}
