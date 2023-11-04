<template>
  <q-responsive :ratio="16 / 9" style="height: 100vh; width: 100%;">
    <div
      style="display: flex; align-items: center; justify-content: center; flex-direction: column; background-color: #c4e3ff;">
      <WizardRegister />
    </div>
  </q-responsive>
</template>

<script setup>
import WizardRegister from "src/components/Wizard/WizardRegister.vue";

</script>

<style lang="scss" scoped>
.my-card {
  max-width: 690px;
  height: 500px;
}
</style>

