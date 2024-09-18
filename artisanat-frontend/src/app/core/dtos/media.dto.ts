import { ProductDto } from './product.dto';
import { Type } from '../enums/type.enum';

export interface MediaDto {
  mediaUrl: string;
  type: Type;
  product: ProductDto;
}
