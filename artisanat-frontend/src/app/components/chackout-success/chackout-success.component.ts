import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-chackout-success',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './chackout-success.component.html',
  styleUrl: './chackout-success.component.css'
})
export class ChackoutSuccessComponent {
  orderDate: string = '27/04/2022';
  customerName: string = 'John Miller';
  customerAddress: string = '57 West 45th Street, New York, USA, 10036';
  orderNumber: string = '586789963';
  totalAmount: string = '$1058.72';
  orderItems: Array<{name: string, code: string, quantity: number, price: string}> = [
    { name: 'Luxury Charms Ring', code: '78205', quantity: 1, price: '$620.73' },
    { name: 'Exquisite Earrings', code: '92701', quantity: 1, price: '$125.28' }
  ];
}
