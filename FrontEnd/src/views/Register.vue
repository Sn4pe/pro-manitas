<template>
  <div class="flex justify-center my-20">
    <Form @submit="handleRegister" :validation-schema="schema" class="flex flex-col w-1/2 lg:w-1/4">
      <div v-if="!successful">
        <label for="user" class="py-2">Usuario</label>
        <Field type="text" name="username" id="username" class="inputCustom"/>
        <ErrorMessage name="username" class="error-feedback"/>

        <label for="email" class="py-2">Email</label>
        <Field type="email" name="email" id="email" class="inputCustom"/>
        <ErrorMessage name="email" class="error-feedback"/>

        <label for="pass" class="py-2 mt-4">Contraseña</label>
        <Field type="password" name="password" id="pass" class="inputCustom"/>
        <ErrorMessage name="password" class="error-feedback"/>

        <button type="submit"
                class="mt-6 py-2 px-4 bg-ligth_orange hover:bg-primary focus:ring-offset-accent text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none focus:ring-2 focus:ring-offset-2 rounded-lg">
          Register
        </button>
      </div>
    </Form>
    <div
        v-if="message"
    >
      {{ message }}
    </div>
  </div>

</template>
<script>
import {Form, Field, ErrorMessage} from "vee-validate";
import * as yup from "yup";

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      username: yup
          .string()
          .required("¡Se requiere el nombre de usuario!")
          .min(3, "¡Debe tener al menos 3 caracteres!")
          .max(20, "¡Debe tener como máximo 20 caracteres!"),
      email: yup
          .string()
          .required("¡Se requiere el correo electrónico!")
          .email("¡El correo electrónico no es válido!")
          .max(50, "¡Debe tener como máximo 50 caracteres!"),
      password: yup
          .string()
          .required("¡Se requiere la contraseña!")
          .min(6, "¡Debe tener al menos 6 caracteres!")
          .max(40, "¡Debe tener como máximo 40 caracteres!"),
    });

    return {
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },
  methods: {
    handleRegister(user) {
      console.log('Form submitted', user);
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
          (data) => {
            this.message = data.message;
            this.successful = true;
            this.loading = false;
          },
          (error) => {
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            this.successful = false;
            this.loading = false;
          }
      );
    },
  },
};
</script>
<style scoped>
.error-feedback {
  color: red;
}
</style>