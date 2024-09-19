import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {HeaderComponent} from "./shared/components/header/header.component";
import {FooterComponent} from "./shared/components/footer/footer.component";
import {HeroHomeComponent} from "./shared/components/hero-home/hero-home.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {AboutUsComponent} from "./components/about-us/about-us.component";
import {ContactUsComponent} from "./components/contact-us/contact-us.component";
import {ArtisanInfosComponent} from "./components/artisan-infos/artisan-infos.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, HeroHomeComponent, PageNotFoundComponent, AboutUsComponent, ContactUsComponent, ArtisanInfosComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'artisanat-frontend';
}
