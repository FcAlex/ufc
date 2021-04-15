<template>
  <div>
    <button @click="fetchUsers" type="button" class="btn btn-success mb-2 mr-2">Resultado URL</button>
    <label for="showUrl">Show url</label> <input type="checkbox" v-model="check" id="showUrl">
    <ul class="list-group">
      <li v-for="photo in photos" :key="photo.id"  class="list-group-item">
          <img :src="photo.url" v-if="!check">
          <p v-else>{{photo.url}}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
    name: "Photos",
    data() {
        return {
            photos: [],
            check: false,
            baseURI: "https://jsonplaceholder.typicode.com/photos"
        }
    },
    methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.photos = result.data.filter((p) => p.albumId == 3);
      });
    },
  }
}
</script>

<style>
#showUrl, label {
    cursor: pointer;
}
</style>