import {Component, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Collection} from "../../../../../core/enums/collection.enum";
import {Category} from "../../../../../core/enums/category.enum";
import {ProductService} from "../../../../../core/services/product.service";
import {Product} from "../../../../../core/models/product.model";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {NgForOf} from "@angular/common";
import {UploadComponent} from "../../../../../features/media/upload/upload.component";
import {DropdownModule} from "primeng/dropdown";
import {GalleryComponent} from "../../../../../features/media/gallery/gallery.component";
import {ConfirmationService, MessageService} from "primeng/api";
import {ToastModule} from "primeng/toast";
import {ProductRequestDto} from "../../../../../core/dtos/request/product-request-dto";
import {ArtisanService} from "../../../../../core/services/artisan.service";
import {ArtisanResponseDto} from "../../../../../core/dtos/response/artisan-response-dto";
import {VerificationStatus} from "../../../../../core/enums/verification-status.enum";

@Component({
  selector: 'app-product-form',
  standalone: true,
  templateUrl: './form.component.html',
  imports: [
    ReactiveFormsModule,
    FloatLabelModule,
    InputTextModule,
    NgForOf,
    UploadComponent,
    DropdownModule,
    GalleryComponent,
    ToastModule
  ],
  providers: [MessageService, ConfirmationService],
  styleUrl: './form.component.css'
})
export class ProductFormComponent {
  artisan!: ArtisanResponseDto;
  productForm: FormGroup;
  collections = Object.values(Collection);
  categories = Object.values(Category);

  @ViewChild(GalleryComponent) galleryComponent!: GalleryComponent;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private messageService: MessageService,
    private artisanService: ArtisanService
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      price: ['', Validators.required],
      stock: ['', Validators.required],
      collection: ['', Validators.required],
      category: ['', Validators.required],
      description: ['', Validators.required]
    });
  }


  onSave(): void {
    if (!this.productForm.valid) {
      console.error('Form is not valid.');
      return;
    }

    const formValues = this.productForm.value;
    const product: ProductRequestDto = <Product>{
      name: formValues.name,
      price: formValues.price,
      stock: formValues.stock,
      collection: formValues.collection,
      category: formValues.category,
      description: formValues.description,
    };

    const attachments: File[] = this.galleryComponent.files.map(file => file.file);
    console.log(attachments)

    this.artisanService.getArtisanById().subscribe(resp => {
      this.artisan = resp;

      if (this.artisan.verificationStatus === VerificationStatus.APPROVED) {
        this.productService.createProductWithMedia(product, attachments).subscribe({
          next: (response) => {
            console.log('Product created successfully:', response);
            this.messageService.add({
              severity: 'success',
              summary: 'Product Created',
              detail: 'The product has been created successfully.',
              life: 3000
            });
            this.productForm.reset();
            this.galleryComponent.onClear();
          },
          error: (error) => {
            console.error('Error creating product:', error);
            this.messageService.add({
              severity: 'error',
              summary: 'Creation Error',
              detail: 'An error occurred while creating the product. Please try again later.'
            });
          }
        });
      } else {
        this.messageService.add({
          severity: 'error',
          summary: 'Verification Required',
          detail: 'Your artisan account is not approved. Please contact support.'
        });
      }
    });

  }
}
