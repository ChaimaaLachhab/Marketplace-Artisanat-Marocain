import {Component, OnInit} from '@angular/core';
import {DatePipe, NgForOf} from "@angular/common";
import {Router} from "@angular/router";
import {OrderResponseDto} from "../../../core/dtos/response/order-response-dto";
import {HeaderComponent} from "../../components/header/header.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {OrderService} from "../../../core/services/order.service";

@Component({
  selector: 'app-chackout-success',
  standalone: true,
  imports: [
    NgForOf,
    HeaderComponent,
    FooterComponent,
    DatePipe,
  ],
  templateUrl: './checkout-success.component.html',
  styleUrl: './checkout-success.component.css'
})
export class CheckoutSuccessComponent implements OnInit{
  orderResponse!: OrderResponseDto;

  constructor(
    private router: Router,
    private orderStateService: OrderService
  ) {}

  ngOnInit(): void {
    this.orderResponse = this.orderStateService.getOrderResponse();
    if (this.orderResponse) {
      console.log('Received order response:', this.orderResponse);
    } else {
      console.warn('Order response not found, redirecting to error page.');
      this.router.navigate(['/error-page']);
    }
  }
}
