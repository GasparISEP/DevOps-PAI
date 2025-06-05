// microfiche: frontend/cypress/e2e/courseRegistrationHappyPath.spec.js

// This Function highlights an element, which will be selected.
function highlightAndAct(chainable, actionCallback, outline = '3px solid black') {
    chainable
        .should('exist')
        .then($el => {
            const originalOutline = $el.css('outline');
            const originalOutlineOffset = $el.css('outline-offset');

            $el.css('outline', outline);
            $el.css('outline-offset', '2px'); // This creates a small gap between the element and the outline

            return Cypress.Promise.delay(1000).then(() => {
                actionCallback(cy.wrap($el, { log: false }));
                return Cypress.Promise.delay(1000).then(() => {
                    $el.css('outline', originalOutline);
                    $el.css('outline-offset', originalOutlineOffset);
                });
            });
        });
}


// Add the failed path test
describe('Course Registration Failed Path', () => {
    beforeEach(() => {
        // Set a larger viewport size
        cy.viewport(1550, 1250);

        // Add CSS to zoom out the page
        cy.document().then((doc) => {
            doc.body.style.zoom = "100%";
        });
    });

    it('Should show validation errors when submitting form with missing required fields', () => {
        cy.visit('http://localhost:3000');
        cy.wait(500);

        // Access the Formulary
        highlightAndAct(
            cy.contains('Course').should('be.visible'),
            $el => $el.click()
        );
        cy.wait(300);

        // Fill only the Programme field
        highlightAndAct(
            cy.get('select[name="programme"]'),
            $el => $el.select(3)
        );
        cy.wait(300);

        // Verify selected value contains 'DIS'
        cy.get('select[name="programme"]')
            .should('have.value', 'DIS');
        cy.wait(300);

        // Try to submit the form without filling other required fields
        highlightAndAct(
            cy.contains('REGISTER').should('be.visible'),
            $el => $el.click()
        );
        cy.wait(300);

        // Verify validation error messages
        cy.get('.error-message')
            .should('exist')
            .and('be.visible');

        // Verify that required fields are marked as invalid
        cy.get('select[name="course"]').should('have.class', 'is-invalid');
        cy.get('select[name="semester"]').should('have.class', 'is-invalid');
        cy.get('select[name="curricularYear"]').should('have.class', 'is-invalid');
        cy.get('select[name="duration"]').should('have.class', 'is-invalid');
        cy.get('input[name="qtdECTS"]').should('have.class', 'is-invalid');
    });
});