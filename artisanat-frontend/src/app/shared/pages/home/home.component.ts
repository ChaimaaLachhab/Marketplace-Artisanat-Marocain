import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {Router} from "@angular/router";
import {HeaderComponent} from "../../components/header/header.component";
import {HeroHomeComponent} from "./hero-home/hero-home.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {FeaturesComponent} from "../product-details/features/features.component";
import {RatedProductsComponent} from "./rated-products/rated-products.component";
import {RecentlyProductsComponent} from "./recently-products/recently-products.component";
import {CollectionGridComponent} from "./collection-grid/collection-grid.component";
import {TestimonialComponent} from "./testimonial/testimonial.component";
import {OurStoryComponent} from "./our-story/our-story.component";

@Component({
  selector: 'app-home',
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  imports: [
    Button,
    HeaderComponent,
    HeroHomeComponent,
    FooterComponent,
    FeaturesComponent,
    RatedProductsComponent,
    RecentlyProductsComponent,
    CollectionGridComponent,
    TestimonialComponent,
    OurStoryComponent
  ],
  standalone: true
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate(['/auth/login']);
  }
}
