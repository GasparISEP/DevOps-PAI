// cypress/support/commands.js
Cypress.Commands.add('highlightOutline', { prevSubject: 'element' }, el =>
    cy.wrap(el)
        .invoke('css', 'outline', '3px solid red')
        .invoke('css', 'outline-offset', '2px')
);

Cypress.Commands.add('highlightBox', { prevSubject: 'element' },
    (el, thickness = 3, color = 'red') => {
        return cy
            .wrap(el)
            // faz com que a caixa do elemento se ajuste ao conteúdo
            .invoke('css', 'display', 'inline-block')
            .invoke('css', 'width', 'auto')
            // depois aplica o contorno
            .invoke('css', 'outline', `${thickness}px solid ${color}`)
            .invoke('css', 'outline-offset', '2px')
    }
)



