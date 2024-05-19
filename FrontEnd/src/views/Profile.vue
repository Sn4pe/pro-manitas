<template>
  <Page>
    <div class="flex flex-col justify-center my-20">
      <header>
        <h3>
          <strong class="text-ligth_orange">Usuario:</strong>
          {{ currentUser?.username }}
        </h3>
      </header>
      <p class="py-2">
        <strong class="text-ligth_orange">Token:</strong>
        {{
          (currentUser?.accessToken?.substring(0, 20) || '') + ' ... ' + (currentUser?.accessToken?.substr(-20) || '')
        }}
      </p>
      <p class="py-2">
        <strong class="text-ligth_orange">Id:</strong>
        {{ currentUser?.id }}
      </p>
      <p class="py-2">
        <strong class="text-ligth_orange">Email:</strong>
        {{ currentUser?.email }}
      </p>
      <strong class="text-ligth_orange">Authorities:</strong>
      <ul class="py-2">
        <li v-for="role in currentUser?.roles" :key="role">{{ role }}</li>
      </ul>

      <div v-if="!isProvider">
        <form @submit.prevent="createProvider">
          <div>
            <label class="py-2 mt-4" for="descripcion_servicio">Descripción del servicio:</label>
            <input class="inputCustom" v-model="provider.descripcion_servicio" id="descripcion_servicio" type="text" required/>
          </div>
          <div>
            <label class="py-2 mt-4" for="disponibilidad">Disponibilidad:</label>
            <input class="inputCustom" v-model="provider.disponibilidad" id="disponibilidad" type="checkbox"/>
          </div>
          <button type="submit">Crear proveedor</button>
          <div v-if="message" class="alert alert-danger" role="alert">
            {{ message }}
          </div>
        </form>
      </div>
    </div>
  </Page>
</template>

<script>
import ProviderService from "@/services/provider.service";
import Page from "@/views/Page.vue";

export default {
  components: {Page},
  data() {
    return {
      currentUser: null,
      provider: {
        descripcion_servicio: "",
        disponibilidad: false,
      },
      message: "",
      isProvider: false,
    };
  },
  mounted() {
    this.currentUser = this.$store.state.auth.user;
    if (this.currentUser) {
      this.checkIfProvider();
    }
  },
  methods: {
    async checkIfProvider() {
      try {
        const response = await ProviderService.checkIfProvider(this.currentUser.id);
        this.isProvider = response.data.isProvider;
      } catch (error) {
        this.message =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      }
    },
    async createProvider() {
      const userId = this.currentUser.id;
      const token = this.currentUser.accessToken;

      try {
        const response = await ProviderService.createProvider(userId, this.provider, token);
        this.message = response.data.message;
        this.isProvider = true;
      } catch (error) {
        this.message =
            (error.response && error.response.data && error.response.data.message) ||
            error.message ||
            error.toString();
      }
    },
  },
};
</script>