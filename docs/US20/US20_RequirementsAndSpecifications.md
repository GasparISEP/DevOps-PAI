# System Requirements and specifications #

## US20: As an Programme Director, I want to define the RUC for a course edition. ##


### 1. Introduction ###
This document provides a detailed specification and analysis of **User Story 20** from the PAI project.

Also, outlines the functional and non-functional requirements, acceptance criteria, business rules, and associated risks, offering a comprehensive foundation for the development and validation of this functionality.

### 2. User Story (US) Description ###
**As a Programme Director, I want to define the responsible teacher (RUC) for each course edition** to ensure proper organization.

### 3. Functional Requirements ###


- **FR1:** The system must allow a **Programme Director** to define a RUC for each Course Edition.
- **FR2:** Upon successful creation, the information shall be persisted in the system for future use.
- **FR3:** The system shall confirm successful creation to the **Programme Director**.

### 4. Non Functional Requirements ###
- **NFR1:** The system must ensure data security by preventing a RUC from being set for a Course Edition if it has already been set previously.
- **NFR2:** The system must ensure that multiple RUCs can be set concurrently without data conflicts.
- **NFR3:** The system shall follow best practices such as clean code structure, maintainability, and performance efficiency during development.
- **NFR4:** The system’s response time for validation should be fast under normal conditions.

### 5. Acceptance Criteria ###
- The system must allow a Programme Director to define a RUC for each Course Edition.
- The system must provide a form where the user can enter the necessary details.
- The system must validate that the selected Course Edition and the selected Teacher exist.
- The system shall reject a creation attempt if the RUC is already define for a Course Edition.
- If required information is missing or incorrect, the system shall prompt the Programme Director to correct the errors.
- The system shall store the RUC's information persistently in the repository upon successful validation.

### 6. Business Rules ###

- A RUC must be unique for a given Course Edition. 
- A RUC cannot be defined without specifying the Course Edition and the Teacher.

### 7. EventFlow ###
- The Programme Director initiates the process by requesting to define a RUC for a Course Edition.
- The system prompts the Programme Director to choose a Course Edition.
- The Programme Director chooses a Course Edition.
- The system confirms there is the Course Edition in the system.
- The system prompts the Programme Director to choose a Teacher.
- The Programme Director chooses a Teacher.
- The System confirms there is the Teacher in the system.
- The System verifies if the RUC for that Course Edition is already defined.
- The System registers the RUC for that Course Edition.
- The System confirms that the RUC for that Course Edition was successfully defined to the Programme Director.

### 8. Testing and validation ###
- Verify that a RUC can be defined when valid data is provided.
- Ensure that a RUC cannot be defined without specifying a Course Edition and a Teacher.
- Ensure that a RUC cannot be defined if it has already been defined previously.

### 9. Identified Risks ###
- **Data Integrity Risk:** risk of having a duplicated RUC for a Course Edition, if proper validation is not in place.
- **Performance Degradation:** if there are multiple RUCs being defined simultaneously in the system, it may affect performance and response times.
- **Scalability Issues:** As the number of Course Editions and RUCs in the system, the performance of creation and registration process might degrade due to an increased load.
- **Human Error:** There is a risk of human error during the data entry phase.

### 10. Conclusion ###
The system will be able to support the administrative tasks involved in defining a RUC for a Course Edition while ensuring data consistency, reliability, and ease of use. 

By addressing the risks identified and focusing on robust design and thorough testing, we can confidently ensure the success of the programme edition creation functionality in the system.