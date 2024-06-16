import GetConcertClient from '../api/getConcertClient';
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

class GetConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['displayAllConcertsHTML', 'displayAllConcertsByBandHTML', 'displaySingleConcertHTML',
        'displayAllConcertsByVenueHTML', 'dropDownChange', 'getHTMLForAllConcertsView', 'getHTMLForAllConcertsByBandView',
        'getHTMLForSingleConcertView', 'getHTMLForAllConcertsByVenueView', 'getIdentity',  'mount', 'startupActivities',
        'submitBandViewButton', 'submitDateViewButton', 'submitVenueViewButton', 'submitViewButtonClick'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

   /**
    * Add the header to the page and load the GetConcertClient.
    */
    mount() {

        this.header.addHeaderToPage();
        this.client = new GetConcertClient();
        this.startupActivities();
    }

    async startupActivities() {
        const{email, name} = await this.client.getIdentity().then(result => result);
        //const concertView = document.querySelector('#concertViewOptions');
        const concertView = document.getElementById('concertViewOptions');
        concertView.addEventListener("change", this.dropDownChange);
    }

    async dropDownChange() {
        var dropDown = document.getElementById('concertViewOptions');
        var selectedValue = dropDown.options[dropDown.selectedIndex].value;
        var concertDisplayArea = document.getElementById('submitConcertArea');

        if (selectedValue == 1) {
            var html = "";
            html += '<br><label>Enter Concert Date<br><input type="date" id="concertDateId" /><br><input type="button" hidden value="Submit" id="submitDateButton" />'
            concertDisplayArea.innerHTML = "";
            concertDisplayArea.innerHTML = html;

            //var dateSelected = document.querySelector("#concertDateId");
            var dateSelected = document.getElementById('concertDateId');
            //var submitDateButton = document.querySelector("#submitDateButton");
            var submitDateButton = document.getElementById('submitDateButton');
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
            //var bandNameInput = document.querySelector("#bandNameInput");
            var bandNameInput = document.getElementById('bandNameInput');
            bandNameInput.value == "";
            bandNameInput.focus();
            //var submitBandButton = document.querySelector("#submitBandNameButton");
            var submitBandButton = document.getElementById('submitBandNameButton');

            bandNameInput.addEventListener("keyup", function() {
                bandName = bandNameInput.value;
                submitBandNameButton.removeAttribute("hidden");
            });

            submitBandNameButton.addEventListener("click", () => this.submitBandViewButton(bandName));
            bandNameInput.addEventListener('keydown', (event) => {
                submitBandNameButton.disabled = false;
                if (event.key == 'Enter') {
                    this.submitBandViewButton(bandName);
                    bandNameInput.value = "";
                    bandNameInput.focus();
                    submitBandNameButton.disabled = true;
                }
            });
        } else if (selectedValue == 6) {
            var html = "";
            html += '<br><label>Enter Venue Name<br><input type="text" autocomplete="off", id="venueInput" /><br><input type="button" hidden value="Submit" id="submitVenueButton" />'
            concertDisplayArea.innerHTML = "";
            concertDisplayArea.innerHTML = html;

            var venue = "";
            //var venueInput = document.querySelector("#venueInput");
            var venueInput = document.getElementById('venueInput');
            venueInput.value == "";
            venueInput.focus();
            //var submitVenueButton = document.querySelector("#submitVenueButton");
             var submitVenueButton = document.getElementById('submitVenueButton');

            venueInput.addEventListener("keyup", function() {
                venue = venueInput.value;
                submitVenueButton.removeAttribute("hidden");
            });

            submitVenueButton.addEventListener("click", () => {
                submitVenueButton.disabled = false;
                venueInput.value = "";
                venueInput.focus();
                this.submitVenueViewButton(venue);
                submitVenueButton.disabled = true;
            });
            venueInput.addEventListener('keydown', (event) => {
                submitVenueButton.disabled = false;
                if (event.key == 'Enter') {
                    venueInput.value = "";
                    venueInput.focus();
                    this.submitVenueViewButton(venue);
                    submitVenueButton.disabled = true;
                }
            });
        } else if (selectedValue == 2 || selectedValue == 3 || selectedValue == 4) {
            var html = "";
            html += '<br><label for="submitViewButton"></label><input type ="button" value="Submit" id="submitViewButton" />';
            concertDisplayArea.innerHTML = html;
            var submitViewButton = document.getElementById('submitViewButton');
            //var submitViewButton = document.querySelector("#submitViewButton");
            submitViewButton.addEventListener("click", () => this.submitViewButtonClick(selectedValue));
        }
    }

    async submitViewButtonClick(selectedValue){
        var submitViewButton = document.getElementById('submitViewButton');

        //var submitViewButton = document.querySelector("#submitViewButton");
        submitViewButton.style.display = 'none';

        if (selectedValue == 0) {
            searchResultsDisplay.innerHTML = "";
        } else {
            var searchCriteria = email;
            const results = await this.client.getAllConcerts(searchCriteria);

            this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
            this.dataStore.set([SEARCH_RESULTS_KEY], results);
            this.displayAllConcertsHTML(selectedValue, submitViewButton);
        }
    }

    async submitBandViewButton(bandName){
        //var submitBandNameButton = document.querySelector("#submitBandNameButton");
        var submitBandNameButton = document.getElementById('submitBandNameButton');
        submitBandNameButton.style.display = "none";
        var emailKey = email;
        var bandKey = bandName;
        const searchCriteria = [emailKey, bandKey];
        const results = await this.client.getAllConcertsByBand(emailKey, bandKey);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displayAllConcertsByBandHTML(bandName, submitBandNameButton);
    }

    async submitVenueViewButton(venue){
       // var submitVenueButton = document.querySelector("#submitVenueButton");
        var submitVenueButton = document.getElementById('submitVenueButton');
        submitVenueButton.style.display='none';
        var emailKey = email;
        var venueKey = venue;
        const searchCriteria = [emailKey, venueKey];
        const results = await this.client.getAllConcertsByVenue(emailKey, venueKey);

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
        this.displayAllConcertsByVenueHTML(venue, submitVenueButton);
    }

    async submitDateViewButton(date, selectedValue){
        //var submitDateButton = document.querySelector("#submitDateButton");
        var submitDateButton = document.getElementById('submitDateButton');
        submitDateButton.style.display='none';

        var emailKey = email;
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
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        if (selectedValue == 2) {
            var viewType = "Date";
            searchResultsDisplay.innerHTML = "";
        } else if (selectedValue == 3) {
            var viewType = "Band";
            searchResultsDisplay.innerHTML = '';
            searchResults.sort((a,b) => {
                if (a.bandName && b.bandName) {
                    const band1 = a.bandName.trim().toLowerCase(), band2 = b.bandName.trim().toLowerCase();
                        return band1 === band2 ? 0 : band1 < band2 ? -1 : 1;
                }
            });
        } else if (selectedValue == 4) {
            var viewType = "Venue";
            searchResultsDisplay.innerHTML == '';
            searchResults.sort((a,b) => {
                if (a.venue && b.venue) {
                    const venue1 = a.venue.trim().toLowerCase(), venue2 = b.venue.trim().toLowerCase();
                        return venue1 === venue2 ? 0 : venue1 < venue2 ? -1 : 1;
                }
            });
        }

        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML += this.getHTMLForAllConcertsView(searchResults, viewType);
        submitViewButton.style.display='none';
    }

    //All Concerts by band
    displayAllConcertsByBandHTML(bandName, submitBandNameButton) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML = this.getHTMLForAllConcertsByBandView(searchResults, bandName);
        submitBandNameButton.style.display='none';
    }

    //Single Concert by Date
    displaySingleConcertHTML(date, submitDateViewButton) {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        const searchResultsDisplay = document.getElementById('searchResultsDisplay');

        searchResultsDisplay.innerHTML = "";
        searchResultsDisplay.innerHTML = this.getHTMLForSingleConcertView(searchResults, date);
        //submitDateViewButton.classList.add('hidden');
        submitDateViewButton.style.display = 'none';


    }

    //All Concerts by venue
   displayAllConcertsByVenueHTML(venue, submitVenueButton) {
       const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
       const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
       const searchResultsDisplay = document.getElementById('searchResultsDisplay');

       searchResultsDisplay.innerHTML = "";
       searchResultsDisplay.innerHTML = this.getHTMLForAllConcertsByVenueView(searchResults, venue);
       submitVenueButton.style.display = 'none';
   }

   getHTMLForAllConcertsView(searchResults, viewType) {

         if (searchResults == null || searchResults.length == 0) {
            let html = '<h3>No Concerts found</h3>';

           return html;
        }

        let html = '<div id="cut-top-margin"><h3>Concerts Attended sorted by ' + viewType + '</h3><br></div>'

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

      html += '</table><br><br><br><br>';
      return html;
    }

    getHTMLForAllConcertsByBandView(searchResults, bandName) {
        //var bandNameInput = document.querySelector("#bandNameInput");
     var bandNameInput = document.getElementById('bandNameInput');
     //var submitBandNameButton = document.querySelector("#submitBandNameButton");
     var submitBandNameButton = document.getElementById('submitBandNameButton');
        if (searchResults == null || searchResults.length == 0) {
             let html = '<h3>No Concerts found for ' + bandName + '</h3>';
              bandNameInput.value = "";
              bandNameInput.focus();
              submitBandNameButton.style.display='block';
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
         if (searchResults == null || searchResults.length == 0) {
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

        html += '</table>'

        return html;
    }

    getHTMLForAllConcertsByVenueView(searchResults, venue) {
        if (searchResults.length == 0) {
            let html = '<h3>No Concerts found for ' + venue + '</h3>';
                return html;
            }

            let html = '<h3>Concerts at ' + venue + '</h3><br>';
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
