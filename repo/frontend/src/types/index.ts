export interface User {
  id: number;
  username: string;
  email: string;
  role: string;
  status: string;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  success: boolean;
  message: string;
  user?: User;
}
