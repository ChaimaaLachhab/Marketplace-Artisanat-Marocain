import {Customer} from "./customer.model";
import {Product} from "./product.model";
import {Status} from "../enums/status.enum";

export interface Order {
  id: number;
  quantity: number;
  orderDate: string;
  location: string;
  status: Status;
  totalAmount: number;
  customer: Customer;
  products: Product[];
}
