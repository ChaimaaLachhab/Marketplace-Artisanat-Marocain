import {Component, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {SidebarModule} from "primeng/sidebar";
import {Button} from "primeng/button";
import {TieredMenuModule} from "primeng/tieredmenu";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    SidebarModule,
    Button,
    TieredMenuModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements  OnInit{
  sidebarVisible: boolean = false;
  menuItems: any[] | undefined;

  ngOnInit() {
    this.menuItems = [
      {label: 'Profile', icon: 'pi pi-user', routerLink: '/profile'},
      {label: 'Logout', icon: 'pi pi-sign-out', command: () => this.logout()}
    ];
  }
  logout() {

  }
}
