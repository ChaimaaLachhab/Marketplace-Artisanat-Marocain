import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-hero-product',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './hero-product.component.html',
  styleUrl: './hero-product.component.css'
})
export class HeroProductComponent {
  searchProduct(event: Event) {
    event.preventDefault();
  }
}
