
describe('Teachers Management - Non-Happy Paths', () => {
    beforeEach(() => {
        // Garante que a app inicia no estado inicial
        cy.viewport(1440, 1440)
        cy.visit('/');
        cy.wait(500);
        //Abre o menu Teacher
        cy.get('li.navbar-dropdown').trigger('mouseover').wait(1000);
        cy.get('ul.navbar-dropdown-menu.show').should('be.visible');
        //Clica em Register Teacher
        cy.contains('Register Teacher').highlightBox().wait(1000).click();
        cy.url().should('include', '/teachers/register');
    });

    it('exibe erro ao tentar registar um teacher com um Acronym já existente', () => {
        const teacher = {
            name: 'Cypress Switch Dev Two',
            acronym: 'CSD',
            email: 'cypressswitchdev2@isep.ipp.pt',
            nif: '124192426',
            academicBackground: 'Licenciatura Farmácia',
            street: 'Rua Dr. António Bernardino de Almeida',
            postalCode: '4249-015',
            location: 'Porto'
        };
        Object.entries(teacher).forEach(([field, value]) => {
            cy.get(`input[name="${field}"]`)
                .clear()
                .type(value).wait(500);
        });
        // Seleciona país
        cy.get('.teacher-form-select__control').click().wait(500);
        cy.get('.teacher-form-select__menu')
            .contains('Portugal')
            .click().wait(1000);
        // Telefone e departamento
        cy.get('input.teacher-phone-number').clear().type('912345678', { delay: 50 }).wait(500);
        cy.get('select[name="departmentID"]').select(2).wait(500);

        // Submete e espera popup de erro
        cy.contains('button', 'REGISTER').highlightOutline().wait(1000).click();
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Registration Error').should('be.visible');
        // Fecha o modal
        cy.contains('button', 'Close').as('closeBtn')
        cy.get('@closeBtn').highlightOutline().wait(2000).click();
        cy.get('.modal-overlay').should('not.exist');
    });

    it('exibe erro ao tentar registar um teacher com um NIF já existente', () => {
        const teacher = {
            name: 'Cypress Switch Dev Two',
            acronym: 'CST',
            email: 'cypressswitchdev2@isep.ipp.pt',
            nif: '124192426',
            academicBackground: 'Licenciatura Farmácia',
            street: 'Rua Dr. António Bernardino de Almeida',
            postalCode: '4249-015',
            location: 'Porto'
        };
        Object.entries(teacher).forEach(([field, value]) => {
            cy.get(`input[name="${field}"]`)
                .clear()
                .type(value).wait(500);
        });
        // Seleciona país
        cy.get('.teacher-form-select__control').click().wait(500);
        cy.get('.teacher-form-select__menu')
            .contains('Portugal')
            .click().wait(1000);
        // Telefone e departamento
        cy.get('input.teacher-phone-number').clear().type('912345678', { delay: 50 }).wait(500);
        cy.get('select[name="departmentID"]').select(2).wait(500);

        // Submete e espera popup de erro
        cy.contains('button', 'REGISTER').highlightOutline().wait(1000).click();
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Registration Error').should('be.visible');
        // Fecha o modal
        cy.contains('button', 'Close').as('closeBtn')
        cy.get('@closeBtn').highlightOutline().wait(2000).click();
        cy.get('.modal-overlay').should('not.exist');
    });

    it('tenta criar novamente um novo teacher com sucesso', () => {
        const teacher = {
            name: 'Cypress Switch Dev Two',
            acronym: 'CST',
            email: 'cypressswitchdev2@isep.ipp.pt',
            nif: '202004011',
            academicBackground: 'Licenciatura Farmácia',
            street: 'Rua Dr. António Bernardino de Almeida',
            postalCode: '4249-015',
            location: 'Porto'
        };
        Object.entries(teacher).forEach(([field, value]) => {
            cy.get(`input[name="${field}"]`)
                .clear()
                .type(value).wait(200);
        });
        // Seleciona país
        cy.get('.teacher-form-select__control').click().wait(200);
        cy.get('.teacher-form-select__menu')
            .contains('Portugal')
            .click().wait(500);
        // Telefone e departamento
        cy.get('input.teacher-phone-number').clear().type('912345678').wait(200);
        cy.get('select[name="departmentID"]').select(2).wait(200);

        // Submete e espera popup de erro
        cy.contains('button', 'REGISTER').highlightOutline().wait(1000).click();
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Success').should('be.visible');
        // Fecha o modal
        cy.contains('button', 'Close').as('closeBtn')
        cy.get('@closeBtn').highlightOutline().wait(2000).click();
        cy.get('.modal-overlay').should('not.exist');
    });
});
