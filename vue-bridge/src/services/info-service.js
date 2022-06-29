import httpCommon from "@/http-common";

class InfoService {

    #httpCommon = null;

    constructor(httpCommonObj) {
        this.#httpCommon = httpCommonObj;
    }

    getUsers() {
        return this.#httpCommon.get('info/users');
    }

    getGames() {
        return this.#httpCommon.get('info/games');
    }
}

export default new InfoService(httpCommon) ;