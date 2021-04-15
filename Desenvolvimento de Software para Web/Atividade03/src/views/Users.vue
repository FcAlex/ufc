<template>
  <div>
    <button @click="fetchUsers" type="button" class="btn btn-success mb-2">Resultado</button>
    <ul class="list-group">
      <li v-for="user in limitador" :key="user.id"  class="list-group-item">
          <p>Name: {{ user.name }}</p>
          <p>Email: {{ user.email }}</p>
          <p>Address: 
            {{ user.address.street }}, {{ user.address.suite }},
            {{ user.address.city }}
          </p>
          <p>Geo: {{user.address.geo.lat}}, {{user.address.geo.lng}} </p>
          <p>Phone: {{ user.phone }}</p>
          <p>WebSite: {{ user.website }}</p>
          <p>Company: {{user.company.name}}, {{user.company.catchPhrase}}, {{user.company.bs}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      users: [],
      baseURI: "https://jsonplaceholder.typicode.com/users",
    };
  },
  methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.users = result.data;
      });
    },
  },
  computed: {
    limitador: function () {
      return this.users.filter((i) => i.id < 6);
    },
  },
};
</script>

<style>

</style>