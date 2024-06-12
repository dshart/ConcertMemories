import GetConcertClient from '../api/getConcertClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    //[SEARCH_CRITERIA_KEY]: [],
    //[SEARCH_RESULTS_KEY]: [],
    [SEARCH_CRITERIA_KEY]: '',
        [SEARCH_RESULTS_KEY]: ''
};

class GetConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['displayAllConcertsHTML', 'displayAllConcertsByBandHTML', 'displaySingleConcertHTML',
        'dropDownChange', 'getHTMLForAllConcertsView', 'getHTMLForAllConcertsByBandView', 'getHTMLForSingleConcertView',
        'getUserEmail', 'mount', 'startupActivities', 'submitBandViewButton', 'submitDateViewButton',
        'submitViewButton', ], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

   /**
    * Add the header to the page and load the GetConcertClient.
    */
    mount() {

        this.header.addHeaderToPage();
        this.client = new GetConcertClient();
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
        var concertDisplayArea = document.getElementById("submitConcertArea");

        if (selectedValue == 1) {
            var html = "";
            html += '<br><label>Enter Concert Date<br><input type="date" id="concertDateId" /><br><input type="button" hidden value="Submit" id="submitDateButton" />'
            concertDisplayArea.innerHTML = "";
            concertDisplayArea.innerHTML = html;

            var dateSelected = document.querySelector("#concertDateId");
            var submitDateButton = document.querySelector("#submitDateButton");
            var date;

            dateSelected.addEventListener("change",  function() {
                date = dateSelected.value;
                submitDateButton.removeAttribute("hidden");
            });
            submitDateButton.addEventListener("click", () => this.submitDateViewButton(date, selectedValue));
        } else if (selectedValue == 5) {
            var html = "";
            html += '<br><label>Enter Band Name<br><input type="text" autocomplete="off", id="bandNameInput" /><br><input type="button" hidden value="Submit" id="submitBandNameButton" />'
            concertDisplayArea.innerHTML = "";
            concertDisplayArea.innerHTML = html;

            var bandName = "";
            var bandNameInput = document.querySelector("#bandNameInput");
            bandNameInput.value == "";
            bandNameInput.focus();
            var submitBandButton = document.querySelector("#submitBandNameButton");

            bandNameInput.addEventListener("keyup", function() {
                bandName = bandNameInput.value;
                submitBandNameButton.removeAttribute("hidden");
            });

            submitBandNameButton.addEventListener("click", () => this.submitBandViewButton(bandName));
            bandNameInput.addEventListener('keydown', (event) => {
                submitBandNameButton.disabled = false;
                if (event.key == 'Enter') {
                    this.submitBandViewButton(bandName)
                    bandNameInput.value = "";
                    bandNameInput.focus();
                    submitBandNameButton.disabled = true;
                }
            });
        } else {
            var html = "";
            html += '<br><label for="submitViewButton"></label><input type ="button" value="Submit" id="submitViewButton" />';
            concertDisplayArea.innerHTML = html;
            var submitViewButton = document.querySelector("#submitViewButton");
            submitViewButton.addEventListener("click", () => this.submitViewButton(selectedValue));
        }
    }

    async submitViewButton(selectedValue){
        var submitViewButton = document.querySelector("#submitViewButton");
        submitViewButton.classList.add("hidden");

        if (selectedValue == 0) {
            searchResultsDisplay.innerHTML = "";
            searchResultsDisplay.innerHTML = "";
        } else {
            var searchCriteria = this.getUserEmail();
            const results = await this.client.getAllConcerts(searchCriteria);

            this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
            this.dataStore.set([SEARCH_RESULTS_KEY], results);
            this.displayAllConcertsHTML(selectedValue, submitViewButton);
        }
    }

    async submitBandViewButton(bandName){
        var submitBandNameButton = document.querySelector("#submitBandNameButton");
        submitBandNameButton.add
        var emailKey = this.getUserEmail();
        var bandKey = bandName;
        const searchCriteria = [emailKey, bandKey];
        const results = await this.client.getAllConcertsByBand(emailKey, bandKey);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displayAllConcertsByBandHTML(bandName, submitBandNameButton);
    }

    async submitDateViewButton(date, selectedValue){
        var submitDateButton = document.querySelector("#submitDateButton");
        submitDateButton.classList.add("hidden");

        var emailKey = this.getUserEmail();
        var dateKey = date;
        const searchCriteria = [emailKey, dateKey];
        const results = await this.client.getConcert(emailKey, dateKey);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displaySingleConcertHTML(date, selectedValue, submitDateButton);
    }

   /**
     * Pulls search results from the datastore and displays them on the html page.
    */

    //All concerts sorted by either date, band, or venue
    displayAllConcertsHTML(selectedValue, submitViewButton) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        //const searchResultsContainer = document.getElementById('searchResultsContainer');
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        if (selectedValue == 2) {
            var viewType = "Date";
            searchResultsDisplay.innerHTML = "";
        } else if (selectedValue == 3) {
            var viewType = "Band";
            searchResultsDisplay.innerHTML = '';
            searchResults.sort((a,b) => {
                const band1 = a.bandName.toLowerCase(), band2 = b.bandName.toLowerCase();
                return band1 === band2 ? 0 : band1 < band2 ? -1 : 1;
            })
        } else if (selectedValue == 4) {
            var viewType = "Venue";
           searchResultsDisplay.innerHTML == '';
            searchResults.sort((a,b) => {
            const venue1 = a.venue.toLowerCase(), venue2 = b.venue.toLowerCase();
                return venue1 === venue2 ? 0 : venue1 < venue2 ? -1 : 1;
            });
        }

        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML += this.getHTMLForAllConcertsView(searchResults, viewType);
        submitViewButton.classList.add('hidden');
    }

    //All Concerts by band
    displayAllConcertsByBandHTML(bandName, submitBandNameButton) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsDisplay.innerHTML = this.getHTMLForAllConcertsByBandView(searchResults, bandName);
        submitBandNameButton.classList.add(hidden);
    }


    //Single Concert by Date
    displaySingleConcertHTML(date, submitDateViewButton) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML = this.getHTMLForSingleConcertView(searchResults, date);
        submitDateViewButton.classList.add('hidden');
    }


    /**
     * Uses the client to obtain the Users email and Name;
     * @returns User Email
    */
    async getUserEmail() {
        const { email, name } = await this.client.getIdentity().then(result => result);
        return email;
    }

    getHTMLForAllConcertsView(searchResults, viewType) {
        if (searchResults.length == 0) {
            let html = '<h3>No Concerts found</h3>';
            return html;
        }

//        if (searchResults.dateAttended == null)
//            searchResults.dateAttended = "";
//
//        if (!searchResults.hasOwnProperty("bandName")
//
//            searchResults.bandName = "";
//
//          if (searchResults.openingActs == null) {
//                searchResults.openingActs = [];
//          }
          //alert("Opening Acts " + searchResults.openingActs);
//          var openingActsLength = Object.keys(searchResults.openingActs).length;
//          if (openingActsLength  > 1) {
//            alert("greater");
//            var openingActsList = '<ol>';
//            for (let openingAct of searchResults.openingActs) {
//                openingActsList += '<li>' + openingAct + '</li>'
//            }
//                openingActsList += '</ol>'
//                searchResults.openingActs = openingActsList;
//        }

                let html = '<h3>Concerts Attended sorted by ' + viewType + '</h3><br>';
        html+= '<br><table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>';

        for (const res of searchResults) {
            //if (!Object.prototype.hasOwnProperty.call(searchResults, "bandName"));
           // if (!openingAc in res) {
                //searchResults.defineProperty("bandName:);
           //     res[bandName] = "";
           // }
            //some of these fields may not be filled in DDB so won't exist here and can't add to table what doesn't exits
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

     getHTMLForAllConcertsByBandView(searchResults, bandName) {

         if (searchResults.length == 0) {
             let html = '<h3>No Concerts found for ' + bandName + '</h3>';
             return html;
         }

         let html = '<h3>' + bandName + ' Concerts' + '</h3><br>';
         html += '<br><table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>';

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

    getHTMLForSingleConcertView(searchResults, date) {
        if (searchResults.length == 0) {
            let html = '<h3>No Concert found</h3>';
            return html;
        }

        var openingActsLength = Object.keys(searchResults.openingActs).length;
        if (openingActsLength > 1) {
            var openingActsList = '<ol>';
            for (let openingAct of searchResults.openingActs) {
                openingActsList += '<li>' + openingAct + '</li>'
            }
            openingActsList += '</ol>'
            searchResults.openingActs = openingActsList;
        }

        var songsPlayedLength = Object.keys(searchResults.songsPlayed).length;
        if (songsPlayedLength > 1) {
            var songsPlayedList = '<ol>';
            for (let song of searchResults.songsPlayed) {
                songsPlayedList += '<li>' + song + '</li>'
            }
            songsPlayedList += '</ol>'
            searchResults.songsPlayed = songsPlayedList;
        }

        var memoriesLength = Object.keys(searchResults.memories).length;
        if (memoriesLength > 1) {
            var memoriesList = '<ol>';
            for (let memory of searchResults.memories) {
                memoriesList += '<li>' + memory + '</li>'
            }
            memoriesList += '</ol>'
            searchResults.memories = memoriesList;
        }

        var friendlyDate = new Date(date + "CST");
        var temp = friendlyDate.toDateString();

        let html = '<h3>Concert Attended on  ' + temp + '</h3><br>';
        html+= '<br><table><tr><th>Date Attended</th><th>Band Name</th><th>Tour Name</th><th>Venue</th><th>Opening Act(s)</th><th>Set List</th><th>Memories</th></tr>';

        html += `
            <tr>
                <td>${searchResults.dateAttended}</td>
                <td>${searchResults.bandName}</td>
                <td>${searchResults.tourName}</td>
                <td>${searchResults.venue}</td>
                <td>${searchResults.openingActs}</td>
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
    const getConcert = new GetConcert();
    getConcert.mount();
};

window.addEventListener('DOMContentLoaded', main);
