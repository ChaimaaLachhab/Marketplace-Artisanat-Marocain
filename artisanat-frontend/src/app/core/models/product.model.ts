import {Artisan} from "./artisan.model";
import {Cart} from "./cart.model";
import {Media} from "./media.model";
import {Review} from "./review.model";
import {Order} from "./order.model";
import {Collection} from "../enums/collection.enum";
import {Category} from "../enums/category.enum";
import {CartItem} from "./cartItem.model";


export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stock: number;
  createdAt: string;
  rating: number;
  collection: Collection;
  category: Category;
  artisan: Artisan;
  cartItem: CartItem;
  media: Media[];
  reviews: Review[];
  orders: Order[];
}
