<template>
  <div>
    <form class="form-signin" @submit="verPassEqual">
            <input type="email" id="inputEmail" class="form-control mt-3" placeholder="Email address" required autofocus v-model="email">
            <input type="password" id="inputPassword" class="form-control mt-3" placeholder="Password" required v-model="password">
            <input type="password" id="verifiedPassword" class="form-control mt-3" placeholder="Verify Password" required v-model="verifiedPassword">
            <button class="btn btn-lg bg-button btn-block btn-signin mt-3" type="submit">Sign Up</button>
        </form><!-- /form -->
    <h1>{{ error }}</h1><br>
    <button v-on:click="logIn">Log In</button><br>
  </div>
</template>

<script>
import {firebaseApp} from '../firebase.js'
export default {
  name: 'SignIn',
  data () {
    return {
      error: '',
      email: '',
      password: '',
      verifiedPassword: ''
    }
  },
  function: {

  },
  methods: {
    verPassEqual: function () {
      if(this.password==this.verifiedPassword){this.verPassLong()}
      else{this.error="Passwords are not equal"}
    },
    verPassLong: function () {
      console.log(this.password.length);
      if(this.password.length>=6){this.signIn()}
      else{this.error="Password is too short"}
    },
    signIn: function () {
      console.log(this.email);
      console.log(this.password);
      firebaseApp.auth().createUserWithEmailAndPassword(this.email, this.password)
      .then((user)=>{
        this.$router.replace("/profile")
      })
      .catch((error)=>{
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        this.error=error.code
        // ...
        });
    },
    logIn: function () {
      this.$router.replace("/login")
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
