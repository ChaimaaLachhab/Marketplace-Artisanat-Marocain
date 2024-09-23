import { Component } from '@angular/core';
import {HeaderDashComponent} from "../../../shared/components/header-dash/header-dash.component";
import {SidebarComponent} from "../../../shared/components/sidebar/sidebar.component";

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [
    HeaderDashComponent,
    SidebarComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
