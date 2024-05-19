<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4 text-primary">Añadir Nuevo Servicio</h3>
      <button class="mb-4 p-2 text-white border rounded" @click="goBack">Regresar</button>

      <form @submit.prevent="addService">
        <div class="mb-4">
          <label class="block text-sm font-bold mb-2" for="nombreServicio">
            Nombre del servicio:
          </label>
          <input v-model="service.nombreServicio" class="shadow appearance-none border rounded w-full py-2 px-3 leading-tight focus:outline-none focus:shadow-outline inputCustom" id="nombreServicio" type="text" required>
          <p v-if="errors.nombreServicio" class="text-red-500 text-xs italic">{{ errors.nombreServicio }}</p>
        </div>
        <div class="mb-4">
          <label class="block text-sm font-bold mb-2" for="descripcion">
            Descripción:
          </label>
          <textarea v-model="service.descripcion" class="shadow appearance-none border rounded w-full py-2 px-3 leading-tight focus:outline-none focus:shadow-outline inputCustom" id="descripcion" required></textarea>
          <p v-if="errors.descripcion" class="text-red-500 text-xs italic">{{ errors.descripcion }}</p>
        </div>
        <div class="mb-4">
          <label class="block text-sm font-bold mb-2" for="tarifas">
            Tarifa:
          </label>
          <input v-model="service.tarifas" class="shadow appearance-none border rounded w-full py-2 px-3 leading-tight focus:outline-none focus:shadow-outline inputCustom" id="tarifas" type="number" required>
          <p v-if="errors.tarifas" class="text-red-500 text-xs italic">{{ errors.tarifas }}</p>
        </div>
        <div class="flex items-center justify-between">
          <button class="bg-primary hover:bg-ligth_orange text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
            Añadir
          </button>
        </div>
        <div v-if="message" class="alert alert-danger mt-4" role="alert">
          {{ message }}
        </div>
      </form>
    </div>
  </Page>
</template>

<script>
import ServicesService from "../services/services.service";
import Page from "@/views/Page.vue";
import TokenService from "../services/token.service";

export default {
  name: "AddService",
  components: { Page },
  data() {
    return {
      service: {
        nombreServicio: "",
        descripcion: "",
        tarifas: null
      },
      message: "",
      errors: {
        nombreServicio: null,
        descripcion: null,
        tarifas: null
      }
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    async addService() {
      if (!this.validateService(this.service)) {
        this.message = "Validación fallida. Verifique los datos del servicio.";
        return;
      }
      const token = TokenService.getLocalAccessToken();
      try {
        const response = await ServicesService.createService(this.service, token);
        const serviceId = response.data.id;
        this.$router.push(`/service/${serviceId}`);
      } catch (error) {
        this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      }
    },
    validateService(service) {
      let isValid = true;
      this.errors = { nombreServicio: null, descripcion: null, tarifas: null }; // Reiniciar mensajes de error

      if (!service.nombreServicio || service.nombreServicio.length < 3) {
        this.errors.nombreServicio = "El nombre del servicio debe tener al menos 3 caracteres.";
        isValid = false;
      }
      if (!service.descripcion || service.descripcion.length < 5) {
        this.errors.descripcion = "La descripción debe tener al menos 5 caracteres.";
        isValid = false;
      }
      if (!service.tarifas || isNaN(service.tarifas) || service.tarifas <= 0) {
        this.errors.tarifas = "La tarifa debe ser un número mayor que 0.";
        isValid = false;
      }

      return isValid;
    }
  }
};
</script>
