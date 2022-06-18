import { createRouter, createWebHistory } from 'vue-router';
import HomeView from "@/views/HomeView";
import AboutView from "@/views/AboutView";
import LoginView from "@/views/LoginView";
import DashboardView from "@/views/DashboardView";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: DashboardView
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
