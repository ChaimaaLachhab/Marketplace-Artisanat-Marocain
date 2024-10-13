import { Component } from '@angular/core';
import {DividerModule} from "primeng/divider";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-profile-details',
  standalone: true,
  imports: [
    DividerModule,
    RouterLink
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class ProfileDetailsComponent {
  constructor(private router: Router) {
  }

  editProfile(id: number){
    this.router.navigate(['/dashboard/profile/edit', id])
  }
}
