import {Collection} from "../../enums/collection.enum";
import {Category} from "../../enums/category.enum";
import {Media} from "../../models/media.model";
import {Review} from "../../models/review.model";
import {Artisan} from "../../models/artisan.model";

export interface ProductResponseDto {
  id: number;
  name: string;
  description: string;
  rating: number;
  price: number;
  stock: number;
  createdAt: string;
  collection: Collection;
  category: Category;
  artisan: Artisan;
  media: Media[];
  reviews: Review[];
}
