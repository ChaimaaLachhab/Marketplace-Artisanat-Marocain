import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-hero-collection',
  standalone: true,
    imports: [
        FormsModule
    ],
  templateUrl: './hero-collection.component.html',
  styleUrl: './hero-collection.component.css'
})
export class HeroCollectionComponent {

}
