<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Lista de Servicios</h3>

      <div class="flex justify-end w-full" v-if="isProvider">
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
            <p>Propietario: {{ service.propietario }}</p>
            <p>Disponibilidad: {{ service.disponibilidad ? 'Sí' : 'No' }}</p>
          </router-link>
          <button v-if="!isProvider" class="mt-2 p-2 text-white bg-green-500 border rounded" @click="contratarServicio(service.id)">
            Contratar
          </button>
        </li>
      </ul>
    </div>
  </Page>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';
import ServicesService from "@/services/services.service";
import Page from "@/views/Page.vue";
import TokenService from "@/services/token.service";

export default {
  name: "ServiceList",
  components: {Page},
  setup() {
    const store = useStore();
    const currentUser = computed(() => store.state.auth.user);
    const isProvider = computed(() => currentUser.value && currentUser.value.roles && currentUser.value.roles.includes('ROLE_PROVIDER'));

    return {isProvider, currentUser};
  },
  data() {
    return {
      content: [],
      availabilityFilter: false,
      message: ""
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
    },
    contratarServicio(serviceId) {
      const token = TokenService.getLocalAccessToken();
      ServicesService.contratarServicio(serviceId, token).then((response) => {
        this.message = "Servicio contratado con éxito!";
      }).catch((error) => {
        this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      });
    }
  }
};
</script>
