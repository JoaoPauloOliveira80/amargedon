console.log("Olá, Mundo!");

var ctx = document.getElementById('meuGrafico').getContext('2d');
var meuGrafico = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun'],
        datasets: [{
            label: 'Meu primeiro dataset',
            data: [0, 10, 5, 2, 20, 30],
            borderColor: 'rgba(75, 192, 192, 1)',
            tension: 0.1
        }]
    }
});
