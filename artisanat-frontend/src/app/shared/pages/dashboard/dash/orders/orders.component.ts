import { Component, Input } from '@angular/core';
import {NgClass, NgForOf} from "@angular/common";

interface Order {
  product: string;
  price: number;
  quantity: number;
  amount: number;
  status: string;
}

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  standalone: true,
  imports: [
    NgForOf,
    NgClass
  ],
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {
  @Input() orders: Order[] = [];
}
