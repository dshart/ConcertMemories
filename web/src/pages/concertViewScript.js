import axios from "axios";
import ConcertViewClient from '../api/concertViewClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

class ConcertViewScript extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['displayConcertsHTML', 'getHTMLForConcertsView', 'getUserEmail',
        'mount', 'dropDownChange', 'startupActivities', 'submitViewButton', 'submitDateView'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.authenticator = new Authenticator();
        this.axiosClient = axios;
        axios.defaults.baseURL = process.env.API_BASE_URL;
    }

   /**
    * Add the header to the page and load the ConcertMemoriesClient.
    */
    mount() {
        this.client = new ConcertViewClient();
        this.header.addHeaderToPage();
        this.email = this.getUserEmail();
        this.startupActivities();
    }

    async startupActivities() {
        const concertView = document.querySelector('#concertViewOptions');
        concertView.addEventListener("change", this.dropDownChange);
    }

    async dropDownChange() {
        var dropDown = document.getElementById('concertViewOptions');
        var selectedValue = dropDown.options[dropDown.selectedIndex].value;
        var temp = document.getElementById("submitConcertArea");
        var html = "";

        if (selectedValue == 0) {
            html += '<br><label>Enter Concert Date<br><input type="date" id="concertDateId" /><br><input type="button" disabled value="Submit" id="submitDateButton" />'
            temp.innerHTML = "";
            temp.innerHTML = html;

            var dateSelected = document.querySelector("#concertDateId");
            var submitDateButton = document.querySelector("#submitDateButton");

            dateSelected.addEventListener("change",  function() {
                //var date = dateSelected.value;
                submitDateButton.removeAttribute("disabled");
            });
            submitDateButton.addEventListener("click", () => this.submitDateView(dateSelected));

       }
       else {
            html += '<br><label for="submitViewButton"></label><input type ="button" value="Submit" id="submitViewButton" />';
           temp.innerHTML = "";
           temp.innerHTML = html;
           const submitView = document.querySelector("#submitViewButton");
           submitView.addEventListener("click", () => this.submitViewButton(selectedValue));
       }
    }

    async submitViewButton(selectedValue){
        alert("submit view button clicked");
        const searchCriteria = this.getUserEmail();
        const results = await this.client.getAllConcerts(searchCriteria);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displayConcertsHTML(selectedValue);
    }

    submitDateView(selectedDate){
            // alert("selectedValue " + selectedValue);
            //const searchCriteria = this.getUserEmail();
            //if (searchCriteria) {
            //const results = await this.client.getAllConcerts(searchCriteria);
           // }

           // this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
           // this.dataStore.set([SEARCH_RESULTS_KEY], results);
            //this.dataStore.setState({
            //    [SEARCH_CRITERIA_KEY]: searchCriteria,
            //     [SEARCH_RESULTS_KEY]: results
            //});
            //} else {
                //alert("datastore empty");
            //    this.dataStore.setState(EMPTY_DATASTORE_STATE);

            //need to sort for band and venue as well as add var for each


            //        else if (selectedValue == 6) {//all concerts sorted by band
            //

                        //html+= '<table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>'
                        //                    '<tr>Set List</tr><tr>Memories/tr>';
              //          return;
               //     }


           //this.displayConcertsHTML(selectedValue);
        }
          // this.displayConcertsHTML();
      // this.displayConcertsHTML();

        /**
            * Pulls search results from the datastore and displays them on the html page.
            */
          //displaySearchResults();

    displayConcertsHTML(selectedValue) {
       const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
       const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
       const searchResultsContainer = document.getElementById('searchResultsContainer');
       const searchCriteriaDisplay = document.getElementById('searchCriteriaDisplay');
       const searchResultsDisplay = document.getElementById('searchResultsDisplay');

       if (searchCriteria == '') {
           searchResultsContainer.classList.add('hidden');
           searchCriteriaDisplay.innerHTML = '';
           searchResultsDisplay.innerHTML = '';
       } else {
            if (selectedValue == 1) {
                var view = "Date";
            } else if (selectedValue == 2) {
                var view = "Band";
                searchResults.sort((a,b) => {
                const band1 = a.bandName.toLowerCase(), band2 = b.bandName.toLowerCase();
                    return band1 === band2 ? 0 : band1 < band2 ? -1 : 1;
                })
            } else if (selectedValue == 3) {
                var view = "Venue";
                searchResults.sort((a,b) => {
                    const venue1 = a.venue.toLowerCase(), venue2 = b.venue.toLowerCase();
                    return venue1 === venue2 ? 0 : venue1 < venue2 ? -1 : 1;
                });
            }

            searchResultsContainer.classList.remove('hidden');
            searchResultsDisplay.innerHTML = this.getHTMLForConcertsView(searchResults, view);
       }
    }

    /**
     * Uses the client to obtain the Users email and Name;
     * @returns User Email
    */

    async getUserEmail() {
        const { email, name } = await this.client.getIdentity().then(result => result);
        return email;
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

   getHTMLForConcertsView(searchResults, view) {

       if (Object.keys(searchResults).length === 0) {
           return '<h4>No concerts found</h4>';
       }

       let html = '<h3>Concerts Attended sorted by ${view}</h3><br><br>';
       html+= '<table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>';

       for (const res of searchResults) {
           html += `
               <tr>
                   <td>${res.dateAttended}</td>
                   <td>${res.bandName}</td>
                   <td>${res.tourName}</td>
                   <td>${res.venue}</td>
                   <td>${res.openingActs}</td>
               </tr>`;
           }

           html += '</table>';
               return html;
       }
   }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const concertViewScript = new ConcertViewScript();
    concertViewScript.mount();
};

window.addEventListener('DOMContentLoaded', main);
