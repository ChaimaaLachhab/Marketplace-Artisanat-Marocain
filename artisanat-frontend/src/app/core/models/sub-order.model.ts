import {Artisan} from "./artisan.model";
import {Order} from "./order.model";
import {Product} from "./product.model";

export interface SubOrder {
  id: number;
  subTotal: number;
  artisan: Artisan;
  order: Order;
  products: Product[];
}
