import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {authGuard} from "../../../core/guards/auth.guard";
import {roleGuard} from "../../../core/guards/role.guard";
import {Role} from "../../../core/enums/role.enum";
import {DashboardComponent} from "./dashboard.component";
import {OverviewComponent} from "./overview/overview.component";
import {ProductsComponent} from "./products/products.component";
import {ProductListComponent} from "./products/list/list.component";
import {ProductFormComponent} from "./products/form/form.component";
import {OrdersComponent} from "./orders/orders.component";
import {OrderOverviewComponent} from "./orders/order-overview/order-overview.component";
import {OrderDetailComponent} from "./orders/order-detail/detail.component";
import {OrderManageComponent} from "./orders/order-manage/manage.component";
import {ProfileComponent} from "./profile/profile.component";
import {ProfileDetailsComponent} from "./profile/profile-details/details.component";
import {ProfileFormComponent} from "./profile/profile-form/form.component";
import {ProductDetailComponent} from "./products/product-details/detail.component";
import {UsersComponent} from "./users/users.component";
import {OverviewUserComponent} from "./users/overview-user/overview-user.component";
import {ArtisanComponent} from "./users/artisan/artisan.component";
import {CustomerComponent} from "./users/customer/customer.component";

const routes: Routes = [
  {
    path: '',
    component: DashboardComponent,
    canActivate: [authGuard],
    children: [
      {
        path: '',
        redirectTo: 'overview',
        pathMatch: 'full'
      },
      {
        path: 'overview',
        component: OverviewComponent,
        canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
      },
      {
        path: 'products',
        component: ProductsComponent,
        canActivate: [authGuard],
        children: [
          {
            path: '',
            redirectTo: 'list',
            pathMatch: 'full'
          },
          {
            path: 'list',
            component: ProductListComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          },
          {
            path: 'details/:id',
            component: ProductDetailComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          },
          {
            path: 'form',
            component: ProductFormComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          }
        ]
      },
      {
        path: 'orders',
        component: OrdersComponent,
        canActivate: [authGuard],
        children: [
          {
            path: '',
            redirectTo: 'all',
            pathMatch: 'full'
          },
          {
            path: 'all',
            component: OrderOverviewComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          },
          {
            path: 'details/:id',
            component: OrderDetailComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          },
          {
            path: 'manage',
            component: OrderManageComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          }
        ]
      },
      {
        path: 'users',
        component: UsersComponent,
        canActivate: [authGuard],
        children: [
          {
            path: '',
            redirectTo: 'all',
            pathMatch: 'full'
          },
          {
            path: 'all',
            component: OverviewUserComponent,
            canActivate: [authGuard, roleGuard([Role.ADMIN])]
          },
          {
            path: 'artisan',
            component: ArtisanComponent,
            canActivate: [authGuard, roleGuard([Role.ADMIN])]
          },
          {
            path: 'customer',
            component: CustomerComponent,
            canActivate: [authGuard, roleGuard([Role.ADMIN])]
          }
        ]
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [authGuard],
        children: [
          {
            path: '',
            redirectTo: 'details',
            pathMatch: 'full'
          },
          {
            path: 'details',
            component: ProfileDetailsComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          },
          {
            path: 'edit/:id',
            component: ProfileFormComponent,
            canActivate: [authGuard, roleGuard([Role.ARTISAN, Role.ADMIN])]
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
