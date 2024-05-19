<template>
  <Page>
    <div class="w-1/2 p-6">
      <h3 class="text-lg font-bold mb-4">Detalle del Servicio</h3>
      <div v-if="service">
        <button class="mb-4 p-2 text-white border rounded" @click="goBack">Regresar</button>
        <h4 class="text-md font-semibold mb-2">Nombre del servicio: {{ service.nombreServicio }}</h4>
        <p>Descripción: {{ service.descripcion }}</p>
        <p>Tarifa: {{ service.tarifas }}</p>
        <p v-if="service.propietario && service.propietario.user">Propietario: {{ service.propietario.user.username }}</p>
        <div v-if="isOwner">
          <button class="mb-4 p-2 text-white bg-blue-500 border rounded" @click="enableEditing">Editar</button>
          <div v-if="editing">
            <input v-model="service.nombreServicio" placeholder="Nombre del servicio" />
            <input v-model="service.descripcion" placeholder="Descripción" />
            <input v-model="service.tarifas" type="number" placeholder="Tarifa" />
            <button class="mb-4 p-2 text-white bg-green-500 border rounded" @click="updateService">Guardar</button>
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
import ServicesService from "../services/services.service";
import Page from "@/views/Page.vue";
import TokenService from "@/services/token.service";

export default {
  name: "ServiceDetail",
  components: { Page },
  data() {
    return {
      service: null,
      isOwner: false,
      editing: false
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1)
    },
    enableEditing() {
      this.editing = true;
    },
    updateService() {
      const serviceId = this.$route.params.id;
      const token = TokenService.getLocalAccessToken();
      ServicesService.updateService(serviceId, this.service, token).then(response => {
        this.service = response.data;
        this.editing = false;
      }).catch(error => {
        console.error(error);
      });
    }
  },
  mounted() {
    const serviceId = this.$route.params.id;
    const token = TokenService.getLocalAccessToken();

    ServicesService.getService(serviceId, token).then((response) => {
      this.service = response.data;
    }, (error) => {
      this.service = (error.response && error.response.data && error.response.data.message) || error.message || error.toString();
    });

    if (token) {
      ServicesService.checkOwnership(serviceId, token).then(response => {
        this.isOwner = response.data;
      }).catch(error => {
        console.error(error);
      });
    }
  }
};
</script>
