import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../../../core/services/customer.service';
import {FooterComponent} from "../../components/footer/footer.component";
import {HeaderComponent} from "../../components/header/header.component";
import {DividerModule} from "primeng/divider";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {CustomerResponseDto} from "../../../core/dtos/response/customer-response-dto";
import {CustomerRequestDto} from "../../../core/dtos/request/customer-request-dto";
import {ConfirmationService, MessageService} from "primeng/api";

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
  providers: [MessageService, ConfirmationService],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit {
  @ViewChild('fileInput') fileInput!: ElementRef;
  userDetails: CustomerResponseDto | undefined;
  profileForm: FormGroup;
  selectedFile: File | null = null;
  previewUrl: string | ArrayBuffer | null = null;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private customerService: CustomerService,
    private messageService: MessageService
  ) {
    this.profileForm = this.fb.group({
      fullName: ['', Validators.required],
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.customerService.getCustomerById().subscribe(userDetails => {
      this.userDetails = userDetails;

      this.profileForm.patchValue({
        fullName: this.userDetails.fullName,
        username: this.userDetails.username,
        email: this.userDetails.email,
        phone: this.userDetails.phone
      });
      this.previewUrl = this.userDetails.userPhoto ? this.userDetails.userPhoto.mediaUrl : null;
    });
  }

  onSaveProfile(): void {
    if (this.profileForm.valid) {
      const customer: CustomerRequestDto = this.profileForm.value;

      this.customerService.updateCustomer(customer, this.selectedFile!).subscribe({
        next: (response) => {
          console.log('Profile updated successfully with photo:', response);
          this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
          this.router.navigate(['/profile']);
        },
        error: (error) => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content' });
          console.error('Error updating profile with photo:', error);
        }
      });
    }
  }

  triggerFileInput(): void {
    this.fileInput.nativeElement.click();
  }

  onChooseImage(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.previewUrl = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onRemoveImage(): void {
    this.selectedFile = null;
    this.previewUrl = null;
  }

  onCancel(): void {
    this.router.navigate(['/profile']);
  }
}
