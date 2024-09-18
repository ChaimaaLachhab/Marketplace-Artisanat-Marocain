import {Product} from "./product.model";
import {Customer} from "./customer.model";


export interface Cart {
  id: number;
  products: Product[];
  customer: Customer;
}
