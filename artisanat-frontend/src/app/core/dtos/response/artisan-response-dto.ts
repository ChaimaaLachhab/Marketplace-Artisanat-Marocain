import {Product} from "../../models/product.model";
import {Specialty} from "../../enums/specialty.enum";
import {VerificationStatus} from "../../enums/verification-status.enum";
import {UserDTO} from "../user.dto";

export interface ArtisanResponseDto extends UserDTO{
  specialty: Specialty;
  location: string;
  experience: number;
  verificationStatus: VerificationStatus;
  products: Product[];
}
