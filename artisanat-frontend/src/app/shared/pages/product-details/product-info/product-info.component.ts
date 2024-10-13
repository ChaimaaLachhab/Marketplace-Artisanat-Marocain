import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {ProductService} from "../../../../core/services/product.service";
import {Product} from "../../../../core/models/product.model";
import {RatingModule} from "primeng/rating";
import {FormsModule} from "@angular/forms";
import {MatIcon} from "@angular/material/icon";
import {Router, RouterLink} from "@angular/router";
import {CartService} from "../../../../core/services/cart.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {ProductResponseDto} from "../../../../core/dtos/response/product-response-dto";

@Component({
  selector: 'app-product-info',
  standalone: true,
  imports: [
    NgForOf,
    NgClass,
    RatingModule,
    FormsModule,
    MatIcon,
    RouterLink,
    NgIf
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './product-info.component.html',
  styleUrl: './product-info.component.css'
})
export class ProductInfoComponent implements OnInit{
  @Input() productId!: number;
  quantity: number = 1;
  quantityChange = new EventEmitter<number>();
  product: ProductResponseDto | undefined;
  thumbnails: string[] = [];
  mainImageSrc: string = '';
  selectedThumbnail: string = '';
  rating: number | undefined;

  constructor(private productService: ProductService, private cartService: CartService, private messageService: MessageService, private router:Router) { }

  checkout(productId: number | undefined){
    this.addToCart(productId);
    this.router.navigate(['/checkout'])
  }

  addToCart(productId: number | undefined) {
    this.cartService.addProductToCart(productId!, this.quantity).subscribe(
      (response) => {
        console.log(response);
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });
      },
      (error) => {
        console.error(error);
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Message Content' });
      }
    );
  }

  ngOnInit() {
    this.productService.getProductById(this.productId).subscribe(
      (product) => {
        this.product = product;
        this.rating = product?.rating;
        console.log(product);

        if (this.product?.media?.length) {
          this.thumbnails = this.product.media.map(media => media.mediaUrl);
          this.mainImageSrc = this.thumbnails[0];
          this.selectedThumbnail = this.thumbnails[0];
        }

        if (!this.product?.artisan?.userPhoto) {
          console.warn('Artisan or user photo is missing');
        }
      }
    );
  }

  increase(): void {
    this.quantity++;
    this.quantityChange.emit(this.quantity);
  }

  decrease(): void {
    if (this.quantity > 1) {
      this.quantity--;
      this.quantityChange.emit(this.quantity);
    }
  }

  updateMainImage(src: string) {
    this.mainImageSrc = src;
    this.selectedThumbnail = src;
  }
}
