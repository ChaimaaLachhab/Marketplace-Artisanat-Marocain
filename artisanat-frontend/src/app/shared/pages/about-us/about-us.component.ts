import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {HeroAboutUsComponent} from "./hero-about-us/hero-about-us.component";
import {FooterComponent} from "../../components/footer/footer.component";

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
