import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FooterComponent} from "../../components/footer/footer.component";
import {HeaderComponent} from "../../components/header/header.component";
import {DividerModule} from "primeng/divider";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [
    FormsModule,
    FooterComponent,
    HeaderComponent,
    DividerModule,
    FloatLabelModule,
    InputTextModule,
    ReactiveFormsModule
  ],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent {
  fullName: string = "Jennifer Martinez";
  username: string = "elizabeth_taylor";
  email: string = "tayloremily@yahoo.com";
  phone: string = "(925) 576-0917";

  onChooseImage(): void {
    // Implement image selection logic
  }

  onRemoveImage(): void {
    // Implement image removal logic
  }

  onCancel(): void {
    // Implement cancel logic
  }

  onSaveProfile(): void {
    // Implement save profile logic
  }
}
