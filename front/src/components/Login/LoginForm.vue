<template>
  <div style="padding: 0px 20px; text-align: center;">
    <q-card class="my-card q-pa-lg">
      <h5>Entre na sua conta</h5>
      <q-card-section>
        <q-input class="q-mb-md" rounded outlined v-model="form.email" label="Usuário" />
        <q-input rounded outlined v-model="form.password" label="Senha" />
      </q-card-section>

      <q-card-section>
        <q-btn unelevated rounded color="primary" label="Entrar" @click="onSubimit" />
      </q-card-section>

      <q-card-section>
        <p>Ainda não possui uma conta? <a href="#">Cadastre-se aqui!</a></p>
      </q-card-section>
    </q-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import login from '../../services/authService';

const form = ref({
  email: null,
  password: null
});

const onSubimit = () => {
  console.log(form.value);
  login(form.value)
    .then((response) => {
      console.log("response", response);
    })
    .catch((error) => {
      console.log("error", error);
    })
}

</script>

<style lang="scss" scoped>
.my-card {
  max-width: 690px;
  height: 500px;
}
</style>
