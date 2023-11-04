<template>
  <div class="q-pa-md"
    style="display: flex; justify-content: center; width: 100%; height: 100%; flex-direction: column; gap: 3rem;">

    <div>
      <label>Instituição de ensino</label>
      <q-input dense outlined v-model="userStore.info.educations[0].institutionName"
        label="Comente o nome da instituição" />
    </div>

    <div style="display: flex; gap: 20px;">
      <div style="width: 50%;">
        <label for="english-level">Titulação</label>
        <q-select dense outlined id="english-level" clearable v-model="userStore.info.educations[0].degree"
          :options="degreeOptions" option-label="name" emit-value map-options style="width: 100%;"
          label="Selecione uma opção" />
      </div>

      <div style="width: 50%;">
        <label>Área de estudo</label>
        <q-input style="width: 100%;" dense outlined v-model="userStore.info.educations[0].fieldStudy"
          label="Digite uma área de estudo" />
      </div>
    </div>

    <div style="display: flex; gap: 20px;">
      <div>
        <label>Ano de ínicio</label>
        <q-input dense outlined v-model="userStore.info.educations[0].startDate" mask="####" fill-mask>
          <template v-slot:append>
            <q-icon name="event" />
          </template>
        </q-input>
      </div>

      <div>
        <label>Ano de término</label>
        <q-input dense outlined v-model="userStore.info.educations[0].endDate" mask="####" fill-mask>
          <template v-slot:append>
            <q-icon name="event" />
          </template>
        </q-input>
      </div>
    </div>

    <div>
      <label>Descrição</label>
      <q-input v-model="userStore.info.educations[0].description" outlined type="textarea" />
    </div>

    <div>
      <label>Quais as atividades que você exerceu durante o curso?</label>
      <q-input v-model="userStore.info.educations[0].activities" outlined type="textarea" />
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "src/stores/user-store";

const userStore = useUserStore();

const degreeOptions = [
  { value: "HIGH_SCHOOL", name: "Ensino Médio" },
  { value: "TECHNICAL_EDUCATION", name: "Ensino Técnico" },
  { value: "UNIVERSITY_EDUCATION", name: "Ensino Superior" },
  { value: "POSTGRADUATE", name: "Pós Graduação" },
  { value: "MASTERS_DEGREE", name: "Mestrado" },
  { value: "DOCTORATE_DEGREE", name: "Doutorado" },
  { value: "PHD", name: "PhD" },
  { value: "MBA", name: "MBA" },
  { value: "SPECIALIZATION", name: "Especialização" },
  { value: "FREE_COURSES", name: "Cursos livres" }
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
