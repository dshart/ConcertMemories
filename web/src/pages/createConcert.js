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
        this.bindClassMethods(['convertToList', 'getIdentity', 'mount', 'startupActivities', 'submitForm'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new CreateConcertClient();
        this.startupActivities();
        //const email = await this.getUserEmail();
    }

    async startupActivities() {
        let concertForm = document.getElementById('concertFormId');
        concertForm.addEventListener("submit", (e) => {
            e.preventDefault();
            this.submitForm();
        });

        //only allow Enter button to appear if date is selected as it is range key
        var submitConcertFormButton = document.getElementById("submitConcertButtonId");
        var concertDate = document.getElementById("concertDateTextBox");
        concertDate.addEventListener("change",  function() {
            submitConcertFormButton.style.display = "block"
            //set up listener for pressing Enter key only after date is entered
             window.addEventListener('keydown', (event) => {

                 if (event.key == 'Enter') {
                    submitConcertFormButton.style.display = "none";
                    concertForm.submit();
                 }
            });

           submitConcertFormButton.addEventListener("click", function() {
               concertForm.submit();
           });
        });
    }

    convertToList(stringToConvert) {
         var arr = [];
         if (stringToConvert != null) {
             arr = stringToConvert.split(",");
         }
         return arr;
     }

     async submitForm() {
        alert("in submit form");
        //what about using the form.submit pattern?  Is it easier
        var oa = document.getElementById('openingActs').value;
        var sp = document.getElementById('songsPlayed').value;
        var m = document.getElementById('memories').value;
        var oaList = this.convertToList(oa);
        var spList = this.convertToList(sp);
        var mList = this.convertToList(m);


        const{email, name} = await this.client.getIdentity().then(result => result);
        await this.client.createConcert(
            email,
            document.getElementById('concertDateTextBox').value,
            document.getElementById('bandName').value,
            document.getElementById('tourName').value,
            document.getElementById('venue').value,
            oaList,
            spList,
            mList
        ).then(results => {
             const searchCriteria = this.taskDataStore.get(TASK_SEARCH_CRITERIA_KEY);
             var searchResults = this.taskDataStore.get(TASK_SEARCH_RESULTS_KEY);
             searchResults.push(results);
             this.taskDataStore.setState({
                [TASK_SEARCH_CRITERIA_KEY]: searchCriteria,
                [TASK_SEARCH_RESULTS_KEY]: searchResults,
             });
             return results;
        }).catch(e => {
            console.log(e);
        });
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


