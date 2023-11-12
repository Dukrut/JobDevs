<template>
  <q-toolbar class="bg-primary text-white">
    <q-toolbar-title>
      <q-btn flat dense to="/">
        JobDevs
      </q-btn>
    </q-toolbar-title>

    <q-btn flat round dense to="/profile">
      <q-avatar>
        <img src="https://cdn.quasar.dev/img/avatar.png">
      </q-avatar>
    </q-btn>
    <q-btn class="q-ml-sm" round flat icon="logout" @click="onLogout()"></q-btn>
  </q-toolbar>
</template>

<script setup>
import { useAuthStore } from 'src/stores/auth-store';
import { useRouter } from 'vue-router';
const route = useRouter()

const authStore = useAuthStore();

const onLogout = () => {
  authStore.clear()
  route.push("/login")
}
</script>