import { Component } from '@angular/core';
import {NgClass, NgForOf} from "@angular/common";

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    NgClass,
    NgForOf
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ListComponent {
  orders: Order[] = [
    {
      id: '45678',
      price: 70000,
      customerName: 'Jennifer Wilson',
      customerEmail: 'emilybrown@yahoo.com',
      date: '12/04/2022',
      status: 'CANCELLED',
      productImage: 'https://cdn.builder.io/api/v1/image/assets/TEMP/a550f942ecf36099b11fcddfc0a3cd7d5e0d28c535a9c09e6b6a9c624fbf0099?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4'
    },
    {
      id: '334567',
      price: 70000,
      customerName: 'Jennifer Wilson',
      customerEmail: 'emilybrown@yahoo.com',
      date: '12/04/2022',
      status: 'SHIPPED',
      productImage: 'https://cdn.builder.io/api/v1/image/assets/TEMP/475587a8bbe9ccc335d559375b4fd4d1f5436fd8d426e6464ae32dcf86fcf3d7?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4'
    },
    {
      id: '567898',
      price: 70000,
      customerName: 'Jennifer Wilson',
      customerEmail: 'emilybrown@yahoo.com',
      date: '12/04/2022',
      status: 'PENDING',
      productImage: 'https://cdn.builder.io/api/v1/image/assets/TEMP/475587a8bbe9ccc335d559375b4fd4d1f5436fd8d426e6464ae32dcf86fcf3d7?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4'
    },
    {
      id: '235432',
      price: 70000,
      customerName: 'Jennifer Wilson',
      customerEmail: 'emilybrown@yahoo.com',
      date: '12/04/2022',
      status: 'DELIVERED',
      productImage: 'https://cdn.builder.io/api/v1/image/assets/TEMP/475587a8bbe9ccc335d559375b4fd4d1f5436fd8d426e6464ae32dcf86fcf3d7?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4'
    }
  ]
}
export interface Order {
  id: string;
  price: number;
  customerName: string;
  customerEmail: string;
  date: string;
  status: 'CANCELLED' | 'SHIPPED' | 'PENDING' | 'DELIVERED';
  productImage: string;
}
