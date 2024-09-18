import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authGuard} from "../../core/guards/auth.guard";
import {ListComponent} from "./list/list.component";
import {roleGuard} from "../../core/guards/role.guard";
import {Role} from "../../core/enums/role.enum";
import {DetailComponent} from "./detail/detail.component";
import {FormComponent} from "./form/form.component";

const routes: Routes = [
  {
    path: 'list',
    component: ListComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },
  {
    path: 'detail/:id',
    component: DetailComponent,
    canActivate: [authGuard, roleGuard([Role.CUSTOMER])]
  },
  {
    path: 'form',
    component: FormComponent,
    canActivate: [authGuard, roleGuard([Role.CUSTOMER])]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
