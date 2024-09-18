import { CustomerDTO } from './customer.dto';
import { Status } from '../enums/status.enum';

export interface OrderDto {
  quantity: number;
  orderDate: string;
  location: string;
  status: Status;
  totalAmount: number;
  customer: CustomerDTO;
}
