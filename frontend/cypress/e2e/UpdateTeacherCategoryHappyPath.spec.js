describe("update teacher category happy path spec", () => {
  it("happy path", () => {
    // Visit the page
    cy.visit("http://localhost:3000/teachers/update-category");

    // Confirm the page title is visible
    cy.contains("Update Teacher Category");

    // Select teacher and category
    cy.get('select[name="teacher"]').select("AAA");
    cy.get('select[name="teacherCategory"]').select("Professor Assistente");

    // Open the datepicker (ensure only one visible input is clicked)
    cy.get('input[placeholder="Select a Date"]')
        .filter(':visible')
        .click();

    // Confirm the visible month is June
    cy.get('.react-datepicker__current-month')
        .should('contain', 'June');

    // Click on day 20 of June, visible and within the current month
    cy.get('.react-datepicker__day--020:not(.react-datepicker__day--outside-month)')
        .filter(':visible')
        .first()
        .click({ force: true });

    // Verify that the selected date was filled in correctly
    cy.get('input[placeholder="Select a Date"]')
        .should('have.value', '20-06-2025');

    // Click the visible submit button with the text "UPDATE"
    cy.get('button[type="submit"]')
        .filter(':visible')
        .contains('UPDATE')
        .click();

    // Wait for the success modal to appear
    cy.get('.modal-overlay', { timeout: 10000 }).should('be.visible');

    // Verify the success message appears in the modal
    cy.contains("The teacher category was updated successfully.", { timeout: 10000 });
  });
});
