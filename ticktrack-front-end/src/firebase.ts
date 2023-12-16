// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyBxRbSm-sd82wIHek8JjwGAyHivyJpWbhE",
    authDomain: "ticktrack-ttr94.firebaseapp.com",
    projectId: "ticktrack-ttr94",
    storageBucket: "ticktrack-ttr94.appspot.com",
    messagingSenderId: "890710640435",
    appId: "1:890710640435:web:609aa2d0b49e36c02da046"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firebase Authentication and get a reference to the service
const auth = getAuth(app);

export {app, auth}