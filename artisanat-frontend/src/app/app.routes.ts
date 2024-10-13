import { Routes } from '@angular/router';
import {authGuard} from "./core/guards/auth.guard";
import {ContactUsComponent} from "./shared/pages/contact-us/contact-us.component";
import {AboutUsComponent} from "./shared/pages/about-us/about-us.component";
import {CollectionListingComponent} from "./shared/pages/collection-listing/collection-listing.component";
import {ProductListingComponent} from "./shared/pages/product-listing/product-listing.component";
import {HomeComponent} from "./shared/pages/home/home.component";
import {PageNotFoundComponent} from "./shared/pages/page-not-found/page-not-found.component";
import {UserProfileComponent} from "./shared/pages/user-profile/user-profile.component";
import {EditProfileComponent} from "./shared/pages/edit-profile/edit-profile.component";
import {ProductDetailsComponent} from "./shared/pages/product-details/product-details.component";
import {ArtisanInfosComponent} from "./shared/pages/artisan-infos/artisan-infos.component";
import {CheckoutComponent} from "./shared/pages/checkout/checkout.component";
import {CheckoutSuccessComponent} from "./shared/pages/checkout-success/checkout-success.component";

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

  { path: 'product-infos/:id',
    component: ProductDetailsComponent
  },

  { path: 'artisan-infos/:id',
    component: ArtisanInfosComponent
  },

  { path: 'collection',
    component: CollectionListingComponent
  },

  { path: 'checkout',
    component: CheckoutComponent
  },

  { path: 'checkout-success',
    component: CheckoutSuccessComponent
  },

  { path: 'about',
    component: AboutUsComponent
  },

  { path: 'contact',
    component: ContactUsComponent
  },

  { path: 'profile',
    component: UserProfileComponent,
    canActivate: [authGuard]
  },

  { path: 'edit-profile',
    component: EditProfileComponent
  },

  {
    path: 'auth',
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },

  {
    path: 'dashboard',
    loadChildren: () => import('./shared/pages/dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [authGuard]
  },

  { path: '**', component: PageNotFoundComponent }
];
