describe('Gestão de Teachers - Happy Path', () => {

    beforeEach(() => {
        //garante que a app está no estado inicial
        cy.visit('/');
        cy.wait(500);
    })

    it('abre o menu e mostra a lista de teachers', () => {
        //1 Hover no menu Teacher
        cy.get('li.navbar-dropdown')
            .trigger('mouseover')
            .wait(1000)

        //2 Seleciona Display Teachers
        cy.get('ul.navbar-dropdown-menu.show')
            .contains('Display Teachers')
            .as('displayLink');

        //3 Destaca e clica
        cy.get('@displayLink')
            .highlightBox()
            .wait(800)
            .click()
            .wait(2000);

        //testes
        cy.url().should('include', '/teachers/display');
        cy.get('table.teacher-form-table').should('exist');
        cy.get('table.teacher-form-table tbody tr')
            .its('length')
            .should('be.gte', 1);
    });

    it('regista um novo teacher via UI', () => {
        //1 Abrir menu e ir para registo
        cy.get('li.navbar-dropdown')
            .trigger('mouseover')
            .wait(1000)
        cy.get('ul.navbar-dropdown-menu.show')
            .contains('Register Teacher')
            .as('registerLink')
        cy.get('@registerLink')
            .highlightBox()
            .wait(1000)
            .click()

        //2 Pagina de registo
        cy.url().should('include', '/teachers/register') //teste

        //4 Preenche o formulário
        //nome
        cy.get('input[name="name"]').type('Cypress Switch Dev').wait(500)
        //acronym
        cy.get('input[name="acronym"]').type('CSD').wait(500)
        //email
        cy.get('input[name="email"]').type('cypressswitchdev@isep.ipp.pt').wait(500)
        //nif
        cy.get('input[name="nif"]').type('124192426').wait(500)
        //academic background
        cy.get('input[name="academicBackground"]').type('Licenciatura Engenharia Informática').wait(500)
        //street
        cy.get('input[name="street"]').type('Rua Dr. António Bernardino de Almeida').wait(500)
        //postalCode
        cy.get('input[name="postalCode"]').type('4249-015').wait(500)
        //location
        cy.get('input[name="location"]').type('Porto').wait(500)
        //country
        cy.get('.teacher-form-input__control').click().wait(500);
        cy.get('.teacher-form-input__input').type('Portugal{enter}').wait(500);
        //phoneNumber - alterar
        cy.get('input.student-phone-number').clear().type('912345678').wait(500)
        //department
        cy.get('select[name="departmentID"]').select(1).wait(500);

        //4 Submete e aguarda PopUp
        cy.intercept('POST', '/teachers').as('saveTeacher')
        cy.get('button[type="submit"]').highlightOutline().wait(800).click()
        cy.wait('@saveTeacher').its('response.statusCode').should('eq', 201).wait(3000)

        //Verifica PopUp de sucesso
        cy.get('.modal-overlay').should('be.visible');
        cy.contains('Success!').should('be.visible');
        cy.get('.modal-content')
            .contains('Cypress Switch Dev')
            .should('be.visible');
        cy.get('.modal-btn').highlightOutline().wait(1000).click().wait(800);
        cy.get('.modal-overlay').should('not.exist');

    });

        it('verificar o novo teacher registado', () => {
        //5 Voltar a listagem e confirmar inserção
        cy.get('li.navbar-dropdown').trigger('mouseover').wait(1000)
        cy.get('ul.navbar-dropdown-menu.show').contains('Display Teachers').as('displayLink')
        cy.get('@displayLink').highlightBox().wait(1000).click().wait(2000)

        cy.contains('Page 1 of 2').should('be.visible')
        //Clica em Next para ir à página 2
        cy.contains('button', 'Next').as('nextBtn')
        cy.get('@nextBtn').scrollIntoView({ offset: { top: -100, left: 0 } }).highlightOutline().wait(1000).click();
        //Confirma que agora estamos na página 2
        cy.contains('Page 2 of 2').should('be.visible')

        //Verifica o teacher recém-criado
        cy.get('table.teacher-form-table tbody tr')
            .contains('td', 'Cypress Switch Dev').parent('tr').as('newRow')

        //Faz scroll até ao elemento
        cy.get('@newRow').scrollIntoView({ offset: { top: -100, left: 0 } }).wait(1000).should('be.visible')
        // Destaca-o em vermelho
        cy.get('@newRow')
            .invoke('css', 'background-color', 'rgba(255,0,0,0.2)')
            .invoke('css', 'border', '2px solid red')
    })
})

