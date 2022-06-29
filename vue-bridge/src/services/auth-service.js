import httpCommon from "@/http-common";

class AuthService {

    #httpCommon = null;

    constructor(httpCommon) {
        this.#httpCommon = httpCommon;
    }

    login(user) {
        return this.#httpCommon.post('auth/login', {
            email: user.email,
            password: user.password,

        }).then(response => {
            if(response.data.accessToken) {
                localStorage.setItem('user', JSON.stringify(response.data));
            }

            return response.data;
        });
    }

    logout() {
        localStorage.removeItem('user');
    }

    register(user) {
        return this.#httpCommon.post('auth/signup', {
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            sex: user.sex,
            birth: user.birth,
            password: user.password
        });
    }

    isLogin() {
        return localStorage.getItem('user') != null;
    }
}

export default new AuthService(httpCommon);