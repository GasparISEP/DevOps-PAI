-- LIMPEZA DAS TABELAS (ordem importa para FK)
DELETE FROM student_grade;
DELETE FROM course_edition_enrolments;
DELETE FROM course_edition;
DELETE FROM course;
DELETE FROM student;
DELETE FROM programme;
DELETE FROM school_year;

-- ESTUDANTE
INSERT INTO student (
    unique_number,
    name,
    country,
    country_code,
    location,
    nif_country,
    nif_number,
    phone_number,
    postal_code,
    street,
    student_email,
    student_academic_email
) VALUES (
             1102840,
             'João Silva',
             'Portugal',
             'PT',
             'Lisboa',
             'PT',
             '123456789',
             '912345678',
             '1000-001',
             'Rua Principal 1',
             'joao.silva@email.com',
             'joao.silva@academic.edu'
         );


-- PROGRAMA
INSERT INTO programme (programme_acronym, name)
VALUES ('LEI', 'Engenharia Informática');

-- ANO LETIVO
INSERT INTO school_year (school_year_id, description, start_date, end_date)
VALUES ('11111111-1111-1111-1111-111111111111', '2024/2025', '2024-09-01', '2025-07-31');

-- CURSO
INSERT INTO course (
    courseid_acronym,
    courseid_name
) VALUES (
             'PAI',
             'Processos de Apoio à Inovação'
         );

-- PROGRAMME EDITION
INSERT INTO programme_edition (
    edition_programme_acronym,
    edition_school_year,
    generated_id
) VALUES (
             'LEI',
             '11111111-1111-1111-1111-111111111111',
             '22222222-2222-2222-2222-222222222222' -- usa qualquer UUID válido
         );

-- COURSE EDITION
INSERT INTO course_edition (
    courseid_acronym,
    courseid_name,
    local_date,
    programme_acronym,
    edition_programme_acronym,
    edition_school_year,
    course_edition_generated_id,
    teacher_acronym
) VALUES (
             'PAI',
             'Processos de Apoio à Inovação',
             '2020-09-01',
             'LEI',
             'LEI',
             '11111111-1111-1111-1111-111111111111',
             '09f55463-aed7-4821-bfb2-712151f62714',
             NULL
         );

-- INSCRIÇÃO DO ESTUDANTE
INSERT INTO course_edition_enrolments (
    programme_acronym,
    edition_programme_acronym,
    edition_school_year,
    courseid_acronym,
    courseid_name,
    local_date,
    unique_number,
    status,
    enrolment_date,
    cee_generated_id
) VALUES (
             'LEI',
             'LEI',
             '11111111-1111-1111-1111-111111111111',
             'PAI',
             'Processos de Apoio à Inovação',
             '2020-09-01',
             1102840,
             TRUE,
             CURRENT_DATE,
             '09f55463-aed7-4821-bfb2-712151f62714'
         );
