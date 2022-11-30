import Vue from 'vue'
import VueRouter from 'vue-router'
import SignUp from '../views/SignUp.vue'
import Login from '../views/LoginPage.vue'
import HomeView from '../views/HomeView.vue'
import CreateRoom from '../views/CreateRoom.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'signUp',
    component: SignUp
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/createRoom',
    name: 'createRoom',
    component: CreateRoom
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
