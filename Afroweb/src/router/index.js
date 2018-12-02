import Vue from 'vue'
import Router from 'vue-router'
import LogIn from '@/components/LogIn'
import SignIn from '@/components/SignIn'
import Profile from '@/components/Profile'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login', name: 'LogIn', component: LogIn
    },
    {
      path: '/signin', name: 'SignIn', component: SignIn
    },
    {
      path: '/profile', name: 'Profile', component: Profile
    }
  ]
})
