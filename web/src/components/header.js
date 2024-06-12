//import ConcertMemoriesClient from '../api/concertMemoriesClient';
//import BindingClass from "../util/bindingClass";
//
///**
// * The header component for the website.
// */
//export default class Header extends BindingClass {
//    constructor() {
//        super();
//
//        const methodsToBind = ['addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader', 'createLoginButton',
//         'createLogoutButton', 'createButton']
//        this.bindClassMethods(methodsToBind, this);
//        this.client = new ConcertMemoriesClient();
//    }
//
//    /**
//     * Add the header to the page.
//     */
//    async addHeaderToPage() {
//         const currentUser = await this.client.getIdentity();
//
//         const siteTitle = this.createSiteTitle();
//         const userInfo = this.createUserInfoForHeader(currentUser);
//         const header = document.getElementById('header');
//         header.appendChild(siteTitle);
//         header.append(userInfo);
//    }
//
//    createSiteTitle() {
//        alert("in here");
//        const siteTitle = document.createElement('p');
//        siteTitle.innerHTML = `<pre><h1>Concert Memories</h1>
//                               <h3>Relive the Rush!</h3></pre>`;
//        return siteTitle;
//    }
//
//    createUserInfoForHeader(currentUser) {
//        const userInfo = document.createElement('div');
//        userInfo.classList.add('user');
//
//        const childContent = currentUser
//            ? this.createLogoutButton(currentUser)
//            : this.createLoginButton();
//
//        userInfo.appendChild(childContent);
//
//        return userInfo;
//    }
//
//    createLoginButton() {
//        return this.createButton('Login', this.client.login);
//    }
//
//    createLogoutButton(currentUser) {
//        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
//    }
//
//    createButton(text, clickHandler) {
//        const button = document.createElement('a');
//        button.classList.add('button');
//        button.href = '#';
//        button.innerText = text;
//
//        button.addEventListener('click', async () => {
//            await clickHandler();
//        });
//
//        return button;
//    }
//}

import ConcertMemoriesClient from '../api/concertMemoriesClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader', 'createLoginButton',
         'createLogoutButton', 'createButton']
        this.bindClassMethods(methodsToBind, this);
        this.client = new ConcertMemoriesClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
    }

    createSiteTitle() {
        const siteTitle = document.createElement('p');
        siteTitle.innerHTML = `<pre><h1>Concert Memories</h1>
                               <h3>Relive the Rush!</h3></pre>`;
        return siteTitle;
    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser
            ? this.createLogoutButton(currentUser)
            : this.createLoginButton();

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}

//import concertMemoriesClient from '../api/concertMemoriesClient';
////import createConcertClient from '../api/createConcertClient';
////import deleteConcertClient from '../api/deleteConcertClient';
////import getConcertClient from '../api/getConcertClient';
//
//import BindingClass from "../util/bindingClass";
//
///**
// * The header component for the website.
// */
//export default class Header extends BindingClass {
//    constructor() {
//        super();
//
//        const methodsToBind = [
//            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
//            'createLoginButton', 'createLoginButton', 'createLogoutButton'
//        ];
//        this.bindClassMethods(methodsToBind, this);
//
//        this.client = new ConcertMemoriesClient();
//    }
//
//    /**
//     * Add the header to the page.
//     */
//    async addHeaderToPage() {
//        const currentUser = await this.client.getIdentity();
//
//        const siteTitle = this.createSiteTitle();
//        const userInfo = this.createUserInfoForHeader(currentUser);
//
//        const header = document.getElementById('header');
//        header.appendChild(siteTitle);
//        header.appendChild(userInfo);
//    }
//
//    createSiteTitle() {
//        const homeButton = document.createElement('a');
//        homeButton.classList.add('header_home');
//        homeButton.href = 'index.html';
//        homeButton.innerText = 'Inventory Management System';
//
//        const siteTitle = document.createElement('div');
//        siteTitle.classList.add('site-title');
//        siteTitle.appendChild(homeButton);
//
//        return siteTitle;
//    }
//
//    createUserInfoForHeader(currentUser) {
//        const userInfo = document.createElement('div');
//        userInfo.classList.add('user');
//
//        const childContent = currentUser
//            ? this.createLogoutButton(currentUser)
//            : this.createLoginButton();
//
//        userInfo.appendChild(childContent);
//
//        return userInfo;
//    }
//
//    createLoginButton() {
//        return this.createButton('Login', this.client.login);
//    }
//
//    createLogoutButton(currentUser) {
//        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
//    }
//
//    createButton(text, clickHandler) {
//        const button = document.createElement('a');
//        button.classList.add('button');
//        button.href = '#';
//        button.innerText = text;
//
//        button.addEventListener('click', async () => {
//            await clickHandler();
//        });
//
//        return button;
//    }
//}
