import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web app
  *
*/
export default class GetConcertClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getConcert', 'getAllConcerts', 'getTokenOrThrow', 'handleError'];
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
     * Gets single concert in the database by date.
     * @returns A single concert object
    */

    async getConcert(dateAttended) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get a concert");
            const response = await this.axiosClient.get(`concerts/${dateAttended}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }

            });

            return response.data.concert;
        } catch (error) {
            this.handleError(error)
        }
    }

     /**
      * Gets all concerts in the database.
     * @returns A list of concerts
     */
     async getAllConcerts() {
         try {
             const token = await this.getTokenOrThrow("Encountered token error trying to call Concert endpoint.");
             const response = await this.axiosClient.get(`concerts`, {
                 headers: {
                     Authorization: `Bearer ${token}`
                 }
             });

                return response.data.allConcerts;
             } catch (error) {
                  this.handleError(error)
             //let arr = Object.keys(response.data.items).map(key=> response.data.items[key]);
            //return arr;
            // return (response => {

            // });
             //return response.data;
         //} catch (error) {
         //    this.handleError(error)
         }
     }

     /**
      * Gets all concerts in the database for a specific band.
      * @returns A list of concerts seen of a specific band
     */

     async getAllConcertsByBand(bandName) {
         try {
             const token = await this.getTokenOrThrow("Only authenticated users can get a concert");
             const response = await this.axiosClient.get(`concertsbyband/${bandName}`, {
                 headers: {
                     Authorization: `Bearer ${token}`
                 }
             });

             return response.data.allConcertsByBand;
         } catch (error) {
             this.handleError(error)
         }
     }

     /**
         * Gets all concerts in the database for a specific venue.
         * @returns A list of concerts seen at a specific venue
     */

     async getAllConcertsByVenue(venue) {

         try {
             const token = await this.getTokenOrThrow("Only authenticated users can get a concert");
             const response = await this.axiosClient.get(`concertsbyvenue/${venue}`, {
                 headers: {
                      Authorization: `Bearer ${token}`
                 }
             });

             return response.data.allConcertsByVenue;
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
