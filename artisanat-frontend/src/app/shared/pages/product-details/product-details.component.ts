import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {DetailComponent} from "../../../features/product/detail/detail.component";
import {ListComponent} from "../../../features/review/list/list.component";

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    HeaderComponent,
    DetailComponent,
    ListComponent,
    FooterComponent,
    DetailComponent,
    ListComponent
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
