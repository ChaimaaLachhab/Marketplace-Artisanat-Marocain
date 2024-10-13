import {Component, OnInit} from '@angular/core';
import {MatIcon} from "@angular/material/icon";
import {CurrencyPipe, NgForOf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {Router, RouterLink} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {Product} from "../../../../core/models/product.model";
import {ProductService} from "../../../../core/services/product.service";
import {ProductResponseDto} from "../../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-recently-products',
  standalone: true,
  imports: [
    NgForOf,
    CurrencyPipe,
    MatIcon,
    RatingModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './recently-products.component.html',
  styleUrl: './recently-products.component.css'
})
export class RecentlyProductsComponent implements OnInit{
  products: ProductResponseDto[] | undefined;

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit(): void {
    this.loadRatedProducts();
  }

  loadRatedProducts() {
    this.productService.getRecentlyAddedProducts().subscribe(
      (data) => {
        this.products = data
      },
      (error) => {
        console.error(error)
      }
    )
  }

  navigateToProductDetails(productId: number) {
    this.router.navigate(['/product-infos', productId]);
  }
}
