// 🔒 Disable scroll just for this test file
Cypress.config('scrollBehavior', false);

describe('Student Registration Flow - Duplicated NIF', () => {
    // Cypress-friendly highlight + action helper
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

    it('fills the form and registers a student', () => {
        cy.visit('http://localhost:3000');
        cy.wait(3000);

        // Click on "Student"
        highlightAndAct(
            cy.contains('Student').should('be.visible'),
            $el => $el.click()
        );

        // Name
        highlightAndAct(
            cy.get('#name').should('be.visible'),
            $el => $el.type('Carlos Miguel')
        );

        // NIF
        highlightAndAct(
            cy.get('#nif'),
            $el => $el.type('123456782')
        );

        // NIF Country (France)
        highlightAndAct(
            cy.get('#nifcountry'),
            $el => $el.click()
        );
        highlightAndAct(
            cy.contains('.student-form-select__option', 'France')
                .scrollIntoView()
                .should('be.visible'),
            $el => $el.click()
        );

        // Street
        highlightAndAct(
            cy.get('#street'),
            $el => $el.type('Rua das Tulipas 456')
        );

        // Postal Code
        highlightAndAct(
            cy.get('#postalCode'),
            $el => $el.type('4200-123')
        );

        // Location
        highlightAndAct(
            cy.get('#location'),
            $el => $el.type('Porto')
        );

        // Address Country (France)
        highlightAndAct(
            cy.get('#addressCountry'),
            $el => $el.click()
        );
        highlightAndAct(
            cy.contains('.student-form-select__option', 'France')
                .scrollIntoView()
                .should('be.visible'),
            $el => $el.click()
        );

        // Phone
        highlightAndAct(
            cy.get('input.student-phone-number'),
            $el => $el.type('918765432')
        );

        // Email
        highlightAndAct(
            cy.get('#email'),
            $el => $el.type('carlos.miguel@example.com')
        );

        // Click Register
        highlightAndAct(
            cy.contains('REGISTER').should('be.visible'),
            $el => $el.click()
        );

        // Check success message
        highlightAndAct(
            cy.contains('⚠️ Student with this information is already registered!', { timeout: 10000 }).should('be.visible'),
            () => {}
        );
    });
});
