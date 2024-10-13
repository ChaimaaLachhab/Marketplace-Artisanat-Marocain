import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthenticationService } from '../../../../core/services/authentication.service';
import { ArtisanRequestDto } from '../../../../core/dtos/request/artisan-request-dto';
import { Router } from '@angular/router';
import { ToastModule } from 'primeng/toast';
import { StepsModule } from 'primeng/steps';
import { NgForOf, NgIf } from '@angular/common';
import { ButtonDirective } from 'primeng/button';
import { Specialty } from '../../../../core/enums/specialty.enum';
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {PasswordModule} from "primeng/password";
import {DropdownModule} from "primeng/dropdown";

@Component({
  selector: 'app-artisan',
  templateUrl: './artisan.component.html',
  styleUrls: ['./artisan.component.css'],
  standalone: true,
  imports: [
    ToastModule,
    StepsModule,
    ReactiveFormsModule,
    NgIf,
    ButtonDirective,
    NgForOf,
    FloatLabelModule,
    InputTextModule,
    PasswordModule,
    DropdownModule
  ],
  providers: [MessageService]
})
export class ArtisanComponent implements OnInit {
  items: MenuItem[] = [];
  activeIndex: number = 0;
  specialtyOptions = Object.values(Specialty).map(specialty => ({ name: specialty }));
  personalForm!: FormGroup;
  professionalForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private messageService: MessageService,
    private artisanService: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit() {
    this.items = [
      { label: '', command: () => this.messageService.add({ severity: 'info', summary: 'First Step', detail: 'Personal Information' }) },
      { label: '', command: () => this.messageService.add({ severity: 'info', summary: 'Second Step', detail: 'Professional Information' }) },
    ];

    this.personalForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.professionalForm = this.fb.group({
      specialty: [null, Validators.required],
      location: ['', Validators.required],
      experience: [null, [Validators.required, Validators.min(0)]]
    });
  }

  onActiveIndexChange(event: number) {
    this.activeIndex = event;
  }

  onNext() {
    if (this.activeIndex === 0 && this.personalForm.valid) {
      this.activeIndex++;
    } else if (this.activeIndex === 1 && this.professionalForm.valid) {
      this.activeIndex++;
    }
  }

  onPrevious() {
    this.activeIndex = Math.max(0, this.activeIndex - 1);
  }

  onSubmit() {
    if (this.personalForm.valid && this.professionalForm.valid) {
      const artisanDTO: ArtisanRequestDto = {
        ...this.personalForm.value,
        ...this.professionalForm.value,
        specialty: this.professionalForm.value.specialty?.name || this.professionalForm.value.specialty
      };

      this.artisanService.addArtisan(artisanDTO).subscribe(response => {

        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Artisan registered successfully!' });
        this.activeIndex = 0;
        this.personalForm.reset();
        this.professionalForm.reset();
        this.router.navigate(['/auth/login'])
      });
    }
  }
}
