import {AfterViewInit, Component, OnInit} from '@angular/core';
import {CurrencyPipe, NgForOf} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {Product} from "../../../../core/models/product.model";
import {ProductService} from "../../../../core/services/product.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {ProductResponseDto} from "../../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-rated-products',
  standalone: true,
  imports: [
    NgForOf,
    CurrencyPipe,
    MatIcon,
    RatingModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './rated-products.component.html',
  styleUrl: './rated-products.component.css'
})
export class RatedProductsComponent implements OnInit{
  products: ProductResponseDto[] | undefined;

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit(): void {
    this.loadRatedProducts();
  }

  loadRatedProducts() {
    this.productService.getTopRatedProducts().subscribe(
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
