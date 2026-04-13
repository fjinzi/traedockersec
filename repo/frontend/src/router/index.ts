import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'Login',
      component: () => import('@/views/LoginView.vue'),
    },
    {
      path: '/home',
      name: 'Home',
      component: () => import('@/views/HomeView.vue'),
    },
  ],
});

export default router;
