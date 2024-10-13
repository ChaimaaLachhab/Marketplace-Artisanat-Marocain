import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FloatLabelModule} from "primeng/floatlabel";
import {ChipsModule} from "primeng/chips";
import {DividerModule} from "primeng/divider";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-form',
  standalone: true,
  imports: [
    FormsModule,
    FloatLabelModule,
    ChipsModule,

    ReactiveFormsModule,
    DividerModule
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class ProfileFormComponent {

  constructor(private router: Router) {
  }

  fullName: string = "Jennifer Martinez";
  username: string = "elizabeth_taylor";
  email: string = "tayloremily@yahoo.com";
  phone: string = "(925) 576-0917";
  specialty: string = "Jennifer Martinez";
  location: string = "elizabeth_taylor";
  experience: string = "tayloremily@yahoo.com";
  description: string = "(925) 576-0917";

  onChooseImage(): void {
    // Implement image selection logic
  }

  onRemoveImage(): void {
    // Implement image removal logic
  }

  onCancel(): void {
    this.router.navigate(['/dashboard/profile/details'])
  }

  onSaveProfile(): void {
    // Implement save profile logic
  }

}
