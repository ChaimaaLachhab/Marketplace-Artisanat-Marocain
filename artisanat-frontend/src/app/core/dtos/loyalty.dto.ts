import { CustomerDTO } from './customer.dto';

export interface LoyaltyDto {
  points: number;
  customer: CustomerDTO;
}
