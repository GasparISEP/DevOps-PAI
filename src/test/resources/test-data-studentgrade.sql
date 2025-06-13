DELETE FROM course_edition_enrolments;
DELETE FROM course_edition;
DELETE FROM programme_edition;
DELETE FROM school_year;

INSERT INTO school_year (
    school_year_id, description, start_date, end_date
) VALUES (
             '11111111-1111-1111-1111-111111111111', '2024/2025', '2024-09-01', '2025-06-30'
         );

INSERT INTO programme_edition (
    edition_programme_acronym, edition_school_year, generated_id
) VALUES (
             'LEI', '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'
         );

INSERT INTO course_edition (
    courseid_acronym, courseid_name, local_date,
    programme_acronym, edition_programme_acronym, edition_school_year,
    course_edition_generated_id, teacher_acronym
) VALUES (
             'PAI', 'Processos de Apoio à Inovação', '2020-09-01',
             'LEI', 'LEI', '11111111-1111-1111-1111-111111111111',
             '4d65ec3a-8799-4e3c-bcf1-350b38dd0baf', 'AAB'
         );

INSERT INTO course_edition_enrolments (
    programme_acronym, edition_programme_acronym, edition_school_year,
    courseid_acronym, courseid_name,
    local_date, unique_number, status,
    enrolment_date, cee_generated_id, version
) VALUES (
             'LEI', 'LEI', '11111111-1111-1111-1111-111111111111',
             'PAI', 'Processos de Apoio à Inovação',
             '2020-09-01', 1102840, TRUE,
             CURRENT_DATE, '4d65ec3a-8799-4e3c-bcf1-350b38dd0baf', 0
         );

