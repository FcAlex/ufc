<template>
  <div>
    <button @click="fetchUsers" type="button" class="btn btn-success mb-2">Resultado</button>
    <ul class="list-group">
      <li v-for="comment in pares" :key="comment.id"  class="list-group-item">
          <p>Post id: {{comment.postId}} </p>
          <p>id: {{comment.id}} </p>
          <p>Name: {{comment.name}} </p>
          <p>Email: {{comment.email}} </p>
          <p>Message: {{comment.body}} </p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
name: "Comments",
  data() {
    return {
      comments: [],
      baseURI: "https://jsonplaceholder.typicode.com/comments",
    };
  },
  methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.comments = result.data;
      });
    },
  },
  computed: {
    pares: function () {
      return this.comments.filter((i) => i.id % 2 == 0);
    },
  },
}
</script>

<style>

</style>