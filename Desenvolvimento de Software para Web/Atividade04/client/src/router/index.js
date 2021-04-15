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
    path: '/listar',
    name: 'Listar',
    component: function () {
      return import('../views/Listar.vue')
    }
  },
  {
    path: '/cadastro',
    name: 'Cadastro',
    component: function () {
      return import('../views/Cadastro.vue')
    }
  },
  {
    path: '/busca',
    name: 'Busca',
    component: function () {
      return import('../views/Busca.vue')
    }
  },
  {
    path: '/filtro',
    name: 'Filtro',
    component: function () {
      return import('../views/Filtro.vue')
    }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
