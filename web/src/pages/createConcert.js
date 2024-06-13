import CreateConcertClient from '../api/createConcertClient';
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
/**
 * Logic needed for the enterConcertInfo page of the website.
 */
class CreateConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['convertToList', 'getIdentity', 'getUserEmail', 'mount', 'startupActivities', 'submitForm'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new CreateConcertClient();
        this.startupActivities();
       // this.email = this.getUserEmail();
    }

    async startupActivities() {
        //var concertEntryForm = document.querySelector("#entryForm");
       // concertEntryForm.addEventListener('submit', () => this.submitForm());
        document.getElementById('submitConcertButton').addEventListener('click', this.submitForm);
    }

    /* Uses the client to obtain the Users email and Name;
         * @returns User Email
        */
     async getUserEmail() {
        const { email, name } = await this.client.getIdentity().then(result => result);
        //alert(email);
        return email;
     }

     convertToList(stringToConvert) {
         var arr = [];
         if (stringToConvert != null) {
             arr = stringToConvert.split(",");
         }
         return arr;
     }

    async submitForm() {
      //  alert("submitting");
        var oa = document.getElementById('openingActs').value;
        var sp = document.getElementById('songsPlayed').value;
        var m = document.getElementById('memories').value;

        var oaList = this.convertToList(oa);
        var spList = this.convertToList(sp);
        var mList = this.convertToList(m);
        var email = await this.getUserEmail();

      //  alert("before create concert");
        await this.client.createConcert(
            email,
            document.getElementById('concertDate').value,
            document.getElementById('bandName').value,
            document.getElementById('tourName').value,
            document.getElementById('venue').value,
            oaList,
            spList,
            mList
        ).then(results => {
             //alert("in response: " + response);
             const searchCriteria = this.taskDataStore.get(TASK_SEARCH_CRITERIA_KEY);
             var searchResults = this.taskDataStore.get(TASK_SEARCH_RESULTS_KEY);
             searchResults.push(results);

                                    //console.log(searchResults)
             this.taskDataStore.setState({
             [TASK_SEARCH_CRITERIA_KEY]: searchCriteria,
             [TASK_SEARCH_RESULTS_KEY]: searchResults,
        });
                                    //console.log(response);
           // alert("returning results");
            return results;
        }).catch(e => {
           // alert("Error, Will Robinson!");
            console.log(e);
        });

       // alert("Form submitted success!");
        window.location.href = "concertsAndBands.html";

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
    const createConcert = new CreateConcert();
    createConcert.mount();
};

window.addEventListener('DOMContentLoaded', main);


