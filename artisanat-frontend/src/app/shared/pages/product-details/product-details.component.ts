import {Component, OnInit} from '@angular/core';
import {ProductReviewComponent} from "./product-review/product-review.component";
import {ProductInfoComponent} from "./product-info/product-info.component";
import {RelatedProductsComponent} from "./related-products/related-products.component";
import {FeaturesComponent} from "./features/features.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {HeaderComponent} from "../../components/header/header.component";
import {RatedProductsComponent} from "../home/rated-products/rated-products.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [
    ProductReviewComponent,
    ProductInfoComponent,
    RelatedProductsComponent,
    FeaturesComponent,
    FooterComponent,
    HeaderComponent,
    RatedProductsComponent
  ],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  productId!: number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.productId = id ? Number(id) : 0;
    });
  }
}
