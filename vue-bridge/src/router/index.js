import { createRouter, createWebHistory } from 'vue-router';

import HomeView from "@/views/HomeView";
import AboutView from "@/views/AboutView";
import LoginView from "@/views/LoginView";
import SignupView from "@/views/SignupView";
import ProfileView from "@/views/ProfileView";
import DashboardView from "@/views/DashboardView";

const routes = [
    // FOR ALL USERS
  {
    path: '/',
    alias: '/home',
    name: 'home-view',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about-view',
    component: AboutView
  },
  {
    path: '/login',
    name: 'login-view',
    component: LoginView
  },
  {
    path: '/signup',
    name: 'signup-view',
    component: SignupView
  },
  {
    path: '/profile',
    name: 'profile-view',
    component: ProfileView
  },

    // FOR ADMINS
  {
    path: '/dashboard',
    name: 'dashboard-view',
    component: DashboardView
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
