# System Requirements and Specification



## US16: As an ASC, I want to enrol a student in a course edition



### 1. Introduction 

This document provides a detailed specification and analysis of User Story 16 from the PAI project. 
Also, outlines the functional and non-functional requirements, acceptance criteria, business rules, and associated risks, offering a comprehensive foundation for the development and validation of this functionality.



### 2. User Story (US) Description

As an Academic Service Collaborator (ASC), I want to enrol a student in a specific course edition so that the student can attend the classes, access course materials, and be evaluated within that context.

This functionality is essential for managing academic enrolments and ensuring that students are properly associated with their respective course editions for a given school year.



### 3. Functional Requirements

- The system shall allow the ASC to search for and select a student by student ID.

- The ASC shall be able to search for and select a programme edition from the list of programme editions in which the student is enrolled.

- The ASC shall be able to select a course edition that belongs to the selected programme edition.

- The enrolment shall be persisted and stored in a repository, along with all relevant associated information.

- The system shall validate that the student is not already enrolled in the selected course edition.

- The enrolment date shall be recorded automatically by the system.

- Upon enrolment, the student’s status in that course edition shall be set to active.



### 4. Non-Functional Requirements

- The system shall support **concurrent enrolment** operations while ensuring data consistency and uniqueness. Even when multiple enrolments are processed simultaneously, the system must prevent duplicate records and avoid race conditions or data corruption.

- It is important to follow best practices such as encapsulation, clean code structure, and performance awareness during development.



### 5. Acceptance Criteria

- The system shall successfully enrol a valid student into the selected course edition.

- The system shall prevent duplicate enrolments of the same student in the same course edition.

- The system shall reject enrolment attempts if the student entity is null or does not exist in the system.



### 6. Business Rules

- A student may not be enrolled in multiple overlapping editions of the same course.

- The enrolment date must always be recorded and stored.

- A student may only be enrolled in a course edition if they are already enrolled in a programme edition that includes that course edition.



### 7. Event Flow

- The ASC initiates the enrolment process by requesting to enrol a student in a specific course edition.

- The system prompts the ASC to identify the student to be enrolled.

- The ASC provides the corresponding student identifier.

- The system retrieves and presents a list of programme editions in which the student is currently enrolled, and prompts the ASC to select one.

- The ASC selects the appropriate programme edition.

- The system then retrieves and displays a list of course editions associated with the selected programme edition, requesting the ASC to choose a course edition.

- The ASC selects the desired course edition.

- The system requests confirmation before proceeding with the enrolment.

- The ASC confirms the enrolment operation.

- Upon confirmation, the system completes the enrolment and provides a success message indicating that the student has been successfully enrolled in the selected course edition.



### 8. Testing and Validation

- **T1:** Test course edition enrolment with a null student

Ensure that the system correctly identifies and rejects enrolment attempts when the student entity is null or does not exist in the system.

- **T2:** Test valid student enrolment in a course edition

Verify that the system successfully enrols a valid student in a selected course edition when all input data is correct and the enrolment conditions are met.

- **T3:** Test multiple course edition enrolments

Ensure that the system allows the enrolment of the same student in multiple valid course editions, provided there are no conflicts or rule violations (e.g., overlapping schedules).

- **T4:** Test duplicate student enrolment in the same course edition

Confirm that the system prevents a student from being enrolled more than once in the same course edition and displays the appropriate error or warning message.



### 9. Identified Risks

- Over-enrolment may exceed classroom or instructor capacity, causing logistical constraints.

- Manual enrolments increase the likelihood of human error if appropriate validations are not enforced.



### 10. Conclusion

The enrolment of students into course editions is a critical component of academic operations. This document has formally defined the expected behavior and constraints associated with this process, ensuring that enrolments are performed in a consistent, secure, and reliable manner.
By addressing both functional and non-functional aspects, as well as potential risks, the analysis supports the implementation of a robust solution that meets the needs of academic service staff while maintaining data integrity and alignment with programme structures.