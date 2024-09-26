import { Component } from '@angular/core';

@Component({
  selector: 'app-hero-artisan',
  standalone: true,
  imports: [],
  templateUrl: './hero-artisan.component.html',
  styleUrl: './hero-artisan.component.css'
})
export class HeroArtisanComponent {
  artisan = {
    name: 'Ahmed El Moukhtar',
    specialty: 'Handcrafted Silver Jewelry',
    location: 'Marrakech, Morocco',
    experience: '25 years in silver craftsmanship',
    description: 'Intricately designed rings, bracelets, and necklaces'
  };
}
