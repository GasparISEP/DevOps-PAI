function highlightAndAct(chainable, actionCallback) {
    chainable
        .should('exist')
        .then($el => {
            const originalOutline = $el.css('outline');
            const originalBg = $el.css('background-color');

            $el.css('outline', '3px solid green');
            $el.css('background-color', '#e0ffe0');

            return Cypress.Promise.delay(1000).then(() => {
                actionCallback(cy.wrap($el));
                return Cypress.Promise.delay(1000).then(() => {
                    $el.css('outline', originalOutline);
                    $el.css('background-color', originalBg);
                });
            });
        });
}

describe('Negative Tests - EnrollStudentForm', () => {
    beforeEach(() => {
        cy.visit('/students/enroll');
    });

    it('Should show error if Student ID has less than 7 digits', () => {
        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type('123');
        });
        cy.wait(3000);
        cy.get('input[name="studentId"]').blur();
        cy.contains('Student ID must be between 1000000 and 2000000.').should('be.visible');
        cy.wait(5000);
    });

    it('Should reject Student ID with letters', () => {
        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type('12ab4cd');
        });
        cy.get('input[name="studentId"]').should('have.value', '124');
        cy.wait(5000);
    });

    it('Should show error if Student does not exist', () => {
        const fakeId = '1234567';
        cy.intercept('GET', '/programmes/1234567/programmes-enrolled-in', {
            statusCode: 404,
            body: { message: 'Student not found' }
        }).as('getEnrolledProgrammes');
        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type(fakeId).blur();
        });
        cy.wait(3000);
        cy.wait('@getEnrolledProgrammes');
        cy.contains('Error loading student programmes.').should('be.visible');
        cy.wait(5000);
    });

    it('Should show error if programme or edition is not selected', () => {
        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type('1001000').blur();
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="programme"]'), ($select) => {
            $select.select('');
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="edition"]'), ($select) => {
            $select.select('');
        });
        cy.wait(3000);
        highlightAndAct(cy.get('button[type="submit"]').first(), ($btn) => {
            $btn.click();
        });
        cy.contains('Please fill in all fields and select at least one course.').should('be.visible');
        cy.wait(5000);
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
        cy.wait(3000);
        cy.intercept('GET', '**/available-courses*', mockCourses).as('getCourses');

        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type('1001000').blur();
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="programme"]'), ($select) => {
            $select.select('Computer Sci');
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="edition"]'), ($select) => {
            $select.select('2015');
        });
        cy.wait(3000);
        cy.get('input[type="checkbox"]').each(($checkbox, index) => {
            if (index < 10) {
                cy.wrap($checkbox).check({ force: true });
            }
        });
        cy.wait(3000);
        cy.get('input[type="checkbox"]').eq(10).should('be.disabled');
        cy.wait(5000);
    });

    it('Should show error message if enrolment fails', () => {
        cy.intercept('POST', '/students/1001000/enrolments', {
            statusCode: 400,
            body: { message: 'Enrolment failed due to validation error.' }
        }).as('enrolFail');
        cy.wait(3000);
        highlightAndAct(cy.get('input[name="studentId"]'), ($input) => {
            $input.type('1001000').blur();
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="programme"]'), ($select) => {
            $select.select('Computer Sci');
        });
        cy.wait(3000);
        highlightAndAct(cy.get('select[name="edition"]'), ($select) => {
            $select.select('2015');
        });
        cy.wait(3000);
        highlightAndAct(cy.get('input[type="checkbox"]').first(), ($checkbox) => {
            $checkbox.check({ force: true });
        });
        cy.wait(3000);
        highlightAndAct(cy.get('button[type="submit"]').first(), ($btn) => {
            $btn.click();
        });
        cy.wait(3000);
        cy.contains('button', 'Confirm', { timeout: 5000 })
            .should('be.visible')
            .then($confirmBtn => {
                highlightAndAct(cy.wrap($confirmBtn), (wrappedBtn) => {
                    wrappedBtn.click({ force: true });
                });
            });
        cy.wait(3000);
        cy.wait('@enrolFail');
        cy.contains('Erro ao inscrever o estudante no programa e unidades curriculares.').should('be.visible');
        cy.wait(5000);
    });

    it('Should keep submit button disabled if fields are empty', () => {
        cy.get('button[type="submit"]').should('be.disabled');
        cy.wait(5000);
    });
});
