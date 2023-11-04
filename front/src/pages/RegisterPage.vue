<template>
  <q-responsive :ratio="16 / 9" style="height: 100vh; width: 100%;">
    <div
      style="display: flex; align-items: center; justify-content: center; flex-direction: column; background-color: #c4e3ff;">
      <RegisterForm />
    </div>
  </q-responsive>
</template>

<script>
import { defineComponent } from 'vue'
import RegisterForm from 'src/components/Register/RegisterForm.vue'

export default defineComponent({
  components: { RegisterForm },
  name: 'RegisterPage',
  setup() {
    return {
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
    }
  }
})
</script>
