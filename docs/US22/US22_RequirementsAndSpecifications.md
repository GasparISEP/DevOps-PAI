# System Requirements and Specification



## US22: As a RUC, I want to grade a student in a course edition



### 1. Introduction

his document presents a comprehensive overview of User Story 22 from the PAI project. It describes the key functional and non-functional requirements, relevant business rules, and potential risks, providing essential guidance for the implementation and evaluation of this feature.


### 2. User Story (US) Description

As RUC, I want to assign a grade to a student in a specific course edition, so that their academic performance can be formally recorded and evaluated within that course's context.

This functionality is essential for maintaining accurate academic records and ensuring that each student's progress in individual course editions is correctly documented as part of their overall educational journey.



### 3. Functional Requirements

- The system shall allow the RUC to search for and select a student using their unique student ID.

- The RUC shall be able to browse and choose a course edition from the list of available options.

- The system shall verify whether the selected student is associated with the chosen course edition.

- If the student has not yet been graded in that course edition, the system shall allow the RUC to assign a grade and specify the grading date.

- The grade shall be validated and stored in the system alongside the student and course edition identifiers.

- The system shall automatically generate a unique identifier for the grade entry based on the student and course edition combination.

- Upon successful submission, the grade shall be persisted in the academic records.

- The system shall ensure that a student cannot be graded multiple times for the same course edition.



### 4. Non-Functional Requirements

- **Scalability**: The system shall be designed to handle a growing number of grade records and concurrent users without performance degradation.

- **Security**: Only authorized roles (e.g., RUC) shall be allowed to assign grades, and all operations shall be logged for auditing purposes (in the future).

- **Reliability**: The system shall ensure high availability and fault tolerance during grade assignment operations, avoiding data loss or corruption.

- **Maintainability**: The codebase shall be structured for easy maintenance, allowing future changes or extensions to the grading logic with minimal impact.

- **Auditability**: Each grade assignment shall be traceable, with a timestamp and reference to the user who performed the operation.



### 5. Acceptance Criteria

- The system shall verify whether the student is already graded in the selected course edition.

- A valid student shall be successfully assigned a grade in the specified course edition.

- The system shall ensure that each student can only receive one grade per course edition.

- The system shall reject grading attempts if the student entity is null or does not exist in the system.

- The system shall validate that the course edition exists before proceeding with the grade assignment.

- The grading operation shall only proceed if both student and course edition references are valid and linked appropriately.


### 6. Business Rules

- Each grade entry shall have a unique identifier composed of the student and course edition identifiers.

- Grade information must always be recorded and persistently stored.

- A student cannot be assigned more than one grade for the same course edition.

- A grade can only be assigned to a student if they are already associated with the corresponding course edition.

- A grade cannot be assigned if either the student or the course edition does not exist or is not properly linked.


### 7. Main Entities

To clarify the core domain elements involved in this user story, the following entities are defined:

- **Student:** An individual participating in one or more course editions, eligible to receive academic evaluations.

- **Course Edition:** A specific instance of a curricular unit offered as part of a programme edition during a defined academic period.

- **Student Grade:** Represents the formal evaluation assigned to a student for a specific course edition, including the grade value and grading date.

- **StudentGradeID:** A unique identifier composed of the student ID and course edition ID, ensuring each grade entry is uniquely referenced.

- **Grade:** A value expressing the student’s performance in a given course edition, based on institutional grading rules.

- **Date:** The specific date on which the grade is assigned and recorded.


### 8. Event Flow

- The RUC initiates the grade assignment process for a student in a specific course edition.

- The system prompts the RUC to provide the student’s identifier.

- The system displays a list of available course editions, and the RUC selects the relevant one.

- The system verifies whether the student is associated with the selected course edition.

- The system checks if a grade has already been assigned to that student for the selected course edition.

- If no existing grade is found, the system prompts the RUC to enter the grade and confirms the grading date.

- The system creates a unique grade entry and persists it in the academic records.

- Upon successful submission, the system displays a confirmation message indicating that the grade has been recorded.



### 9. Testing and Validation


- **T1: Grade assignment with invalid student reference**
Ensure the system correctly rejects attempts to assign a grade when the student entity is null or not registered in the system.

- **T2: Course edition not found**
Verify that the system properly prevents grading when the selected course edition does not exist.

- **T3: Student already graded in course edition**
Confirm that the system does not allow a student to be graded more than once in the same course edition.

- **T4: Successful grade assignment**
Validate that a valid student can be assigned a grade in a valid course edition when all preconditions are met (e.g., student is associated with the course edition, grade is valid, etc.).

- **T5: Grade value is null**
Ensure the system throws an appropriate exception when a null grade is provided.

- **T6: Grade date is null**
Ensure the system rejects grade creation if no grading date is provided.

- **T7: Retrieve correct grade value**
Verify that the correct grade value is returned when requested.

- **T8: Identity uniqueness**
Validate that each grade is uniquely identified by the combination of student ID and course edition ID, preventing duplicate entries.



### 10. Identified Risks

- Manual grade entry may lead to human errors if proper validation mechanisms are not enforced (e.g., missing data, duplicate entries).

- Incorrect or duplicate grade assignments could compromise the integrity of academic records and impact student evaluations.

- Lack of strict access control may allow unauthorized users to assign or alter grades, posing security and compliance risks.



### 11. Conclusion

The process of assigning grades to students within specific course editions is a fundamental element of academic record management.
This document outlines the expected behavior, constraints, and validations involved in grading operations, ensuring that grades are recorded consistently, securely, and accurately. By covering both functional and non-functional requirements, as well as potential risks, this analysis provides a solid foundation for implementing a dependable grading system that upholds data integrity and supports the responsibilities of academic records staff.