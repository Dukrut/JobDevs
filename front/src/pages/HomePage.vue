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
      <q-btn push color="white" text-color="primary" label="Buscar por vagas" no-caps @click="onSearchForJobs()"/>
    </div>

    <q-separator inset />

    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card" flat bordered>
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

          <q-card-section class="col-5 flex flex-center">
            <q-img class="rounded-borders" src="https://cdn.quasar.dev/img/parallax2.jpg" />
          </q-card-section>
        </q-card-section>

        <q-separator />

        <q-card-actions>
          <q-btn flat round icon="event" />
          <q-btn flat>
            7:30PM
          </q-btn>
          <q-btn flat color="primary">
            Reserve
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

const authStore = useAuthStore();
const jobList = ref([])

const onSearchForJobs = () => {
  searchJobs().then((response) => {
    if (response.status === 200)
      jobList.value = response.data.info
  }).catch((err) => {
    console.log("err", err)
  });
}

</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 350px
</style>
