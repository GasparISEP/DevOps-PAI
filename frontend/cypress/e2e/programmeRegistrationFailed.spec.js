describe('Programme Registration - Invalid Acronym Demo', () => {

    beforeEach(() => {
        cy.visit('/');
        cy.wait(2000);
    });

    it('demonstrates failed registration with invalid acronym (full word) based on current form layout', () => {
        // Navigate to Programme Registration page
        cy.contains('Programme').then($elements => {
            cy.wrap($elements.first()).click({ force: true });
        });

        // Verify we're on the programmes page
        cy.url().should('include', '/programmes');
        cy.wait(2000);

        // Fill the dropdowns first, with delays for demonstration
        // Degree Type (first dropdown)
        cy.get('select[name="degreeTypeID"]')
            .should('be.visible')
            .select(1);
        cy.wait(500);

        // Department (second dropdown)
        cy.get('select[name="departmentID"]')
            .should('be.visible')
            .select(1);
        cy.wait(500);

        // Programme Director (third dropdown)
        cy.get('select[name="teacherID"]')
            .should('be.visible')
            .select(1);
        cy.wait(500);

        // Then fill the text inputs (Name, then Acronym)
        cy.get('input[name="name"]')
            .should('be.visible')
            .clear()
            .type('Computer Science', { delay: 100 });

        // Use a full word as acronym - this should trigger the error
        cy.get('input[name="acronym"]')
            .should('be.visible')
            .clear()
            .type('Computer Science', { delay: 100 }); // Using a full word as invalid acronym


        cy.wait(3000);

        // Highlight the REGISTER button specifically
        cy.contains('button', 'REGISTER')
            .should('be.visible')
            .should('not.be.disabled')
            .then($btn => {
                // Add a visual highlight for the demo
                $btn.css('border', '3px solid red');
                $btn.css('background-color', '#ffcccc');
            })
            .wait(2000) // Pause to show the highlighted button
            .click();

        // Wait for the error to appear
        cy.wait(3000);

        // Demonstrate the error message appearing
        cy.contains('Registration Error', { timeout: 10000 })
            .should('be.visible')
            .then($error => {
                // Highlight the error for demo
                $error.css('border', '3px solid red');
                $error.css('background-color', '#ffe6e6');
            });

        // Assert for the specific error message related to the acronym format
        cy.contains('Acronym must contain only uppercase letters, followed by optional digits', { timeout: 5000 })
            .should('be.visible')
            .then($errorMsg => {
                // Highlight the specific error message
                $errorMsg.css('font-weight', 'bold');
                $errorMsg.css('color', 'red');
            });

        // Take a screenshot showing the error
        cy.screenshot('demo-invalid-acronym-error');

        // Wait to show the error message clearly in the recording
        cy.wait(5000);

        // Try to close the error modal/dialog using the class name as seen in ProgrammeErrorModal.jsx
        cy.get('button.modal-btn', { timeout: 5000 })
            .should('be.visible')
            .then($btn => $btn.css('border', '2px solid blue'))
            .wait(1000)
            .click();

        cy.wait(2000); // Wait after closing modal

        // Verify we're still on the registration page (form submission failed)
        cy.url().should('include', '/programmes');

        // Show that the form is still there with the invalid data
        cy.get('input[name="acronym"]').should('have.value', 'Math'); // Verify the invalid acronym is still in the input

        // Final screenshot showing we're back to the form
        cy.screenshot('demo-back-to-form-after-error');

        // Wait at the end for the demo recording
        cy.wait(3000);
    });

    it('shows various invalid acronym examples', () => {
        const invalidExamples = [
            { acronym: 'Mathematics', name: 'Mathematics Program' },
            { acronym: 'Engineering', name: 'Engineering Program' },
            { acronym: 'computer', name: 'Computer Science' }, // lowercase
            { acronym: 'BIO-TECH', name: 'Biotechnology' }, // with hyphen
            { acronym: 'INV-AC', name: 'Another Invalid Acronym' } // Added another example
        ];

        invalidExamples.forEach((example, index) => {
            cy.log(`Demo ${index + 1}: Testing "${example.acronym}" as invalid acronym`);

            // Navigate to form
            cy.visit('/');
            cy.wait(1000);

            cy.contains('Programme').then($elements => {
                cy.wrap($elements.first()).click({ force: true });
            });
            cy.url().should('include', '/programmes');

            cy.get('select[name="degreeTypeID"]', { timeout: 10000 }).should('be.visible');

            // Fill form with current invalid example
            cy.get('select[name="degreeTypeID"]').select(1).wait(300);
            cy.get('select[name="departmentID"]').select(1).wait(300);
            cy.get('select[name="teacherID"]').select(1).wait(300);

            cy.get('input[name="name"]').clear().type(example.name, { delay: 150 });
            cy.get('input[name="acronym"]').clear().type(example.acronym, { delay: 150 });

            // Submit and demonstrate the error
            cy.contains('button', 'REGISTER')
                .should('be.visible')
                .then($btn => $btn.css('background-color', '#ffdddd'))
                .wait(1000)
                .click();

            cy.wait(2000); // Wait for error modal

            // Show the error message
            cy.contains('Acronym must contain only uppercase letters', { timeout: 8000 })
                .should('be.visible');

            cy.screenshot(`demo-invalid-example-${index + 1}-${example.acronym.replace(/[^a-zA-Z0-9]/g, '_')}`);

            // Close the error modal for the next iteration
             cy.get('button.modal-btn', { timeout: 5000 })
                .should('be.visible')
                .click();
            cy.get('.modal-overlay', { timeout: 5000 }).should('not.exist');

            // Wait to show each example clearly before the next loop iteration
            cy.wait(5000);
        });
    });
});