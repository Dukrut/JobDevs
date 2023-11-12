<template>
  <div>
    <ToolbarComponent></ToolbarComponent>
  </div>

  <div>
    <div class="q-pa-md row items-start q-gutter-md">
      <q-btn push color="white" text-color="primary" :disable="loading" label="Buscar por vagas" no-caps
        @click="onSearchForJobs()" />
    </div>

    <q-separator inset />

    <div v-if="loading">
      <LoadingComponent />
    </div>

    <div class="q-pa-md row items-start q-gutter-md" style="display: flex; justify-content: center; align-items: center;"
      v-else>
      <q-card class="my-card" flat bordered v-for="job, key in jobList" :key="key">
        <q-card-section horizontal>
          <q-card-section>
            <div class="text-overline row">
              <div class="col-11">
                <img :src="job.careerPageLogo" width="25" alt="" style="vertical-align: middle;">
                {{ job.careerPageName }}
              </div>
              {{ job.similarity_score * 100 }}
            </div>

            <!-- <div class="text-h5">{{ job.name }}</div> -->

            <q-card-section style=" width: 320px; padding-left: 0px;">
              <h5 class="q-pt-none" style="margin: 0px;">{{ job.name }}</h5>
            </q-card-section>

            <div class="text-overline">
              <q-badge class="q-mr-sm">
                {{ getLabelJobTypeFormated(job.type) }}
              </q-badge>
              <q-badge color="orange">
                {{ getLabelWorkplaceTypeFormated(job.workplaceType) }}
              </q-badge>
            </div>

            <q-card-section class="text-overline" style="line-height: 1.5rem; padding-left: 0px;">
              <div v-if="job.country">Data de publicação: {{ job.publishedDate.substring(0,
                job.publishedDate.indexOf('T'))
              }}</div>
              <div v-if="job.country">País: {{ job.country }}</div>
              <div v-if="job.state">Estado: {{ job.state }}</div>
              <div v-if="job.city">Cidade: {{ job.city }}</div>
            </q-card-section>

            <q-card-section class="text-caption text-grey description q-pt-none"
              style=" width: 320px; padding-left: 0px;">
              <p class="limit" v-html="job.description"></p>
            </q-card-section>
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn flat color="primary" :href="job.jobUrl" target="_blank" no-caps>
            Visualizar vaga
          </q-btn>

          <q-btn flat color="primary" :href="job.careerPageUrl" target="_blank" no-caps>
            Ver mais sobre a empresa
          </q-btn>
        </q-card-actions>
      </q-card>
    </div>
  </div>
</template>

<script setup>
import searchJobs from 'src/services/jobService';
import { ref } from 'vue';
import LoadingComponent from 'src/components/LoadingComponent.vue';
import ToolbarComponent from 'src/components/ToolbarComponent.vue';

const jobList = ref([])
const loading = ref(false)

const onSearchForJobs = () => {
  loading.value = true;
  searchJobs().then((response) => {
    if (response.status === 200) {
      jobList.value = response.data.info
      sortJobsByScore()
    }
  }).catch((err) => {
    console.log("err", err)
  }).finally(() => loading.value = false);
}

const sortJobsByScore = () => {
  jobList.value = jobList.value.sort((a, b) => b.job.similarity_score - a.job.similarity_score);
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

<style lang="scss" scoped>
.my-card {
  width: 100%;
  max-width: 340px;
}

.limit {
  text-align: justify;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  overflow: hidden;
  -webkit-box-orient: vertical;
}
</style>
