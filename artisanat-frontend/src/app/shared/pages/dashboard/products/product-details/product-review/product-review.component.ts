import {Component, Input} from '@angular/core';
import {ReviewSummaryComponent} from "./review-summary/review-summary.component";
import {ReviewListComponent} from "./review-list/review-list.component";

@Component({
  selector: 'app-product-reviews',
  standalone: true,
  imports: [
    ReviewSummaryComponent,
    ReviewListComponent
  ],
  templateUrl: './product-review.component.html',
  styleUrl: './product-review.component.css'
})
export class ProductReviewsComponent {
  @Input() productId!: number;
}
