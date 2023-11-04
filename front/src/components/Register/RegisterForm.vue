<template>
  <div style="padding: 0px 20px; text-align: center">
    <q-card class="my-card q-pa-lg" style="height: 100%; width: 100%;">
      <h5>Cadastre a sua conta</h5>

      <q-form @submit="onSubmit" class="q-gutter-md">
        <q-card-section>
          <q-input class="q-mb-md" rounded outlined v-model="form.name" label="Nome completo" placeholder="Maria da Silva"
            :rules="[(val) => !!val || 'Preencha o campo obrigatório!']" />

          <q-input class="q-mb-md" rounded outlined v-model="form.email" label="E-mail" placeholder="exemplo@exemplo.com"
            :rules="[(val) => !!val || 'Preencha o campo obrigatório!']" />

          <q-input v-model="form.password" rounded outlined :type="isPwd ? 'password' : 'text'" label="Senha"
            :rules="[(val) => !!val || 'Preencha o campo obrigatório!']">
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
import { useAuthStore } from "../../stores/auth-store";
import register from "src/services/userService";

const $q = useQuasar();
const authStore = useAuthStore();

const form = ref({
  name: null,
  email: null,
  password: null
});

const isPwd = ref(true);

const onSubmit = () => {
  if (validateForm()) {
    register(form.value)
      .then((response) => {
        if (response.data.info)
          authStore.setJWt(response.data.info);
      })
      .catch((error) => {
        $q.notify({
          icon: "warning",
          color: "negative",
          message: "Não foi possível cadastrar o usuário!"
        });
      });
  }
};

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
