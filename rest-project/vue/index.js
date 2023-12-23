const API = `http://localhost:8080/agenda/contato`

const myAppComponent = {
    data() {
        return {

            /* Response Data will fetch this values */
            contatos: {},
            pagina: 1,
            resultadosPorPagina: 10,
            totalDeContatos: null,
            totalDePaginas: null,
            /** END of Response Data */

        }
    },

    methods: {
        async fetchApi() {
            await axios.get(API, {
                params: {
                    pagina: this.pagina,
                    resultadosPorPagina: this.resultadosPorPagina
                },

            })

            .then((response) => {
                this.contatos        =  response.data.listaDeContatos
                this.totalDePaginas  =  response.data.totalDePaginas
                this.totalDeContatos =  response.data.totalDeContatos
            })

            .catch((error) => {
                console.log(error)
                this.tratarErro(error)
            })

        },

        getContatos(pagina, resultadosPorPagina) {
            this.pagina = pagina
            this.resultadosPorPagina = resultadosPorPagina
            this.fetchApi()
        },

        tratarErro(error) {
            if (error.response) {
                if (error.response.status == 403) {
                    alert(`Usuário não Possui Autorização para Acessar esta URL\nErro: '${error.response.status}': ${error.response.data}`)
                }
            }

            if (error.code == 'ERR_NETWORK') {
                alert(`Serviço REST Indisponível.\nNão foi possível se conectar à ${API}`)
            }
        }
    },

    mounted() {
        this.fetchApi()
    }
}

const myApp = Vue.createApp(myAppComponent).mount('#myapp')
