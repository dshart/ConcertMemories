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
        concertView.addEventListener("change", () => {
            this.dropDownChange();
        });





//        var dateInput = document.getElementById("DateSelector");
//
//        dateInput.addEventListener('change', function(event) {
//           console.log(event.target.value)
//        });
//        <div class = "Comparisons_Wrapper">
//            <input type="date" class="DateSelector" id = "DateSelector">
//        </div>

            //if default value is changed
                                                                                          // selector.addEventListener("change", () => {
                                                                                           // if value switched by client
    }                                                                                        // switch (selector.value) {
                                                                                            //   case "add":
                                                                                               //do somthing with  , "add" value
                                                                                            //     result.innerHTML = selector.value;this.dropDownChange());
             // }

       // function addActivityItem(){
              //option is selected
         //     alert("yeah");

         //const selectElement = document.querySelector('#ConcertViewOptions');
         //selectElement.addEventListener('change', (event) => {
         //const result = document.querySelector('.tutorial');
          //const result = document.querySelector('submitViewButton');
         //   dropDownChange(result);
         //if result == 6
          //  var html = 'Select concert date<br> <input type =date id="concert" name="concertdate"';

         //submitButton(result);
         //result.classList
         //result.textContent = ""
         //result.textContent = `You like ${event.target.value}`;
         //});

        //const selectElement = document.querySelector('.ConcertViewOptions');
        //selectElement.addEventListener('change', (event) => {this.dropDownChange() });

        //const result = document.querySelector('.tutorial');
//            result.textContent = `You like ${event.target.value}`;



                //document.getElementById('submitViewButton').addEventListener('click', () => { this.submitButton(selectedValue) });
        //document.getElementById('concertViewOptions').addEventListener('onChange', () => { this.dropDownChange() });
        //document.getElementById('concertViewOptions').addEventListener('onChange', () => { this.dropDownChange() });
        //await this.submitButton();
        //element = document.querySelector(selectors);



//
//    /Get select object
//    var objSelect = document.getElementById("Mobility");
//
//    //Set selected
//    setSelectedValue(objSelect, "10");
//
//    function setSelectedValue(selectObj, valueToSet) {
//        for (var i = 0; i < selectObj.options.length; i++) {
//            if (selectObj.options[i].text== valueToSet) {
//                selectObj.options[i].selected = true;
//                return;
//            }
//        }
  //  }

//    async populateDropdown() {
//        const select = document.getElementById('organizations');
//        const orgList = await this.organizationClient.getAllOrgs();
//        this.dataStore.set(ORG_LIST_KEY, orgList);
//        for (const org of orgList) {
//            var opt = document.createElement('option');
//            opt.innerText = org.displayName;
//            select.appendChild(opt);
//        }
//    }

//    async submitButton() {
//        if(document.getElementById('organizations').selectedIndex == 0 || document.getElementById('jobRoles').selectedIndex == 0) {
//            alert("Error: please fill out all fields!")
//            return;
//        }
//        const orgId = this.dataStore.get(ORG_LIST_KEY)[document.getElementById('organizations').selectedIndex -1].orgId
//          if(await this.checkIfExists(orgId) == true) {
//                alert("Error: you already have a role at this organization.")
//    } else {
//           await this.createNewUserRole(orgId);
//////           alert("Role submitted!")
//////           window.location.href = "index.html";
//////        }
////    }

    async dropDownChange() {
        alert("in dropdown change");

        var dropDown = document.getElementById('concertViewOptions');
        var selectedValue = dropDown.options[dropDown.selectedIndex].value;


        //if (selectedValue == "0")

        // let selector = document.getElementById("ConcertViewOptions");
        //        let button = document.getElementById("submitViewButton");

        //var selectedValue = dropDown.options[dropDown.selectedIndex].innerHTML;


        // Change .innerHTML to .value if you need value instead of text
        //var selectedValue = dropDown.options[dropDown.selectedIndex].innerHTML;
        //var selectedValue = dropDown.options[dropDown.selectedIndex].value;


        var temp = document.getElementById("submitConcertArea");
        var html = "";
        if (selectedValue == 6) {
            //var button = document.getElementById("submitButton");
            //button.classList.add('hidden');

             html += '<br><label>Enter Concert Date<br><input type="date" id="concertDateId" /><br><input type="button" disabled value="Submit" id="submitDateButton" />'
             temp.innerHTML = "";
             temp.innerHTML = html;

             var grabDate = document.querySelector("#concertDateId");
             var date;
             var grabSubmit;
             var submitDateViewButton;
             grabDate.addEventListener("change",  function() {
                date = grabDate.value;
                grabSubmit = document.querySelector('#submitDateButton');
                grabSubmit.removeAttribute("disabled");
                grabSubmit.addEventListener("click", () => submitDateView(selectedView));
              });

                //submitDateView.addEventListener('click', click.bind(fooImg, 'foo'));

                //return date;

            //var grabSubmitEnabled = document.getElementById('submitDateButton');
            //grabSubmitEnabled.addEventListener("click", this.submitDateView(date));


                //document.getElementById("demo").innerHTML = "Hello World";)

            //const submit = document.querySelector(".submitDateButton");
            //submit.addEventListener("click", this.submitDateButton(selectedValue));

           // document.getElementById("dateInput").addEventListener("change", function() {
           //     var input = this.value;
           //     var dateEntered = new Date(input);


            //            this.submitViewButton(selectedValue);

          //   document.getElementById("concertDateId").addEventListener("click", function(event) {
           //      alert(event.target.value);
           //   });

//            <label for="concertDate">Concert Date</label> <input type ="date" id="concertDateId" name="concertDateName">'
            //button.innerHTML = html;
            //button.classList.remove('hidden');
            //var dateControl = document.querySelector('input[type="date"]');
            //var concertDate = dateControl.value;


            //alert(concertDate);
         //   html += '<br><br><br><label for="dateSumbitButton">Submit Date</label> <input type ="button" id="dateSubmitButton" name="dateSubmitButton">';
            //var x = document.getElementById("myDate").value;
            //alert(concertDate);
            //console.log(dateControl.value); // prints "2017-06-01"
            //console.log(dateControl.valueAsNumber); // prints 1496275200000, a JavaScript timestamp (ms)


//        =====
//        function onButtonClick() {
//          alert('Button clicked!');
//        }
//
//        const button = document.querySelector('button');
//        button.addEventListener('click', onButtonClick);
//
//        const newButton = document.createElement('button');
//        newButton.textContent = 'Submit!';
//        document.body.appendChild(newButton);
//
//        newButton.addEventListener('click', () => {
//          alert('New button clicked!');
//        });
//        =====







       }
       else {
            alert("in here");
           //drop down 0-5 for all concerts
           html += '<br><label for="submitViewButton"></label><input type ="button" value="Submit" id="submitViewButton" />';
           temp.innerHTML = "";
           temp.innerHTML = html;
           const submit = document.querySelector("#submitViewButton");
           submit.addEventListener("click", this.submitViewButton(selectedValue));
               //this.submitViewButton(selectedValue);
               //alert(submit);

          // document.getElementById("submitViewButton").addEventListener("click", this.submitViewButton());

           // this.submitViewButton(selectedValue);
        }

    }



   async submitViewButton(selectedValue){
        alert("submit button clicked");
       //alert(dropDown.value);
        // alert("loading concerts");
       // alert("selectedValue " + selectedValue);
        const searchCriteria = this.getUserEmail();
        //if (searchCriteria) {
        const results = await this.client.getAllConcerts(searchCriteria);
       // }

        this.dataStore.set([SEARCH_CRITERIA_KEY], searchCriteria);
        this.dataStore.set([SEARCH_RESULTS_KEY], results);
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


       this.displayConcertsHTML(selectedValue);
    }

    async submitDateView(selectedDate){
        alert("submit date View entered with: " + selectedDate);
           //alert(dropDown.value);
            // alert("loading concerts");
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
       alert("in displayConcertsHTML");
       const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
       const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
       const searchResultsContainer = document.getElementById('searchResultsContainer');
       const searchCriteriaDisplay = document.getElementById('searchCriteriaDisplay');
       const searchResultsDisplay = document.getElementById('searchResultsDisplay');

       if (searchCriteria == '') {
      //     alert("bad");
           //searchResultsContainer.style.display = "block";
           searchResultsContainer.classList.add('hidden');
           searchCriteriaDisplay.innerHTML = '';
           searchResultsDisplay.innerHTML = '';
       } else {
            alert("so far so good");
            //set view for text portion and sort by band and venue if necessary
        if (selectedValue == 0) { //all concerts sorted by band
            var view = "Date";
                  //  searchResultsContainer.classList.add('hidden');
       } else if (selectedValue == 1) {
                              //var button = document.getElementById("submitViewButton");
                                  //button.classList.remove("hidden");
                                  //alert(button.)
                           //var html = '<h3>Concerts Attended sorted by Band</h3><br><br>'
                   var view = "Band";
                   searchResults.sort((a,b) => {
                   const band1 = a.bandName.toLowerCase(), band2 = b.bandName.toLowerCase();
                        return band1 === band2 ? 0 : band1 < band2 ? -1 : 1;
                   })
       } else if (selectedValue == 2) { //all concerts sorted by venue
                   var view = "Venue";
                           //var html = '<h3>Concerts Attended sorted by Venue</h3><br><br>';
                   searchResults.sort((a,b) => {
                        const venue1 = a.venue.toLowerCase(), venue2 = b.venue.toLowerCase();
                        return venue1 === venue2 ? 0 : venue1 < venue2 ? -1 : 1;
                   });
       }
        //    alert("good");
             //searchResultsContainer.style.display = "none";
       searchResultsContainer.classList.remove('hidden');
      // searchResultsDisplay.innerHTML = this.getHTMLForConcertsView(searchResults, view);
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


         /**
             * Pulls search results from the datastore and displays them on the html page.
             */
     //    displayConcertsHTML() {
     //        alert("display ");
//             const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
//             const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
//
//             const searchResultsContainer = document.getElementById('search-results-container');
//             const searchCriteriaDisplay = document.getElementById('search-criteria-display');
//             const searchResultsDisplay = document.getElementById('search-results-display');
//
//             if (searchCriteria === '') {
//                 searchResultsContainer.classList.add('hidden');
//                 searchCriteriaDisplay.innerHTML = '';
//                 searchResultsDisplay.innerHTML = '';
//             } else {
//                searchResultsContainer.classList.remove('hidden');
//                searchResultsDisplay.innerHTML = await this.getHTMLForConcertsView(searchResults);
//             }
     //   }


     getHTMLForConcertsView(searchResults, view) {

            alert("in getHTML");
            alert(view);
            //alert(selectedValue);
//             if (searchResults.length() == 0) {
//                return '<h4>No concerts found</h4>';
//             }

                /*
                 const editNameField = document.getElementById('new-project-name');
                 const editStatusField = document.getElementById('status');
                        const editDescriptionField = document.getElementById('new-project-description');
                        const editEndDateField = document.getElementById('endDate');



                        editNameField.value = searchResults.name;
                        editStatusField.value = searchResults.projectStatus;
                        editDescriptionField.value = searchResults.projectDescription;

                        if (searchResults.endDate != null) {
                            var convertedEndDate = searchResults.endDate;
                            var date = new Date(convertedEndDate * 1000);
                            date = date.toISOString().slice(0, 10).toString();
                            editEndDateField.value = date;
                        }
                */

  //          this.sortByProperty(searchResults, searchResults.bandName, ascending);

            //searchResults.sort((a,b) => {
            //const bandName1 = a.bandName.toLowerCase(), bandName2 = b.bandName.toLowerCase();
            //return bandName1 === bandName2 ? 0 : bandName1 < bandName2 ? -1 : 1;
            //});

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
   // alert("concert view main")
    const concertViewScript = new ConcertViewScript();
    concertViewScript.mount();
};

window.addEventListener('DOMContentLoaded', main);
