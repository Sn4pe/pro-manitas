<script setup>

</script>

<template>
  <div class="flex justify-center my-20">
    <form action="" method="post" class="flex flex-col w-1/2 lg:w-1/4">
      <label for="user" class="py-2">Usuario</label>
      <input type="text" name="user" id="user" class="inputCustom">
      <label for="pass" class="py-2 mt-4">Contraseña</label>
      <input type="password" name="pass" id="pass" class="inputCustom">

      <button type="button" class="mt-6 py-2 px-4 bg-ligth_orange hover:bg-primary focus:ring-offset-accent text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg">        Login
      </button>

    </form>
  </div>

</template>

<style scoped>

</style>