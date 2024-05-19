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

      <form @submit.prevent="createProvider">
        <div>
          <label class="py-2 mt-4" for="descripcion_servicio">Descripción del servicio:</label>
          <input class="inputCustom" v-model="provider.descripcion_servicio" id="descripcion_servicio" type="text" required/>
        </div>
        <div>
          <label class="py-2 mt-4" for="disponibilidad">Disponibilidad:</label>
          <input class="inputCustom" v-model="provider.disponibilidad" id="disponibilidad" type="checkbox"/>
        </div>
        <button type="submit"><strong class="text-ligth_orange">Crear proveedor</strong></button>
        <div v-if="message" class="alert alert-danger" role="alert">
          {{ message }}
        </div>
      </form>
    </div>
  </Page>
</template>

<script>
import Page from "@/views/Page.vue";
import TokenService from "@/services/token.service";
import AuthService from "@/services/auth.service";

export default {
  name: 'Profile',
  components: { Page },
  data() {
    return {
      provider: {
        descripcion_servicio: '',
        disponibilidad: false
      },
      message: ''
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    async createProvider() {
      try {
        let accessToken = TokenService.getLocalAccessToken();
        const refreshToken = TokenService.getLocalRefreshToken();

        const refreshResponse = await AuthService.refreshToken(refreshToken);
        accessToken = refreshResponse.data.accessToken;

        TokenService.updateLocalAccessToken(accessToken);

        const response = await fetch(`http://localhost:8000/api/provider/create/${this.currentUser?.id}`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${accessToken}`
          },
          body: JSON.stringify(this.provider)
        });

        if (!response.ok) {
          throw new Error('Error en la creación del proveedor');
        }

        const data = await response.json();
        console.log("Data sent:", this.currentUser?.id, this.provider, accessToken);
        console.log(data);
        this.message = 'Proveedor creado con éxito';
      } catch (error) {
        console.error(error);
        this.message = error.message;

        if (error.response && error.response.status === 401) {
          this.$router.push('/login');
        }
      }
    }
  },
  mounted() {
    this.currentUser = TokenService.getUser();
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>
