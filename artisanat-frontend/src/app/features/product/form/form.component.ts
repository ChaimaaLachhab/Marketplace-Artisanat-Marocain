import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Collection} from "../../../core/enums/collection.enum";
import {Category} from "../../../core/enums/category.enum";
import {ProductService} from "../../../core/services/product.service";
import {Router} from "@angular/router";
import {Product} from "../../../core/models/product.model";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {NgForOf} from "@angular/common";
import {UploadComponent} from "../../media/upload/upload.component";
import {DropdownModule} from "primeng/dropdown";

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
    DropdownModule
  ],
  styleUrl: './form.component.css'
})
export class FormComponent {
  productForm: FormGroup;
  collections = Object.values(Collection);
  categories = Object.values(Category);

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private router: Router
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

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      // Handle file upload logic here
      console.log('File selected:', input.files[0].name);
    }
  }
}
