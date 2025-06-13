INSERT INTO course_edition_enrolments (
    cee_programme_acronym,
    cee_programme_name,
    edition_programme_acronym,
    edition_school_year,
    courseid_acronym,
    courseid_name,
    local_date,
    unique_number,
    status,
    enrolment_date,
    cee_generated_id,
    version
)
VALUES (
           'LEI',
           'Engenharia Informática',
           'LEI',
           '550e8400-e29b-41d4-a716-446655440000',
           'PAI',
           'Processos de Apoio à Inovação',
           '2023-09-01',
           1234567,
           TRUE,
           CURRENT_DATE,
           '09f55463-aed7-4821-bfb2-712151f62714',
           0
       );
