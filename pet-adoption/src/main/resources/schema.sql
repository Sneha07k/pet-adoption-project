-- =============================================
--  Pet Adoption Database Setup Script
-- =============================================

-- Create the database
CREATE DATABASE IF NOT EXISTS pet_adoption_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- Use the database
USE pet_adoption_db;

-- =============================================
--  Create pets table (optional - JPA auto-creates it)
--  Run this only if you prefer manual table creation
--  and set ddl-auto=none in application.properties
-- =============================================

CREATE TABLE IF NOT EXISTS pets (
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    name            VARCHAR(100)    NOT NULL,
    type            VARCHAR(50)     NOT NULL,
    breed           VARCHAR(100),
    age             INT             NOT NULL,
    description     TEXT,
    location        VARCHAR(150)    NOT NULL,
    owner_name      VARCHAR(100)    NOT NULL,
    owner_contact   VARCHAR(100)    NOT NULL,
    created_at      DATETIME        NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- =============================================
--  Sample Data (optional - DataSeeder handles this)
-- =============================================

INSERT INTO pets (name, type, breed, age, description, location, owner_name, owner_contact, created_at)
VALUES
    ('Buddy',   'Dog',    'Golden Retriever', 2, 'Friendly and energetic dog, great with kids.',       'New York, NY',    'John Smith',     'john.smith@email.com',   NOW()),
    ('Whiskers','Cat',    'Persian',          3, 'Calm and affectionate indoor cat, loves to cuddle.','Los Angeles, CA', 'Emily Johnson',  '+1-555-234-5678',        NOW()),
    ('Tweety',  'Bird',   'Canary',           1, 'Cheerful yellow canary who loves to sing.',          'Chicago, IL',     'Michael Brown',  'm.brown@gmail.com',      NOW()),
    ('Max',     'Dog',    'German Shepherd',  4, 'Intelligent and loyal dog, well-trained.',           'Houston, TX',     'Sarah Davis',    'sarah.davis@email.com',  NOW()),
    ('Luna',    'Cat',    'Siamese',          2, 'Playful and talkative Siamese cat.',                 'Phoenix, AZ',     'Chris Wilson',   '+1-555-876-5432',        NOW()),
    ('Nibbles', 'Rabbit', 'Holland Lop',      1, 'Gentle and curious rabbit, loves fresh vegetables.','New York, NY',    'Karen Martinez', 'karen.m@email.com',      NOW());
