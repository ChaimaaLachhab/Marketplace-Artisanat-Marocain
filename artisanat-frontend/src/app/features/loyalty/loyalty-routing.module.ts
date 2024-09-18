import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListComponent} from "./list/list.component";
import {authGuard} from "../../core/guards/auth.guard";
import {DetailComponent} from "./detail/detail.component";
import {FormComponent} from "./form/form.component";

const routes: Routes = [
  {
    path: 'list',
    component: ListComponent,
    canActivate: [authGuard]
  },
  {
    path: 'detail/:id',
    component: DetailComponent,
    canActivate: [authGuard]
  },
  {
    path: 'form',
    component: FormComponent,
    canActivate: [authGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoyaltyRoutingModule { }
