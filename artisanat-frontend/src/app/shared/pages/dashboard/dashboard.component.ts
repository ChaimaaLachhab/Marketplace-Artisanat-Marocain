import { Component } from '@angular/core';
import { SidebarComponent } from './sidebar/sidebar.component';
import {HeaderDashboardComponent} from "./header-dashboard/header-dashboard.component";
import {DashComponent} from "./dash/dash.component";
import {ListComponent} from "../../../features/product/list/list.component";
import {UploadComponent} from "../../../features/media/upload/upload.component";
import {FormComponent} from "../../../features/product/form/form.component";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  standalone: true,
  imports: [
    SidebarComponent,
    HeaderDashboardComponent,
    DashComponent,
    ListComponent,
    UploadComponent,
    FormComponent
  ],
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  menuItems = [
    { icon: 'https://cdn.builder.io/api/v1/image/assets/TEMP/c66ef7fd34746b718fa2d9117b68f4d9f9dc79f2082e9a69224257b88db1c4f7?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', label: 'Dashboard', active: true },
    { icon: 'https://cdn.builder.io/api/v1/image/assets/TEMP/4c7cd227dec78b3f27adc062b6b9ace564c6257f0b764a8d6ee4ce25cd64a307?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', label: 'Products', active: false },
    { icon: 'https://cdn.builder.io/api/v1/image/assets/TEMP/6caa4887506b0543f0a187b8398f728a64d693753ecb757d603cadeb4e0614d1?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', label: 'Orders', active: false },
    { icon: 'https://cdn.builder.io/api/v1/image/assets/TEMP/8544eb477205241930663885425f33978449ceaf8bedf48b601bc104116d6af5?placeholderIfAbsent=true&apiKey=4bd7f7a51e424385abb4e97f8d321ab4', label: 'Profile', active: false }
  ];

  createNewProduct() {
    console.log('Create new product');
  }

  logout() {
    console.log('Logout');
  }
}
