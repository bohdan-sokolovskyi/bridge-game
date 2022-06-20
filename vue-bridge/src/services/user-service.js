export default class UserService {

    #http = null;

    constructor(http) {
        this.#http = http;
    }
}