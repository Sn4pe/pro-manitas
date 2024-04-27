import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import User from "@/views/User/User.vue";


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: "/admin/usuario",
    name: "AdminUser",
    component: User,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
