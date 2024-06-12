import CreateConcertClient from '../api/createConcertClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Authenticator from '../api/authenticator';

const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_NAME_KEY]: '',
    [COGNITO_EMAIL_KEY]: ''
};

/**
 * Logic needed for the enterConcertInfo page of the website.
 */
class CreateConcert extends BindingClass {
    constructor() {
        super();

        this.header = new Header(this.dataStore);
        this.bindClassMethods(['getIdentity', 'mount', 'startupActivities', 'submitForm'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
    }

    mount() {
        this.header.addHeaderToPage();
        this.client = new CreateConcertClient();
        this.startupActivities();
        this.email = this.getUserEmail();
    }

    async startupActivities() {
//        if (await this.client.verifyLogin()) {
//            const{email, name} = await this.client.getIdentity().then(result => result);
//            this.dataStore.set([COGNITO_EMAIL_KEY], email);
//            this.dataStore.set([COGNITO_NAME_KEY], name);
//        }

        //var concertEntryForm = document.getElementById("entryForm");
        var concertEntryForm = document.querySelector("#entryForm");
        concertEntryForm.addEventListener('submit', () => this.submitForm());
        document.getElementById('submitConcertButton').addEventListener('click', this.submitForm);
    }

     /**
         * Uses the client to obtain the Users email and Name;
         * @returns User Email
        */
        async getUserEmail() {
            const { email, name } = await this.client.getIdentity().then(result => result);
            return email;
        }

    async submitForm() {
        alert("submitting");

        var oA = document.getElementById(openingActs);
        var sP = document.getElementsById(songsPlayed);
        var m = document.getElementsById(memories);

        var oaArray = new Array();
        var spArray = new Array();
        var mArray = new Array();

        oaArray = oA.value.split(",");
        spArray = sP.value.split(',');
        mArray = m.value.split(',');

        const results = await this.client.createConcert(
            this.dataStore.get(COGNITO_EMAIL_KEY),
            document.getElementById('concertDate').value,
            document.getElementById('bandName').value,
            document.getElementById('tourName').value,
            document.getElementById('venue').value,
            oaArray,
            spArray,
            mArray
//            document.getElementById('openingActs').value,
//            document.getElementById('songsPlayed').value,
//            document.getElementById('memories').value
        )

        alert("Form submitted!");
        window.location.href = "enterConcertInfo.html";

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
    console.log();
    createConcert.mount();
};

window.addEventListener('DOMContentLoaded', main);


