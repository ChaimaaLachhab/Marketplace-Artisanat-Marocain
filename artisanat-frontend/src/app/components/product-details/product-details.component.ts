import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {DetailComponent} from "../../features/product/detail/detail.component";
import {ListComponent} from "../../features/review/list/list.component";
import {FooterComponent} from "../../shared/components/footer/footer.component";

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    HeaderComponent,
    DetailComponent,
    ListComponent,
    FooterComponent
  ],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  quantity: number = 1;

  incrementQuantity(): void {
    this.quantity++;
  }

  decrementQuantity(): void {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }
}
