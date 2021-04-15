<template>
	<section class="container">
		<form class="form border col-5 d-block mx-auto p-3" @submit="cadastrar">
			<h1 class="h3">
				<i class="fa fa-car" aria-hidden="true"></i> Cadastro
			</h1>
			<hr />
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

			<button type="submit" class="btn btn-primary">
				<i class="fa fa-plus" aria-hidden="true"></i> Cadastrar
			</button>
		</form>
	</section>
</template>

<script>
export default {
	name: "Cadastro",
	data() {
		return {
			nome: "",
			marca: "",
			fabricacao: 0,
			modelo: 0,
      venda: "",
      baseURI: "http://localhost:8080/api/carros"
		};
  },
  methods: {
    cadastrar() {
      this.$http.post(this.baseURI, {
        nome: this.nome,
        marca: this.marca,
        anoFabricacao: this.fabricacao,
        anoModelo: this.modelo,
        dataVenda: this.venda
      }).then(result => {
        console.log(result);
        alert("Cadastrado com Sucesso!");
      }).catch(error => {
        console.log(e);
      })
    }
  }
};
</script>

<style>
</style>