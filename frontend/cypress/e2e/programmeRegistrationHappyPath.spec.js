describe('Successfully Register a Programme', () => {

    beforeEach(() => {
        cy.visit('/');
        cy.wait(1000);
    });

    it('navigates to programme registration', () => {
        // Click Programme button directly
        cy.contains('Programme')
            .as('programmeLink');

        // Highlight and click
        cy.get('@programmeLink')
            .highlightBox()
            .wait(2000)
            .click();

        // Verify URL
        cy.url().should('include', '/programmes');
    });

    it('registers a new programme via UI', () => {
        // Navigate to Programme Registration page
        cy.contains('Programme')
            .as('programmeLink');
        cy.get('@programmeLink')
            .highlightBox()
            .wait(2000)
            .click();

        cy.url().should('include', '/programmes');

        // Inputs

        // Programme Name
        cy.get('input[name="name"]').type('Comp Sci').wait(1000);
        // Programme Acronym
        cy.get('input[name="acronym"]').type('CS').wait(1000);
        // Quantity of Semesters
        cy.get('input[name="quantSemesters"]').type('6').wait(1000);
        // Degree Type
        cy.get('select[name="degreeTypeID"]').select(1).wait(1000);
        // Department
        cy.get('select[name="departmentID"]').select(1).wait(1000);
        // Programme Director (Teacher)
        cy.get('select[name="teacherID"]').select(1).wait(1000);

        // Submit and wait for success
        cy.intercept('POST', '/programmes').as('saveProgramme');
        cy.get('button[type="submit"]').highlightOutline().wait(1600).click();
        cy.wait('@saveProgramme').its('response.statusCode').should('eq', 201).wait(6000);

        // Verify success popup
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Success!').should('be.visible');
        cy.get('.modal-content')
            .contains('Computer Science')
            .should('be.visible');
        cy.get('.modal-btn').highlightOutline().wait(2000).click().wait(1600);
        cy.get('.modal-overlay').should('not.exist');
    });

    it('verifies the newly registered programme', () => {
        // Navigate to programme display
        cy.contains('Programme')
            .as('programmeLink');
        cy.get('@programmeLink')
            .highlightBox()
            .wait(2000)
            .click()
            .wait(4000);

        // Verify the programme is in the list
        cy.get('table.programme-form-table tbody tr')
            .contains('td', 'Computer Science')
            .parent('tr')
            .as('newRow');

        // Scroll to and highlight the new programme
        cy.get('@newRow')
            .scrollIntoView({ offset: { top: -100, left: 0 } })
            .wait(2000)
            .should('be.visible')
            .invoke('css', 'background-color', 'rgba(255,0,0,0.2)')
            .invoke('css', 'border', '2px solid red');
    });
});