import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgClass, NgForOf, NgIf} from "@angular/common";
import {ProductResponseDto} from "../../../../../../core/dtos/response/product-response-dto";
import {ProductService} from "../../../../../../core/services/product.service";
import {CartService} from "../../../../../../core/services/cart.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-infos',
  standalone: true,
  imports: [
    NgForOf,
    NgClass,
    NgIf
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './product-infos.component.html',
  styleUrl: './product-infos.component.css'
})
export class ProductInfosComponent implements OnInit{
  @Input() productId!: number;
  product: ProductResponseDto | undefined;
  thumbnails: string[] = [];
  mainImageSrc: string = '';
  selectedThumbnail: string = '';

  constructor(private productService: ProductService, private cartService: CartService, private messageService: MessageService, private router:Router) { }

  ngOnInit() {

    this.productService.getProductById(this.productId).subscribe(
      (product) => {
        this.product = product;

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

  updateMainImage(src: string) {
    this.mainImageSrc = src;
    this.selectedThumbnail = src;
  }
}
