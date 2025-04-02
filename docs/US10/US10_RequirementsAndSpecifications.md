# System Requirements and Specification



## US10: As an Administrator, I want to configure degree types/levels in the system.



        ### 1. Introduction

This document provides a detailed specification and analysis of User Story 10 from the PAI project. It outlines the functional and
non-functional requirements, business rules, and associated risks, serving as a foundation for the development and validation of this
functionality.


### 2. User Story (US) Description

As an Administrator, I want to be able to create different types/levels of degrees which can be assign to a programme upon its creation.

This functionality is crucial to define specific guidelines and attributes to a programme making it easier to categorize it and manage it.



        ### 3. Functional Requirements

- The system shall allow the Programme Director to create a degree type.

- The Programme Director shall be able to set the specific parameters that define each degree type.

        - The Programme Director shall specify the name of the degree type and the maximum level of ECTS that each degree level can have.

- The system shall verify if the ECTS value and name are valid.

        - If not, the system shall informe the Administrator that the name or ECTS value are invalid.

- The system shall create and store a new degree type according to the Administrator specifications.



### 4. Non-Functional Requirements

- The system shall ensure data consistency and uniqueness, preventing the creation and storage of duplicate degree types.


### 5. Acceptance Criteria

- The system shall verify if the inserted name is within the required specifications in terms of lenght, uppercase and tupe of characters.

- The system shall verify if the given ECTS value is within the 1 and 240 gap which is the lower and higher value of ECTS allowed by law.

- A new degree type should be created and storage.



### 6. Business Rules

- The degree type must have a name.

- The degree type must have an unique ID given by the Administrator.

- The degree type must have set a maximum number of ECTS.


### 8. Event Flow

- The Administrator initiates the creation of a new degree type.

- The system prompts the Administrator to insert a valid name, ID and maximum ECTS value.

- The system then checks and validates the data given by the administrator and creates the degree type if the data is valid.

- The system compares if the new degree type already exists in the system.

- The system then stores the new degree type created.


### 9. Testing and Validation


- **T1:** Test valid values

Ensure that the system correctly identifies the values within specifications.


- **T2:** Test for null values

Ensure that the system correctly identifies and rejects null values.


- **T3:** Test rejects duplicate objects

Verify and rejects when trying to create a degree type that's already in the system.


### 10. Identified Risks

- Manual introducing the ID value may present itself as a risk if the duplicate validations are not done properly.



        ### 10. Conclusion

Creating a degree type is the foundation of any school organization since it sets and organizes the different programmes that might exist.
This document formally defines the expected behavior and constraints of the process, ensuring that process is executed in
a consistent, secure, and reliable manner. By addressing functional and non-functional aspects, along with potential risks,
this analysis supports the implementation of a robust foundation that meets the needs of the Administrator while maintaining
data integrity of the system.