describe('Count Enrolments In Course Edition', () => {

    it('count number of students in a course edition', () => {
        cy.visit('/');
        cy.wait(2000);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(2500)
            .click();

        cy.wait(2500);

        cy.contains('Display Course Editions')
            .should('be.visible')
            .highlightBox()
            .click();

        cy.wait(2500);

        cy.get('select')
            .should('be.visible')
            .select('Course Acronym');

        cy.wait(2500);

        cy.get('input[type="text"]')
            .should('be.visible')
            .clear()
            .type('ALCH');

        cy.wait(2500);

        cy.get('table.display-form-table tbody tr')
            .eq(1)
            .within(() => {
                cy.get('button')
                    .click();
            });

        cy.wait(2500);

        cy.contains('Count Enrolments')
            .should('be.visible')
            .click();

        cy.wait(2500);

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible');

        cy.wait(2500);

        cy.get('.modal-overlay')
            .find('button.pagination-btn-secondary')
            .contains('Close')
            .should('be.visible')
            .click();

        cy.wait(2500);

        cy.contains('Back to Main Page')
            .should('be.visible')
            .click();
    });

});