import {Customer} from "./customer.model";

export interface Review {
  id: number;
  rating: number;
  comment: string;
  reviewDate: string;
  customer: Customer;
}
