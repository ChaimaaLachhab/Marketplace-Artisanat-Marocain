import { ArtisanDTO } from './artisan.dto';
import { Collection } from '../enums/collection.enum';
import { Category } from '../enums/category.enum';

export interface ProductDto {
  name: string;
  description: string;
  price: number;
  stock: number;
  createdAt: string; // Use ISO string format
  collection: Collection;
  category: Category;
  artisan: ArtisanDTO;
}
