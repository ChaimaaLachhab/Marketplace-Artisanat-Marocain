import { Component } from '@angular/core';
import {SidebarComponent} from "./sidebar/sidebar.component";
import {HeaderDashboardComponent} from "./header-dashboard/header-dashboard.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    SidebarComponent,
    HeaderDashboardComponent,
    RouterOutlet
  ],
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  pageTitle: string = 'Dashboard';

  onMenuSelection(page: string): void {
    this.pageTitle = page;
  }

  createNewProduct() {
    console.log('Create new product');
  }

  logout() {
    console.log('Logout');
  }
}
