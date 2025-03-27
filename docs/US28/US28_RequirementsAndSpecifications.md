# **System Requirements & Specification** 
## US28: As an ASC, I want to remove the enrolment of a student in a course edition

## Table of Contents
1. [Purpose](#purpose)
2. [User Story Description](#user-story-description)
3. [Main Entities](#main-entities)
4. [Functional Requirements](#functional-requirements)
5. [Non-Functional Requirements](#non-functional-requirements)
6. [Acceptance Criteria](#acceptance-criteria)
7. [Event Flow](#event-flow)
8. [Conclusion](#conclusion)

---

## Purpose
This document provides a comprehensive analysis of **User Story 28**, outlining the requirements, acceptance criteria, business rules, and associated risks for enabling an ASC (Academic Services Collaborator) to remove a student’s enrolment in a course edition. It serves as a foundational reference for both the development and validation of this functionality.

## User Story Description
In this context, removing an enrolment means deactivating the link between a student and a course edition. The student will no longer be enrolled in the course, but the enrolment record will remain in the system. Instead of a permanently deleting the enrolment, its status of the enrolment will be changed to **"inactive"**, ensuring that the student’s historical data is preserved.

## Main Entities
To better understand the key entities involved in this user story, the following entities are defined:
- **Student**: A person who is enrolled in a course edition, participating in the academic programme offered by the institution.
- **Programme Edition**: Represents a specific iteration of an academic programme delivered within a defined school year.
- **Course Edition**: Represents an edition/instance of a curricular unit within a programme edition.
- **Course Edition Enrolment**: Represents the enrolment of a student in a specific course edition.

## Functional Requirements
- The system shall allow the ASC to search for and select a student and a course edition.
- The system shall allow the ASC to request the removal of a student's enrolment from a course edition.
- The system shall change the enrolment status of the student to **"inactive"** rather than deleting the record.
- The system shall validate that the enrolment exists (the student is currently enrolled in the selected course edition) before the removal process begins.
- The system shall support the batch removal of multiple enrolments, whether for multiple students or multiple enrolments for the same student across different course editions.
- Upon successful removal, the system must confirm the operation to the ASC.

## Non-Functional Requirements
At this stage, there are no specific non-functional requirements imposed for this user story. However, it is essential to adhere to best practices during the development process, including:

- Data Integrity: The system must ensure that no data is lost during the enrolment removal process, and all enrolment statuses are updated correctly.
- Encapsulation: The system should ensure that each component and class is well-defined, with clear responsibilities and minimized dependencies, following the principles of object-oriented design.
- Clean Code Structure: The code should be organized, readable, and maintainable, with proper documentation and a consistent coding style, ensuring ease of understanding and future enhancements.


## Acceptance Criteria
The acceptance criteria define the expected behaviour of the system for both **successful** enrolment removal and **failure** scenarios.

### Success Cases
- **T1:** **Successful Enrolment Removal**: When the ASC selects a student enrolled in a course edition and confirms the removal, the system should successfully mark the student’s enrolment as “inactive”, ensuring the student is no longer considered actively enrolled in that course edition.


- **T2:** **Multiple Course Edition Removals**: Ensures that the student can be correctly removed from multiple course editions.


- **T3:** **Batch Removal of Multiple Students**: Given multiple students enrolled in the same course edition, when the ASC selects and removes several students simultaneously, the system should successfully process each removal and mark their enrolments as inactive.

### Failure Handling
- **T4:** **Nonexistent Enrolment**: If an attempt is made to remove an enrolment that does not exist, the system should reject the operation.


- **T5:** **Multiple Removal Attempts of Same Enrolment**: If a student’s enrolment has already been removed (marked as inactive), when the ASC attempts to remove the enrolment again, the system should reject the request. The system should ensure that only the first removal attempt succeeds, and any subsequent removal attempts for the same enrolment fail.


- **T6:** **Null Information**: If there is missing information regarding the student or the course edition (null data), when the ASC attempts to process the removal, the system should reject the request.

## Event Flow
- The ASC initiates the interaction by requesting the system to remove the enrolment of a student in a specific course edition.

- The system prompts the ASC to specify which student should have their enrolment removed.

- The ASC provides the information regarding the student whose enrolment should be removed.

- The system asks again for the ASC to specify which course edition is associated with the student.

- The ASC provides the information about the specific course edition the student is enrolled in.

- The system requests confirmation to ensure the ASC is certain about removing the enrolment.

- The ASC confirms the request by responding affirmatively.

- The system informs the ASC that the enrolment removal was successful.

## Conclusion
The removal of a student’s enrolment from a course edition is a vital process within the academic workflow, ensuring that the student’s records are accurately maintained while preserving data integrity. This document clearly outlines the functional and non-functional requirements, business rules, and acceptance criteria, providing a solid foundation for the next phases of the project. By defining both success and failure scenarios, this analysis ensures that the removal process will be handled efficiently and securely. As such, along with the System Sequence Diagram and the Domain Model, it serves as the basis for the design phase (including diagramming), implementation, and testing, ensuring that the solution mantains consistency and transparency throughout the enrolment management lifecycle.