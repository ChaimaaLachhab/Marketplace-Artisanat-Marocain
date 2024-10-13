import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-hero-home',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './hero-home.component.html',
  styleUrl: './hero-home.component.css'
})
export class HeroHomeComponent {
}
