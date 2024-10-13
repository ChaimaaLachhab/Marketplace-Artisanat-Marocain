import { Component } from '@angular/core';
import {ListComponent} from "./list/list.component";
import {StatisticsComponent} from "./statistics/statistics.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order-overview',
  standalone: true,
    imports: [
        ListComponent,
        StatisticsComponent
    ],
  templateUrl: './order-overview.component.html',
  styleUrl: './order-overview.component.css'
})
export class OrderOverviewComponent {

  constructor(private router: Router) {}

  manageOrders() {
    this.router.navigate(['/dashboard/orders/manage']);
  }
}
