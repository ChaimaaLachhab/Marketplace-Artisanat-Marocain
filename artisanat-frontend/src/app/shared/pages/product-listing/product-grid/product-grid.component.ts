import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {NgForOf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {Router} from "@angular/router";
import {ProductService} from "../../../../core/services/product.service";
import {ProductResponseDto} from "../../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-product-grid',
  standalone: true,
    imports: [
        NgForOf,
        RatingModule,
        FormsModule,
        MatIcon
    ],
  templateUrl: './product-grid.component.html',
  styleUrl: './product-grid.component.css'
})
export class ProductGridComponent implements OnInit{
  @Input() filters: any;

  products: ProductResponseDto[] = [];

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit() {
    this.loadProducts();
  }

  onSortChange(event: Event) {
    const sortValue = (event.target as HTMLSelectElement).value;
    if (sortValue === 'low-to-high') {
      this.products.sort((a, b) => a.price - b.price);
    } else if (sortValue === 'high-to-low') {
      this.products.sort((a, b) => b.price - a.price);
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['filters']) {
      this.loadProducts();
    }
  }

  loadProducts() {
    const { collections, categories, minPrice, maxPrice, rating } = this.filters;
    const selectedCollection = Object.keys(collections).find(key => collections[key]);
    const selectedCategory = Object.keys(categories).find(key => categories[key]);

    this.productService.getFilteredProducts(
      undefined,
      selectedCategory,
      selectedCollection,
      minPrice,
      maxPrice,
      rating
    ).subscribe((data) => {
      this.products = data;
    });
  }

  navigateToProductDetails(productId: number) {
    this.router.navigate(['/product-infos', productId]);
  }
}
