<template>
	<section class="container">
		<!-- ------------------ Busca ------------------------- -->
		<div class="form col-5 d-block mx-auto p-3">
			<div class="input-group mb-3">
				<input
					type="text"
					class="form-control"
					placeholder="Digite o ID"
					v-model="id"
				/>
				<div class="input-group-append">
					<button
						type="button"
						class="btn btn-primary"
						@click="buscarID"
					>
						Buscar
					</button>
				</div>
			</div>
		</div>

		<!-- ------------------ Resultado ------------------------- -->
		<table class="table table-bordered" v-if="car != ''">
			<thead>
				<tr>
					<th scope="col">id</th>
					<th scope="col">Modelo</th>
					<th scope="col">Marca</th>
					<th scope="col">Fabricação</th>
					<th scope="col">Modelo</th>
					<th scope="col">Venda</th>
					<th scope="col">Ações</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>{{ car.id }}</td>
					<td>{{ car.nome }}</td>
					<td>{{ car.marca }}</td>
					<td>{{ car.anoFabricacao }}</td>
					<td>{{ car.anoModelo }}</td>
					<td>{{ car.dataVenda }}</td>
					<td>
						<a
							href="#"
							class="badge badge-danger p-2"
							style="font-size: 12pt"
							@click="deletar"
						>
							<i class="fa fa-trash" aria-hidden="true"></i>
						</a>

						<a
							href="#"
							class="badge badge-info p-2 ml-2"
							style="font-size: 12pt"
							data-toggle="modal"
							data-target="#modalAtualizar"
						>
							<i
								class="fa fa-pencil-square-o"
								aria-hidden="true"
							></i>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		<h3 v-else>Nenhum Resultado!</h3>

		<!-- ------------------ Modal Atualizar ------------------------- -->
		<div class="modal fade" id="modalAtualizar" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<i
								class="fa fa-pencil-square-o"
								aria-hidden="true"
							></i>
							Atualizar
						</h5>
						<button
							type="button"
							class="close"
							data-dismiss="modal"
							aria-label="Close"
						>
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form
							class="form d-block mx-auto p-3"
							@submit="atualizar"
						>
							<div
								class="alert alert-info alert-dismissible fade show"
							>
								<strong
									><i
										class="fa fa-info-circle"
										aria-hidden="true"
									></i>
								</strong>
								Os campos em branco não serão atualizados
								<button
									type="button"
									class="close"
									data-dismiss="alert"
									aria-label="Close"
								>
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="form-group">
								<label for="nome">Nome: </label>
								<input
									type="text"
									class="form-control"
									id="nome"
									placeholder="Nome do carro"
									v-model="nome"
									required
								/>
							</div>

							<div class="form-group">
								<label for="marca">Marca: </label>
								<input
									type="text"
									class="form-control"
									id="marca"
									placeholder="Fiat"
									v-model="marca"
									required
								/>
							</div>

							<div class="form-group">
								<label for="anoFab">Ano de Fabricação: </label>
								<input
									type="number"
									maxlength="4"
									minlength="4"
									max="2100"
									min="1900"
									class="form-control"
									id="anoFab"
									placeholder="0000"
									v-model="fabricacao"
									required
								/>
							</div>

							<div class="form-group">
								<label for="nome">Ano do Modelo: </label>
								<input
									type="number"
									maxlength="4"
									minlength="4"
									max="2100"
									min="1900"
									class="form-control"
									id="anoModelo"
									placeholder="0000"
									v-model="modelo"
									required
								/>
							</div>

							<div class="form-group">
								<label for="marca">Data de Venda: </label>
								<input
									type="date"
									class="form-control"
									id="data"
									v-model="venda"
									required
								/>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button
							type="button"
							class="btn btn-secondary"
							data-dismiss="modal"
						>
							Fechar
						</button>
						<button
							type="button"
							class="btn btn-primary"
							@click="atualizar"
							data-dismiss="modal"
						>
							Salvar Mudanças
						</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</template>

<script>
export default {
	name: "Busca",
	data() {
		return {
			id: "",
			car: "",
			nome: "",
			marca: "",
			fabricacao: 0,
			modelo: 0,
			venda: "",
			baseURI: "http://localhost:8080/api/carros",
		};
	},
	methods: {
		buscarID() {
			this.$http
				.get(this.baseURI + "/" + this.id)
				.then((result) => {
					this.car = result.data;
				})
				.catch((error) => {
					if (error.response.status === 404) {
						this.car = [];
					} else {
						console.log(error)
					}
				});
		},
		deletar() {
			this.$http
				.delete(this.baseURI + "/" + this.id)
				.then((result) => {
					alert("Deletado com sucesso!");
					console.log(result);
				})
				.catch((error) => {
					console.log(error);
				});
		},
		abrirAtualizacao() {},
		atualizar() {
			this.$http
				.put(this.baseURI + "/" + this.id, {
					nome: this.nome != "" ? this.nome : this.car.nome,
					marca: this.marca != "" ? this.marca : this.car.marca,
					anoFabricacao:
						this.fabricacao != ""
							? this.fabricacao
							: this.car.anoFabricacao,
					anoModelo:
						this.modelo != "" ? this.modelo : this.car.anoModelo,
					dataVenda:
						this.venda != "" ? this.venda : this.car.dataVenda,
				})
				.then((result) => {
					alert("Atualizado!");
					this.car = result.data;
					console.log(result);
				})
				.catch((error) => {
					if (error.response.status === 404) {
						this.car = [];
					}
				});
		},
	},
};
</script>

<style>
</style>