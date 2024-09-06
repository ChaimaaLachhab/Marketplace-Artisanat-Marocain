export abstract class User {
  id: number;
  username: string;
  password?: string;
  email: string;
  role: string;

  constructor(id: number, username: string, email: string, role: string, password?: string) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.role = role;
    this.password = password;
  }
}
