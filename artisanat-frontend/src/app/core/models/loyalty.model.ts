import {Customer} from "./customer.model";

export interface Loyalty {
  id: number;
  points: number;
  customer: Customer;
}
