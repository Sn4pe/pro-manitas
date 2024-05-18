<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Lista de Profesionales</h3>
      <button class="mb-4 p-2 border rounded" @click="toggleAvailabilityFilter">
        {{ availabilityFilter ? 'Mostrar todos los profesionales' : 'Filtrar por disponibilidad' }}
      </button>
      <ul class="space-y-4">
        <li v-for="(provider, index) in filteredProviders" :key="index" class="border p-4 rounded-md shadow">
          <router-link :to="'/provider/' + provider.id">
            <h4 class="text-md font-semibold mb-2">Usuario: {{ provider.user.username }}</h4>
            <p>Email: {{ provider.user.email }}</p>
            <p>Descripción de servicio: {{ provider.descripcion_servicio }}</p>
            <p>Disponibilidad: {{ provider.disponibilidad ? 'Sí' : 'No' }}</p>
          </router-link>
        </li>
      </ul>
    </div>
  </Page>
</template>

<script>
import ProviderService from "../services/provider.service";
import Page from "@/views/Page.vue";

export default {
  name: "Home",
  components: {Page},
  data() {
    return {
      content: [],
      availabilityFilter: false
    };
  },
  computed: {
    filteredProviders() {
      return this.availabilityFilter
          ? this.content.filter(provider => provider.disponibilidad)
          : this.content;
    }
  },
  mounted() {
    ProviderService.getAllProviders().then((response) => {
      this.content = response.data;
    }, (error) => {
      this.content = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
    });
  },
  methods: {
    toggleAvailabilityFilter() {
      this.availabilityFilter = !this.availabilityFilter;
    }
  },
};
</script>