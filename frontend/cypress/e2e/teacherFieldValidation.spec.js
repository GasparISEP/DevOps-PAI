describe('Teachers Management - Field Validation', () => {
    beforeEach(() => {
        cy.viewport(1440, 1440);
        cy.visit('/');
        // Abre o menu e acede ao registo
        cy.get('li.navbar-dropdown').trigger('mouseover');
        cy.get('ul.navbar-dropdown-menu.show').contains('Register Teacher').click();
        cy.url().should('include', '/teachers/register');
    });

    // Valores válidos para todos os campos
    const validTeacher = {
        name: 'Valid Name',
        acronym: 'VAL',
        email: 'valid@isep.ipp.pt',
        nif: '999999999',
        academicBackground: 'BG',
        street: 'Street 1',
        postalCode: '1234-567',
        location: 'City',
        country: 'Portugal',
        phone: '912345678',
        departmentID: ''  // not used directly
    };

    // Lista de campos a testar
    const fields = Object.keys(validTeacher).concat(['departmentID']);

    fields.forEach((field) => {
        it(`campo "${field}" é obrigatório`, () => {
            // Preenche todos os campos com dados válidos, exceto o em teste
            fields.forEach((k) => {
                if (k === field) {
                    // deixa em branco o campo em teste
                    return;
                }
                if (k === 'country') {
                    cy.get('.teacher-form-select__control').click();
                    cy.get('.teacher-form-select__menu').contains(validTeacher.country).click();
                } else if (k === 'phone') {
                    cy.get('input.teacher-phone-number').clear().type(validTeacher.phone);
                } else if (k === 'departmentID') {
                    // Seleciona a segunda option do select (index 1)
                    cy.get('select[name="departmentID"]').select(1);
                } else {
                    cy.get(`input[name="${k}"]`).clear().type(validTeacher[k]);
                }
            });

            // Tenta submeter e espera para visualização
            cy.contains('button', 'REGISTER').click();
            cy.wait(1000);

            // Verifica validação HTML5 ou bloqueio de rota
            if (field === 'country' || field === 'phone' || field === 'departmentID') {
                // não avança de rota
                cy.url().should('include', '/teachers/register');
            } else {
                // campo inválido
                cy.get(`input[name="${field}"]:invalid`).should('exist');
            }

            cy.wait(500); // pausa antes do próximo
        });
    });
});