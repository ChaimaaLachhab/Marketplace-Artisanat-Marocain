import {Type} from "../../enums/type.enum";

export interface MediaRequestDto {
  mediaUrl: string;
  mediaId: string;
  type: Type;
  productId?: number;
  userId?: number;
}
