<template>
  <div style="padding: 0px 20px; text-align: center">
    <q-card class="my-card q-pa-lg" style="height: 100%; width: 100%;">
      <h5>Cadastre a sua conta</h5>

      <q-form @submit="onSubmit" class="q-gutter-md">
        <q-card-section>
          <q-input class="q-mb-md" rounded outlined v-model="userStore.info.name" label="Nome completo"
            placeholder="Maria da Silva" lazy-rules :rules="[(val) => !!val || 'Preencha o campo obrigatório!']" />

          <q-input class="q-mb-md" rounded outlined v-model="userStore.info.email" label="E-mail"
            placeholder="exemplo@exemplo.com" lazy-rules :rules="[(val) => !!val || 'Preencha o campo obrigatório!']" />

          <q-input v-model="userStore.info.password" rounded outlined :type="isPwd ? 'password' : 'text'" label="Senha"
            lazy-rules :rules="[(val) => !!val || 'Preencha o campo obrigatório!']">
            <template v-slot:append>
              <q-icon :name="isPwd ? 'visibility_off' : 'visibility'" class="cursor-pointer" @click="isPwd = !isPwd" />
            </template>
          </q-input>
        </q-card-section>

        <q-card-section>
          <q-btn unelevated rounded color="primary" label="Cadastrar e criar meu perfil" type="submit" />
        </q-card-section>
      </q-form>

      <q-card-section>
        <p>
          Você já possui uma conta?
          <router-link to="/">
            Entre aqui!
          </router-link>
        </p>
      </q-card-section>
    </q-card>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useQuasar } from "quasar";
import { useRouter } from "vue-router";
import { useUserStore } from "src/stores/user-store";

const $q = useQuasar();
const router = useRouter();
const userStore = useUserStore();

const isPwd = ref(true);

const checkFormIsInvalid = () => {
  return !userStore.info.name || !userStore.info.email || !userStore.info.password;
}

const onSubmit = () => {
  // Verifique se o formulário é válido antes de prosseguir
  if (checkFormIsInvalid()) {
    $q.notify({
      color: 'red-5',
      textColor: 'white',
      icon: 'warning',
      message: 'Preencha todos os campos!'
    })
  } else {
    router.push('/register/wizard');
  }

}

// const form = ref({
//   name: null,
//   email: null,
//   password: null
// });

// const onSubmit = () => {
//   if (validateForm()) {
//     register(form.value)
//       .then((response) => {
//         if (response.data.info)
//           authStore.setJWt(response.data.info);
//       })
//       .catch((error) => {
//         $q.notify({
//           icon: "warning",
//           color: "negative",
//           message: "Não foi possível cadastrar o usuário!"
//         });
//       });
//   }
// };

// const validateForm = () => {
//   return userStore.info.email && userStore.info.password;
// };
</script>

<style lang="scss" scoped>
.my-card {
  max-width: 690px;
  height: 500px;
}
</style>
