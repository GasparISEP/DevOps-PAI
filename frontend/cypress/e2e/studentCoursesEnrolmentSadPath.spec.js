describe('Negative Tests - EnrollStudentForm', () => {
    beforeEach(() => {
        cy.visit('/students/enroll');
    });

    it('Should show error if Student ID has less than 7 digits', () => {
        cy.get('input[name="studentId"]').type('123');
        cy.wait(1000);
        cy.get('input[name="studentId"]').blur();
        cy.wait(1000);
        cy.contains('Student ID must be between 1000000 and 2000000.').should('be.visible');
        cy.wait(2000);
    });

    it('Should reject Student ID with letters', () => {
        cy.get('input[name="studentId"]').type('12ab4cd');
        cy.wait(1000);
        cy.get('input[name="studentId"]').should('have.value', '124');
        cy.wait(2000);
    });

    it('Should show error if Student does not exist', () => {
        const fakeId = '1234567';

        cy.intercept('GET', '/programmes/1234567/programmes-enrolled-in', {
            statusCode: 404,
            body: { message: 'Student not found' }
        }).as('getEnrolledProgrammes');

        cy.get('input[name="studentId"]').type('1234567').blur();
        cy.wait('@getEnrolledProgrammes');
        cy.contains('Error loading student programmes.').should('be.visible');
    });

    it('Should show error if programme or edition is not selected', () => {
        // Assume que temos um student válido já com info carregada
        cy.get('input[name="studentId"]').type('1001000').blur();
        cy.wait(1000);
        cy.get('select[name="programme"]').select('');
        cy.get('select[name="edition"]').select('');
        cy.get('button[type="submit"]').first().click();;
        cy.contains('Please fill in all fields and select at least one course.').should('be.visible');
        cy.wait(2000);
    });

    it('Should not allow selecting more than 60 ECTS', () => {
        const mockCourses = Array.from({ length: 5 }, (_, i) => ({
            acronym: `C${i + 1}`,
            name: `Course ${i + 1}`,
            qtyECTS: 15,
            semester: 1,
            curricularYear: 1,
            studyPlanDate: "2024-09-01",
            programmeAcronym: "ISEP"
        }));

        cy.intercept('GET', '**/available-courses*', mockCourses).as('getCourses');

        cy.get('input[name="studentId"]').type('1001000').blur();
        cy.get('select[name="programme"]').select('Computer Sci');
        cy.get('select[name="edition"]').select('2015');


        cy.get('input[type="checkbox"]').each(($checkbox, index) => {
            if (index < 11) {
                cy.wrap($checkbox).check({ force: true });
            }
        });


        cy.get('input[type="checkbox"]').eq(11).should('be.disabled');
        cy.wait(2000);
    });

    it('Should show error message if enrolment fails', () => {
        cy.intercept('POST', '/students/1001000/enrolments', {
            statusCode: 400,
            body: { message: 'Enrolment failed due to validation error.' }
        }).as('enrolFail');


        cy.get('input[name="studentId"]').type('1001000').blur();
        cy.get('select[name="programme"]').select('Computer Sci');
        cy.get('select[name="edition"]').select('2015');

        cy.get('input[type="checkbox"]').first().check({ force: true });

        cy.get('button[type="submit"]').first().click();
        cy.wait('@enrolFail');

        cy.contains('Erro ao inscrever o estudante no programa e unidades curriculares.').should('be.visible');
        cy.wait(2000);
    });

    it('Should keep submit button disabled if fields are empty', () => {
        cy.get('button[type="submit"]').should('be.disabled');
        cy.wait(2000);
    });
});
