describe('Student Registration Flow', () => {
    it('fills the form and registers a student', () => {
        cy.visit('http://localhost:3000');

        cy.contains('Student').click();
        cy.contains('Register Student').should('be.visible').wait(1000);

        // Fill inputs
        cy.get('#name').type('Maria Silva').wait(1000);
        cy.get('#nif').type('123456789').wait(1000);
        // ---- SELECT nifcountry (Portugal) ----
        cy.get('#nifcountry').click(); // open dropdown
        cy.get('.student-form-select__option').contains('Portugal').click();

        cy.get('#street').type('Rua das Flores 123').wait(1000);
        cy.get('#postalCode').type('4000-007').wait(1000);
        cy.get('#location').type('Porto').wait(1000);
        // ---- SELECT addressCountry (Portugal) ----
        cy.get('#addressCountry').click().wait(1000); // open dropdown
        cy.get('.student-form-select__option').contains('Portugal').click().wait(1000);
        // ---- PHONE input using react-phone-input-2 ----
        cy.get('input.student-phone-number').type('912345678').wait(1000);

        cy.get('#email').type('maria.silva@example.com').wait(1000);


        // ---- PHONE input using react-phone-input-2 ----
        cy.get('input.student-phone-number').type('912345678').wait(1000);

        // ---- Click REGISTER button ----
        cy.contains('REGISTER').click().wait(1000);

        // ---- Expect a success message
        cy.contains('registered successfully').should('be.visible').wait(1000);
    });
});
