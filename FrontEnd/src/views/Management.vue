<template>
    <Page>
      <div class="flex flex-col justify-center my-20">
        <header>
          <h1 id="title_Management" class="w-auto lg:order-3 lg:w-3/12 lg:text-center text-ligth_orange text-4xl">
            <strong> {{ currentUser?.username }} </strong>
          </h1>
        </header>
        <div id="actualizarServicios" v-if="updateUser">
          <form @submit.prevent="updateServices">
            <div>
              <label class="py-2 mt-4" for="descripcion_servicio">Descripción del Proveedor:</label>
              <input class="inputCustom" v-model="updateUser.descripcion_servicio" id="descripcion_servicio" type="text" required/>
            </div>
            <div>
              <label class="py-2 mt-4" for="disponibilidad">Disponibilidad:</label>
              <input class="inputCustom" v-model="updateUser.disponibilidad" id="disponibilidad" type="checkbox"/>
            </div>
            <button>Actualizar Profesional</button>
          </form>
        </div>
      </div>
    </Page>
  </template>
  
  <script>
  import providerService from "@/services/provider.service";
  import Page from "@/views/Page.vue";
  
  export default {
    components: {Page},
    data() {
      return {
        currentUser: null,
        updateUser: {}, 
        message: "",
      };
    },
    mounted() {
      this.currentUser = this.$store.state.auth.user;
      this.getProviderInfo();
    },
    methods: {
      updateServices(){
        
      },
      async getProviderInfo(){
        const userId = this.currentUser.id;
        try {
          const response = await providerService.getProvider(userId);
          this.updateUser = response.data;
          console.log(response.data);
        } catch (error) {
          this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
        }
      }
    },
  };
  </script>
  
  <style>
  #title_Management {
      margin-top: -2em;
  }
  </style>
  