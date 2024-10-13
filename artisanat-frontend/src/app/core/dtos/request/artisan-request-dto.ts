import {Specialty} from "../../enums/specialty.enum";

export interface ArtisanRequestDto {
  fullName: string;
  username: string;
  password?: string;
  email: string;
  phone: string;
  specialty: Specialty;
  location: string;
  experience: number;
}
