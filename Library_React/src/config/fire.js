import firebase from 'firebase';

var firebaseConfig = {
  apiKey: "AIzaSyAIdKGmtsFXVFh3KAoavJO_EsECmbpBOUM",
  authDomain: "forms-f41bc.firebaseapp.com",
  projectId: "forms-f41bc",
  storageBucket: "forms-f41bc.appspot.com",
  messagingSenderId: "308000873917",
  appId: "1:308000873917:web:562b7dda8df554085786d4"
};

  const fire= firebase.initializeApp(firebaseConfig);

  export default fire;