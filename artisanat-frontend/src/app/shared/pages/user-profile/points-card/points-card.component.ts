import {Component, OnInit} from '@angular/core';
import {DividerModule} from "primeng/divider";
import {CustomerService} from "../../../../core/services/customer.service";

@Component({
  selector: 'app-points-card',
  standalone: true,
    imports: [
        DividerModule
    ],
  templateUrl: './points-card.component.html',
  styleUrl: './points-card.component.css'
})
export class PointsCardComponent implements OnInit{
  points!: number;

  constructor(private customerService: CustomerService) {}

  ngOnInit() {
    this.customerService.getLoyaltyPoints().subscribe(data => {
      this.points = data;
    })
  }
}
