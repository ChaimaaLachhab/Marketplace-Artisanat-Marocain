import {Artisan} from "../../models/artisan.model";
import {SubOrderItemResponseDto} from "./sub-order-item-response-dto";

export interface SubOrderResponseDto {
  id: number;
  subTotal: number;
  artisan: Artisan;
  subOrderItems: SubOrderItemResponseDto[];
}
