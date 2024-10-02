import { Component } from '@angular/core';
import {CommonModule, Location, NgClass, NgForOf, NgIf} from "@angular/common";
import {Collection} from "../../../core/enums/collection.enum";
import {Category} from "../../../core/enums/category.enum";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthenticationService} from "../../../core/services/authentication.service";
import {Router} from "@angular/router";
import {JwtService} from "../../../core/services/jwt.service";
import {ProductService} from "../../../core/services/product.service";
import {LoginUserDto} from "../../../core/dtos/login-user.dto";
import {Product} from "../../../core/models/product.model";
import {BadgeModule} from "primeng/badge";
import {Button, ButtonModule} from "primeng/button";
import {ToastModule} from "primeng/toast";
import {FileUploadModule} from "primeng/fileupload";
import {MessageService, PrimeNGConfig} from "primeng/api";
import _default from "chart.js/dist/core/core.interaction";
import
import {ProgressBarModule} from "primeng/progressbar";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    NgForOf,
    FloatLabelModule,
    InputTextModule,
    ReactiveFormsModule,
    BadgeModule,
    Button,
    NgIf,
    ToastModule,
    FileUploadModule,
    NgClass,
    FileUploadModule,
    ButtonModule,
    BadgeModule,
    ProgressBarModule,
    ToastModule,
    HttpClientModule,
    CommonModule],

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

  files = [];

  totalSize : number = 0;

  totalSizePercent : number = 0;

  choose(event: any, callback: () => void) {
    callback();
  }

  onRemoveTemplatingFile(event: any, file: { size: any; }, removeFileCallback: (arg0: any, arg1: any) => void, index: any) {
    removeFileCallback(event, index);
    this.totalSize -= parseInt(this.formatSize(file.size));
    this.totalSizePercent = this.totalSize / 10;
  }

  onClearTemplatingUpload(clear: () => void) {
    clear();
    this.totalSize = 0;
    this.totalSizePercent = 0;
  }

  onTemplatedUpload() {
    this.messageService.add({ severity: 'info', summary: 'Success', detail: 'File Uploaded', life: 3000 });
  }

  onSelectedFiles(event: { currentFiles: never[]; }) {
    this.files = event.currentFiles;
    this.files.forEach((file) => {
      this.totalSize += parseInt(this.formatSize(file.size));
    });
    this.totalSizePercent = this.totalSize / 10;
  }

  uploadEvent(callback: () => void) {
    callback();
  }

  formatSize(bytes: number) {
    const k = 1024;
    const dm = 3;
    const sizes = this.config.translation.fileSizeTypes;
    if (bytes === 0) {
      return `0 ${sizes ? sizes : [0]}`;
    }

    const i = Math.floor(Math.log(bytes) / Math.log(k));
    const formattedSize = parseFloat((bytes / Math.pow(k, i)).toFixed(dm));

    return `${formattedSize} ${sizes ? sizes : [i]}`;
  }

  protected readonly _default = _default;
  protected readonly index = index;
}
