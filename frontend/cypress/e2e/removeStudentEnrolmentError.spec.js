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
            .type('1000067', { delay: 50 })
            .should('have.value', '1000067')
            .wait(3000);
    });
});