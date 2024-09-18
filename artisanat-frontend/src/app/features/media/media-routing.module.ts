import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {authGuard} from "../../core/guards/auth.guard";
import {ListComponent} from "./list/list.component";
import {UploadComponent} from "./upload/upload.component";
import {GalleryComponent} from "./gallery/gallery.component";

const routes: Routes = [
  {
    path: 'gallery',
    component: GalleryComponent,
    canActivate: [authGuard]
  },
  {
    path: 'upload',
    component: UploadComponent,
    canActivate: [authGuard]
  },
  {
    path: 'list',
    component: ListComponent,
    canActivate: [authGuard]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MediaRoutingModule { }
