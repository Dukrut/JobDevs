import re
import argparse
import requests
import json
import openai
openai.api_key = "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"


def format_place(place_enum):
    job_place = {
        "PRESENTIAL": "on-site",
        "HOME_OFFICE": "remote",
        "HYBRID": "hybrid"
    }

    return job_place[place_enum]


def format_contract_preference(contract_enum):
    contract_preference = {
        "FREELANCE": "vacancy_type_freelancer",
        "CLT": "vacancy_type_effective",
        "PJ": "vacancy_legal_entity"
    }

    return contract_preference[contract_enum]


def format_gender(gender_enum):
    gender = {
        "M": "masculino",
        "F": "feminino",
    }

    return gender[gender_enum]


def format_proficiency(proficiency_enum):
    proficiency = {
        "BEGINNER": "básico",
        "INTERMEDIARY": "intermediário",
        "ADVANCED": "avançado"
    }

    return proficiency[proficiency_enum]


def format_experience_time(experience_enum):
    experience = {
        "LESS_THAN_1_YEAR": "menos que 1 ano",
        "ONE_YEAR": "1 ano",
        "TWO_YEARS": "2 anos",
        "THREE_YEARS": "3 anos",
        "FOUR_YEARS": "4 anos",
        "FIVE_YEARS": "5 anos",
        "MORE_THAN_FIVE_YEARS": "mais que 5 anos"
    }

    return experience[experience_enum]


def format_user_info(user_profile):
    user_description = user_profile['profile']['description']
    user_skills = ", ".join(["{} - {}".format(skill['name'], format_experience_time(
        skill['experienceTime'])) for skill in user_profile['skills']])
    user_languages = ", ".join(
        [lang['proficiency'] for lang in user_profile['language']['languageSkills']])

    full_description = (
        f"Meu perfil: {user_description} "
        f"Minhas habilidades: {user_skills} "
        f"Idiomas: {user_languages}"
    )
    return full_description


def get_gpt3_similarity(text1, text2):
    prompt = {
        'messages': [
            {'role': 'system', 'content': 'You are a helpful assistant.'},
            {
                'role': 'user',
                'content': f"You only need to return the rating on a scale of 0 to 1 in comparison to the vacancy profile and the person's profile.Take into account that we are dealing with a software developer vacancy, carefully analyze the skills, languages, experience, qualifications and requirements of the vacancy.\nPerson profile:{text1}\nVacancy profile:{text2}\nAnswer like this: Rating: rate_value\n"
            }
        ]
    }

    try:
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=prompt['messages']
        )
        content_str = response['choices'][0]['message']['content']
        match = re.search(r'Rating: (\d+.\d+)', content_str)
        if match:
            relevance_score = float(match.group(1))
            return relevance_score
        else:
            return 0
    except Exception as e:
        return 0


def search_jobs_synonyms(user_profile):
    user_skills = ",".join(["{}".format(skill['name'])
                           for skill in user_profile['skills']])
    synonyms = [
        'desenvolvedor',
        'programador',
    ]
    synonyms = synonyms + user_skills.split(",")
    
    total_jobs = []
    
    job_type = format_contract_preference(
        user_profile["profile"]["contractPreference"])
    job_place = format_place(user_profile["profile"]["jobPreference"])
    for synonym in synonyms:
        url = 'https://portal.api.gupy.io/api/v1/jobs'
        params = {
            'jobName': f"{synonym.lower().replace(' ', '%20')}",
            'type': f"{job_type}",
            "workplaceType": f"{job_place}",
            'limit': 5,
            'offset': 1
        }

        response = requests.get(url, params=params)

        if response.status_code == 200:
            response_data = response.json()

            jobs = response_data.get('data', [])
            if jobs and isinstance(jobs[0], dict):
                total_jobs.extend(jobs)

    full_user_description = format_user_info(user_profile)

    for job in total_jobs:
        job_description = f"Título:{job['name']}\nDescrição: {job['description']}"
        score = get_gpt3_similarity(full_user_description, job_description)
        job['similarity_score'] = score

    matching_jobs = sorted(
        total_jobs, key=lambda x: x['similarity_score'], reverse=True)

    print(json.dumps(matching_jobs, indent=2))


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Process some arguments.')
    parser.add_argument('--user_profile', type=str,
                        help='A string of JSON containing the user profile.')
    args = parser.parse_args()

    user_profile = json.loads(args.user_profile)
    search_jobs_synonyms(user_profile)
