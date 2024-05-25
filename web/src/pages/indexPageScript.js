
import ConcertMemoriesClient from '../api/concertMemoriesClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

//const COGNITO_NAME_KEY = 'cognito-name';
const COGNITO_EMAIL_KEY = 'cognito-name-results';
const EMPTY_DATASTORE_STATE = {
    [COGNITO_EMAIL_KEY]: ''
};

/**
 * Logic needed for the index page of the website.
 */
class IndexPageScript extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'startupActivities' ], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
    }


    mount() {
        this.header.addHeaderToPage();
        this.concertMemoriesClient = new ConcertMemoriesClient();
        this.startupActivities();
    }

    async startupActivities() {
        //If user is logged in when app starts, this method will initialize page elements
        if (await this.concertMemoriesClient.verifyLogin()) {
            const{email, name} = await this.concertMemoriesClient.getIdentity().then(result => result);
            this.dataStore.set([COGNITO_EMAIL_KEY], email);
            document.getElementById('enter-site').innerText = "Enter Site";
            document.getElementById("enter-site").removeAttribute("hidden");
            var button = document.getElementById("enter-site");
            button.addEventListener("click", function(event){
                document.location.href = "concertsAndBands.html";
            });
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const indexPageScript = new IndexPageScript();
    indexPageScript.mount();
};

window.addEventListener('DOMContentLoaded', main);


