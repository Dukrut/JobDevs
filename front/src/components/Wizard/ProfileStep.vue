<template>
  <div class="q-pa-md"
    style="display: flex; justify-content: center; width: 100%; height: 100%; flex-direction: column; gap: 3rem;">

    <div>
      <label>Faça uma breve introdução sobre você.</label>
      <q-input v-model="userStore.profile.description" outlined type="textarea" />
    </div>

    <div>
      <label for="english-level">Gênero</label>
      <q-select dense outlined id="english-level" clearable v-model="userStore.profile.gender" :options="genderOptions"
        option-label="name" emit-value map-options style="width: 40%;" label="Selecione uma opção" />
    </div>

    <div>
      <label>Nos conte um pouco sobre seus hobbies.</label>
      <q-input v-model="userStore.profile.hobbies" outlined type="textarea" />
    </div>

    <div style="display: flex; flex-direction: column; gap: 10px;">
      <label>Qual foi seu útimo cargo e empresa?</label>
      <q-checkbox v-model="hasNotExperience" label="Não possuo experiência profissional" />

      <div v-if="!hasNotExperience" style="display: flex;">
        <q-input outlined v-model="userStore.profile.lastRole" label="Adicione seu último cargo" clearable dense
          style="width: 40%;  margin-right: 20px;" />
        <q-input outlined v-model="userStore.profile.lastCompany" label="Adicione o nome da empresa" clearable dense
          style="width: 40%;  margin-right: 20px;" />
      </div>
    </div>

    <div>
      <label>Qual a sua preferência de modelo de trabalho?</label>
      <div class="q-gutter-sm">
        <q-checkbox v-model="userStore.profile.jobPreference" val="HOME_OFFICE" label="Home-office" />
        <q-checkbox v-model="userStore.profile.jobPreference" val="HYBRID" label="Híbrido" />
        <q-checkbox v-model="userStore.profile.jobPreference" val="PRESENTIAL" label="Presencial" />
      </div>
    </div>

    <div>
      <label>Qual o tipo de contrato você prefere?</label>
      <div class="q-gutter-sm">
        <q-checkbox v-model="userStore.profile.contractPreference" val="FREELANCE" label="Freelance" />
        <q-checkbox v-model="userStore.profile.contractPreference" val="CLT" label="CLT" />
        <q-checkbox v-model="userStore.profile.contractPreference" val="PJ" label="PJ" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "src/stores/user-store";

const userStore = useUserStore();

const hasNotExperience = ref(false);
const genderOptions = [
  { value: "F", name: "Feminino" },
  { value: "M", name: "Masculino" }
]

</script>

<style lang="scss" scoped>
.list-languages-container {
  max-height: 220px;
  overflow-y: auto;
  transition: all 0.3s ease;
  scroll-behavior: smooth;
  padding-top: 15px;
  display: flex;
  flex-direction: column;
  width: 100%;
  gap: 10px;
}
</style>
