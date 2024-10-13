import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {ProductGridComponent} from "../product-listing/product-grid/product-grid.component";
import {HeroCollectionDetailsComponent} from "./hero-collection-details/hero-collection-details.component";
import {FooterComponent} from "../../components/footer/footer.component";

@Component({
  selector: 'app-collection-details',
  standalone: true,
  imports: [
    HeaderComponent,
    ProductGridComponent,
    HeroCollectionDetailsComponent,
    FooterComponent
  ],
  templateUrl: './collection-details.component.html',
  styleUrl: './collection-details.component.css'
})
export class CollectionDetailsComponent {

}
