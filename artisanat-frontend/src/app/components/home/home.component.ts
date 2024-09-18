import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  imports: [
    Button
  ],
  standalone: true
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToLogin() {
    this.router.navigate(['/auth/login']);
  }
}
