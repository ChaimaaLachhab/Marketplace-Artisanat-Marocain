import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {RatingModule} from "primeng/rating";
import {ProductService} from "../../../../../core/services/product.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {ReviewRequestDto} from "../../../../../core/dtos/request/review-request-dto";

@Component({
  selector: "app-review-form",
  templateUrl: "review-form.component.html",
  standalone: true,
  styleUrls: ["review-form.component.css"],
  imports: [FormsModule, RatingModule, ReactiveFormsModule, ToastModule],
  providers: [MessageService, ConfirmationService],
})
export class ReviewFormComponent implements OnInit {
  @Input() productId!: number;
  @Output() reviewAdded = new EventEmitter<void>();
  reviewForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.reviewForm = this.fb.group({
      feedback: ['', [Validators.required, Validators.minLength(10)]],
      rating: [0, Validators.required]
    });
  }

  submitReview(): void {
    if (this.reviewForm.valid) {
      const reviewDto: ReviewRequestDto = {
        comment: this.reviewForm.value.feedback,
        rating: this.reviewForm.value.rating
      };
      this.productService.addReviewToProduct(this.productId, reviewDto).subscribe(
        (product) => {
          console.log('Review added:', product);
          this.reviewForm.reset();
          this.reviewAdded.emit();
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });

        },
        (error) => {
          console.error('Error adding review:', error);
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content' });

        }
      );
    }
  }
}
