console.log("Olá, Mundo!");
/*
var ctx = document.getElementById('meuGrafico').getContext('2d');
var meuGrafico = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun'],
        datasets: [{
            label: 'Depósitos',
            data: [0, 10, 5, 2, 20, 30],
            borderColor: 'rgba(75, 192, 192, 1)',
            tension: 0.1
        },
        {
            label: 'Dividendos',
            data: [10, 20, 15, 12, 32, 45],
            borderColor: 'rgba(153, 102, 255, 1)',
            tension: 0.1
        }]
    }
});*/

fetch('/depositos')
    .then(response => response.json())
    .then(data => {
        // Agrupa os depósitos por mês e soma os valores
        let depositosPorMes = data.reduce((acc, deposito) => {
            let mes = deposito.dt_deposito.slice(0, 7); // Pega o ano e o mês do depósito
            if (!acc[mes]) {
                acc[mes] = 0; // Inicializa a soma para este mês
            }
            acc[mes] += deposito.valorDep; // Soma o valor do depósito
            return acc;
        }, {});

        let labels = Object.keys(depositosPorMes); // Os meses dos depósitos
        let valores = Object.values(depositosPorMes); // As somas dos valores dos depósitos

        // Cria uma array de objetos, cada um contendo o mês e a soma dos valores
        let depositos = labels.map((label, i) => ({mes: label, valor: valores[i]}));

        // Ordena os depósitos na ordem crescente do mês
        depositos.sort((a, b) => a.mes.localeCompare(b.mes));

        // Atualiza os rótulos e valores com a ordem correta
        labels = depositos.map(deposito => deposito.mes);
        valores = depositos.map(deposito => deposito.valor);

        let ctx = document.getElementById('meuGrafico').getContext('2d');
        let chart = new Chart(ctx, {
            type: 'line', // Tipo de gráfico que você deseja criar
            data: {
                labels: labels, // Os meses dos depósitos
                datasets: [{
                    label: 'Depósitos',
                    data: valores, // As somas dos valores dos depósitos
                    backgroundColor: 'rgba(75, 192, 192, 0.2)', // Cor de fundo
                    borderColor: 'rgba(75, 192, 192, 1)', // Cor da borda
                    borderWidth: 1 // Largura da borda
                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            }
        });
    });

/*

Promise.all([fetch('/depositos'), fetch('/proventos')])
    .then(responses => Promise.all(responses.map(response => response.json())))
    .then(([depositosData, proventosData]) => {
        let depositosPorMes = agruparPorMes(depositosData);
        let proventosPorMes = agruparPorMes(proventosData);

        let labels = Object.keys({...depositosPorMes, ...proventosPorMes}); // Os meses dos depósitos e proventos
        labels.sort(); // Ordena os meses na ordem crescente

        let depositosValores = labels.map(mes => depositosPorMes[mes] || 0); // As somas dos valores dos depósitos
        let proventosValores = labels.map(mes => proventosPorMes[mes] || 0); // As somas dos valores dos proventos

        let ctx = document.getElementById('meuGrafico').getContext('2d');
        let chart = new Chart(ctx, {
            type: 'line', // Tipo de gráfico que você deseja criar
            data: {
                labels: labels, // Os meses dos depósitos e proventos
                datasets: [{
                    label: 'Depósitos',
                    data: depositosValores, // As somas dos valores dos depósitos
                    backgroundColor: 'rgba(75, 192, 192, 0.2)', // Cor de fundo
                    borderColor: 'rgba(75, 192, 192, 1)', // Cor da borda
                    borderWidth: 1 // Espessura da borda
                }, {
                    label: 'Proventos',
                    data: proventosValores, // As somas dos valores dos proventos
                    backgroundColor: 'rgba(153, 102, 255, 0.2)', // Cor de fundo
                    borderColor: 'rgba(153, 102, 255, 1)', // Cor da borda
                    borderWidth: 1 // Espessura da borda
                }]
            },
            options: {
                responsive: true,
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            callback: function(value) {if (value % 1 === 0) {return value;}}
                        }
                    }]
                }
            }
        });
    });

function agruparPorMes(data) {
    return data.reduce((acc, item) => {
        let mes = item.dt_recebimeto.slice(0, 7); // Pega o ano e o mês do item
        if (!acc[mes]) {
            acc[mes] = 0; // Inicializa a soma para este mês
        }
        acc[mes] += item.valorDep; // Soma o valor do item
        return acc;
    }, {});
}*/



