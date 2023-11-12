<template>
  <div>
    <ToolbarComponent></ToolbarComponent>
  </div>
  <div class="q-pa-md items-start q-gutter-md">
    <q-card>
      <q-card-section>
        <div class="text-h6">Perfil</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <ProfileStep></ProfileStep>
      </q-card-section>
    </q-card>
    <hr>
    <q-card>
      <q-card-section>
        <div class="text-h6">Habilidades</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <SkillStep></SkillStep>
      </q-card-section>
    </q-card>
    <hr>
    <q-card>
      <q-card-section>
        <div class="text-h6">Formação</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <EducationStep></EducationStep>
      </q-card-section>
    </q-card>
    <hr>
    <q-card>
      <q-card-section>
        <div class="text-h6">Idiomas</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <LanguageStep></LanguageStep>
      </q-card-section>
    </q-card>
  </div>
  <div class="q-mb-md text-center">
    <q-btn color="primary" @click="onUpdateUser">Salvar</q-btn>
  </div>
</template>

<script setup>
import ToolbarComponent from 'src/components/ToolbarComponent.vue';
import EducationStep from 'src/components/Wizard/EducationStep.vue';
import LanguageStep from 'src/components/Wizard/LanguageStep.vue';
import ProfileStep from 'src/components/Wizard/ProfileStep.vue';
import SkillStep from 'src/components/Wizard/SkillStep.vue';
import { get, update } from 'src/services/userService'
import { useAuthStore } from 'src/stores/auth-store';
import { useUserStore } from 'src/stores/user-store';
import { onMounted } from 'vue';

const authStore = useAuthStore()
const userStore = useUserStore()

onMounted(() => {
  onLoadUser()
})

const onLoadUser = () => {
  console.log(authStore.user)
  get(authStore.user.id).then((response) => {
    if (response.data.info) {
      userStore.info = response.data.info
    }
  }).catch((err) => console.log(err))
}

const onUpdateUser = () => {
  update(userStore.info).then((response) => {
  }).catch((err) => {
    console.log(err)
  })
}


</script>