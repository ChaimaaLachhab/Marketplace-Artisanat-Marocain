import { Component } from '@angular/core';
import {FooterComponent} from "../../components/footer/footer.component";
import {HeaderComponent} from "../../components/header/header.component";
import {NgForOf} from "@angular/common";
import {UserInfoComponent} from "./user-info/user-info.component";
import {PointsCardComponent} from "./points-card/points-card.component";
import {OrdersTableComponent} from "./orders-table/orders-table.component";

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [
    FooterComponent,
    HeaderComponent,
    NgForOf,
    UserInfoComponent,
    PointsCardComponent,
    OrdersTableComponent
  ],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {

}
