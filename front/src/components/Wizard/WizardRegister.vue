<template>
  <div class="q-pa-md" style="display: flex; justify-content: center; width: 80%; height: 100%;">
    <q-stepper id="wizard" v-model="step" ref="stepper" alternative-labels color="primary" animated
      style="height: 100%; width: 80%; overflow: auto;">
      <q-step :name="1" title="Habilidades" icon="create_new_folder" :done="step > 1">
        <SkillStep />
      </q-step>

      <q-step :name="2" title="Formação" icon="create_new_folder" :done="step > 2">
        <EducationStep />
      </q-step>

      <q-step :name="3" title="Idiomas" icon="add_comment" :done="step > 3">
        <LanguageStep />
      </q-step>

      <q-step :name="4" title="Perfil" icon="add_comment" :done="step > 4">
        <ProfileStep />
      </q-step>

      <template v-slot:navigation>
        <q-stepper-navigation>
          <q-btn @click="step === 4 ? finishProfileRegister() : $refs.stepper.next()" color="primary"
            :label="step === 4 ? 'Buscar por vagas' : 'Seguir'" />
          <q-btn v-if="step > 1" flat color="primary" @click="$refs.stepper.previous()" label="Voltar" class="q-ml-sm" />
        </q-stepper-navigation>
      </template>
    </q-stepper>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "src/stores/user-store";

const userStore = useUserStore();

import LanguageStep from "./LanguageStep.vue"
import SkillStep from "./SkillStep.vue";
import ProfileStep from "./ProfileStep.vue";
import EducationStep from "./EducationStep.vue";

const step = ref(1);

const finishProfileRegister = () => {
  console.log(userStore);
}

</script>

<style lang="scss" scoped></style>

