import {Component, OnInit} from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {HeaderComponent} from "../../components/header/header.component";
import {HeroArtisanComponent} from "./hero-artisan/hero-artisan.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../../../core/services/product.service";
import {MatIcon} from "@angular/material/icon";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {ProductResponseDto} from "../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-artisan-infos',
  standalone: true,
  imports: [
    HeaderComponent,
    HeroArtisanComponent,
    NgIf,
    NgForOf,
    FooterComponent,
    MatIcon,
    RatingModule,
    FormsModule
  ],
  templateUrl: './artisan-infos.component.html',
  styleUrl: './artisan-infos.component.css'
})
export class ArtisanInfosComponent implements OnInit{
  artisanId!: number;
  products: ProductResponseDto[] | undefined;

  constructor(private router: Router, private route: ActivatedRoute, private productService: ProductService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.artisanId = id ? Number(id) : 0;
    });

    this.productService.getProductsByArtisan(this.artisanId).subscribe(
      (products) => {
        this.products = products
      }
    )
  }


  navigateToProductDetails(productId: number) {
    this.router.navigate(['/product-infos', productId]);
  }
}
