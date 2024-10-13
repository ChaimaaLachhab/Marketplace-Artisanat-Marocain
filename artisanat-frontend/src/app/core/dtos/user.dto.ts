import {Media} from "../models/media.model";

export interface UserDTO {
  id: number
  fullName: string;
  username: string;
  password: string;
  email: string;
  phone: string;
  userPhoto: Media
}
