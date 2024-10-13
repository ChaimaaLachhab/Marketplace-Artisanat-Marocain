import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {UserProfileComponent} from "./shared/pages/user-profile/user-profile.component";
import {CheckoutComponent} from "./shared/pages/checkout/checkout.component";
import {DashboardComponent} from "./shared/pages/dashboard/dashboard.component";
import {CollectionDetailsComponent} from "./shared/pages/collection-details/collection-details.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, UserProfileComponent, CheckoutComponent, DashboardComponent, CollectionDetailsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'artisanat-frontend';
}
