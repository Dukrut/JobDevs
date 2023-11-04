import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    info: {
      id: null,
      name: null,
      email: null,
      password: null,
      skills: [
        {
          name: null,
          experienceTime: null,
        },
      ],
      language: {
        workedWithEnglish: false,
        englishProficiency: null,
        languageSkills: [
          {
            languageId: null,
            proficiency: null,
          },
        ],
      },
      educations: [
        {
          institutionName: null,
          fieldStudy: null,
          startDate: null,
          endDate: null,
          degree: null,
          description: null,
          activities: null,
        },
      ],
      profile: {
        description: null,
        gender: null,
        hobbies: null,
        lastRole: null,
        lastCompany: null,
        jobPreference: [],
        contractPreference: [],
      },
    },
  }),

  actions: {},

  persist: false,
});
