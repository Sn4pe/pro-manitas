<script setup>

</script>

<template>

  <nav class="flex flex-wrap items-center justify-between p-4 bg-secondary">
    <div class="w-auto lg:order-2 lg:w-1/5 lg:text-center">
      <a class="text-xl font-semibold font-heading text-ligth_orange" href="#">
        Pro<strong class="text-melocoton">Manitas</strong>
      </a>
    </div>
    <div class="block lg:hidden">
      <button class="flex items-center px-3 py-2 text-text-melocoton border border-text-melocoton rounded navbar-burger">
        <svg class="w-3 h-3 fill-current" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
          <title>
            Menu
          </title>
          <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z">
          </path>
        </svg>
      </button>
    </div>
    <div class="hidden w-full navbar-menu lg:order-1 lg:block lg:w-2/5">
      <a class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="#">
        Home
      </a>
      <a class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="#">
        Team
      </a>
      <a class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="#">
        Galery
      </a>
    </div>
    <div class="hidden w-full navbar-menu lg:order-3 lg:block lg:w-2/5 lg:text-right">
      <a class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="#">
        Content
      </a>
      <a class="block mt-4 mr-10 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="#">
        FAQ
      </a>
      <a class="block mt-4 text-text-melocoton lg:inline-block lg:mt-0 hover:text-ligth_orange" href="/login">
        Login
      </a>
    </div>
  </nav>

</template>

<style scoped>

</style>