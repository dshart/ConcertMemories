import axios from "axios";
import ConcertViewClient from '../api/concertViewClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};

const TASK_SEARCH_CRITERIA_KEY = 'search-criteria';
const TASK_SEARCH_RESULTS_KEY = 'search-results';

class ConcertViewScript extends BindingClass {
    constructor(props = {}) {
        super();
        this.bindClassMethods(['clientLoaded', 'displayConcertsHTML', 'loadConcertsOnClick', 'mount', 'startupActivities', 'submitButton'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
         this.header = new Header(this.dataStore);


       //this.authenticator = new Authenticator();; //scooby don't think I need this
       this.props = props;  //scooby not sure what this is for
       this.axiosClient = axios;
       axios.defaults.baseURL = process.env.API_BASE_URL;
       this.clientLoaded();

    }

    /**
      * Run any functions that are supposed to be called once the client has loaded successfully.
    */
    clientLoaded() {
        alert("client loaded");
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }


   /**
    * Add the header to the page and load the ConcertMemoriesClient.
    */


    mount() {
        alert("mount");


        document.getElementById('submitViewButton').addEventListener('click', this.submitButton);
        this.client = new ConcertViewClient;
        this.header.addHeaderToPage();
        this.startupActivities();

    }

    async startupActivities() {
       alert("startup");
         await this.submitButton()


////            document.getElementById('concertdisplayName').value = name;
////            this.populateDropdown();
////            document.getElementById('greeting').hidden=false;
////            document.getElementById('please-wait').hidden=true;
////            document.getElementById('name-form').hidden=false;
////            document.getElementById('organizations').hidden=false;
////            document.getElementById('jobRoles').hidden=false;
////            document.getElementById('submit-btn').hidden=false;
////            document.getElementById('cancel-btn').hidden=false;
////              }
    }
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


   async submitButton(){
       //alert("button");
       var dropDown = document.getElementById('concertViewOptions');

        // Change .innerHTML to .value if you need value instead of text
       // var selectedValue = dropDown.options[dropDown.selectedIndex].innerHTML;
       alert(dropDown.value);
       loadConcertOnClick();
       displayConcertsHTML();

        /**
            * Pulls search results from the datastore and displays them on the html page.
            */
       //    displaySearchResults() {
//       const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
//       const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
//
//       const searchResultsContainer = document.getElementById('concertViewRender');
//       const searchCriteriaDisplay = document.getElementById('search-criteria-display');
//       const searchResultsDisplay = document.getElementById('search-results-display');
//
//               if (searchCriteria === '') {
//                   searchResultsContainer.classList.add('hidden');
//                   searchCriteriaDisplay.innerHTML = '';
//                   searchResultsDisplay.innerHTML = '';
//               } else {
//                   searchResultsContainer.classList.remove('hidden');
//                   searchResultsDisplay.innerHTML = this.getHTMLForProject(searchResults);
//               }
//           }
        }


        async loadConcertsOnClick() {
             const email = new URLSearchParams(window.location.search).get('email');
             if (email) {
                 const results = await client.getAllConcerts(email);
                 this.dataStore.setState({
                    [SEARCH_CRITERIA_KEY]: email,
                    [SEARCH_RESULTS_KEY]: results
                 });
             } else {
                 this.dataStore.setState(EMPTY_DATASTORE_STATE);
            }
        }

         /**
             * Pulls search results from the datastore and displays them on the html page.
             */
         displayConcertsHTML() {
             const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
             const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

             const searchResultsContainer = document.getElementById('search-results-container');
             const searchCriteriaDisplay = document.getElementById('search-criteria-display');
             const searchResultsDisplay = document.getElementById('search-results-display');

             if (searchCriteria === '') {
                 //searchResultsContainer.classList.add('hidden');
                 searchCriteriaDisplay.innerHTML = '';
                 searchResultsDisplay.innerHTML = '';
             } else {
                // searchResultsContainer.classList.remove('hidden');
                //searchResultsDisplay.innerHTML = this.getHTMLForConcertView(searchResults);
             }

             //getHTMLForProject(searchResults) {
             if (searchResults.length === 0) {
                return '<h4>No concerts found</h4>';
             }

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

             let html = '<table><tr><th>Date Attended</th><th>Band Name</th> <th>Tour Name</th><th>Venue</th><th>Opening Act(s)</tr>';
             const res = searchResults;

             html += `
                <tr>
                   <td>${res.dateAttended}</td>
                   <td>${res.bandName}</td>
                   <td>${res.tourName}</td>
                   <td>${res.venue}</td>
                   <td>${res.openingActs}</td>
                </tr>`;

             html += '</table>';
             return html;
         }



//       switch(dropDown.value) {
//         case "0":
//            alert("single concert");
//            break;
//         case "1":
//            alert("all concerts sort by date");
//           // const allConcertsSortedByDate = document.ById('displayConcertData');
//           // const results = await.this.concertViewClient.getAllConcerts();
//           // System.out.println(result);
//            break;
//         case "2":
//            alert("all concerts sort by band");
//            break;
//         case "3":
//            alert("all concerts sort by venue");
//            break;
//         case "4":
//            alert("concerts by band");
//            break;
//         case "5":
//            alert("concerts by venue")
//            break;
//         case "6":
//            alert("concerts by year");
//            break;
//        }

//          const orgId = new URLSearchParams(window.location.search).get('orgId');
//                const projectId = new URLSearchParams(window.location.search).get('projectId');
//
//                if (orgId && projectId) {
//                    const results = await this.projectClient.getProject(orgId, projectId);
//                    this.dataStore.setState({
//                        [SEARCH_CRITERIA_KEY]: projectId,
//                        [SEARCH_RESULTS_KEY]: results,
//                    });
//                    this.loadTaskForProject();
//                } else {
//                    this.dataStore.setState(EMPTY_DATASTORE_STATE);
//                }

        //ments.GetElements();
        //const headings = Elements.GetHeadings();
        //const properties = Elements.GetProperties();

            // start table and add caption
//        let tablehtml = "<table><caption>Concerts Attended ...(add specific view/caption>";
//
//            // insert row of headings
//            tablehtml  += "<tr>";
//            for(let heading of headings)
//            {
//                tablehtml  += `<th>${heading}</th>`;
//            }
//            tablehtml += "</tr>";
//
//            // iterate data and add row of cells for each
//            for(let element of elements)
//            {
//                tablehtml  += "<tr>";
//
//                for(let property of properties)
//                {
//                    tablehtml  += `<td>${element[property]}</td>`;
//                }
//
//                tablehtml  += "</tr>";
//            }
//
//            // end of table
//            tablehtml += "</table>";
//
//            // add table to the empty div
//            document.getElementById("tablediv").innerHTML = tablehtml;
   // }




//        alert("in concert query");
//        var e = document.getElementById("concertViewOptions");
//        if (e.selectedIndex == "0")
//            alert("Single Concert By Date");
//        else if (e.selectedIndex == "1")
//            alert("All Concerts - sorted by Date");
//        else if (e.selectedIndex == "2")
//            alert("All Concerts - sorted by Band");
//        else if (e.selectedIndex == "3")
//            alert("All Concerts - sorted by by Venue");
//        else if (e.selectedIndex  == "4")
//            alert("All Concerts By Band");
//        else if (e.selectedIndex  == "5")
//            alert("All Concerts By Venue");
//        else if (e.selectedIndex  == "6")
//            alert("All Concerts By Year");
    }

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    alert("concert view main")
    const concertViewScript = new ConcertViewScript();
    concertViewScript.mount();
};

window.addEventListener('DOMContentLoaded', main);
