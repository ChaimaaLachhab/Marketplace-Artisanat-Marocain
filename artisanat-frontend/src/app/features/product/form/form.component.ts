import { Component } from '@angular/core';
import { MessageService, PrimeNGConfig } from 'primeng/api';
import {FileRemoveEvent, FileSelectEvent, FileUploadModule} from 'primeng/fileupload';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { BadgeModule } from 'primeng/badge';
import { HttpClientModule } from '@angular/common/http';
import { ProgressBarModule } from 'primeng/progressbar';
import { ToastModule } from 'primeng/toast';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Collection} from "../../../core/enums/collection.enum";
import {Category} from "../../../core/enums/category.enum";
import {ProductService} from "../../../core/services/product.service";
import {Router} from "@angular/router";
import {Product} from "../../../core/models/product.model";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [FileUploadModule, ButtonModule, BadgeModule, ProgressBarModule, ToastModule, HttpClientModule, CommonModule, ReactiveFormsModule, FloatLabelModule, InputTextModule],
  providers: [MessageService],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  productForm: FormGroup;
  collections = Object.values(Collection);
  categories = Object.values(Category);

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router,
    private location : Location,
    private config: PrimeNGConfig,
    private messageService: MessageService
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
    const product: Product = <Product>{
      name: formValues.name,
      price: formValues.price,
      stock: formValues.stock,
      collection: formValues.collection,
      category: formValues.category,
      description: formValues.description,
    };

    console.log('Product saved');
  }

  files: File[] = [];

  totalSize: number = 0;

  totalSizePercent: number = 0;

  choose(event: Event, callback: () => void): void {
    callback();
  }

  onRemoveTemplatingFile(event: Event, file: File, removeFileCallback: (event: FileRemoveEvent) => void, index: number): void {
    removeFileCallback({ originalEvent: event, file: file });
    this.totalSize -= file.size;
    this.totalSizePercent = this.totalSize / 10000; // Assuming 1MB = 100%
  }

  onClearTemplatingUpload(clear: () => void): void {
    clear();
    this.totalSize = 0;
    this.totalSizePercent = 0;
  }

  onTemplatedUpload(): void {
    this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
  }

  onSelectedFiles(event: FileSelectEvent): void {
    this.files = [...(this.files || []), ...event.files];
    this.updateTotalSize();
  }

  updateTotalSize(): void {
    this.totalSize = this.files.reduce((sum, file) => sum + file.size, 0);
    this.totalSizePercent = this.totalSize / 10000;
  }

  uploadEvent(callback: () => void): void {
    callback();
  }

  formatSize(bytes: number): string {
    const k = 1024;
    const dm = 3;
    const sizes = this.config.translation?.fileSizeTypes || ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    if (bytes === 0) {
      return `0 ${sizes[0]}`;
    }

    const i = Math.floor(Math.log(bytes) / Math.log(k));
    const formattedSize = parseFloat((bytes / Math.pow(k, i)).toFixed(dm));

    return `${formattedSize} ${sizes[i]}`;
  }
}
