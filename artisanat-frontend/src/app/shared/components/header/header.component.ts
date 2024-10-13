import {Component, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {SidebarModule} from "primeng/sidebar";
import {Button} from "primeng/button";
import {TieredMenuModule} from "primeng/tieredmenu";
import {MatIcon} from "@angular/material/icon";
import {NgIf} from "@angular/common";
import {DividerModule} from "primeng/divider";
import {SummaryComponent} from "../../../features/cart/summary/summary.component";
import {IconProfileComponent} from "./icon-profile/icon-profile.component";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    SidebarModule,
    Button,
    TieredMenuModule,
    MatIcon,
    NgIf,
    DividerModule,
    SummaryComponent,
    IconProfileComponent
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

}
