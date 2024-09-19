import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {HeroAboutUsComponent} from "../../shared/components/hero-about-us/hero-about-us.component";
import {FooterComponent} from "../../shared/components/footer/footer.component";

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroAboutUsComponent,
    FooterComponent
  ],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.css'
})
export class AboutUsComponent {

}
