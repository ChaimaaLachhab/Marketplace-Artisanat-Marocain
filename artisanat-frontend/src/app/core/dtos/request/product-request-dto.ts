import {Category} from "../../enums/category.enum";
import {Collection} from "../../enums/collection.enum";

export interface ProductRequestDto {
  name: string;
  description: string;
  price: number;
  stock: number;
  category: Category;
  collection: Collection;
}
