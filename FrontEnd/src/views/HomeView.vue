<template>

  <div class="flex flex-col md:flex-row items-center justify-center m-20 bg-secondary">
    <img src="../assets/logo.jpeg" alt="imagen_logo" class="w-1/4">
    <div class="flex flex-col p-2 text-center w-1/2">
      <h2 class="text-6xl text-white">El servicio profesional que necesitas, <strong class="text-ligth_orange">cuándo y dónde lo necesitas</strong>.</h2>
      <i class="text-xl my-6">Electricistas, fontaneros y mucho más... <strong>¡Soluciona tus problemas del hogar con solo unos clics!</strong></i>
    </div>
  </div>
  <div class="arrow-down-container" @click="scrollToImages">
    <a class="arrow-down" @click="scrollToImages"></a>
  </div>
  <img src="../assets/waves.svg" alt="imagen_separador" class="max-h-56 object-cover">
  <div id="images" class="bg-primary flex flex-col justify-center items-center py-32 px-4">
    <h2 class="text-3xl font-bold p-6">Los mejores especialistas y mucho más en España</h2>
    <div class="flex flex-col justify-center">
      <div class="flex flex-wrap justify-center">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa1">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa2">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa3">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa4">
      </div>
      <div class="flex flex-wrap justify-center">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa5">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa6">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa7">
        <img class="rounded-image" src="https://placehold.co/400" alt="empresa8">
      </div>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
export default {
  methods: {
    scrollToImages() {
      const element = document.getElementById('images');
      element.scrollIntoView({ behavior: 'smooth' });
    },
  },
  mounted() {
    UserService.getPublicContent().then(
        (response) => {
          this.content = response.data;
        },
        (error) => {
          this.content =
              (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
              error.message ||
              error.toString();
        }
    );
  },
};

</script>

<style>
.rounded-image {
  width: 150px;
  height: auto;
  margin: 20px;
  clip-path: circle(50%);
}
.arrow-down-container {
  display: flex;
  justify-content: center;
  margin: 20px;
}

.arrow-down {
  width: 0;
  height: 0;
  border-left: 15px solid transparent;
  border-right: 15px solid transparent;
  border-top: 30px solid #FEF0D3;
  animation: bounce 1s infinite;
}

.arrow-down:hover {
  border-top: 30px solid #f3e19c;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-20px);
  }
}
</style>
