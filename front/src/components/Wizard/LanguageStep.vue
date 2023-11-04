<template>
  <div class="q-pa-md"
    style="display: flex; justify-content: center; width: 100%; height: 100%; flex-direction: column; gap: 3rem;">
    <div>
      <label for="english-level">Qual o seu nível de inglês?</label>
      <q-select dense outlined id="english-level" clearable v-model="userStore.info.language.englishProficiency"
        :options="proficiencyOptions" option-label="name" emit-value map-options style="width: 40%; padding-top: 15px;"
        label="Selecione uma opção" />
    </div>

    <div>
      <label for="working-english">Você trabalha ou já trabalhou exclusivamente utilizando inglês?</label>

      <div>
        <q-radio id="working-english" v-model="userStore.info.language.workedWithEnglish" checked-icon="task_alt"
          unchecked-icon="panorama_fish_eye" :val="true" label="Sim" />
        <q-radio id="working-english" v-model="userStore.info.language.workedWithEnglish" checked-icon="task_alt"
          unchecked-icon="panorama_fish_eye" :val="false" label="Não" />
      </div>
    </div>

    <div>
      <label>Quais idiomas você conhece/domina?</label>

      <div style="max-height: 220px; overflow: auto;">
        <div class="list-languages-container" ref="listLanguagesRef">
          <div style="display: flex; width: 100%;" v-for="(language, index) in userStore.info.language.languageSkills"
            :key="index">
            <q-select dense outlined clearable v-model="language.languageId" :options="languagesOptions"
              label="Selecione uma opção" style="width: 40%; margin-right: 20px;" option-value="id" option-label="name"
              emit-value map-options />

            <q-select dense outlined clearable v-model="language.proficiency" :options="proficiencyOptions"
              label="Selecione uma opção" style="width: 40%; margin-right: 20px;" option-label="name" emit-value
              map-options />

            <q-btn v-if="index === 0" round color="secondary" icon="add" @click="addLine" />
            <q-btn v-else round color="negative" icon="close" @click="removeLine(index)" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import getLanguages from "../../services/languagesService";
import { useUserStore } from "src/stores/user-store";

const userStore = useUserStore();

const languagesOptions = ref([]);
const listLanguagesRef = ref(null);

const proficiencyOptions = [
  { value: "BEGINNER", name: "Básico" },
  { value: "INTERMEDIARY", name: "Intermediário" },
  { value: "ADVANCED", name: "Avançado" },
  { value: "FLUENT", name: "Fluente/Nativo" }
]

onMounted(() => {
  getLanguagesOptions();
})

const getLanguagesOptions = () => {
  getLanguages()
    .then((response) => {
      if (response.data.info)
        languagesOptions.value = response.data.info;
    })
    .catch((error) => {
      console.log(error);
    })
}

const addLine = () => {
  userStore.info.language.languageSkills.push({ languageId: null, proficiency: null });

  setTimeout(() => {
    listLanguagesRef.value.scrollTop = listLanguagesRef.value.scrollHeight;
  });
}

const removeLine = (index) => {
  userStore.info.language.languageSkills.splice(index, 1);
}
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
