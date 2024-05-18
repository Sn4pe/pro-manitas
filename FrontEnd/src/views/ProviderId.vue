<template>
  <Page>
    <div v-if="provider" class="w-1/2 p-6">
      <button class="mb-4 p-2 text-white border rounded" @click="goBack">Regresar</button>
      <h3 class="text-lg font-bold mb-4">Detalles del Proveedor</h3>
      <div class="border p-4 rounded-md shadow">
        <h4 class="text-md font-semibold mb-2">Usuario: {{ provider.user.username }}</h4>
        <p>Email: {{ provider.user.email }}</p>
        <p>Descripción de servicio: {{ provider.descripcion_servicio }}</p>
        <p>Disponibilidad: {{ provider.disponibilidad ? 'Sí' : 'No' }}</p>
      </div>
    </div>
  </Page>
</template>

<script>
import ProviderService from "../services/provider.service";
import Page from "@/views/Page.vue";

export default {
  name: "ProviderDetails",
  components: {Page},
  data() {
    return {provider: null};
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    }
  },
  mounted() {
    const providerId = this.$route.params.id;
    ProviderService.getProvider(providerId).then((response) => {
      this.provider = response.data;
    }, (error) => {
      console.error(
          (error.response && error.response.data && error.response.data.message) || error.message || error.toString()
      );
    });
  },
};
</script>