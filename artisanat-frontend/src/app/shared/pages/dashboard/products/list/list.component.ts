import {Component, OnInit, ViewChild} from '@angular/core';
import {Table, TableModule} from "primeng/table";
import {TagModule} from "primeng/tag";
import {InputTextModule} from "primeng/inputtext";
import {Ripple} from "primeng/ripple";
import {CommonModule, NgForOf} from "@angular/common";
import {Router, RouterModule} from "@angular/router";
import {ButtonModule} from "primeng/button";
import {ToastModule} from "primeng/toast";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {PaginatorModule} from "primeng/paginator";
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {DialogModule} from "primeng/dialog";
import {ConfirmationService, MessageService} from "primeng/api";
import {Collection} from "../../../../../core/enums/collection.enum";
import {Category} from "../../../../../core/enums/category.enum";
import {Product} from "../../../../../core/models/product.model";
import {ProductService} from "../../../../../core/services/product.service";
import {RatingModule} from "primeng/rating";
import {ProductResponseDto} from "../../../../../core/dtos/response/product-response-dto";
import {ProductRequestDto} from "../../../../../core/dtos/request/product-request-dto";
import {AuthenticationService} from "../../../../../core/services/authentication.service";

@Component({
  selector: 'app-list-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    ToastModule,
    ConfirmDialogModule,
    Ripple,
    PaginatorModule,
    TagModule,
    ReactiveFormsModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    NgForOf,
    RatingModule
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css'
})
export class ProductListComponent implements OnInit{
  @ViewChild('dt') dt!: Table;
  products: ProductResponseDto[] = [];
  collections = Object.values(Collection);
  categiries = Object.values(Category);
  clonedProduct: { [s: string]: Product } = {};
  artisanId!:number;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private authService: AuthenticationService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  onNumber() : number{
    return this.products.length;
  }

  loadProducts(): void {
    this.authService.currentUser$.subscribe((user) => {
      this.artisanId =user.id
    })

    this.productService.getProductsByArtisan(this.artisanId).subscribe({
      next: (products) => {
        this.products = products;
        console.log(products)
        this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Message Content' });
      },
      error: () => this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content' })
    });
  }

  onRowEditInit(product: Product) {
    this.clonedProduct[product.id] = { ...product };
  }

  onRowEditSave(product: ProductResponseDto) {
    if (product.id > 0) {
      const dto: ProductRequestDto = {
        name: product.name,
        description: product.description,
        price: product.price,
        stock: product.stock,
        collection: product.collection,
        category: product.category,
      };

      this.productService.updateProduct(product.id ,dto).subscribe({
        next: (updatedEquipment) => {
          this.products[this.products.findIndex(e => e.id === product.id)] = updatedEquipment;
          delete this.clonedProduct[product.id];
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Product is updated' });
        },
        error: () => {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Failed to update Product' });
          this.onRowEditCancel(product, this.products.findIndex(e => e.id === product.id));
        }
      });
    } else {
      this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Invalid Product' });
    }
  }

  onRowEditCancel(product: ProductResponseDto, index: number) {
    this.products[index] = this.clonedProduct[product.id];
    delete this.clonedProduct[product.id];
  }

  getCollection(status: Collection): 'success' | 'secondary' | 'info' | 'warning' | 'danger' | undefined {
    switch (status) {
      case Collection.HOME_DECOR:
        return 'success';
      case Collection.ACCESSORIES:
        return 'secondary';
      case Collection.CLOTHING:
        return 'warning';
      case Collection.KITCHENWARE:
        return 'info';
      case Collection.FURNITURE:
        return 'danger';
      default:
        return undefined;
    }
  }

  getCategory(status: Category): 'success' | 'secondary' | 'info' | 'warning' | 'danger' | undefined {
    switch (status) {
      case Category.WALL_ART:
        return 'success';
      case Category.RUGS:
        return 'success';
      case Category.VASES:
        return 'success';
      case Category.CANDLES:
        return 'success';
      case Category.LANTERNS:
        return 'success';
      case Category.JEWELRY:
        return 'secondary';
      case Category.BAGS:
        return 'secondary';
      case Category.SCARVES:
        return 'secondary';
      case Category.BELTS:
        return 'secondary';
      case Category.HATS:
        return 'secondary';
      case Category.DRESSES:
        return 'warning';
      case Category.SHIRTS:
        return 'warning';
      case Category.PANTS:
        return 'warning';
      case Category.JACKETS:
        return 'warning';
      case Category.KAFTANS:
        return 'warning';
      case Category.PLATES:
        return 'info';
      case Category.BOWLS:
        return 'info';
      case Category.TEAPOTS:
        return 'info';
      case Category.CUTLERY:
        return 'info';
      case Category.GLASSWARE:
        return 'info';
      case Category.TABLES:
        return 'danger';
      case Category.CHAIRS:
        return 'danger';
      case Category.CABINETS:
        return 'danger';
      case Category.SOFAS:
        return 'danger';
      case Category.BEDS:
        return 'danger';
      default:
        return undefined;
    }
  }

  confirmDelete(product: Product): void {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete the Product ${product.name}?`,
      accept: () => {
        this.productService.getProductById(product.id!).subscribe({
          next: () => {
            this.loadProducts();
            this.showSuccess('Product deleted successfully');
          },
          error: () => this.showError('Failed to delete Product')
        });
      }
    });
  }

  productInfos(product: Product){
    this.router.navigate(['/dashboard/products/details/', product.id])
  }

  private showSuccess(message: string): void {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: message });
  }

  private showError(message: string): void {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: message });
  }
}
