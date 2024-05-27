//import ConcertsClient from '../api/concertsClient';
//import Header from '../components/header';
//import BindingClass from "../util/bindingClass";
////import DataStore from "../util/DataStore";
//
//
//class ConcertViewsScript extends BindingClass {
//    constructor(props = {}) {
//        super();
//
//        const methodsToBind = ['clientLoaded', 'mount', 'startupActivities', 'submitButton', 'queryConcertViewsList'];
//        this.bindClassMethods(methodsToBind, this);
//
//       //this.authenticator = new Authenticator();;
//       this.props = props;
//       axios.defaults.baseURL = process.env.API_BASE_URL;
//       this.axiosClient = axios;
//       this.clientLoaded();
//    }
//
//    /**
//      * Run any functions that are supposed to be called once the client has loaded successfully.
//    */
//    clientLoaded() {
//        if (this.props.hasOwnProperty("onReady")) {
//            this.props.onReady(this);
//        }
//    }
//
//
//   /**
//    * Add the header to the page and load the ConcertsClient.
//    */
//
//
//    mount() {
//
//        this.header.addHeaderToPage();
//        this.concertMemoriesClient = new ConcertMemoriesClient();
//       // document.getElementById('submit-btn').addEventListener('click', this.submitButton);
//       // document.getElementById("list").onchange = queryConcertViewList;
//        //document.getElementById("concertViewOptions").addEventListener("click", queryList);
//      //  this.startupActivities();
//    }
//
//    async startupActivities() {
//
////            document.getElementById('concertdisplayName').value = name;
////            this.populateDropdown();
////            document.getElementById('greeting').hidden=false;
////            document.getElementById('please-wait').hidden=true;
////            document.getElementById('name-form').hidden=false;
////            document.getElementById('organizations').hidden=false;
////            document.getElementById('jobRoles').hidden=false;
////            document.getElementById('submit-btn').hidden=false;
////            document.getElementById('cancel-btn').hidden=false;
////        } else {
////            window.location.href = "index.html"
////        }
////    }
////
////    /Get select object
////    var objSelect = document.getElementById("Mobility");
////
////    //Set selected
////    setSelectedValue(objSelect, "10");
////
////    function setSelectedValue(selectObj, valueToSet) {
////        for (var i = 0; i < selectObj.options.length; i++) {
////            if (selectObj.options[i].text== valueToSet) {
////                selectObj.options[i].selected = true;
////                return;
////            }
////        }
//    }
//
////    async populateDropdown() {
////        const select = document.getElementById('organizations');
////        const orgList = await this.organizationClient.getAllOrgs();
////        this.dataStore.set(ORG_LIST_KEY, orgList);
////        for (const org of orgList) {
////            var opt = document.createElement('option');
////            opt.innerText = org.displayName;
////            select.appendChild(opt);
////        }
////    }
//
////    async submitButton() {
////        if(document.getElementById('organizations').selectedIndex == 0 || document.getElementById('jobRoles').selectedIndex == 0) {
////            alert("Error: please fill out all fields!")
////            return;
////        }
////        const orgId = this.dataStore.get(ORG_LIST_KEY)[document.getElementById('organizations').selectedIndex -1].orgId
////          if(await this.checkIfExists(orgId) == true) {
////                alert("Error: you already have a role at this organization.")
////        } else {
////           await this.createNewUserRole(orgId);
////           alert("Role submitted!")
////           window.location.href = "index.html";
////        }
////    }
//
//
//   async queryConcertViewsList(){
////
////        alert("in concert query");
////        var e = document.getElementById("concertViewOptions");
////        if (e.selectedIndex == "0")
////            alert("Single Concert By Date");
////        else if (e.selectedIndex == "1")
////            alert("All Concerts - sorted by Date");
////        else if (e.selectedIndex == "2")
////            alert("All Concerts - sorted by Band");
////        else if (e.selectedIndex == "3")
////            alert("All Concerts - sorted by by Venue");
////        else if (e.selectedIndex  == "4")
////            alert("All Concerts - sorted bBy Year");
////        else if (e.selectedIndex  == "5")
////            alert("All Concerts By Band");
////        else if (e.selectedIndex  == "6")
////            alert("All Concerts By Venue");
////        else if (e.selectedIndex  == "7")
////            alert("All Concerts By Year");
//    }
//}
///**
// * Main method to run when the page contents have loaded.
// */
//const main = async () => {
//    alert("concert view main");
//    const concertViewsScript = new ConcertViewsScript();
//    concertViewsScript.mount();
//};
//
//window.addEventListener('DOMContentLoaded', main);
