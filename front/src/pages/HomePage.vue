<template>
  <div>
    HomePage
    {{ authStore }}
  </div>
</template>

<script setup>

import { useAuthStore } from 'src/stores/auth-store';

const authStore = useAuthStore();

</script>