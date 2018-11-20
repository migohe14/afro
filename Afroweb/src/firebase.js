import {firebaseConfig} from "./firebaseConfig.js"
import Firebase from 'firebase'
export const firebaseApp = Firebase.initializeApp(firebaseConfig)
// export const db = firebaseApp.database();
// export const testdb = firebaseApp.database().ref('test');
// console.log(firebaseApp.database());
// console.log(firebaseApp.database().ref('info').child('name'));
// // export const info = db.ref('info').once('value').then(function(snapshot) {  return snapshot.val()}
// // console.log(info);
// // console.log(db);
// export function getValue(path){
//   return(db.ref(path).on('value')
//   .then(function(snapshot) {
//   return snapshot.val()
//     }
//   ))
// }
