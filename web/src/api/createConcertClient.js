import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web app
  *
*/
export default class CreateConcertClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'createConcert', 'getIdentity', 'getTokenOrThrow', 'handleError', 'login',
            'logout', 'verifyLogin',];
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
     * Creates single concert in the database by date.
     * @returns A single concert object
    */

   async createConcert(emailAddress, dateAttended, bandName, tourName, venue, openingActs, songsPlayed, memories) {
       try {
           const token = await this.getTokenOrThrow("Only authenticated users can create a concert");
           const response = await this.axiosClient.post(`createconcert`, {
               emailAddress: emailAddress,
               dateAttended: dateAttended,
               tourName: tourName,
               bandName: bandName,
               venue: venue,
               openingActs: openingActs,
               songsPlayed: songsPlayed,
               memories: memories
           }, {
               headers: {
                   Authorization: `Bearer ${token}`
                }
           });

           return await response.data.concert;
       } catch (error) {
           this.handleError(error)
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

     /**
         * Helper method to log the error and run any error functions.
         * @param error The error received from the server.
         */
     handleError(error) {
         console.error(error);

         const errorFromApi = error?.response?.data?.error_message;
         if (errorFromApi) {
             console.error(errorFromApi)
             error.message = errorFromApi;
         }
     }
}
