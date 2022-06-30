import { createRouter, createWebHistory } from 'vue-router';

import HomeView from "@/views/HomeView";
import AboutView from "@/views/AboutView";
import LoginView from "@/views/LoginView";
import SignupView from "@/views/SignupView";
import ProfileView from "@/views/ProfileView";
import NotFoundView from "@/views/NotFoundView";
import UsersView from "@/views/UsersView";
import GamesView from "@/views/GamesView";
import NewGameView from "@/views/NewGameView";
import GameConnectView from "@/views/GameConnectView";
import PlayView from "@/views/PlayView";

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
    path: '/new_game',
    name: 'new-game-view',
    component: NewGameView
  },
  {
    path: '/game_connect',
    name: 'game-connect-view',
    component: GameConnectView
  },
  {
    path: '/games',
    name: 'games-view',
    component: GamesView
  },
  {
    path: '/play',
    name: 'play-view',
    component: PlayView
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
