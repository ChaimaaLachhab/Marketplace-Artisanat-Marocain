import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SummaryComponent} from "./summary/summary.component";
import {ItemComponent} from "./item/item.component";
import {authGuard} from "../../core/guards/auth.guard";
import {CheckoutComponent} from "./checkout/checkout.component";

const routes: Routes = [{
  path: 'summary',
  component: SummaryComponent,
  canActivate: [authGuard]
},
  {
    path: 'item',
    component: ItemComponent,
    canActivate: [authGuard]
  },
  {
    path: 'checkout',
    component: CheckoutComponent,
    canActivate: [authGuard]
  },];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
