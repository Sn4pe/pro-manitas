<template>
  <div class="flex flex-col min-h-[100vh]">
    <Header/>
    <router-view class="router-vista"/>
    <Footer/>
  </div>
</template>

<script>
import Footer from "@/views/Footer.vue";
import Header from "@/views/Header.vue";

export default {
  name: "app",
  components: {Header, Footer},
};
</script>

<style>

</style>
