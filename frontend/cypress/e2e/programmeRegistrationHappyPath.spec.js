describe('Programme Registration - Happy Path & Duplicate Demo', () => {

    it('attempts to register a programme (might fail if duplicate exists)', () => {
        // Start this test by navigating to the HOME page as requested
        cy.visit('/'); // Ensure we are on ISEP's home page
        cy.wait(2000); // Increased wait

        // Navigate to Programme Registration page by clicking the link
        cy.contains('Programme')
            .as('programmeLink');
        cy.get('@programmeLink')
            .highlightBox()
            .wait(2500) // Increased wait
            .click();

        // Ensure we are on the registration page before filling in the form
        cy.url().should('include', '/programmes');
        // Add a wait for the form elements to be present after navigation
        cy.get('select[name="degreeTypeID"]', { timeout: 15000 }).should('be.visible'); // Increased timeout

        // Degree Type (first dropdown)
        cy.get('select[name="degreeTypeID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Department (second dropdown)
        cy.get('select[name="departmentID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Programme Director (third dropdown)
        cy.get('select[name="teacherID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Programme Name (using a hardcoded name)
        cy.get('input[name="name"]')
            .should('be.visible')
            .type('Quantum Computing 1', { delay: 50 }) // Increased delay
            .should('have.value', 'Quantum Computing 1')
            .wait(1500); // Increased wait

        // Programme Acronym (using a hardcoded acronym)
        cy.get('input[name="acronym"]')
            .should('be.visible')
            .type('QC', { delay: 50 }) // Increased delay
            .should('have.value', 'QC')
            .wait(1500); // Increased wait

        // Submit and wait for response (could be 201 or 409)
        cy.intercept('POST', '/programmes').as('saveProgrammeAttempt1'); // Use a specific alias
        cy.contains('button', 'REGISTER')
            .should('be.visible')
            .should('not.be.disabled')
            .highlightOutline()
            .wait(2000) // Increased wait
            .click();

        // Wait for the response
        cy.wait('@saveProgrammeAttempt1', { timeout: 20000 }); // Increased timeout

        // Ensure any modal that appears is visible, wait, and then close it.
        cy.get('body').then(($body) => {
            if ($body.find('.modal-overlay').length > 0) {
                cy.get('.modal-overlay', { timeout: 10000 }).should('be.visible'); // Wait for modal to be visible
                cy.contains('.modal-content', 'Quantum Computing', { timeout: 10000 }).should('be.visible') // Wait for content in modal
                cy.wait(5000); // Wait for 5 seconds to show the first popup
                cy.get('.modal-btn', { timeout: 7000 }).should('be.visible').click(); // Click close button
                cy.get('.modal-overlay', { timeout: 70000 }).should('not.exist'); // Wait for modal to disappear
            }
        });

         cy.wait(3000); // Increased wait for the page to settle after modal close (if any)
    });

    it('fails to register the same programme again (confirms duplicate logic)', () => {
        // Start this test by navigating directly to the programmes page (as per preference)
        cy.visit('/programmes'); // Ensure we are on the correct page
        cy.wait(2000); // Increased wait for page load

         // Add a wait for the form elements to be present after navigation
        cy.get('select[name="degreeTypeID"]', { timeout: 15000 }).should('be.visible'); // Increased timeout

        // Inputs using the *same hardcoded* details from the previous test
        // Degree Type (first dropdown)
        cy.get('select[name="degreeTypeID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Department (second dropdown)
        cy.get('select[name="departmentID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Programme Director (third dropdown)
        cy.get('select[name="teacherID"]')
            .should('be.visible')
            .select(1)
            .wait(1500); // Increased wait

        // Programme Name (Second one)
        const programmeName = 'Computer Science'; // Use the same consistent name
        cy.get('input[name="name"]')
            .should('be.visible')
            .type(programmeName, { delay: 50 }) // Increased delay
            .should('have.value', programmeName)
            .wait(1500); // Increased wait

        // Programme Acronym (using the same hardcoded acronym)
        cy.get('input[name="acronym"]')
            .should('be.visible')
            .type('QC', { delay: 50 }) // Increased delay
            .should('have.value', 'QC')
            .wait(1500); // Increased wait

        // Submit and wait for failure (expecting duplicate error)
        cy.intercept('POST', '/programmes').as('saveProgrammeAttempt2'); // Use a different alias
        cy.contains('button', 'REGISTER')
            .should('be.visible')
            .should('not.be.disabled')
            .highlightOutline()
            .wait(2000) // Increased wait
            .click();

        // Wait for the failed response (expecting status code 400 or 409 for duplicate)
        cy.wait('@saveProgrammeAttempt2', { timeout: 20000 }) // Increased timeout
            .its('response.statusCode')
            .should('be.oneOf', [400, 409]);

        // Verify error modal appears (increased timeouts)
        cy.get('.modal-overlay', { timeout: 15000 }).should('be.visible');
        cy.contains('Registration Error', { timeout: 10000 }).should('be.visible'); // Assuming error modal title

        // Assert for a message indicating a duplicate or conflict
        cy.get('.modal-content')
             .should('contain', 'already exists')
             .should('be.visible');

        cy.log('Staying on error message for a long time...');
        cy.wait(30000); // Wait for 30 seconds to show the error popup

        // Close the error modal (increased timeouts)
        cy.get('.modal-btn', { timeout: 10000 })
            .should('be.visible')
            .click();
        cy.get('.modal-overlay', { timeout: 10000 }).should('not.exist');
    });
});