import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ListComponent} from "./list/list.component";
import {DetailComponent} from "./detail/detail.component";
import {FormComponent} from "./form/form.component";
import {authGuard} from "../../core/guards/auth.guard";
import {roleGuard} from "../../core/guards/role.guard";
import {Role} from "../../core/enums/role.enum";
import {DashboardComponent} from "../../components/features/admin/dashboard/dashboard.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },
  {
    path: 'list',
    component: ListComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },
  {
    path: 'detail/:id',
    component: DetailComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },
  {
    path: 'form',
    component: FormComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
