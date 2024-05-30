import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call ConcertMemories web app
  *
*/
export default class ConcertMemoriesClient extends BindingClass {
    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded','getAllConcerts'];
        this.bindClassMethods(methodsToBind, this);
     //   this.authenticator = new Authenticator();
        this.props = props;
        this.axiosClient = axios;
        axios.defaults.baseURL = process.env.API_BASE_URL;

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
        alert("get all concerts");
        try {
            const token = await this.getTokenOrThrow("Encountered token error trying to call Concert endpoint.");
            const response = await this.axiosClient.get(`concerts`, {
            headers: {
                Authorization: `Bearer ${token}`
            }});
                return response.data.concerts;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
     }
 }
