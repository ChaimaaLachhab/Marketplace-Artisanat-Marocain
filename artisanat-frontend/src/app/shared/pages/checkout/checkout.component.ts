import { Component } from '@angular/core';
import {HeaderComponent} from "../../components/header/header.component";
import {FooterComponent} from "../../components/footer/footer.component";
import {OrderSummaryComponent} from "./order-summary/order-summary.component";
import {BillingDetailsComponent} from "./billing-details/billing-details.component";

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    HeaderComponent,
    FooterComponent,
    OrderSummaryComponent,
    BillingDetailsComponent
  ],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent {

}
