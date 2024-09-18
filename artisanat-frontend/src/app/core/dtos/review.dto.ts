import { CustomerDTO } from './customer.dto';

export interface ReviewDto {
  rating: number;
  comment: string;
  reviewDate: string; // Use ISO string format
  customer: CustomerDTO;
}
