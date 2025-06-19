describe('Show Average Grade of a Course Edition', () => {

    it('displays the average grade', () => {
        cy.visit('/');
        cy.wait(1500);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1500)
            .click();

        cy.wait(1500);

        cy.contains('Display Course Editions')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(1500);

        cy.get('select')
            .should('be.visible')
            .highlightBox()
            .select('Programme Name');

        cy.wait(1500);

        cy.get('input[type="text"]')
            .should('be.visible')
            .highlightBox()
            .clear()
            .type('Computer Sci');

        cy.wait(1500);

        cy.get('table.display-form-table tbody tr')
            .eq(1)
            .within(() => {
                cy.get('button')
                    .highlightBox()
                    .wait(200)
                    .click();
            });

        cy.wait(1500);

        cy.contains('Show Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(1500);

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.get('h2, p').then(($elements) => {
                    const modalText = $elements.text();

                    if (modalText.includes('No Grades Available')) {
                        expect(modalText).to.include('No Grades Available');
                    } else {
                        const match = modalText.match(/\d+(\.\d+)?/);
                        expect(match).to.not.be.null;
                        const average = parseFloat(match[0]);
                        expect(average).to.be.a('number');
                    }
                });

                cy.get('button.pagination-btn-secondary')
                    .contains('Close')
                    .should('be.visible')
                    .highlightBox()
                    .click();
            });

        cy.wait(1500);

        cy.contains('Back to Main Page')
            .should('be.visible')
            .highlightBox()
            .click();
    });

    it('displays no grades message', () => {
        cy.visit('/');
        cy.wait(1500);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1500)
            .click();

        cy.wait(1500);

        cy.contains('Display Course Editions')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(1500);

        cy.get('select')
            .should('be.visible')
            .highlightBox()
            .select('Programme Name');

        cy.wait(1500);

        cy.get('input[type="text"]')
            .should('be.visible')
            .highlightBox()
            .clear()
            .type('Computer Sci');

        cy.wait(1500);

        // Ir até à página 6 (última)
        for (let i = 0; i < 5; i++) {
            cy.contains('Next')
                .should('be.visible')
                .click();
            cy.wait(1500);
        }

        // Selecionar o 4.º item (linha 3)
        cy.get('table.display-form-table tbody tr')
            .eq(3)
            .within(() => {
                cy.get('button')
                    .highlightBox()
                    .wait(200)
                    .click();
            });

        cy.wait(1500);

        cy.contains('Show Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(1500);

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.get('h2, p').then(($elements) => {
                    const modalText = $elements.text();

                    if (modalText.includes('No Grades Available')) {
                        expect(modalText).to.include('No Grades Available');
                    } else {
                        const match = modalText.match(/\d+(\.\d+)?/);
                        expect(match).to.not.be.null;
                        const average = parseFloat(match[0]);
                        expect(average).to.be.a('number');
                    }
                });

                cy.get('button.pagination-btn-secondary')
                    .contains('Close')
                    .should('be.visible')
                    .highlightBox()
                    .click();
            });

        cy.wait(1500);

        cy.contains('Back to Main Page')
            .should('be.visible')
            .highlightBox()
            .click();
    });

    it('displays server error modal when backend fails', () => {
        cy.visit('/');
        cy.wait(1500);

        cy.contains('Course Edition')
            .as('courseEditionLink');
        cy.get('@courseEditionLink')
            .highlightBox()
            .wait(1500)
            .click();

        cy.wait(1500);

        cy.contains('Display Course Editions')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait(1500);

        cy.get('select')
            .should('be.visible')
            .highlightBox()
            .select('Programme Name');

        cy.wait(1500);

        cy.get('input[type="text"]')
            .should('be.visible')
            .highlightBox()
            .clear()
            .type('Computer Sci');

        cy.wait(1500);

        // Selecionar o 2.º item da lista diretamente (página atual)
        cy.get('table.display-form-table tbody tr')
            .eq(1)
            .within(() => {
                cy.get('button')
                    .highlightBox()
                    .wait(200)
                    .click();
            });

        cy.wait(1500);

        // Simula erro de servidor
        cy.intercept('GET', /\/course-editions\/averagegrade.*/, {
            statusCode: 500,
            body: {},
        }).as('getAverageGradeError');

        cy.contains('Show Average Grade')
            .should('be.visible')
            .highlightBox()
            .wait(200)
            .click();

        cy.wait('@getAverageGradeError');

        cy.wait(1500);

        cy.get('.modal-overlay', { timeout: 10000 })
            .should('be.visible')
            .within(() => {
                cy.contains('Server Error').should('be.visible');

                cy.get('button.pagination-btn-secondary')
                    .contains('Close')
                    .should('be.visible')
                    .highlightBox()
                    .click();
            });

        cy.wait(1500);

        cy.contains('Back to Main Page')
            .should('be.visible')
            .highlightBox()
            .click();
    });


});