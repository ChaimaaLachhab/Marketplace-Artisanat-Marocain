import {Product} from "./product.model";
import {Customer} from "./customer.model";
import {Cart} from "./cart.model";


export interface CartItem {
  id: number;
  cart: Cart;
  products: Product;
  quantity: number;
}
