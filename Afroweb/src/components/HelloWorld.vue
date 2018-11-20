<template>
  <div class="hello">
    <input type="email" v-model="email" placeholder="e-mail"><br>
    <input type="password" v-model="password" placeholder="password"><br>
    <button v-on:click="logIn">Log In</button><br>
    <button v-on:click="logOut">Log Out</button><br>
    <h1>{{ state }}</h1><br>
  </div>
</template>

<script>
import {firebaseApp} from '../firebase.js'
export default {
  name: 'HelloWorld',
  data () {
    return {
      state: 'Nothing happen',
      email: '',
      password: ''
    }
  },
  function: {

  },
  methods: {
    logIn: function () {
      this.state="Logging In"
      console.log(this.email);
      console.log(this.password);
      firebaseApp.auth().signInWithEmailAndPassword(this.email, this.password)
      .then((user)=>{
        this.state=user.user.email
      })
      .catch((error)=>{
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        this.state=error.code
        // ...
        });
    },
    logOut: function () {
      this.state="Logging Out"
      firebaseApp.auth().signOut()
      .then(()=>{this.state="Log Out Succesfully"})
      .catch((error)=>{console.log(error.code)})
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
