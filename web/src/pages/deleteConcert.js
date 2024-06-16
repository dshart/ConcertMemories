import DeleteConcertClient from '../api/deleteConcertClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: ''
};

class DeleteConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['deleteConcert', 'getIdentity', 'mount'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

   /**
    * Add the header to the page and load the GetConcertClient.
    */
    mount() {

        this.header.addHeaderToPage();
        this.client = new DeleteConcertClient();
        this.startupActivities();
    }

    async startupActivities() {
        const{email, name} = await this.client.getIdentity().then(result => result);
        //const concertView = document.querySelector('#concertDateToDelete');
        var concertView = document.getElementById('concertDateToDelete');
       // var dateSelected = document.querySelector("#deleteConcertDateId");
        var dateSelected = document.getElementById('deleteConcertDateId');
        //var submitDateToDeleteButton = document.querySelector("#submitDateToDeleteButton");
        var submitDateToDeleteButton = document.getElementById('submitDateToDeleteButton');
        var date;

        dateSelected.addEventListener("change",  function() {
            date = dateSelected.value;
            submitDateToDeleteButton.removeAttribute("hidden");
        });
        submitDateToDeleteButton.addEventListener("click", () => this.deleteConcert(date, dateSelected, submitDateToDeleteButton));
    }

    async deleteConcert(dateAttended, dateSelected, DateToDeleteButton) {
        var emailKey = email;
        var dateKey = dateAttended
        const searchCriteria = [emailKey, dateKey];
        const results = await this.client.deleteConcert(emailKey, dateKey);
        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);

        dateSelected.value = "";
        dateSelected.focus();

    }


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

}

/**
* Main method to run when the page contents have loaded.
*/
const main = async () => {
    const deleteConcert = new DeleteConcert();
        deleteConcert.mount();
};

window.addEventListener('DOMContentLoaded', main);