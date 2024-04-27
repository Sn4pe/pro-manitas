<script>

const axios = require('axios');
export default {
  name: "User",
  data(){
    return {
      baseURL : "http://localhost:8000",
      users: null,
    }
  },
  methods: {
    async getUsers(){
      await axios.get(this.baseURL + "/admin/usuario/")
          .then(res => this.users = res.data)
          .catch(err => console.log(err))
    }
  },
  mounted() {
    this.getUsers();
  }
}
</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-12 text-center">
        <h3 class="pt-3">Our Users</h3>
      </div>
    </div>
    <div class="row">
      <div v-for="user of users" :key="user.id" class="">
        <div class="card" style="width: 18rem;">
          <div class="card-body">
            <h5 class="card-title">{{user.name}}</h5>
            <p class="card-text">{{user.email}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>