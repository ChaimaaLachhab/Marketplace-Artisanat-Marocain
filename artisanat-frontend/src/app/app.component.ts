import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {UserProfileComponent} from "./shared/pages/user-profile/user-profile.component";
import {CheckoutComponent} from "./shared/pages/checkout/checkout.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, UserProfileComponent, CheckoutComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'artisanat-frontend';
}
