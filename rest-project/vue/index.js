const API = 'http://localhost:8080/agenda/contato'

const myAppComponent = {
    data() {
        return {
            contatos: {},
            pagina: 1,
            resultadosPorPagina: 5,
            paginas: [1, 2, 3, 4]
        }
    },

    methods: {
        async fetchApi() {
            await axios.get(API, {
                params: {
                    pagina: this.pagina,
                    resultadosPorPagina: this.resultadosPorPagina
                  }
            })
            .then((response) => {
                this.contatos = response.data
            })
            .catch((error) => {
                console.log(error)
            })
        },

        getContatos(pagina, resultadosPorPagina) {
            this.pagina = pagina
            this.resultadosPorPagina = resultadosPorPagina
            this.fetchApi()
        }
    },

    mounted() {
        this.fetchApi()
    }
}

const myApp = Vue.createApp(myAppComponent).mount('#myapp')
