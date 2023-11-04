CREATE TABLE IF NOT EXISTS `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UN` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `educations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `institution_name` varchar(255) NOT NULL,
  `degree` varchar(100) DEFAULT NULL,
  `field_study` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `activities` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `education_FK` (`user_id`),
  CONSTRAINT `education_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `read` tinyint(4) DEFAULT 0,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `notification_FK` (`user_id`),
  CONSTRAINT `notification_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `profiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `hobbies` varchar(255) DEFAULT NULL,
  `last_role` varchar(255) DEFAULT NULL,
  `last_company` varchar(255) DEFAULT NULL,
  `job_preference` enum('HOME_OFFICE','HYBRID','PRESENTIAL') NOT NULL,
  `contract_preference` enum('FREELANCE','CLT','PJ') DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_FK` (`user_id`),
  CONSTRAINT `profile_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `experience_time` ENUM('LESS_THAN_1_YEAR', 'ONE_YEAR', 'TWO_YEARS', 'THREE_YEARS', 'FOUR_YEARS', 'FIVE_YEARS', 'MORE_THAN_FIVE_YEARS') NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_has_skill_FK` (`user_id`),
  CONSTRAINT `user_has_skill_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `user_languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `english_proficiency` enum('BEGINNER','INTERMEDIARY','ADVANCED') DEFAULT NULL,
  `worked_with_english` TINYINT(1) DEFAULT 0,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UN` (`user_id`),
  KEY `user_has_language_FK` (`user_id`),
  CONSTRAINT `user_has_language_FK` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `user_languages_has_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `proficiency` enum('BEGINNER','INTERMEDIARY','ADVANCED') DEFAULT NULL,
  `language_id` int(11) NOT NULL,
  `user_language_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_has_language_skills_FK` (`user_language_id`),
  KEY `user_has_language_skills_FK_1` (`language_id`),
  CONSTRAINT `user_has_language_skills_FK` FOREIGN KEY (`user_language_id`) REFERENCES `user_languages` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_has_language_skills_FK_1` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;