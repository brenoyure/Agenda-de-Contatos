<template>
    <h1>Login</h1>
    <form @submit.prevent="doLogin">
        <div>
            <label for="username">Login</label>
            <input type="text" id="username" v-model="credenciais.username" />
        </div>
        <div>
            <label for="username">Password</label>
            <input type="password" id="password" v-model="credenciais.password" />
        </div>
        <div>
            <button type="submit">Sign in</button>
        </div>
    </form>
</template>

<script>
import axios from '../axios'
export default {
    name: 'Login-Form-Component',
    data() {
        return {
            credenciais: {
                username: null, 
                password: null
            }
        }
    },

    methods: {
        async doLogin() {
            await
                axios
                    .post('/login', this.credenciais)
                    .then((response) => {
                        console.log(response)
                    })
                    .catch((error) => {
                        console.log(error)
                        if (error.response) {
                            alert(error.response.message)
                            return
                        }
                        if (error.code === 'ERR_NETWORK') {
                            alert('Serviço REST Indisponível')
                            return
                        }
                        alert('Erro desconhecido')
                        return
                    })
        }
    }
}

</script>