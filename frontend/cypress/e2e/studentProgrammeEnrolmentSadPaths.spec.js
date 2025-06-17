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

describe('Negative Tests - StudentProgrammeEnrolmentForm', () => {
    beforeEach(() => {
        cy.visit('/students/enrol-programme');
    });

    it('Should show error if Student ID has less than 7 digits', () => {
        highlightAndAct(cy.get('input[name="studentID"]'), ($input) => {
            $input.type('123');
        });
        cy.wait(3000);
        cy.get('input[name="studentID"]').blur();
        cy.wait(3000);
        cy.contains('Student ID must be exactly 7 digits.').should('be.visible');
        cy.wait(5000);
    });

    it('Should reject Student ID with letters', () => {
        highlightAndAct(cy.get('input[name="studentID"]'), ($input) => {
            $input.type('12a4bc7');
        });
        cy.wait(3000);
        cy.get('input[name="studentID"]').should('have.value', '1247');
        cy.wait(5000);
    });

    it('Should show error if Student does not exist', () => {
        const nonExistentID = '9999999';
        cy.wait(3000);
        cy.intercept('GET', '**/students', {
            statusCode: 200,
            body: [{ studentID: 1234567 }, { studentID: 7654321 }]
        }).as('getAllStudents');

        highlightAndAct(cy.get('input[name="studentID"]'), ($input) => {
            $input.type(nonExistentID).blur();
        });

        cy.wait('@getAllStudents');
        cy.contains('Student does not exist.').should('be.visible');
        cy.wait(5000);
    });

    it('Should show error if date format is incorrect', () => {
        highlightAndAct(cy.get('input[name="studentID"]'), ($input) => {
            $input.type('1000003').blur();
        });
        cy.wait(3000);

        highlightAndAct(cy.get('#departmentID'), ($select) => {
            $select.click().type('{downarrow}{enter}');
        });
        cy.wait(3000);

        highlightAndAct(cy.get('#programmeAcronym'), ($select) => {
            $select.click().type('{downarrow}{enter}');
        });
        cy.wait(3000);

        highlightAndAct(cy.get('#accessMethodID'), ($select) => {
            $select.click().type('{downarrow}{enter}');
        });
        cy.wait(3000);

        highlightAndAct(cy.get('input[name="date"]'), ($input) => {
            $input.type('2015-06-12');
        });
        cy.wait(3000);

        cy.contains('Date must be in DD-MM-YYYY format.').should('be.visible');
        cy.wait(5000);
    });

    it('Should not allow submission if required fields are missing', () => {
        cy.get('button[type="submit"]').should('be.disabled');
        cy.wait(5000);
    });
});
