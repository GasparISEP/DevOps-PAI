
describe('Teachers Management - Non-Happy Paths', () => {
    beforeEach(() => {
        // Garante que a app inicia no estado inicial
        cy.visit('/');
        cy.wait(500);
        // Abre o menu Teacher
        cy.get('li.navbar-dropdown').trigger('mouseover');
        cy.get('ul.navbar-dropdown-menu.show').should('be.visible');
        // Clica em Register Teacher
        cy.contains('Register Teacher').click();
        cy.url().should('include', '/teachers/register');
    });

    it('não permite submeter formulário vazio', () => {
        // Tenta submeter sem preencher nada
        cy.contains('button', 'REGISTER').click();
        // Deve manter campos inválidos destacados
        cy.get('input:invalid').should('have.length.gte', 1);
    });

    it('exibe erro ao usar NIF já existente', () => {
        // Preenche com NIF duplicado
        cy.get('input[name="name"]').type('Dup Nif');
        cy.get('input[name="acronym"]').type('DUP');
        cy.get('input[name="email"]').type('dup@isep.ipp.pt');
        cy.get('input[name="nif"]').type('000000000');
        cy.get('input[name="academicBackground"]').type('BG');
        cy.get('input[name="street"]').type('Str');
        cy.get('input[name="postalCode"]').type('0000-000');
        cy.get('input[name="location"]').type('City');
        cy.get('.teacher-form-select__control').click();
        cy.get('.teacher-form-select__menu').contains('Portugal').click();
        cy.get('input.teacher-phone-number').type('912345678');
        cy.get('select[name="departmentID"]').select(1);

        // Submete e espera popup de erro
        cy.contains('button', 'REGISTER').click();
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Registration Error').should('be.visible');
        cy.get('.modal-content').contains('NIF').should('exist');
        // Fecha o modal
        cy.get('.modal-btn').click();
        cy.get('.modal-overlay').should('not.exist');
    });

    it('exibe erro ao usar Acronym já existente', () => {
        // Preenche com acronym duplicado
        cy.get('input[name="name"]').type('Dup Acr');
        cy.get('input[name="acronym"]').type('CSD');
        cy.get('input[name="email"]').type('dup2@isep.ipp.pt');
        cy.get('input[name="nif"]').type('123456789');
        cy.get('input[name="academicBackground"]').type('BG');
        cy.get('input[name="street"]').type('Str');
        cy.get('input[name="postalCode"]').type('0000-000');
        cy.get('input[name="location"]').type('City');
        cy.get('.teacher-form-select__control').click();
        cy.get('.teacher-form-select__menu').contains('Portugal').click();
        cy.get('input.teacher-phone-number').type('912345678');
        cy.get('select[name="departmentID"]').select(1);

        // Submete e espera popup de erro
        cy.contains('button', 'REGISTER').click();
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Registration Error').should('be.visible');
        cy.get('.modal-content').contains('acronym').should('exist');
        // Fecha o modal
        cy.get('.modal-btn').click();
        cy.get('.modal-overlay').should('not.exist');
    });
});
