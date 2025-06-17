describe('Negative Tests - StudentProgrammeEnrolmentForm', () => {
    beforeEach(() => {
        cy.visit('/students/enrol-programme');
    });

    it('Should show error if Student ID has less than 7 digits', () => {
        cy.get('input[name="studentID"]').type('123');
        cy.wait(1000);
        cy.get('input[name="studentID"]').blur();
        cy.wait(1000);
        cy.contains('Student ID must be exactly 7 digits.').should('be.visible');
        cy.wait(3000);
    });

    it('Should reject Student ID with letters', () => {
        cy.get('input[name="studentID"]').type('12a4bc7');
        cy.wait(1000);
        cy.get('input[name="studentID"]').should('have.value', '1247');
        cy.wait(3000);
    });

    it('Should show error if Student does not exist', () => {
        const nonExistentID = '9999999';
        cy.wait(1000);
        cy.intercept('GET', '**/students', {
            statusCode: 200,
            body: [{ studentID: 1234567 }, { studentID: 7654321 }]
        }).as('getAllStudents');

        cy.get('input[name="studentID"]').type(nonExistentID).blur();

        cy.wait('@getAllStudents');
        cy.contains('Student does not exist.').should('be.visible');
        cy.wait(3000);
    });

    it('Should show error if date format is incorrect', () => {
        // Preencher Student ID válido
        cy.get('input[name="studentID"]').type('1000003').blur();
        cy.wait(1000);
        // Preencher Department (assumindo select controlado)
        cy.get('#departmentID').click();
        cy.wait(1000);
        cy.get('#departmentID').type('{downarrow}{enter}');
        cy.wait(1000);
        // Preencher Programme
        cy.get('#programmeAcronym').click();
        cy.wait(1000);
        cy.get('#programmeAcronym').type('{downarrow}{enter}');
        cy.wait(1000);
        // Preencher Access Method para habilitar o campo date
        cy.get('#accessMethodID').click();
        cy.wait(1000);
        cy.get('#accessMethodID').type('{downarrow}{enter}');
        cy.wait(1000);
        // Agora o campo date deve estar habilitado, escrever data inválida
        cy.get('input[name="date"]').should('not.be.disabled').type('2015-06-12');
        cy.wait(1000);
        // Validar mensagem de erro do formato de data
        cy.contains('Date must be in DD-MM-YYYY format.').should('be.visible');
        cy.wait(3000);
    });

    it('Should not allow submission if required fields are missing', () => {
        cy.get('button[type="submit"]').should('be.disabled');
        cy.wait(3000);
    });
});
