<template>
  <nav class="flex flex-wrap items-center justify-between p-4 bg-secondary">

    <div class="w-full navbar-menu lg:order-1 lg:w-3/12 lg:flex lg:flex-row">
      <router-link to="/service" class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Servicios</router-link>
      <router-link to="/provider" class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Profesionales</router-link>
      <router-link v-if="showModeratorBoard" to="/my-services" class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Mis servicios</router-link>

    </div>

    <div class="w-auto lg:order-3 lg:w-3/12 lg:text-center">
      <router-link to="/" class="text-xl font-semibold font-heading text-ligth_orange">Pro<strong class="text-melocoton">Manitas</strong></router-link>
    </div>

    <div v-if="currentUser" class="hidden w-full navbar-menu lg:order-1 lg:block lg:w-1/12">
      <router-link v-if="showAdminBoard" to="/admin" class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Admin Board</router-link>
      <router-link v-if="showModeratorBoard" to="/management" class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Gestión Profesional</router-link>
    </div>

    <div v-if="!currentUser" class="hidden w-full navbar-menu lg:order-3 lg:block lg:w-1/12 lg:text-right lg:flex lg:flex-row">
      <router-link to="/register" class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Sign Up</router-link>
      <router-link to="/login" class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">Login</router-link>
    </div>

    <div v-if="currentUser" class="hidden w-full navbar-menu lg:order-3 lg:block lg:w-1/5 lg:text-right">
      <router-link to="/profile" class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange">{{ currentUser.username }}</router-link>
      <a class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" @click="logOut">LogOut</a>
    </div>
  </nav>
</template>

<script setup>
import { computed } from 'vue';
import { useStore } from 'vuex';
import router from "@/router";

const store = useStore();

const currentUser = computed(() => store.state.auth.user);

const showAdminBoard = computed(() => currentUser.value && currentUser.value.roles && currentUser.value.roles.includes('ROLE_ADMIN'));

const showModeratorBoard = computed(() => currentUser.value && currentUser.value.roles && currentUser.value.roles.includes('ROLE_PROVIDER'));

const logOut = () => {
  store.dispatch('auth/logout');
  router.push('/login');
};

</script>