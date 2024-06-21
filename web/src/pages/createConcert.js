import ConcertMemoriesClient from '../api/concertMemoriesClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_RESULTS_KEY]: ''
};

/**
 * Logic needed for the enterConcertInfo page of the website.
 */
class CreateConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['convertToList', 'handleError', 'mount', 'startupActivities', 'submit'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new ConcertMemoriesClient();
        this.startupActivities();
    }

    async startupActivities() {
        var submitConcertFormButton = document.getElementById("submitConcertButtonId");
        submitConcertFormButton.addEventListener("click", this.submit);

         //only allow Enter button to appear if date is selected as it is range key

        var concertDate = document.getElementById("concertDateTextBox");

    }

    convertToList(stringToConvert) {
         var arr = [];
         if (stringToConvert != null) {
             arr = stringToConvert.split(",");
         }
         return arr;
    }

    async submit(evt) {
       evt.preventDefault();
        alert("in submit form");
        var oa = document.getElementById('openingActs').value;
        var sp = document.getElementById('songsPlayed').value;
        var m = document.getElementById('memories').value;
        var oaList = this.convertToList(oa);
        var spList = this.convertToList(sp);
        var mList = this.convertToList(m);




       const concert = await this.client.createConcert(
            document.getElementById('concertDateTextBox').value,
            document.getElementById('bandName').value,
            document.getElementById('tourName').value,
            document.getElementById('venue').value,
            oaList,
            spList,
            mList
       );
       this.dataStore.set('concert', concert);


// const playlist = await this.client.createPlaylist(playlistName, tags, (error) => {
//            createButton.innerText = origButtonText;
//            errorMessageDisplay.innerText = `Error: ${error.message}`;
//            errorMessageDisplay.classList.remove('hidden');
//        });
//        this.dataStore.set('playlist', playlist);
//    }

//        await this.client.createConcert(
//            document.getElementById('concertDateTextBox').value,
//            document.getElementById('bandName').value,
//            document.getElementById('tourName').value,
//            document.getElementById('venue').value,
//            oaList,
//            spList,
//            mList
//        ).then(results => {
//             var searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
//             searchResults.push(results);
//             this.DataStore.setState({
//                 [SEARCH_RESULTS_KEY]: searchResults,
//             });
//
//             alert("results" + results);
//
//             return results;
//        }).catch(e => {
//            console.log(e);
//        });
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

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createConcert = new CreateConcert();
    createConcert.mount();
};

window.addEventListener('DOMContentLoaded', main);


