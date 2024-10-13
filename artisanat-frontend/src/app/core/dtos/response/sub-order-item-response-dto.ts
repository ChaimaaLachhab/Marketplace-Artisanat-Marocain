import {Artisan} from "../../models/artisan.model";
import {ProductResponseDto} from "./product-response-dto";

export interface SubOrderItemResponseDto {
  id: number;
  quantity: number;
  product: ProductResponseDto;
}
