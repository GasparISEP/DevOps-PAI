describe('Negative Tests - StudentProgrammeEnrolmentForm', () => {
    beforeEach(() => {
        cy.visit('/students/enrol-programme');
    });

    it('Should show error if Student ID has less than 7 digits', () => {
        cy.get('input[name="studentID"]').type('123');
        cy.get('input[name="studentID"]').blur();
        cy.contains('Student ID must be exactly 7 digits.').should('be.visible');
    });

    it('Should reject Student ID with letters', () => {
        cy.get('input[name="studentID"]').type('12a4bc7');
        cy.get('input[name="studentID"]').should('have.value', '1247'); // Digits only
    });

    it('Should show error if Student does not exist', () => {
        const nonExistentID = '9999999';

        cy.intercept('GET', '**/students', {
            statusCode: 200,
            body: [{ studentID: 1234567 }, { studentID: 7654321 }]
        }).as('getAllStudents');

        cy.get('input[name="studentID"]').type(nonExistentID).blur();

        cy.wait('@getAllStudents');
        cy.contains('Student does not exist.').should('be.visible');
    });

    it('Should show error if date format is incorrect', () => {
        // Preencher Student ID válido
        cy.get('input[name="studentID"]').type('1000003').blur();

        // Preencher Department (assumindo select controlado)
        cy.get('#departmentID').click();
        cy.get('#departmentID').type('{downarrow}{enter}');

        // Preencher Programme
        cy.get('#programmeAcronym').click();
        cy.get('#programmeAcronym').type('{downarrow}{enter}');

        // Preencher Access Method para habilitar o campo date
        cy.get('#accessMethodID').click();
        cy.get('#accessMethodID').type('{downarrow}{enter}');

        // Agora o campo date deve estar habilitado, escrever data inválida
        cy.get('input[name="date"]').should('not.be.disabled').type('2015-06-12');

        // Validar mensagem de erro do formato de data
        cy.contains('Date must be in DD-MM-YYYY format.').should('be.visible');
    });

    it('Should not allow submission if required fields are missing', () => {
        cy.get('button[type="submit"]').should('be.disabled');
    });
});
