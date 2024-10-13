import {Component, Input} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {Product} from "../../../../core/models/product.model";
import {Router} from "@angular/router";
import {ProductService} from "../../../../core/services/product.service";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {ProductResponseDto} from "../../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-related-products',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    RatingModule,
    FormsModule,
    MatIcon
  ],
  templateUrl: './related-products.component.html',
  styleUrl: './related-products.component.css'
})
export class RelatedProductsComponent {
  @Input() productId!: number;

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
