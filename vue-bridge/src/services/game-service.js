import httpCommon from "@/http-common";

class GameService {
    #httpCommon = null;

    constructor(httpCommon) {
        this.#httpCommon = httpCommon;
    }

    create(gameData) {
        return this.#httpCommon.post('game/create', {
            email: gameData.email,
            gameName: gameData.gameName,

        }).then(response => {
            if(response.data.gameId) {
                localStorage.setItem('game', JSON.stringify(response.data));
            }

            return response.data;
        });
    }

    finish() {
        localStorage.removeItem('game');
    }
}

export default new GameService(httpCommon);