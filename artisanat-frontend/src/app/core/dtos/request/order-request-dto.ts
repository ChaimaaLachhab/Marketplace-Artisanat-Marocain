import {Status} from "../../enums/status.enum";

export interface OrderRequestDto {
  quantity: number;
  orderDate: string;
  location: string;
  status: Status;
  productIds: number[];
}
