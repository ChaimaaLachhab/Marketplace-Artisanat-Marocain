import {Media} from "./media.model";

export interface User {
  id: number;
  fullName: string;
  username: string;
  email: string;
  phone: string;
  userPhoto: Media;
}
