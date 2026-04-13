import axios from 'axios';
import type { LoginRequest, LoginResponse } from '@/types';

const api = axios.create({
  baseURL: '/api',
  timeout: 5000,
});

export const authApi = {
  login: async (data: LoginRequest): Promise<LoginResponse> => {
    const response = await api.post<LoginResponse>('/auth/login', data);
    return response.data;
  },
};
