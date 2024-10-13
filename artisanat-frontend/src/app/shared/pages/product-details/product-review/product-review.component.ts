import {Component, Input, OnInit} from '@angular/core';
import {ReviewFormComponent} from "./review-form/review-form.component";
import {ReviewListComponent} from "./review-list/review-list.component";
import {ReviewResponseDto} from "../../../../core/dtos/response/review-response-dto";
import {ReviewService} from "../../../../core/services/review.service";

@Component({
  selector: 'app-product-review',
  standalone: true,
  imports: [
    ReviewFormComponent,
    ReviewListComponent
  ],
  templateUrl: './product-review.component.html',
  styleUrl: './product-review.component.css'
})
export class ProductReviewComponent implements OnInit {
  @Input() productId!: number;

  reviews: ReviewResponseDto[] = [];

  constructor(private reviewService: ReviewService) {
  }

  ngOnInit() {
    this.loadReviews()
  }

  loadReviews(){
    this.reviewService.getAllReviewsByProductId(this.productId).subscribe(
      (reviews) => {
        this.reviews = reviews;
        console.log(reviews)
      }
    )
  }

  reloadReviews() {
    this.loadReviews();
  }
}
