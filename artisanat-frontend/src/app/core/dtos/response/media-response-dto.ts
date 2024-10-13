import {Type} from "../../enums/type.enum";
import {Product} from "../../models/product.model";
import {User} from "../../models/user.model";

export interface MediaResponseDto {
  id: number;
  mediaUrl: string;
  mediaId: string;
  type: Type;
  product: Product;
  user: User;
}
