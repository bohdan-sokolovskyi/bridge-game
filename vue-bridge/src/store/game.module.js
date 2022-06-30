import gameService from "@/services/game-service";

const gameData = JSON.parse(localStorage.getItem('game'));
const initialState = gameData
    ? { status: { inGame: true }, gameData }
    : { status: { inGame: false}, gameData: null };

export const game = {
    namespaced: true,
    state: initialState,
    actions: {
        create({ commit }, gameData) {
            return gameService.create(gameData).then(
                gameData => {
                    commit('created', gameData);
                    return Promise.resolve(gameData);
                },

                error => {
                    commit('notCreated');
                    return Promise.reject(error);
                }
            );
        },

        finish({ commit }) {
            gameService.finish();
            commit('finish');
        }
    },

    mutations: {
        created(state, gameData) {
            state.status.inGame = true;
            state.gameData = gameData;
        },

        notCreated(state) {
            state.status.inGame = false;
            state.gameData = null;
        },

        finish(state) {
            state.status.inGame = false;
            state.gameData = null;
        }
    }
}