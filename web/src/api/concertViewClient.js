import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web app
  *
*/
export default class ConcertViewClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getAllConcerts', 'getIdentity', 'verifyLogin', 'login', 'logout',
        'getTokenOrThrow'];
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

    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }

     /**
         * Gets all concerts in the database.
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns A list of concerts
         */
     async getAllConcerts(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Encountered token error trying to call Concert endpoint.");
            const response = await this.axiosClient.get(`concerts`, {
            headers: {
                Authorization: `Bearer ${token}`
            }});
            return response.data.allConcerts;
        } catch (error) {
           // alert("error");
            this.handleError(error, errorCallback)
        }
     }

     /**
          * Get the identity of the current user
          * @param errorCallback (Optional) A function to execute if the call fails.
          * @returns The user information for the current user.
          */
     async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();
            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
     }

     async verifyLogin(errorCallback) {
                 try {
                     const isLoggedIn = await this.authenticator.isUserLoggedIn();
                      return isLoggedIn;

                 } catch (error) {
                     this.handleError(error, errorCallback)
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


     /**
         * Helper method to log the error and run any error functions.
         * @param error The error received from the server.
         * @param errorCallback (Optional) A function to execute if the call fails.
         */
        handleError(error, errorCallback) {
            console.error(error);

            const errorFromApi = error?.response?.data?.error_message;
            if (errorFromApi) {
                console.error(errorFromApi)
                error.message = errorFromApi;
            }

            if (errorCallback) {
                errorCallback(error);
            }
        }
    }
