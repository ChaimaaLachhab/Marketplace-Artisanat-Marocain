import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {HeroProductComponent} from "./hero-product/hero-product.component";

@Component({
  selector: 'app-product-listing',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroProductComponent
  ],
  templateUrl: './product-listing.component.html',
  styleUrl: './product-listing.component.css'
})
export class ProductListingComponent {

}
