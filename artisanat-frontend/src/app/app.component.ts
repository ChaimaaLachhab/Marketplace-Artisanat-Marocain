import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {JewelryShopComponent} from "./components/jewelry-shop/JewelryShop.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, JewelryShopComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'artisanat-frontend';
}
