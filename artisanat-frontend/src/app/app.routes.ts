import { Routes } from '@angular/router';
import {authGuard} from "./core/guards/auth.guard";
import {roleGuard} from "./core/guards/role.guard";
import {Role} from "./core/enums/role.enum";
import {ContactUsComponent} from "./shared/pages/contact-us/contact-us.component";
import {AboutUsComponent} from "./shared/pages/about-us/about-us.component";
import {CollectionListingComponent} from "./shared/pages/collection-listing/collection-listing.component";
import {ProductListingComponent} from "./shared/pages/product-listing/product-listing.component";
import {HomeComponent} from "./shared/pages/home/home.component";
import {PageNotFoundComponent} from "./shared/pages/page-not-found/page-not-found.component";

export const routes: Routes = [
  { path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },

  { path: 'home',
    component: HomeComponent
  },

  { path: 'shop',
    component: ProductListingComponent
  },

  { path: 'collection',
    component: CollectionListingComponent
  },

  { path: 'about',
    component: AboutUsComponent
  },

  { path: 'contact',
    component: ContactUsComponent
  },

  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },

  {
    path: 'admin',
    loadChildren: () => import('./features/admin/admin.module').then(m => m.AdminModule),
    canActivate: [authGuard, roleGuard([Role.ADMIN])]
  },

  {
    path: 'artisan',
    loadChildren: () => import('./features/artisan/artisan.module').then(m => m.ArtisanModule),
    canActivate: [authGuard, roleGuard([Role.ARTISAN])]
  },

  {
    path: 'cart',
    loadChildren: () => import('./features/cart/cart.module').then(m => m.CartModule),
    canActivate: [authGuard]
  },

  {
    path: 'customer',
    loadChildren: () => import('./features/customer/customer.module').then(m => m.CustomerModule),
    canActivate: [authGuard, roleGuard([Role.CUSTOMER])]
  },

  {
    path: 'loyalty',
    loadChildren: () => import('./features/loyalty/loyalty.module').then(m => m.LoyaltyModule),
    canActivate: [authGuard]
  },

  {
    path: 'media',
    loadChildren: () => import('./features/media/media.module').then(m => m.MediaModule),
    canActivate: [authGuard]
  },

  {
    path: 'order',
    loadChildren: () => import('./features/order/order.module').then(m => m.OrderModule),
    canActivate: [authGuard]
  },

  {
    path: 'product',
    loadChildren: () => import('./features/product/product.module').then(m => m.ProductModule),
    canActivate: [authGuard]
  },

  {
    path: 'review',
    loadChildren: () => import('./features/review/review.module').then(m => m.ReviewModule),
    canActivate: [authGuard]
  },

  {
    path: 'sub-order',
    loadChildren: () => import('./features/sub-order/sub-order.module').then(m => m.SubOrderModule),
    canActivate: [authGuard]
  },

  { path: '**', component: PageNotFoundComponent }
];
