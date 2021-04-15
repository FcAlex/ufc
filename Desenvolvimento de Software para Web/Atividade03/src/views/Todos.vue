<template>
  <div>
    <button @click="fetchUsers" type="button" class="btn btn-success mb-2">
      Resultado
    </button>
    <ul class="list-group">
      <li v-for="todo in todos" :key="todo.id" class="list-group-item">
        <p>User Id: {{todo.userId}} </p>
        <p>Id: {{todo.id}} </p>
        <p>Title: {{todo.title}} </p>
        <p>Completed: {{todo.completed}} </p>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Todos",
  data() {
    return {
      todos: [],
      baseURI: "https://jsonplaceholder.typicode.com/todos",
    };
  },
  methods: {
    fetchUsers: function () {
      this.$http.get(this.baseURI).then((result) => {
        this.todos = result.data.filter((todo) => todo.userId == 2 && todo.completed);
      });
    },
  }
};
</script>

<style>
</style>