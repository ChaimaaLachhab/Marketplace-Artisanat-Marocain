import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthenticationService } from '../../../core/services/authentication.service';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import { LoginUserDto } from '../../../core/dtos/login-user.dto';
import {JwtService} from "../../../core/services/jwt.service";
import {MatCard, MatCardContent} from "@angular/material/card";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatCheckbox} from "@angular/material/checkbox";
import {MatInput} from "@angular/material/input";
import {MatAnchor, MatButton} from "@angular/material/button";
import {Role} from "../../../core/enums/role.enum";
import {LoginResponse} from "../../../core/dtos/login-response.dto";
import {Location} from "@angular/common";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterOutlet,
    RouterLink,
    MatCard,
    MatCardContent,
    MatLabel,
    MatFormField,
    MatCheckbox,
    MatInput,
    MatAnchor,
    MatButton,
    FloatLabelModule,
    InputTextModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private router: Router,
    private jwtService : JwtService,
    private location : Location
  ) {
    this.loginForm = this.fb.group({
      userNameOrEmail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    if (!this.loginForm.valid) {
      console.error('Form is not valid.');
      return;
    }

    const formValues = this.loginForm.value;
    const loginUser: LoginUserDto = {
      userNameOrEmail: formValues.userNameOrEmail,
      password: formValues.password
    };

    this.authService.authenticate(loginUser).subscribe({
      next: (response : LoginResponse) => this.handleLoginSuccess(response),
      error: (err) => this.handleLoginError(err),
      complete: () => console.log('Login process complete.')
    });
  }

  private handleLoginSuccess(response: LoginResponse) {
    console.log('Login successful:', response);

    const token = response?.token;
    if (!token) {
      console.error('No token found in the response.');
      return;
    }

    console.log('Token expires in:', response.expiresIn);

    try {
      const role: string | null = this.jwtService.getUserRole(token);

      if (role) {
        this.jwtService.setUserRole(role);
        this.redirectUserByRole(role);
      } else {
        console.error('No role found in the token.');
      }
    } catch (error) {
      console.error('Token decoding failed:', error);
    }
  }



  private handleLoginError(error: any) {
    console.error('Login failed:', error);
  }

  private redirectUserByRole(role: string | null) {
    switch (role) {
      case Role.ADMIN.toString():
        this.router.navigate(['/admin']);
        break;
      case Role.ARTISAN.toString():
        this.router.navigate(['/artisan']);
        break;
      case Role.CUSTOMER.toString():
        this.location.back();
        break;
      default:
        console.error('Unknown role:', role);
        break;
    }
  }
}
