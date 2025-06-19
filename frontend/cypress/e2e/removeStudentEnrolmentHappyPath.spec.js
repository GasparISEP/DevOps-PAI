describe('Remove Student Flow', () => {
    it('should remove a student after filling the required fields', () => {
        // Step 1: Go to the page
        cy.visit('/'); // Ensure we are on ISEP's home page
        cy.wait(2000); // Increased wait

        // Step 2: Click button to start removal process
        cy.contains('Student') // or use a better selector if you have one
            .as('studentMenu');
        cy.get('@studentMenu')
            .highlightBox()
            .wait(1500)
            .click(); // This reveals the dropdown

        cy.contains('Remove Course Enrolment')
            .highlightBox()
            .wait(1500)
            .click(); // This navigates to the removal page/form

        // Step 3: Type the student number
        cy.get('#studentID')
            .should('be.visible')
            .type('1000001', { delay: 50 })
            .should('have.value', '1000001')
            .wait(1500);

        // Step 4: Select course edition from dropdown
        cy.get('[data-testid="courseEdition"]')
            .should('be.visible')
            .wait(1500)
            .select('Arithmancy (ARIT) - 2025')
            .wait(1500);

        // Step 5: Click REMOVE
        cy.get('[data-testid="remove-button"]')
            .should('be.visible')
            .wait(1500)
            .click();

        // Step 7: Assert that student was removed successfully
        cy.get('[data-testid="view-all-icon"]')
            .should('be.visible')
            .wait(1500)
            .click();
    });
});