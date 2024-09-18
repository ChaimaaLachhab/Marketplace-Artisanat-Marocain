import {Product} from "./product.model";
import {User} from "./user.model";
import {Type} from "../enums/type.enum";

export interface Media {
  id: number;
  mediaUrl: string;
  mediaId: string;
  type: Type;
  product?: Product;
  user?: User;
}
