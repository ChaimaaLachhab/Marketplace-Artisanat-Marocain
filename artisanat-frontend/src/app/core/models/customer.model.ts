import {User} from "./user.model";
import {Review} from "./review.model";
import {Order} from "./order.model";
import {Loyalty} from "./loyalty.model";
import {Cart} from "./cart.model";


export interface Customer extends User {
  reviews: Review[];
  orders: Order[];
  loyalty: Loyalty;
  cart: Cart;
}
