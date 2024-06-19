import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web app
  *
*/
export default class DeleteConcertClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'deleteConcert', 'getTokenOrThrow', 'handleError'];
        this.bindClassMethods(methodsToBind, this);
        this.authenticator = new Authenticator();
        this.axiosClient = axios;
        axios.defaults.baseURL = process.env.API_BASE_URL;
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
     * Deletes single concert in the database by date.
     * @returns A single concert object
    */

    async deleteConcert(dateAttended) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can delete a concert");
            const response = await this.axiosClient.delete(`deleteconcert/${dateAttended}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return await response.data.concert;
        } catch (error) {
            this.handleError(error)
        }
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
           console.error(errorFromApi);
           error.message = errorFromApi;
        }
    }
}