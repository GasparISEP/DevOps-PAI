describe('Course Edition Average Grade Form', () => {

    it('shows the average grade when grade is available', () => {
        cy.visit('/');
        cy.wait(2000);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1000)
            .click();

        cy.wait(1500);

        cy.contains('Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(2000);


        cy.get('select#programme')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .select('CSD');

        cy.wait(1000);


        cy.get('select#course')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .select('ALCH - Alchemy');

        cy.wait(1000);

        // Select School Year
        cy.get('select#schoolYear')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .select('2025');

        cy.wait(1000);

        cy.contains('GET AVERAGE')
            .should('be.visible')
            .highlightBox()
            .click();

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.contains('Average Grade').should('be.visible');
                cy.get('p').invoke('text').then(text => {
                    const grade = parseFloat(text);
                    expect(grade).to.be.a('number');
                });

                cy.contains('Close')
                    .should('be.visible')
                    .wait(2000)
                    .click()
                    .wait(1000);
            });
    });

    it('shows "No Grades Available" when there are no grades', () => {
        cy.visit('/');
        cy.wait(2000);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1000)
            .click();

        cy.wait(1500);

        cy.contains('Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(2000);

        cy.get('select#programme')
            .should('be.visible')
            .highlightBox()
            .select('CSD - Computer Sci');

        cy.wait(1000);

        cy.get('select#course')
            .should('be.visible')
            .highlightBox()
            .select('ALCH - Alchemy');

        cy.wait(1000);

        cy.get('select#schoolYear')
            .should('be.visible')
            .highlightBox()
            .select('2015');

        cy.wait(1000);

        cy.contains('GET AVERAGE')
            .should('be.visible')
            .highlightBox()
            .click();

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.contains('No Grades Available').should('be.visible');
                cy.contains('Close')
                    .should('be.visible')
                    .wait(2000)
                    .click()
                    .wait(1000);
            });
    });

    it('shows server error modal when backend fails', () => {
        cy.intercept('GET', /\/course-editions\/averagegrade.*/, {
            statusCode: 500,
            body: {},
        }).as('averageGradeError');

        cy.visit('/');
        cy.wait(2000);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1000)
            .click();

        cy.wait(1500);

        cy.contains('Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(2000);

        cy.get('select#programme')
            .should('be.visible')
            .highlightBox()
            .select('CSD - Computer Sci');

        cy.wait(1000);

        cy.get('select#course')
            .should('be.visible')
            .highlightBox()
            .select('ARIT - Arithmancy');

        cy.wait(1000);

        cy.get('select#schoolYear')
            .should('be.visible')
            .highlightBox()
            .select('2023');

        cy.wait(1000);

        cy.contains('GET AVERAGE')
            .should('be.visible')
            .highlightBox()
            .click();

        cy.wait('@averageGradeError');

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.contains('Server Error').should('be.visible');
                cy.contains('Close')
                    .should('be.visible')
                    .wait(2000)
                    .click()
                    .wait(1000);
            });
    });

});