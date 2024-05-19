<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Servicios Contratados</h3>
      <ul class="space-y-4">
        <li v-for="(service, index) in services" :key="index" class="border p-4 rounded-md shadow">
          <h4 class="text-md font-semibold mb-2">Nombre del servicio: {{ service.servicio.nombreServicio }}</h4>
          <p>Descripción: {{ service.servicio.descripcion }}</p>
          <p>Tarifa: {{ service.servicio.tarifas }}</p>
          <p>Fecha de Contrato: {{ new Date(service.fechaContrato).toLocaleDateString() }}</p>
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
  name: "MyServices",
  components: { Page },
  setup() {
    const store = useStore();
    const currentUser = computed(() => store.state.auth.user);

    return { currentUser };
  },
  data() {
    return {
      services: [],
      message: ""
    };
  },
  mounted() {
    this.fetchMyServices();
  },
  methods: {
    fetchMyServices() {
      const token = TokenService.getLocalAccessToken();
      ServicesService.getMyServices(token).then((response) => {
        this.services = response.data;
      }).catch((error) => {
        this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      });
    }
  }
};
</script>
