import {Status} from "../../enums/status.enum";
import {Product} from "../../models/product.model";
import {Customer} from "../../models/customer.model";
import {SubOrderResponseDto} from "./sub-order-response-dto";

export interface OrderResponseDto {
  id: number;
  quantity: number;
  orderDate: string;
  location: string;
  status: string;
  totalAmount: number;
  customer: Customer;
  subOrders: SubOrderResponseDto[];
}
