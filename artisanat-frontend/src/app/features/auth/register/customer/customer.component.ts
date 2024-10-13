import { Component } from '@angular/core';
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {PaginatorModule} from "primeng/paginator";
import {PasswordModule} from "primeng/password";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {AuthenticationService} from "../../../../core/services/authentication.service";
import {CustomerRequestDto} from "../../../../core/dtos/request/customer-request-dto";

@Component({
  selector: 'app-customer',
  standalone: true,
    imports: [
        FloatLabelModule,
        InputTextModule,
        PaginatorModule,
        PasswordModule,
        ReactiveFormsModule,
        RouterLink
    ],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  customerRegisterForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private router: Router
  ) {
    this.customerRegisterForm = this.fb.group({
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
    if (this.customerRegisterForm.valid) {
      const formValues = this.customerRegisterForm.value;
      const registerUserDto: CustomerRequestDto = {
        fullName: formValues.fullName,
        username: formValues.userName,
        email: formValues.email,
        phone: formValues.phone,
        password: formValues.password,

      };

      this.authService.register(registerUserDto).subscribe({
        next: (response) => {
          console.log('Registration successful:', response);
          this.router.navigate(['/auth/login']);
        },
        error: (err) => {
          console.error('Registration failed:', err);
        }
      });
    }
  }
}
