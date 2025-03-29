# System Requirements and specifications #

## US18: As an ASC, I want to create a programme edition for the current school year. ##


### 1. Introduction ###
This document provides a detailed specification and analysis of **User Story 18** from the PAI project.

Also, outlines the functional and non-functional requirements, acceptance criteria, business rules, and associated risks, offering a comprehensive foundation for the development and validation of this functionality.

### 2. User Story (US) Description ###
**As an Academic Services Collaborator, I want to create a programme edition for the current school year.** so it can be possible to define its associated courses and ensure proper organization.

### 3. Functional Requirements ###


- **FR1:** The system must allow an **Academic Services Collaborator (ASC)** to create a programme edition for a specific school year.
- **FR2:** The system must ensure that each programme edition belongs to a single programme and a single school year.
- **FR3:** Upon successful creation, the programme edition’s information shall be persisted in the system for future use.
- **FR4:** The system shall confirm successful creation to the **Academic Services Collaborator (ASC)**.

### 4. Non Functional Requirements ###
- **NFR1:** The system shall ensure data integrity by preventing duplicate programme edition creation.
- **NFR2:** The system shall allow multiple programme edition creation concurrently without data conflicts.
- **NFR3:** The system shall follow best practices such as clean code structure, maintainability, and performance efficiency during development.
- **NFR4:** The system’s response time for creation should be fast under normal conditions.

### 5. Acceptance Criteria ###
- The system must allow an Academic Services Collaborator (ASC) to create a programme edition for the current school year
- A programme edition must be linked to a single programme and a single school year.
- The system must provide a form where the user can enter the necessary details (e.g., programme).
- The system must validate that the selected programme exists
- The system shall reject a creation attempt if the current school year does not exist in the system.
- If required information is missing or incorrect, the system shall prompt the Academic Services Collaborator (ASC) to correct the errors.
- The system shall store the programme edition’s information persistently in the repository upon successful validation.
- The system must prevent the creation of duplicate programme editions for the same programme and school year.

### 6. Business Rules ###

- A programme edition must be unique for a given programme and school year. Duplicate entries are not allowed.
- A programme edition cannot be created without specifying the programme and the school year.

### 7. EventFlow ###
- The ASC initiates the registration process by requesting to create a new programme edition in the current school year.
- The system prompts the ASC to choose a programme.
- The ASC chooses a programme.
- The system confirms there is a current school year in the system.
- The System creates a programme edition in the current school year
- The System verifies if the programme edition is already registered.
- The System Registers the programme edition.
- the System confirms that the programme edition in the current school year was successfully created and registered to the ASC.

### 8. Testing and validation ###
- Verify that a programme edition can be successfully created when valid data is provided.
- Ensure that a programme edition cannot be created without specifying a programme and a school year.
- Verify that duplicate programme editions cannot be created for the same programme and school year.

### 9. Identified Risks ###
- **Data Integrity Risk:** risk of duplicated programme edition registrations, if proper validation is not in place.
- **Performance Degradation:** if there are multiple programme editions being registered simultaneously in the system, it may affect performance and response times.
- **Scalability Issues:** As the number of programme editions grows in the system, the performance of creation and registration process might degrade due to an increased load.
- **Human Error:** There is a risk of human error during the data entry phase.

### 10. Conclusion ###
The system will be able to support the administrative tasks involved in programme edition creation while ensuring data consistency, reliability, and ease of use. By addressing the risks identified and focusing on robust design and thorough testing, we can confidently ensure the success of the programme edition creation functionality in the system.