# System Requirements and Specification



## US17: As an ASC, I want to enrol a student in a programme in a given school year. It should include the enrolment in a set of courses.



### 1. Introduction

This document provides a detailed specification and analysis of User Story 17 from the PAI project. It outlines the functional and 
non-functional requirements, business rules, and associated risks, serving as a foundation for the development and validation of this
functionality.


### 2. User Story (US) Description

As an Academic Service Collaborator (ASC), I want to enrol a student in a specific programme edition (a programme within a given school
year) and its corresponding set of course editions. This will ensure the student can attend classes, access course materials, and be
evaluated within that academic context.

This functionality is crucial for managing academic enrolments and ensuring that students are correctly associated with their respective
programme and course editions for a given school year.



### 3. Functional Requirements

- The system shall allow the ASC to search for and select a student by student ID.

- The ASC shall be able to search for and select a programme from the list of available programmes.

- The ASC shall select a school year from the existing options.

- The system shall verify if the student is enrolled in the selected programme.

- The system shall find the corresponding programme edition for the selected programme and school year.

- The system shall check whether the student is already enrolled in that programme edition.

- If not, the system shall persist and store the new enrolment along with relevant information.

- The enrolment date shall be recorded automatically.

- The system shall retrieve the course editions linked to the selected programme edition.

- The student shall be enrolled in the identified set of course editions.

- Upon enrolment, the student’s status in each course edition shall be set to active.



### 4. Non-Functional Requirements

- The system shall support concurrent enrolment operations while ensuring data consistency and uniqueness, preventing duplicate
records and race conditions.

- Best practices in software development, including encapsulation, clean code structure, and performance optimization, 
shall be followed.



### 5. Acceptance Criteria

- The system shall verify if the student is already enrolled in the programme; if not, enrolment in the programme edition
shall be prevented.

- A valid student shall be successfully enrolled in the selected programme edition.

- The system shall prevent duplicate enrolments in the same programme edition.

- A valid student shall be successfully enrolled in the set of course editions.

- The system shall prevent duplicate enrolments in the same course edition.

- The system shall reject enrolment attempts if the student entity is null or does not exist in the system.



### 6. Business Rules

- A student cannot be enrolled in multiple overlapping editions of the same programme.

- A student can only be enrolled in a programme edition if they are already enrolled in the related programme.

- The enrolment date for a programme edition shall always be recorded and stored.

- A student cannot be enrolled in multiple overlapping editions of the same course.

- A student can only be enrolled in a course edition if they are already enrolled in the corresponding programme edition.

- The enrolment date for a course edition shall always be recorded and stored.



### 7. Event Flow

- The ASC initiates the enrolment process for a student in a specific programme edition.

- The system prompts the ASC to provide the student’s identifier.

- The system presents a list of available programmes, and the ASC selects one.

- The system presents a list of school years, and the ASC selects one.

- The system verifies if the student is already enrolled in the selected programme.

- The system finds the corresponding programme edition for the selected programme and school year.

- The system verifies if the student is already enrolled in that programme edition.

- The system identifies the course editions linked to the selected programme edition.

- The system completes the enrolment and displays a success message confirming the student's enrolment in the programme 
and course editions.



### 8. Testing and Validation


- **T1:** Test student not enrolle in programme

Ensure that the system correctly identifies and rejects enrolment attempts when the student entity is not enrolled in programme.


- **T2:** Test programme edition not found

Ensure that the system correctly identifies and rejects enrolment attempts when the programme edition not found.

- **T3:** Test student already enrolled in programme edition

Ensure that the system correctly identifies and rejects enrolment attempts when student already enrolled in the programme edition.


- **T4:** Test valid student enrolment in a course edition

Verify that the system successfully enrols a valid student in a selected programme edition and set of course edition when all
input data is correct and the enrolment conditions are met.


- **T5:** Test duplicate student enrolment in the same course edition

Ensure that the system prevents a student from being enrolled more than once in the same course edition and displays the
appropriate error or warning message.



### 9. Identified Risks

- Manual enrolment processes may introduce human errors if adequate validation checks are not implemented.

- Excessive enrolment could surpass available classroom or instructor capacity, leading to logistical challenges.



### 10. Conclusion

The enrolment of students in programme editions and their respective course editions is a critical aspect of academic administration.
This document formally defines the expected behavior and constraints of the process, ensuring that enrolments are executed in
a consistent, secure, and reliable manner. By addressing functional and non-functional aspects, along with potential risks,
this analysis supports the implementation of a robust solution that meets the needs of academic service staff while maintaining
data integrity and alignment with programme structures.