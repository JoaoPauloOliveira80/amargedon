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

/*
fetch('/proventos').then(response => response.json())
.then(proventosData => {
    let data = [...proventosData];

    let proventosPorMes = data.reduce((acc, provento) => {
        let mes = provento.dt_recebimeto.slice(0, 7);
        if (!acc[mes]) {
            acc[mes] = 0;
        }
        acc[mes] += provento.valorDep;
        return acc;
    }, {});

    let labels = Object.keys(proventosPorMes);
    let valores = Object.values(proventosPorMes);

    let proventos = labels.map((label, i) => ({mes: label, valor: valores[i]}));

    proventos.sort((a, b) => a.mes.localeCompare(b.mes));

    labels = proventos.map(provento => provento.mes);
    valores = proventos.map(provento => provento.valor);

    let ctx = document.getElementById('meuGrafico').getContext('2d');
    let chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: 'Proventos',
                data: valores,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
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
*/



/*fetch('/depositos')
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
    });*/

Promise.all([
    fetch('/depositos').then(response => response.json()),
    fetch('/proventos').then(response => response.json())
]).then(([depositosData, proventosData]) => {
    let dataDep = [...depositosData];
    let dataProv = [...proventosData];

    let depositosPorMes = dataDep.reduce((acc, deposito) => {
        let mes = deposito.dt_deposito.slice(0, 7);
        if (!acc[mes]) {
            acc[mes] = 0;
        }
        acc[mes] += deposito.valorDep;
        return acc;
    }, {});

    let proventosPorMes = dataProv.reduce((acc, provento) => {
        let mes = provento.dt_recebimeto.slice(0, 7);
        if (!acc[mes]) {
            acc[mes] = 0;
        }
        acc[mes] += provento.valorDep;
        return acc;
    }, {});

    // Incluindo o mês de dezembro de 2022 nos proventos
    if (!proventosPorMes['2022-12']) {
        proventosPorMes['2022-12'] = 0;
    }

    let labelsDep = Object.keys(depositosPorMes);
    let valoresDep = Object.values(depositosPorMes);

    let labelsProv = Object.keys(proventosPorMes);
    let valoresProv = Object.values(proventosPorMes);

    let depositos = labelsDep.map((label, i) => ({mes: label, valor: valoresDep[i]}));
    let proventos = labelsProv.map((label, i) => ({mes: label, valor: valoresProv[i]}));

    depositos.sort((a, b) => a.mes.localeCompare(b.mes));
    proventos.sort((a, b) => a.mes.localeCompare(b.mes));

    labelsDep = depositos.map(deposito => deposito.mes);
    valoresDep = depositos.map(deposito => deposito.valor);

    labelsProv = proventos.map(provento => provento.mes);
    valoresProv = proventos.map(provento => provento.valor);

    // Adicionando o próximo mês vazio
    let ultimoMesDep = labelsDep[labelsDep.length - 1];
    let ultimoMesProv = labelsProv[labelsProv.length - 1];

    let proximoMesDep = new Date(ultimoMesDep + '-01');
    proximoMesDep.setMonth(proximoMesDep.getMonth() + 1);
    proximoMesDep = proximoMesDep.toISOString().slice(0, 7);

    let proximoMesProv = new Date(ultimoMesProv + '-01');
    proximoMesProv.setMonth(proximoMesProv.getMonth() + 1);
    proximoMesProv = proximoMesProv.toISOString().slice(0, 7);

    labelsDep.push(proximoMesDep);
    valoresDep.push(0);

    labelsProv.push(proximoMesProv);
    valoresProv.push(0);

    let ctx = document.getElementById('meuGrafico').getContext('2d');
    
   // Alterando as cores das linhas
   new Chart(ctx, {
        type: 'line',
        data: {
            labels: labelsDep,
            datasets: [{
                label: 'Depósitos',
                data: valoresDep,
                backgroundColor: '#ff0000', // Cor de fundo preta
                borderColor: '#ff0000', // Cor da linha preta
                borderWidth: 1
            },
            {
                label: 'Proventos',
                data: valoresProv,
                backgroundColor: '#38a138', // Cor de fundo verde
                borderColor: '#228b22', // Cor da linha verde
                borderWidth: 1
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






