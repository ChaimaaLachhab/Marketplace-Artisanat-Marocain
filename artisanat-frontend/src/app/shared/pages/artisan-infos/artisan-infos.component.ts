import { Component } from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HeaderComponent} from "../../components/header/header.component";
import {HeroArtisanComponent} from "./hero-artisan/hero-artisan.component";
import {FooterComponent} from "../../components/footer/footer.component";

interface Products {
  name: string;
  price: number;
  image: string;
  isNew?: boolean;
}

@Component({
  selector: 'app-artisan-infos',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroArtisanComponent,
    NgIf,
    NgForOf,
    FooterComponent
  ],
  templateUrl: './artisan-infos.component.html',
  styleUrl: './artisan-infos.component.css'
})
export class ArtisanInfosComponent {
  products: Products[] = [
    { name: "Luxury Gems Necklace", price: 168.76, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/b403259a610680d09dc9d122d24dce3a7972f8b7fc3c4e0acc0b2c07415734cb?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4"},
    { name: "Exquisite Earrings", price: 125.28, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/8ab7e529bd8bb473d845453856b2d9a98943c05d5bb8f24a3e65c1afa5d34f31?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4"},
    { name: "Reflections Necklace", price: 620.73, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/c068a92b45903cba39ed3a57086df4ae9e71a782a8f7aba888fb5e18bf41f6c4?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" },
    { name: "Dreamy Infinity Ring", price: 327.71, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/1d7ddde4b054f17914246b18e6870df1f84aa0a2b11d365dc44b0c9d6e3ae3fb?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" },
    { name: "Glamour Necklace", price: 620.73, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/2a174d119af37a26ab962401ebfc86c64ab2e82889711afdf19cd23d205888c8?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" },
    { name: "Serene Solitaire Earrings", price: 125.28, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/9572c5484043bd8365d39ff08d625c7e4aeedce6fb338fd80d87087fe76da7da?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" },
    { name: "Timeless Halo Earrings", price: 620.73, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/9247f41199f6c397376cab4f225b478e78d73caf78be8b948b2c7be2390da064?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" },
    { name: "Exquisite Earrings", price: 327.71, image: "https://cdn.builder.io/api/v1/image/assets/TEMP/fa6d22c0b139cf2c9401e2f04e0c6ece1e14e217792cf11cb77dd20a1e93625f?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4" }
  ];
}
