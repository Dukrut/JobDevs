<template>
  <div style="padding: 0px 20px; text-align: center;">
    <q-card class="my-card q-pa-lg">
      <h5>Entre na sua conta</h5>

      <q-form @submit="onSubmit" class="q-gutter-md">
        <q-card-section>
          <q-input class="q-mb-md" rounded outlined v-model="form.email" label="Usuário"
            :rules="[val => !!val || 'Preencha o campo obrigatório!']" />

          <q-input v-model="form.password" rounded outlined :type="isPwd ? 'password' : 'text'" label="Senha"
            :rules="[val => !!val || 'Preencha o campo obrigatório!']">
            <template v-slot:append>
              <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd = !isPwd" />
            </template>
          </q-input>
        </q-card-section>

        <q-card-section>
          <q-btn unelevated rounded color="primary" label="Entrar" type="submit" />
        </q-card-section>
      </q-form>

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

const isPwd = ref(true);

const onSubmit = () => {
  if (validateForm()) {
    login(form.value)
      .then((response) => {
        console.log("response", response);
      })
      .catch((error) => {
        console.log("error", error);
      })
  }
}

const validateForm = () => {
  return form.value.email && form.value.password;
};

</script>

<style lang="scss" scoped>
.my-card {
  max-width: 690px;
  height: 500px;
}
</style>
