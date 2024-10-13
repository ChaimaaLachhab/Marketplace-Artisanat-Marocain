import {Component, EventEmitter, Input, Output} from '@angular/core';
import {MenuItem} from "primeng/api";
import {NgClass, NgForOf, NgIf, NgStyle} from "@angular/common";
import {Router, RouterLink, RouterLinkActive} from "@angular/router";
import {MatIcon, MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {MatTooltip} from "@angular/material/tooltip";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {JwtService} from "../../../../core/services/jwt.service";

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink,
    RouterLinkActive,
    MatIcon,
    NgClass,
    NgStyle,
    MatTooltip,
    NgIf
  ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  @Output() menuSelection = new EventEmitter<string>();

  constructor(private router: Router, private authService: AuthenticationService, private jwtService: JwtService) {
  }

  onMenuClick(page: string): void {
    this.menuSelection.emit(page);
  }

  onLogout() {

    this.authService.logout();
  }

  isAdmin(): boolean {
    return this.jwtService.isAdmin()
  }
}
