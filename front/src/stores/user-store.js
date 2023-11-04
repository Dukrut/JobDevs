import { defineStore } from "pinia";

export const useUserStore = defineStore("user", {
  state: () => ({
    id: null,
    email: null,
    name: null,
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
          proficiency: null,
          languageId: null,
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
  }),

  actions: {},

  persist: false,
});
