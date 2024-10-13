import {Component, OnInit} from '@angular/core';
import {ProductReviewsComponent} from "./product-review/product-review.component";
import {ProductInfosComponent} from "./product-infos/product-infos.component";
import {OrdersComponent} from "../../overview/orders/orders.component";
import {StatisticsPieComponent} from "../../overview/statistics-pie/statistics-pie.component";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [
    ProductReviewsComponent,
    ProductInfosComponent,
    OrdersComponent,
    StatisticsPieComponent
  ],
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class ProductDetailComponent implements OnInit{
  productId!: number;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      this.productId = id ? Number(id) : 0;
    });
  }
}
