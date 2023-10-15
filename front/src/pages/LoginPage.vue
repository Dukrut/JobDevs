<template>
  <!-- <div style="height: 100vh; width: 100%; display: flex; align-items: center; justify-content: center;"> -->
  <q-responsive :ratio="16 / 9" style="height: 100vh; width: 100%;">
    <div
      style="display: flex; align-items: center; justify-content: center; flex-direction: column; background-color: #c4e3ff;">
      <h4 style="padding: 0px 20px; text-align: center;">Bem vindo(a) ao JobDevs!</h4>
      <LoginForm />
    </div>
  </q-responsive>
  <!-- </div> -->
</template>

<script>
import { defineComponent } from 'vue'
import LoginForm from 'src/components/Login/LoginForm.vue'

export default defineComponent({
  components: { LoginForm },
  name: 'LoginPage',
  setup() {
    return {
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
    }
  }
})
</script>
