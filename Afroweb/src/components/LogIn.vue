<template>
  <div class="hello">
   <form class="form-signin" @submit="logIn">
            <input type="email" id="inputEmail" class="form-control mt-3" placeholder="Email address" required autofocus v-model="email">
            <input type="password" id="inputPassword" class="form-control mt-3" placeholder="Password" required v-model="password">
            <button class="btn btn-lg bg-button btn-block btn-signin mt-3" type="submit">Log In</button>
    </form><!-- /form -->
    <h1>{{ error }}</h1><br>
    <button v-on:click="signIn">Sign In</button><br>
  </div>
</template>

<script>
import {firebaseApp} from '../firebase.js'
export default {
  name: 'LogIn',
  data () {
    return {
      error: '',
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
    signIn: function () {
      this.$router.replace("/signin")
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
