console.log("Olá, Mundo!");

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

	let depositos = labelsDep.map((label, i) => ({ mes: label, valor: valoresDep[i] }));
	let proventos = labelsProv.map((label, i) => ({ mes: label, valor: valoresProv[i] }));

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

fetch('/totalDepositos')
	.then(response => response.json())
	.then(total => {
		document.getElementById('totalDepositos').textContent = total;
	});




