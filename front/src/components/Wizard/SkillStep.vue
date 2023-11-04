<template>
  <div class="q-pa-md"
    style="display: flex; justify-content: center; width: 100%; height: 100%; flex-direction: column; gap: 3rem;">

    <div>
      <label>Nos conte quais são as suas habilidades e com isso, aponte o tempo de experiencia em cada uma delas.</label>

      <div style="max-height: 220px; overflow: auto;">
        <div ref="listSkillsRef" class="list-skills-container">
          <div v-for="(skill, index) in userStore.skills" :key="index" style="display: flex; width: 100%;">
            <q-input v-model="skill.name" outlined label="Adicione uma habilidade" clearable dense
              style="width: 40%;  margin-right: 20px;" />

            <q-select v-model="skill.experienceTime" dense outlined clearable :options="experienceTimeOptions"
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
import { ref } from "vue";
import { useUserStore } from "src/stores/user-store";

const userStore = useUserStore();

const experienceTimeOptions = [
  { value: "LESS_THAN_1_YEAR", name: "Menos de 1 ano" },
  { value: "ONE_YEAR", name: "1 ano" },
  { value: "TWO_YEARS", name: "2 anos" },
  { value: "THREE_YEARS", name: "3 anos" },
  { value: "FOUR_YEARS", name: "4 anos" },
  { value: "FIVE_YEARS", name: "5 anos" },
  { value: "MORE_THAN_FIVE_YEARS", name: "Mais de 5 anos" }
]

const listSkillsRef = ref(null);

const addLine = () => {
  userStore.skills.push({ name: null, experienceTime: null });

  setTimeout(() => {
    listSkillsRef.value.scrollTop = listSkillsRef.value.scrollHeight;
  });
}

const removeLine = (index) => {
  userStore.skills.splice(index, 1);
}
</script>

<style lang="scss" scoped>
.list-skills-container {
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
