import re
import argparse
import requests
import json
import openai
openai.api_key = ""


def format_place(places):
    job_place = {
        "PRESENTIAL": "on-site",
        "HOME_OFFICE": "remote",
        "HYBRID": "hybrid"
    }

    return ','.join([job_place.get(place, "unknown") for place in places])


def format_contract_preference(contracts):
    contract_preference = {
        "FREELANCE": "vacancy_type_freelancer",
        "CLT": "vacancy_type_effective",
        "PJ": "vacancy_legal_entity"
    }

    return ','.join([contract_preference.get(contract, "unknown") for contract in contracts])


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

def find_rating(content, keywords):
    for keyword in keywords:
        match = re.search(fr'{keyword}\s*([\d.]+)', content)
        if match:
            return float(match.group(1))
    return 0


def get_gpt3_similarity(text1, text2):
    
    prompt = prompt = {
        'messages': [
            {
                'role': 'system',
                'content': 'You are an AI assistant specialized in job matching. Your task is to analyze the compatibility between a software developer\'s profile and a job description. Focus on technical skills, experience level, and specific requirements mentioned in the job. Rate the compatibility on a scale of float 0 to 1. I want only number response like this Rating: [float_value]'
            }
        ]
    }
    
    user_message = f"Developer Profile: {text1} Job Description: {text2} Please analyze the above information and rate the compatibility. Remember to consider specific programming languages, back-end versus full-stack development experience, and any other technical requirements. Provide a rating on scale of 0 to 1. Response: Rating: [float_value]"
    prompt['messages'].append({'role': 'user', 'content': user_message})

    try:
        response = openai.chat.completions.create(
            model="gpt-3.5-turbo",
            messages=prompt['messages']
        )

        content_str = response.choices[0].message.content
        rating_keywords = ["Rating:", "Avaliação:", "Classificação:"]
        rating_value = find_rating(content_str, rating_keywords)
        return rating_value

    except Exception as e:
        return 0


def search_jobs_synonyms(user_profile):
    user_skills = ",".join(["{}".format(skill['name'])
                           for skill in user_profile['skills']])
    synonyms = [
        'desenvolvedor',
        'developer',
        'software',
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

        result = get_gpt3_similarity(full_user_description, job_description)
        job['similarity_score'] = result

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
