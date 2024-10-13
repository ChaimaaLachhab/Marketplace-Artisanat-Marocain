import {Product} from "./product.model";
import {Customer} from "./customer.model";
import {CartItem} from "./cartItem.model";


export interface Cart {
  id: number;
  cartItem: CartItem[];
  customer: Customer;
}
