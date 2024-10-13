import {Customer} from "../../models/customer.model";
import {Product} from "../../models/product.model";

export interface ReviewResponseDto {
  id: number;
  rating: number;
  comment: string;
  reviewDate: string;
  customer: Customer;
  product: Product;
}
