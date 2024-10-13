import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {HeroProductComponent} from "./hero-product/hero-product.component";
import {FilterSidebarComponent} from "./filter-sidebar/filter-sidebar.component";
import {ProductGridComponent} from "./product-grid/product-grid.component";

@Component({
  selector: 'app-product-listing',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroProductComponent,
    FilterSidebarComponent,
    ProductGridComponent
  ],
  templateUrl: './product-listing.component.html',
  styleUrl: './product-listing.component.css'
})
export class ProductListingComponent {
  currentFilters: any = {
    collections: {},
    categories: {},
    minPrice: null,
    maxPrice: null,
    rating: null
  };

  onFiltersChanged(filters: any) {
    this.currentFilters = { ...filters };
  }
}
