import axios from "axios";
import ConcertViewClient from '../api/concertViewClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: [],
    [SEARCH_RESULTS_KEY]: [],
};

class ConcertViewScript extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['displayConcertsHTML', 'displaySingleConcertHTML', 'getHTMLForSingleConcertView', 'getHTMLForConcertsView', 'getUserEmail',
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

        if (selectedValue == 1) {
            html += '<br><label>Enter Concert Date<br><input type="date" id="concertDateId" /><br><input type="button" disabled value="Submit" id="submitDateButton" />'
            temp.innerHTML = "";
            temp.innerHTML = html;

            var dateSelected = document.querySelector("#concertDateId");
            var submitDateButton = document.querySelector("#submitDateButton");
            var date;

            dateSelected.addEventListener("change",  function() {
                date = dateSelected.value;
                submitDateButton.removeAttribute("disabled");
            });
            submitDateButton.addEventListener("click", () => this.submitDateView(date, selectedValue));
       } else {
           html += '<br><label for="submitViewButton"></label><input type ="button" value="Submit" id="submitViewButton" />';
           temp.innerHTML = "";
           temp.innerHTML = html;
           const submitView = document.querySelector("#submitViewButton");
           submitView.addEventListener("click", () => this.submitViewButton(selectedValue));
       }
    }

    async submitViewButton(selectedValue){
        const searchCriteria = this.getUserEmail();
        const results = await this.client.getAllConcerts(searchCriteria);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displayConcertsHTML(selectedValue);
    }

    async submitDateView(date, selectedValue){
        var emailKey = this.getUserEmail();
        var dateKey = date;
        const searchCriteria = [emailKey, dateKey];
        const results = await this.client.getConcert(emailKey, dateKey);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displaySingleConcertHTML(date, selectedValue);
    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
    */
    displayConcertsHTML(selectedValue) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsContainer = document.getElementById('searchResultsContainer');
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsContainer.classList.add('hidden');

        if (selectedValue == 0) {
            var viewType = "Clear";
            searchResultsDisplay.innerHTML = "";
            searchResultsContainer.classList.add('hidden');
        } else if (selectedValue == 2) {
            var viewType = "Date";
            searchResultsDisplay.innerHTML = "";
            searchResultsContainer.classList.remove('hidden');
        } else if (selectedValue == 3) {
            var viewType = "Band";
            searchResultsContainer.classList.remove('hidden');
            searchResultsDisplay.innertHTML = '';
            searchResults.sort((a,b) => {
                const band1 = a.bandName.toLowerCase(), band2 = b.bandName.toLowerCase();
                return band1 === band2 ? 0 : band1 < band2 ? -1 : 1;
            })
        } else if (selectedValue == 4) {
            var viewType = "Venue";
            searchResultsContainer.classList.remove('hidden');
            searchResultsDisplay.innertHTML == '';
            searchResults.sort((a,b) => {
            const venue1 = a.venue.toLowerCase(), venue2 = b.venue.toLowerCase();
                return venue1 === venue2 ? 0 : venue1 < venue2 ? -1 : 1;
            });
        }

       searchResultsDisplay.innerHTML = "";
       searchResultsDisplay.innerHTML += this.getHTMLForConcertsView(searchResults, viewType);
    }

    displaySingleConcertHTML(date, selectedValue) {
        if (selectedValue == 0) {
            var viewType = "Clear";
            searchResultsDisplay.innerHTML = "";
            searchResultsContainer.classList.add('hidden');
        }
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsContainer = document.getElementById('searchResultsContainer');
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsContainer.classList.remove('hidden');
        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML = this.getHTMLForSingleConcertView(searchResults, date, viewType);
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
    getHTMLForConcertsView(searchResults, viewType) {
        if (searchResults == null) {
            let html = '<h3>No Concerts found</h3>';
            return html;
        }

        if (viewType == "0") {
            let html = "";
            return html;
        }

        let html = '<h3>Concerts Attended sorted by ' + viewType + '</h3><br>';
        html+= '<br><table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>';

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

    getHTMLForSingleConcertView(searchResults, date, viewType) {
        if (searchResults == null) {
            let html = '<h3>No Concert found</h3>';
            return html;
        }

        if (viewType == "0") {
            let html = "";
            return html;
        }

//        let d = document.createElement("ol");
//        for (let i = 0; i < searchResults.songsPlayed.length; i++) {
//            let li = document.createElement("li");
//            li.textContent = searchResults.songsPlayed[i];
//            d.appendChild(li);
//        }

        let html = '<h3>Concert Attended on  ' + date + '</h3><br>';
        html+= '<br><table><tr><th>Date Attended</th><th>Band Name</th><th>Tour Name</th><th>Venue</th><th>Opening Act(s)</th><th>Set List</th><th>Memories</th></tr>';

        html += `
            <tr>
                <td>${searchResults.dateAttended}</td>
                <td>${searchResults.bandName}</td>
                <td>${searchResults.tourName}</td>
                <td>${searchResults.venue}</td>
                <td>${searchResults.openingActs}</td>
                <!-- <td class>document.getElementById("ol").appendChild(d)</td> -->
                <td>${searchResults.songsPlayed}</td>
                <td>${searchResults.memories}</td>
            </tr>`;

        html += '</table>';
        return html;
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
    const concertViewScript = new ConcertViewScript();
    concertViewScript.mount();
};

window.addEventListener('DOMContentLoaded', main);
