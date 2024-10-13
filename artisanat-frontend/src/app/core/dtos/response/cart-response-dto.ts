import { CartItemResponseDto } from './cart-item-response-dto';

export interface CartResponseDto {
  id: number;
  cartItems: CartItemResponseDto[];
}
