import { Component } from '@angular/core';
import {ActivitiesComponent} from "./activities/activities.component";
import {ChartComponent} from "./chart/chart.component";
import {OrdersComponent} from "./orders/orders.component";
import {StatisticsPieComponent} from "./statistics-pie/statistics-pie.component";
import {StatsComponent} from "./stats/stats.component";

@Component({
  selector: 'app-overview',
  standalone: true,
    imports: [
        ActivitiesComponent,
        ChartComponent,
        OrdersComponent,
        StatisticsPieComponent,
        StatsComponent
    ],
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css'
})
export class OverviewComponent {
  activities = [
    { user: 'Kathryn Murphy', avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/bf53dcbfe9f1cb83ac84c0fe504d9fef8ea66e25a4013709a34fc32130383179?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', description: 'In eu do do cillum lorem exercitation ea dolor.' },
    { user: 'James Harrid', avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/57e37a1676b152996fa28890ebcd5f335baf3338d752b6f9237dffa9e05a6179?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', description: 'Deserunt minim indidunt cillum nostrudo voluptate excepteur.' },
    { user: 'Elon Melon', avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/964015f6b198475baedff56a21d83857d5d4df2530f4c5eeaf13eb88c2a8c947?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', description: 'Mollit consectetur occaecat et ad adipisicing ex deserunt fugiat.' },
    { user: 'Mia Smith', avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/2988938675ce38bffdebbea91900e85dddfed05c36303005d45ab21aba0c0bbb?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', description: 'Cupidatat eiusmod tempor labore amet amet proident duis.' },
    { user: 'James Doe', avatar: 'https://cdn.builder.io/api/v1/image/assets/TEMP/306824d5e29a68076f89faa74c2ea2e4dbf325debc78a3e5681cf0093bcc7586?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', description: 'Duis excepteur enim enim dolore aliqua officia nisi labore ipsum.' }
  ];

  orders = [
    { product: 'Product name', price: 300, quantity: 2, amount: 600, status: 'New' },
    { product: 'Product name', price: 300, quantity: 3, amount: 900, status: 'Delivered' }
  ];
}
