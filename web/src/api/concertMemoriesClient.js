import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web application
*/

export default class ConcertMemoriesClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'getTokenOrThrow', 'handleError', 'login', 'logout', 'verifyLogin'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();
        this.props = props;
        this.clientLoaded();
    }

    /**
    * Run any functions that are supposed to be called once the client has loaded successfully.
    */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @returns The user information for the current user.
     */
    async getIdentity() {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();
            if (!isLoggedIn) {
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error)
        }
    }

    async verifyLogin() {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();
                return isLoggedIn;
        } catch (error) {
            this.handleError(error)
        }
    }

     async login() {
            this.authenticator.login();
        }

        async logout() {
            this.authenticator.logout();
        }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    handleError(error) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }
    }
}
