import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {HeroCollectionComponent} from "../../shared/components/hero-collection/hero-collection.component";

@Component({
  selector: 'app-collection-listing',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroCollectionComponent
  ],
  templateUrl: './collection-listing.component.html',
  styleUrl: './collection-listing.component.css'
})
export class CollectionListingComponent {

}
