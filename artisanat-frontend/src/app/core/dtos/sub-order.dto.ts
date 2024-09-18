import { ArtisanDTO } from './artisan.dto';
import { OrderDto } from './order.dto';
import { ProductDto } from './product.dto';

export interface SubOrderDto {
  subTotal: number;
  artisan: ArtisanDTO;
  order: OrderDto;
  products: ProductDto[];
}
