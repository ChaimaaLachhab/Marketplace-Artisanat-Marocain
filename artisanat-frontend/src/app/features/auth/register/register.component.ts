import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators
} from '@angular/forms';
import { AuthenticationService } from '../../../core/services/authentication.service';
import { RouterLink, RouterOutlet} from "@angular/router";
import { MatCard, MatCardContent} from "@angular/material/card";
import { MatFormField, MatLabel} from "@angular/material/form-field";
import { MatInput} from "@angular/material/input";
import { MatAnchor, MatButton} from "@angular/material/button";
import { MatOption, MatSelect} from "@angular/material/select";
import {Location, NgIf} from '@angular/common';
import {RegisterUserDto} from "../../../core/dtos/register-user.dto";
import {InputTextModule} from "primeng/inputtext";
import {FloatLabelModule} from "primeng/floatlabel";
import {PasswordModule} from "primeng/password";


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterOutlet,
    MatCard,
    MatCardContent,
    RouterLink,
    MatLabel,
    MatFormField,
    MatInput,
    MatAnchor,
    MatButton,
    MatSelect,
    MatOption,
    NgIf,
    InputTextModule,
    FloatLabelModule,
    PasswordModule
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private location: Location
  ) {
    this.registerForm = this.fb.group({
      userName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      email: ['', [
        Validators.required,
        Validators.email,
        Validators.pattern('[a-z0-9]+@[a-z]+.[a-z]{2,3}')
      ]],
      phone:['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  register() {
    if (this.registerForm.valid) {
      const formValues = this.registerForm.value;
      const registerUserDto: RegisterUserDto = {
        userName: formValues.userName,
        email: formValues.email,
        phone: formValues.phone,
        password: formValues.password
      };

      this.authService.registerUser(registerUserDto).subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          this.location.back();        },
        error: (err) => {
          console.error('Registration failed:', err);
        }
      });
    }
  }
}
