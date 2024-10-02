import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-order-summary',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './order-summary.component.html',
  styleUrl: './order-summary.component.css'
})
export class OrderSummaryComponent {
  orderItems: OrderItem[] = [
    { image: 'https://cdn.builder.io/api/v1/image/assets/TEMP/4ac070fd2bc21527bc06c72c6b563b9d8be84abfe9c0218be9df911421ff6009?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', name: 'Luxury Charms Ring', code: '78205', price: '$620.73' },
    { image: 'https://cdn.builder.io/api/v1/image/assets/TEMP/4ac070fd2bc21527bc06c72c6b563b9d8be84abfe9c0218be9df911421ff6009?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', name: 'Luxury Charms Ring', code: '78205', price: '$620.73' },
    { image: 'https://cdn.builder.io/api/v1/image/assets/TEMP/f74617d815866ed3b993d8d65752ae63a3ad6e79cfc4ff68bc866ad563588958?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', name: 'Exquisite Earrings', code: '92701', price: '$125.28' },
    { image: 'https://cdn.builder.io/api/v1/image/assets/TEMP/ff7f3117bb267bd213fb35a9ccdc7e74725b123b3c7aa790e4aa2a38e97dc1cb?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', name: 'Delights Earrings', code: '78201', price: '$327.71' }
  ];
}
interface OrderItem {
  image: string;
  name: string;
  code: string;
  price: string;
}
