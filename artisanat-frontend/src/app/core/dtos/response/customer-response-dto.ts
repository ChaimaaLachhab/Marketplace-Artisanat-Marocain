import {Loyalty} from "../../models/loyalty.model";
import {Cart} from "../../models/cart.model";
import {UserDTO} from "../user.dto";
import {Order} from "../../models/order.model";

export interface CustomerResponseDto extends UserDTO{
  orders: Order[];
  loyalty: Loyalty;
  cart: Cart;
}
