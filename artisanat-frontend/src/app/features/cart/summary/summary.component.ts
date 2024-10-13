import {Component, OnInit} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {SidebarModule} from "primeng/sidebar";
import {Cart} from "../../../core/models/cart.model";
import {CartService} from "../../../core/services/cart.service";
import {JwtService} from "../../../core/services/jwt.service";
import {CurrencyPipe, NgForOf} from "@angular/common";
import {CartResponseDto} from "../../../core/dtos/response/cart-response-dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-summary-cart',
  standalone: true,
  imports: [
    MatIcon,
    SidebarModule,
    NgForOf,
    CurrencyPipe
  ],
  templateUrl: './summary.component.html',
  styleUrl: './summary.component.css'
})
export class SummaryComponent implements OnInit{
  sidebarVisible: boolean = false;
  cart: CartResponseDto | undefined;
  totalCart!: number;

  constructor(private cartService: CartService, private jwtService: JwtService, private router: Router) {}

  ngOnInit() {
    this.cartService.getCart().subscribe(
      (data) => {
        this.cart = data
      }
    )
    this.cartService.calculateCartTotal().subscribe((data) => {
      this.totalCart = data;
    })
  }

  checkout(){
    this.router.navigate(['/checkout'])
  }
}
