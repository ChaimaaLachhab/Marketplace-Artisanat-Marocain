import {Product} from "../../models/product.model";

export interface CartItemResponseDto {
  id: number;
  quantity: number;
  product: Product;
}
