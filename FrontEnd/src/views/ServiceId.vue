<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Detalle del Servicio</h3>
      <div v-if="service">
        <button class="mb-4 p-2 text-white border rounded" @click="goBack">Regresar</button>
        <h4 class="text-md font-semibold mb-2"><strong class="text-ligth_orange">Nombre del servicio:</strong> {{ service.nombreServicio }}</h4>
        <p><strong class="text-ligth_orange">Descripción:</strong> {{ service.descripcion }}</p>
        <p><strong class="text-ligth_orange">Tarifa: </strong>{{ service.tarifas }}</p>
        <div v-if="isOwner">
          <div class="flex justify-end w-full">
            <button class="bg-primary my-2 hover:bg-ligth_orange text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="enableEditing">Editar</button>
          </div>

          <div v-if="editing">
            <div class="mb-4">
              <label for="nombreServicio" class="block text-sm font-bold mb-2">Nombre del servicio:</label>
              <input v-model="service.nombreServicio" id="nombreServicio" placeholder="Nombre del servicio" class="py-2 px-3 inputCustom focus:ring-2 focus:ring-primary" required/>
            </div>

            <div class="mb-4">
              <label for="descripcion" class="block text-sm font-bold mb-2">Descripción:</label>
              <input v-model="service.descripcion" id="descripcion" placeholder="Descripción" class="inputCustom py-2 px-3 focus:ring-2 focus:ring-primary" required/>
            </div>

            <div class="mb-4">
              <label for="tarifas" class="block text-sm font-bold mb-2">Tarifa:</label>
              <input v-model="service.tarifas" id="tarifas" type="number" placeholder="Tarifa" class="inputCustom py-2 px-3 focus:ring-2 focus:ring-primary" required min="0"/>
            </div>
            <div class="flex justify-end w-full">

            <button class="bg-melocoton text-ligth_orange my-2 hover:bg-primary hover:text-melocoton font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="updateService">Guardar</button>
            </div>

          </div>
        </div>
      </div>
      <div v-else>
        <p>Cargando...</p>
      </div>
    </div>
  </Page>
</template>

<script>
import ServicesService from "@/services/services.service";
import Page from "@/views/Page.vue";
import TokenService from "@/services/token.service";

export default {
  name: "ServiceDetail",
  components: { Page },
  data() {
    return {
      service: null,
      isOwner: false,
      editing: false,
      currentUser: null,
      message: "",
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },
    enableEditing() {
      this.editing = !this.editing;
    },
    async updateService() {
      const serviceId = this.$route.params.id;
      const token = TokenService.getLocalAccessToken();

      try {
        const response = await ServicesService.updateService(serviceId, this.service, token);
        this.service = response.data;
        this.editing = false;
      } catch (error) {
        this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
        console.error(this.message);
      }
    },
  },
  async mounted() {
    const serviceId = this.$route.params.id;
    this.currentUser = TokenService.getUser();

    try {
      const response = await ServicesService.getService(serviceId, this.currentUser.accessToken);
      this.service = response.data;
    } catch (error) {
      this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      console.error(this.message);
    }

    try {
      const response = await ServicesService.checkOwnership(serviceId, this.currentUser.accessToken);
      this.isOwner = response.data;
    } catch (error) {
      this.message = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
      console.error(this.message);
    }
  }
};
</script>
