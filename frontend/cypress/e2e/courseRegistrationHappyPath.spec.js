// microfiche: frontend/cypress/e2e/courseRegistrationHappyPath.spec.js

// This Function highlights an element, which will be selected.
function highlightAndAct(chainable, actionCallback, outline = '3px solid #add8e6', bg = '#add8e6') {
    chainable
        .should('exist')
        .then($el => {
            const originalOutline = $el.css('outline');
            const originalBg = $el.css('background-color');

            $el.css('outline', outline);
            $el.css('background-color', bg);

            return Cypress.Promise.delay(1000).then(() => {
                actionCallback(cy.wrap($el));
                return Cypress.Promise.delay(1000).then(() => {
                    $el.css('outline', originalOutline);
                    $el.css('background-color', originalBg);
                });
            });
        });
}

describe('Course Registration Flow', () => {
    it('Fills the Formulary of the Course', () => {
        cy.visit('http://localhost:3000');
        cy.wait(150);

        // Access the Formulary
        highlightAndAct(
            cy.contains('Course').should('be.visible'),
            $el => $el.click()
        );

        // Selects the Programme
        highlightAndAct(
            cy.get('select[name="programme"]'),
            $el => $el.select(3)
        );
        cy.wait(150);

        // Selects Course
        highlightAndAct(
            cy.get('select[name="course"]'),
            $el => $el.select(1)
        );
        cy.wait(150);

        // Selects the Curricular Year
        highlightAndAct(
            cy.get('select[name="curricularYear"]'),
            $el => $el.select(1)
        );
        cy.wait(150);

        // Selects the Semester
        highlightAndAct(
            cy.get('select[name="semester"]'),
            $el => $el.select(1)
        );
        cy.wait(150);

        // Selects the Duration
        highlightAndAct(
            cy.get('select[name="duration"]'),
            $el => $el.select(1)
        );
        cy.wait(150);

        // Fill's the ECTS
        highlightAndAct(
            cy.get('input[name="qtdECTS"]'),
            $el => $el.clear().type('6')
        );
        cy.wait(150);

        // Submit the Formulary
        highlightAndAct(
            cy.contains('Submit'),
            $el => $el.click()
        );
        cy.wait(120);

        // Confirmation PopUp
        highlightAndAct(
            cy.contains('The course was registered successfully.').should('be.visible'),
            () => {}
        );
        cy.wait(120);
    });
});