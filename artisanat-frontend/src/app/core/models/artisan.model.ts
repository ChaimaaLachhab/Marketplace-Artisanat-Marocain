import {User} from "./user.model";
import {Product} from "./product.model";
import {SubOrder} from "./sub-order.model";
import {Specialty} from "../enums/specialty.enum";
import {VerificationStatus} from "../enums/verification-status.enum";

export interface Artisan extends User {
  specialty: Specialty;
  location: string;
  experience: number;
  verificationStatus: VerificationStatus;
  products: Product[];
  subOrders: SubOrder[];
}
