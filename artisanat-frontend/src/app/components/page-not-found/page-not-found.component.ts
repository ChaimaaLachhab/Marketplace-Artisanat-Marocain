import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-page-not-found',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './page-not-found.component.html',
  styleUrl: './page-not-found.component.css'
})
export class PageNotFoundComponent {
  searchProduct(event: Event): void {
    event.preventDefault();
  }
}
