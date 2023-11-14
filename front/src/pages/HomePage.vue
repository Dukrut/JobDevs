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

    <div class="q-pa-lg row items-start q-gutter-md" style="display: flex; justify-content: center; align-items: center;"
      v-else>
      <q-card class="my-card" flat bordered v-for="job, key in jobList" :key="key"
        style="display: -webkit-box; -webkit-line-clamp: 3; overflow: hidden; -webkit-box-orient: vertical; height: 500px;">
        <q-card-section horizontal>
          <q-card-section>
            <div class="text-overline row">
              <div class="col-11">
                <img :src="job.careerPageLogo" width="25" alt="" style="vertical-align: middle;">
                {{ job.careerPageName }}
              </div>
              <!-- {{ job.similarity_score * 100 }} -->

              <div class="col-1">
                <q-badge v-if="job.similarity_score * 100 >= 66" rounded color="green">
                  <q-tooltip>
                    Essa vaga é super compatível com o seu perfil.
                  </q-tooltip>
                </q-badge>

                <q-badge v-else-if="job.similarity_score * 100 >= 33" rounded color="yellow">
                  <q-tooltip>
                    Essa vaga pode ser possui alguns requisitos que correspondem ao seu perfil.
                  </q-tooltip>
                </q-badge>

                <q-badge v-else rounded color="red">
                  <q-tooltip>
                    Essa vaga pode não ser muito interessando para o seu objetivo profissional.
                  </q-tooltip>
                </q-badge>
              </div>
            </div>

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
              <div>País: {{ getValueOrDefault(job.country) }}</div>
              <div>Estado: {{ getValueOrDefault(job.state) }}</div>
              <div>Cidade: {{ getValueOrDefault(job.city) }}</div>
            </q-card-section>

            <q-card-section class="text-caption text-grey description q-pt-none"
              style=" width: 320px; padding-left: 0px;">
              <p class="limit" v-html="job.description"></p>
            </q-card-section>
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions style="margin-top: 17px;">
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

const getValueOrDefault = (text) => {
  if (text.length)
    return text;

  return "Não disponível";
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
