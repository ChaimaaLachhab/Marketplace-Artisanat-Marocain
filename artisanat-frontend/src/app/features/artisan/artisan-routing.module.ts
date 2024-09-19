import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProfileComponent} from "./profile/profile.component";
import {authGuard} from "../../core/guards/auth.guard";
import {roleGuard} from "../../core/guards/role.guard";
import {Role} from "../../core/enums/role.enum";
import {ListComponent} from "./list/list.component";
import {FormComponent} from "./form/form.component";
import {DashboardComponent} from "../admin/dashboard/dashboard.component";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard, roleGuard([Role.ARTISAN])]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [authGuard, roleGuard([Role.ARTISAN])]
  },
  {
    path: 'list',
    component: ListComponent,
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },
  {
    path: 'form',
    component: FormComponent,
    canActivate: [authGuard, roleGuard([Role.ARTISAN])]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArtisanRoutingModule { }
