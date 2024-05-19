<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Lista de Servicios</h3>

      <div class="flex justify-end w-full">
        <button class="bg-primary my-2 hover:bg-ligth_orange text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="goToAddService">
          Añadir Servicio
        </button>
      </div>
      <ul class="space-y-4">
        <li v-for="(service, index) in filteredServices" :key="index" class="border p-4 rounded-md shadow">
          <router-link :to="'/service/' + service.id">
            <h4 class="text-md font-semibold mb-2">Nombre del servicio: {{ service.nombreServicio }}</h4>
            <p>Descripción: {{ service.descripcion }}</p>
            <p>Tarifa: {{ service.tarifas }}</p>
          </router-link>
        </li>
      </ul>
    </div>
  </Page>
</template>

<script>
import ServicesService from "@/services/services.service";
import Page from "@/views/Page.vue";
import TokenService from "@/services/token.service";

export default {
  name: "ServiceList",
  components: { Page },
  data() {
    return {
      content: [],
      availabilityFilter: false
    };
  },
  computed: {
    filteredServices() {
      return this.availabilityFilter
          ? this.content.filter(service => service.disponibilidad)
          : this.content;
    }
  },
  mounted() {
    this.fetchServices();
  },
  methods: {
    fetchServices() {
      const token = TokenService.getLocalAccessToken();
      ServicesService.getAllServices(token).then((response) => {
        this.content = response.data;
      }, (error) => {
        this.content = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      });
    },
    goToAddService() {
      this.$router.push('/add-service');
    }
  }
};
</script>
