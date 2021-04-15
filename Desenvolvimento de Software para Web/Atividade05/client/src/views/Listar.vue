<template>
	<section class="container">
		<table class="table table-sm table-bordered">
			<thead>
				<tr>
					<th scope="col">id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Fabricação</th>
					<th scope="col">Modelo</th>
					<th scope="col">Venda</th>
				</tr>
			</thead>
			<tbody v-for="car in cars" :key="car.id">
        <tr>
          <td>{{car.id}}</td>
          <td>{{car.nome}}</td>
          <td>{{car.marca}}</td>
          <td>{{car.anoFabricacao}}</td>
          <td>{{car.anoModelo}}</td>
          <td>{{car.dataVenda}}</td>
        </tr>
      </tbody>
		</table>
	</section>
</template>

<script>
export default {
	name: "Listar",
	data() {
		return {
			cars: [],
			baseURI: "http://localhost:8080/api/carros",
		};
	},
	created() {
		this.$http
			.get(this.baseURI)
			.then((result) => {
				if(result.status === 200) {
					this.cars = result.data;
				}
			})
			.catch((e) => {
				console.log(e);
			});
	},
};
</script>

<style>
</style>