import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-review-summary',
  standalone: true,
  imports: [],
  templateUrl: './review-summary.component.html',
  styleUrl: './review-summary.component.css'
})
export class ReviewSummaryComponent {
  @Input() productId!: number;

}
