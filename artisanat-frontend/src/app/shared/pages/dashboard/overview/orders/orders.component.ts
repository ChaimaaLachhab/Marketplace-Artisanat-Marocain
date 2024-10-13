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
  orders = [
    { product: 'Product name', price: 300, quantity: 2, amount: 600, status: 'New' },
    { product: 'Product name', price: 300, quantity: 3, amount: 900, status: 'Delivered' },
    { product: 'Product name', price: 300, quantity: 3, amount: 900, status: 'Delivered' }
  ];
}
