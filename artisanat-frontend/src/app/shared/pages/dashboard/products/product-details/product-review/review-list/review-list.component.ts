import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [
    NgForOf,
    RatingModule,
    FormsModule
  ],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent {
  @Input() productId!: number;
  reviews = [
    {
      name: 'Jennifer King',
      date: '3d ago',
      rating: 5,
      comment: 'Nulla laboris fugiat fugiat minim minim excepteur eiusmod quis. Laborum est minim id cillum nostrud cillum consectetur 😍😍😍'
    },
    {
      name: 'Jennifer King',
      date: '3d ago',
      rating: 4,
      comment: 'Nulla laboris fugiat fugiat minim minim excepteur eiusmod quis. Laborum est minim id cillum nostrud cillum consectetur 😍😍😍'
    }
  ];
}

