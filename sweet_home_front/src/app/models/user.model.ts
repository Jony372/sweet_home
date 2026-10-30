export interface UserModel {
  username: string;
  name: string;
  lastName: string;
  email?: string;
  phone?: string;
  isAdmin: boolean;
  createdAt: Date;
  updatedAt: Date;
}
