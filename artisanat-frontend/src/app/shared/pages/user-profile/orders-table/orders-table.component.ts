import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-orders-table',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './orders-table.component.html',
  styleUrl: './orders-table.component.css'
})
export class OrdersTableComponent {


orderItems: OrderItem[] = [
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/9975516e9c50a5aa03dc14ab932823b8d66edd4b54c5f8f7b295b138ba00aabf?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Username', content: 'Content', tag: 'New tag' },
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/faa85902f3b0d7da6141a22c45df25cd8aec3376489d3b35d709caad8f6247b6?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Username', content: 'Content', tag: 'New tag' },
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/326aac7bfd811bdf0675b8d4607521efd67677ca4a4fc4974f0fe3c47ce1fbd3?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Username', content: 'Content', tag: 'New tag' },
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/431a183e884a523a24de421bcd1e1fb8f5bbf6753841554c891d22e542f142e7?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Username', content: 'Content', tag: 'New tag' },
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/d2468e99f14909199d234db373ac8bff03194729c93eecadd78cdc4ac3e46bb1?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Content', content: 'Content', tag: 'New tag' },
  { avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/183816a803d24b5569ea01bae10f8c9da8e9ada707ecdff93ca4b87b6ea2f899?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', username: 'Content', content: 'Content', tag: 'New tag' }
];
}
interface OrderItem {
  avatar: string;
  username: string;
  content: string;
  tag: string;
}
