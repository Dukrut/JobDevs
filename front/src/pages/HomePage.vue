<template>
  <div>
    <q-toolbar class="bg-primary text-white">
      <q-toolbar-title>JobDevs</q-toolbar-title>

      <q-btn flat round dense>
        <q-avatar>
          <img src="https://cdn.quasar.dev/img/avatar.png">
        </q-avatar>
      </q-btn>
    </q-toolbar>
  </div>

  <div>
    <div class="q-pa-md row items-start q-gutter-md">
      <q-btn push color="white" text-color="primary" :disable="loading" label="Buscar por vagas" no-caps @click="onSearchForJobs()" />
    </div>

    <q-separator inset />

    <div v-if="loading">
      <LoadingComponent></LoadingComponent>
    </div>
    <div class="q-pa-md row items-start q-gutter-md" style="display: flex; justify-content: center; align-items: center;" v-else>
      <q-card class="my-card" flat bordered v-for="job, key in jobList" :key="key">
        <q-card-section horizontal>
          <q-card-section class="q-pt-xs">
            <div class="text-overline row">
              <div class="col-11">
                <img :src="job.careerPageLogo" width="25" alt="">
                {{ job.careerPageName }}

                <a :href="job.careerPageUrl" target="_blank">
                  Ver mais sobre a empresa
                </a>
              </div>
                {{ job.similarity_score*100 }}
            </div>
            <div class="text-h5 q-mt-sm q-mb-xs">{{ job.name }}</div>
            <div class="text-overline"> 
              <q-badge class="q-mr-sm">
                {{ getLabelJobTypeFormated(job.type) }}
              </q-badge>
              <q-badge color="orange">
                {{ getLabelWorkplaceTypeFormated(job.workplaceType) }}
              </q-badge>
            </div>
            <div class="text-overline">
              <div v-if="job.country">Data de publicação: {{ job.publishedDate.substring(0, job.publishedDate.indexOf('T')) }}</div>
              <div v-if="job.country">País: {{ job.country }}</div>
              <div v-if="job.state">Estado: {{ job.state }}</div>
              <div v-if="job.city">Cidade: {{ job.city }}</div>
            </div>
            <div class="text-caption text-grey description">
              <div v-html="job.description"></div>
            </div>
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn flat color="primary" :href="job.jobUrl" target="_blank" no-caps>
            Visualizar vaga
          </q-btn>
        </q-card-actions>
      </q-card>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from 'src/stores/auth-store';
import searchJobs from 'src/services/jobService';
import { ref } from 'vue';
import LoadingComponent from 'src/components/LoadingComponent.vue';

const authStore = useAuthStore();
const jobList = ref([])
const loading = ref(false)

const onSearchForJobs = () => {
  loading.value = true;
  searchJobs().then((response) => {
    if (response.status === 200)
      jobList.value = response.data.info
  }).catch((err) => {
    console.log("err", err)
  }).finally(() => loading.value = false);
}

const getLabelWorkplaceTypeFormated = (code) => {
  const workplaceType = {
    "on-site": "Presencial",
    "hybrid": "Híbrido",
    "remote": "Remoto"
  }

  return workplaceType[code]
}

const getLabelJobTypeFormated = (code) => {
  const jobType = {
    "vacancy_type_effective": "CLT",
    "vacancy_legal_entity": "PJ",
    "vacancy_type_internship": "Estágio",
    "vacancy_type_talent_pool": "Banco de talentos",

  }

  return jobType[code]
}

</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 340px
</style>
