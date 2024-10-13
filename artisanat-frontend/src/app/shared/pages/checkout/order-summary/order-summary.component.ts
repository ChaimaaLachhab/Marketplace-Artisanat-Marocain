import { Component } from '@angular/core';
import {CurrencyPipe, NgForOf} from "@angular/common";
import {CartResponseDto} from "../../../../core/dtos/response/cart-response-dto";
import {CartService} from "../../../../core/services/cart.service";
import {JwtService} from "../../../../core/services/jwt.service";

@Component({
  selector: 'app-order-summary',
  standalone: true,
  imports: [
    NgForOf,
    CurrencyPipe
  ],
  templateUrl: './order-summary.component.html',
  styleUrl: './order-summary.component.css'
})
export class OrderSummaryComponent {
  cart: CartResponseDto | undefined;

  constructor(private cartService: CartService, private jwtService: JwtService) {}

  ngOnInit() {
    this.cartService.getCart().subscribe(
      (data) => {
        this.cart = data
      }
    )
  }
}

