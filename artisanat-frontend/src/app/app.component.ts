import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {UserProfileComponent} from "./shared/pages/user-profile/user-profile.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, UserProfileComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'artisanat-frontend';
}
