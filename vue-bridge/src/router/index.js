import { createRouter, createWebHistory } from 'vue-router';

import HomeView from "@/views/HomeView";
import AboutView from "@/views/AboutView";
import LoginView from "@/views/LoginView";
import SignupView from "@/views/SignupView";
import ProfileView from "@/views/ProfileView";
import NotFoundView from "@/views/NotFoundView";
import UsersView from "@/views/UsersView";
import GamesView from "@/views/GamesView";

const routes = [
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
  {
    path: '/users',
    name: 'users-view',
    component: UsersView
  },
  {
    path: '/games',
    name: 'games-view',
    component: GamesView
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'not-found-view',
    component: NotFoundView
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
