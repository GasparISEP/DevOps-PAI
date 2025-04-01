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

