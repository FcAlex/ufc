<template>
  <div>
    <button @click="fetchUsers" type="button" class="btn btn-success mb-2">
      Resultado
    </button>
    <ul class="list-group">
      <li v-for="post in impares" :key="post.id" class="list-group-item">
        <p>User id: {{ post.userId }}</p>
        <p>id: {{ post.id }}</p>
        <p>Title: {{ post.title }}</p>
        <p>Text: {{ post.body }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Posts",
  data() {
    return {
      posts: [],
      baseURI: "https://jsonplaceholder.typicode.com/posts",
    };
  },
  methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.posts = result.data;
      });
    },
  },
  computed: {
    impares: function () {
      return this.posts.filter((i) => i.id % 2 == 1);
    },
  },
};
</script>

<style>
</style>