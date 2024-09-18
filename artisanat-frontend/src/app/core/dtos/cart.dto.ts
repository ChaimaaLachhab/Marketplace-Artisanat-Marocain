import { ProductDto } from './product.dto';
import { CustomerDTO } from './customer.dto';

export interface CartDto {
  id: number;
  products: ProductDto[];
  customer: CustomerDTO;
}
