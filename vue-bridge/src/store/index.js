import { createStore } from 'vuex';
import {auth} from "@/store/auth.module";
import {game} from "@/store/game.module";

export default createStore({
  modules: {
    auth,
    game
  }
});
