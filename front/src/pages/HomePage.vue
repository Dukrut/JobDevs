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
      <q-btn push color="white" text-color="primary" label="Buscar por vagas" no-caps />
    </div>

    <q-separator inset />

    <div class="q-pa-md row items-start q-gutter-md" style="display: flex; justify-content: center; align-items: center;">
      <q-card class="my-card" flat bordered v-for="job, key in jobList" :key="key">
        <q-card-section horizontal>
          <q-card-section class="q-pt-xs">
            <div class="text-overline">Overline</div>
            <div class="text-h5 q-mt-sm q-mb-xs">Title</div>
            <div class="text-caption text-grey">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
              dolore
              magna aliqua.
            </div>
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn flat color="primary" no-caps>
            Ir para vaga
          </q-btn>
        </q-card-actions>
      </q-card>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from 'src/stores/auth-store';
import { ref } from 'vue';

const authStore = useAuthStore();
const jobList = [
  {
    "companyId": 40133,
    "name": "Desenvolvedor Java Sr",
    "description": "1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Responsabilidades e atribui\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Requisitos e qualifica\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.",
    "careerPageId": 140986,
    "careerPageName": "Account Manager Jr",
    "careerPageLogo": "",
    "type": "vacancy_legal_entity",
    "publishedDate": "2023-06-21T14:47:38.135Z",
    "applicationDeadline": null,
    "isRemoteWork": true,
    "city": "S\u00e3o Paulo",
    "state": "S\u00e3o Paulo",
    "country": "Brasil",
    "jobUrl": "https://taking&140986&inactive.gupy.io/job/eyJqb2JJZCI6NTAzMzk0MCwic291cmNlIjoiZ3VweV9wb3J0YWwifQ==?jobBoardSource=gupy_portal",
    "badges": {
      "friendlyBadge": false
    },
    "disabilities": true,
    "workplaceType": "remote",
    "careerPageUrl": "",
    "similarity_score": 0
  },
  {
    "companyId": 40133,
    "name": "Desenvolvedor Java Sr",
    "description": "1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Responsabilidades e atribui\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Requisitos e qualifica\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.",
    "careerPageId": 140986,
    "careerPageName": "Account Manager Jr",
    "careerPageLogo": "",
    "type": "vacancy_legal_entity",
    "publishedDate": "2023-06-21T14:47:38.135Z",
    "applicationDeadline": null,
    "isRemoteWork": true,
    "city": "S\u00e3o Paulo",
    "state": "S\u00e3o Paulo",
    "country": "Brasil",
    "jobUrl": "https://taking&140986&inactive.gupy.io/job/eyJqb2JJZCI6NTAzMzk0MCwic291cmNlIjoiZ3VweV9wb3J0YWwifQ==?jobBoardSource=gupy_portal",
    "badges": {
      "friendlyBadge": false
    },
    "disabilities": true,
    "workplaceType": "remote",
    "careerPageUrl": "",
    "similarity_score": 0
  },
  {
    "companyId": 40133,
    "name": "Desenvolvedor Java Sr",
    "description": "1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Responsabilidades e atribui\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Requisitos e qualifica\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.",
    "careerPageId": 140986,
    "careerPageName": "Account Manager Jr",
    "careerPageLogo": "",
    "type": "vacancy_legal_entity",
    "publishedDate": "2023-06-21T14:47:38.135Z",
    "applicationDeadline": null,
    "isRemoteWork": true,
    "city": "S\u00e3o Paulo",
    "state": "S\u00e3o Paulo",
    "country": "Brasil",
    "jobUrl": "https://taking&140986&inactive.gupy.io/job/eyJqb2JJZCI6NTAzMzk0MCwic291cmNlIjoiZ3VweV9wb3J0YWwifQ==?jobBoardSource=gupy_portal",
    "badges": {
      "friendlyBadge": false
    },
    "disabilities": true,
    "workplaceType": "remote",
    "careerPageUrl": "",
    "similarity_score": 0
  },
  {
    "companyId": 40133,
    "name": "Desenvolvedor Java Sr",
    "description": "1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Responsabilidades e atribui\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.Requisitos e qualifica\u00e7\u00f5es1. Experi\u00eancia comprovada em desenvolvimento de software utilizando Java Spring Boot, SQL Server e Bitbucket.2. Conhecimento desej\u00e1vel em Progress, PostgreSQL, MongoDB e Angular.3. Familiaridade com pr\u00e1ticas de desenvolvimento \u00e1gil e metodologias de gerenciamento de projetos.4. Habilidades s\u00f3lidas de resolu\u00e7\u00e3o de problemas e depura\u00e7\u00e3o de c\u00f3digo.5. Capacidade de trabalhar em equipe, comunicar-se efetivamente e colaborar em projetos conjuntos.6. Boas habilidades de organiza\u00e7\u00e3o e autogerenciamento.7. Forte interesse em aprender novas tecnologias e se manter atualizado com as tend\u00eancias do setor.",
    "careerPageId": 140986,
    "careerPageName": "Account Manager Jr",
    "careerPageLogo": "",
    "type": "vacancy_legal_entity",
    "publishedDate": "2023-06-21T14:47:38.135Z",
    "applicationDeadline": null,
    "isRemoteWork": true,
    "city": "S\u00e3o Paulo",
    "state": "S\u00e3o Paulo",
    "country": "Brasil",
    "jobUrl": "https://taking&140986&inactive.gupy.io/job/eyJqb2JJZCI6NTAzMzk0MCwic291cmNlIjoiZ3VweV9wb3J0YWwifQ==?jobBoardSource=gupy_portal",
    "badges": {
      "friendlyBadge": false
    },
    "disabilities": true,
    "workplaceType": "remote",
    "careerPageUrl": "",
    "similarity_score": 0
  },
]

</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 340px
</style>
