var vm = new Vue({
    el: "#app",
    data: {
        alerta: false,
        id: 0,
        nome: "",
        email: "",
        data: "",
        tabela: "",
        servicos: [
            {
                checked: false,
                value: "Processing - 1 micro - $ 1,00 per hour",
            },
            {
                checked: false,
                value: "Processing - 1 medium - $ 2,00 per hour",
            },
            {
                checked: false,
                value: "Processing - 1 large - $ 10,00 per hour",
            },
            {
                checked: false,
                value: "Storage - 10 GB HD - $ 0,5 per hour",
            },
            {
                checked: false,
                value: "Storage - 1 TB HD - $ 1,00 per hour",
            },
            {
                checked: false,
                value: "Storage - 100 GB SSD - $ 5,00 per hour"
            }
        ]
    },
    methods: {
        add() {
            if(this.alerta) this.alerta = false;

            var data = this.data.split("-"),
                ano = data[0],
                mes = data[1],
                dia = data[2];
            
            var d = new Date,
                ano_atual = d.getFullYear(),
                mes_atual = d.getMonth() + 1,
                dia_atual = d.getDate(),
                idade = ano_atual - ano;
            
            if (mes_atual < mes || mes_atual == mes && dia_atual < dia) {
                idade--;
            }

            if(idade < 18) {
                this.alerta = true;
                return;
            }

            var text = "<tr><td>" + this.nome + "</td>" +
                        "<td>" + this.email + "</td>" +
                        "<td>" + this.data + "</td><td>";

            var tot = 0.0;
            for(i in this.servicos) {
                if(this.servicos[i].checked) {
                    text += this.servicos[i].value + "<br>";
                    tot += extractNumber(this.servicos[i].value);
                }
            }

            text += "</td><td> $ " + tot.toFixed(2).replace(".", ",") + " per hour";
          
            this.tabela += text;

            const parsed = JSON.stringify(this.servicos);
            localStorage.setItem(this.id, parsed);
            this.id++;
        }
    }
});

function extractNumber(str) {
    var t = (str.substring(str.indexOf("$")+1, str.indexOf("per"))).replace(",", ".");
    return parseFloat(t);
}