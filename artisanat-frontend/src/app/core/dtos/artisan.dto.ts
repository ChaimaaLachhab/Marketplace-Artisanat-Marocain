import { Specialty } from '../enums/specialty.enum';
import {UserDTO} from "./user.dto";

export interface ArtisanDTO extends UserDTO {
  specialty: Specialty;
  location: string;
  experience: number;
}
