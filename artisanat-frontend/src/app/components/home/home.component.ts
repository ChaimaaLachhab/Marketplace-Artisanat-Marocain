import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {Router} from "@angular/router";
import {HeaderComponent} from "../../shared/components/header/header.component";
import {HeroHomeComponent} from "../../shared/components/hero-home/hero-home.component";
import {FooterComponent} from "../../shared/components/footer/footer.component";

@Component({
  selector: 'app-home',
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  imports: [
    Button,
    HeaderComponent,
    HeroHomeComponent,
    FooterComponent
  ],
  standalone: true
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate(['/auth/login']);
  }
}
