<template>
	<section class="container">
		<div class="row">
			<div class="form col-5 d-block mx-auto p-3">
        <h1 class="h5">Filtro por quantidade</h1>
				<div class="input-group mb-3">
					<input
						type="number"
						class="form-control"
						placeholder="Quantos elementos você deseja?"
						v-model="qtd"
					/>
					<div class="input-group-append">
						<button
							type="button"
							class="btn btn-primary"
							@click="qtdCarros"
						>
							Filtrar
						</button>
					</div>
				</div>
			</div>

			<div class="form col-5 d-block mx-auto p-3">
        <h1 class="h5">Filtro por marca</h1>
				<div class="input-group mb-3">
					<input
						type="text"
						class="form-control"
						placeholder="Qual a marca que você busca?"
						v-model="marca"
					/>
					<div class="input-group-append">
						<button
							type="button"
							class="btn btn-primary"
							@click="filtroMarca"
						>
							Filtrar
						</button>
					</div>
				</div>
			</div>
		</div>

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
					<td>{{ car.id }}</td>
					<td>{{ car.nome }}</td>
					<td>{{ car.marca }}</td>
					<td>{{ car.anoFabricacao }}</td>
					<td>{{ car.anoModelo }}</td>
					<td>{{ car.dataVenda }}</td>
				</tr>
			</tbody>
		</table>
	</section>
</template>

<script>
export default {
	name: "Filtro",
	data() {
		return {
      cars: [],
      marca: "",
			qtd: 0,
			baseURI: "http://localhost:8080/server/api/filter",
		};
	},
	methods: {
		qtdCarros() {
			this.$http
				.get(this.baseURI + "?qtd=" + this.qtd)
				.then((result) => {
					this.cars = result.data;
				})
				.catch(function (error) {
					console.log(error);
				});
    },
    filtroMarca() {
			this.$http
				.get(this.baseURI + "?marca=" + this.marca.trim())
				.then((result) => {
					this.cars = result.data;
				})
				.catch(function (error) {
					console.log(error);
				});
		},
	},
};
</script>

<style>
</style>