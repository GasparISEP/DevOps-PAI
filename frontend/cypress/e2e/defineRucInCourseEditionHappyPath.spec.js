// frontend/e2e/defineRucInCourseEditionHappyPath.spec.js

function highlightAndAct(chainable, actionCallback, outline = '2px solid black') {
    chainable.should('exist')
        .then($el => {
            const originalOutline = $el.css('outline');
            const originalOutlineOffset = $el.css('outline-offset');

            $el.css('outline', outline);
            $el.css('outline-offset', '2px');

            return Cypress.Promise.delay(1000).then(() => {
                actionCallback(cy.wrap($el, { log: false }));
                return Cypress.Promise.delay(1000).then(() => {
                    $el.css('outline', originalOutline);
                    $el.css('outline-offset', originalOutlineOffset);
                });
            });
        });
}

describe('Define RUC in Course Edition Flow', () => {
    beforeEach(() => {
        cy.viewport(1550, 1250);
        cy.document().then(doc => {
            doc.body.style.zoom = "100%";
        });
    });

    it('Accessing the Formulary to define RUC', () => {
        cy.visit('http://localhost:3000/');
        cy.wait(500);

        highlightAndAct(
            cy.contains('Course Edition').should('be.visible'),
            $el => $el.click()
        );
        cy.wait(500);

        highlightAndAct(
            cy.contains('Define Ruc').should('be.visible'),
            $el => $el.click()
        )
        cy.wait(500);

        highlightAndAct(
            cy.get('select[name="schoolYear"]'),
            $el => $el.select(3)
        );
        cy.wait(500);

        highlightAndAct(
            cy.get('select[name="courseEditionId"]'),
            $el => $el.select(1)
        );
        cy.wait(500);

        highlightAndAct(
            cy.get('select[name="teacherId"]'),
            $el => $el.select(1)
        );
        cy.wait(500);

        highlightAndAct(
            cy.contains('REGISTER').should('be.visible'),
            $el => $el.click()
        );

        highlightAndAct(
            cy.contains('RUC successfully defined!').should('be.visible'),
            $el => $el.click()
        )

    });
});