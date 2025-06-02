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

describe('Course Registration Flow', () => {
    beforeEach(() => {
        // Set a larger viewport size
        cy.viewport(1550, 1250);

        // Add CSS to zoom out the page
        cy.document().then((doc) => {
            doc.body.style.zoom = "100%";  // You can adjust this value (75% = zoomed out, higher than 100% = zoomed in)
        });
    });

    it('Fills the Formulary of the Course', () => {
        cy.visit('http://localhost:3000');
        cy.wait(500);

        // Access the Formulary
        highlightAndAct(
            cy.contains('Course').should('be.visible'),
            $el => $el.click()
        );

        highlightAndAct(
            cy.get('select[name="programme"]', { scrollBehavior: false }),
            $el => $el.select(3)
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
            cy.contains('REGISTER').should('be.visible'),
            $el => $el.click()
        );
        cy.wait(300);

        // Confirmation PopUp
        cy.contains('The course was registered successfully.')
            .should('be.visible');
        cy.wait(300);
    });
});