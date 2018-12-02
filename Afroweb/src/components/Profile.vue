<template>
  <div>
    <h1>{{ user }}</h1><br>
  </div>
</template>

<script>
import {firebaseApp} from '../firebase.js'
export default {
  name: 'Profile',
  data () {
    return {
      user: firebaseApp.auth().currentUser.email,
    }
  },
  function: {},
  methods: {
    logOut: function () {
      firebaseApp.auth().signOut()
      .then(()=>{this.state="Log Out Succesfully"})
      .catch((error)=>{console.log(error.code)})
    }
  }
}
</script>
