# System Requirements and specifications #

## US04: As an Adminstrator, I want to register a teacher in the System ##


### 1. Introduction ###
This document provides a detailed specification and analysis of **User Story 04** from the PAI project.

Also, outlines the functional and non-functional requirements, acceptance criteria, business rules, and associated risks, offering a comprehensive foundation for the development and validation of this functionality.

### 2. User Story (US) Description ###
**As an Administrator, I want to register a teacher in the System:** 
So they can assign to courses, access the academic resources and interact with the institutional framework.

This functionality is essential for managing faculty members efficiently and ensuring they are properly associated with their respective departments.

### 3. Functional Requirements ###

- **FR1:** The system shall allow the Administrator to input and register a new teacher using the following required fields.
- **FR2:** Upon successful registration, the teacher’s information shall be persisted in the system for future use.
- **FR3:** The system shall confirm successful registration to the Administrator.

### 4. Non Functional Requirements ###
- **NFR1:** The system shall ensure data integrity by preventing duplicate teacher registrations.
- **NFR2:** The system shall allow multiple teacher registrations concurrently without data conflicts.
- **NFR3:** The system shall follow best practices such as clean code structure, maintainability, and performance efficiency during development.
- **NFR4:** The system’s response time for registration should be fast under normal conditions.

### 5. Acceptance Criteria ###

- The system shall allow a valid teacher to be registered successfully.
- The system shall prevent duplicate registrations where the Acronym or NIF already exists.
- If required information is missing or incorrect, the system shall prompt the Administrator to correct the errors.
- The system shall store the Teacher’s information persistently in the repository upon successful validation.
### 6. Business Rules ###

- A teacher must be assigned to an existing department.
- The system must prevent duplicate Acronym and NIF values to maintain data integrity.
- The teacher’s registration date must always be recorded automatically.

### 7. EventFlow ###
- The Administrator initiates the registration process by requesting to register a new teacher.
- The system prompts the Administrator for required teacher details.
- Administrator enters the teacher information.
- The system confirms the data input and asks for confirmation.
- The System checks if the Department exists.
- The System verifies if the teacher is already registered.
- The System Registers the teacher.
- the System confirms that the teacher was successfully registered to the Administrator.
### 8. Testing and validation ###
- **T1:** Test with invalid **Department**.
- **T3:** Test with invalid **Attributes**

### 9. Identified Risks ###
- **Data Integrity Risk:** risk of duplicated teacher registrations, if proper validation is not in place.
- **Performance Degradation:** if there are multiple teachers being registered simultaneously in the system, it may affect performance and response times.
- **Scalability Issues:** As the number of teachers grows in the system, the performance of the registration process might degrade due to an increased load.
- **Validation Failure:** The system might fail to validate certain fields.
- **Security Risks:** There is a risk that sensitive information could be exposed if the system is not secure.
- **Human Error:** There is a risk of human error during the data entry phase.
### 10. Conclusion ###
The system will be able to support the administrative tasks involved in teacher registration while ensuring data consistency, reliability, and ease of use. By addressing the risks identified and focusing on robust design and thorough testing, we can confidently ensure the success of the teacher registration functionality in the system.