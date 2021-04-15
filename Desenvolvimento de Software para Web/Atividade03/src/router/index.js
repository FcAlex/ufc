import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/users',
    name: 'Users',
    component: function () {
      return import('../views/Users.vue')
    }
  },
  {
    path: '/todos',
    name: 'Todos',
    component: function () {
      return import('../views/Todos.vue')
    }
  },
  {
    path: '/photos',
    name: 'Photos',
    component: function () {
      return import('../views/Photos.vue')
    }
  },
  {
    path: '/comments',
    name: 'Comments',
    component: function () {
      return import('../views/Comments.vue')
    }
  },
  {
    path: '/posts',
    name: 'Posts',
    component: function () {
      return import('../views/Posts.vue')
    }
  },
  {
    path: "*",
    redirect: "/"
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
