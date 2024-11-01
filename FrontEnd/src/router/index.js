import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import Profile from "@/views/Profile.vue";
import Provider from "@/views/Provider.vue";
import ProviderId from "@/views/ProviderId.vue";
import Service from "@/views/Service.vue";
import ServiceId from "@/views/ServiceId.vue";
import AddService from "@/views/AddService.vue";
import MyServices from "@/views/MyServices.vue";
import Management from "@/views/Management.vue";


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
  },
  {
    path: '/register',
    name: 'register',
    component: Register,
  },
  {
    path: "/provider",
    name: "provider",
    component: Provider,
  },
  {
    path: "/provider/:id",
    name: "providerId",
    component: ProviderId,
  },
  {
    path: "/service",
    name: "service",
    component: Service,
  },
  {
    path: "/service/:id",
    name: "serviceId",
    component: ServiceId,
  },
  {
    path: '/add-service',
    name: 'AddService',
    component: AddService
  },
  {
    path: '/my-services',
    name: 'MyServices',
    component: MyServices
  },
  {
    path: "/management",
    name: "Management",
    component: Management,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
