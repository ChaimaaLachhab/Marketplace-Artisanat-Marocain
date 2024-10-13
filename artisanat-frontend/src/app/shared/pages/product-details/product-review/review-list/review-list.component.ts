import {Component, Input, OnInit} from '@angular/core';
import {DatePipe, NgForOf, NgIf} from "@angular/common";
import {DividerModule} from "primeng/divider";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {ProductService} from "../../../../../core/services/product.service";
import {Review} from "../../../../../core/models/review.model";
import {ReviewService} from "../../../../../core/services/review.service";
import {ReviewResponseDto} from "../../../../../core/dtos/response/review-response-dto";

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [
    NgForOf,
    DividerModule,
    RatingModule,
    FormsModule,
    DatePipe,
    NgIf
  ],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent{
  @Input() productId!: number;
  @Input() reviews!: ReviewResponseDto[] ;

}
