# System Requirements and Specifications

## US21 - As an ASC, I want to get the number of Students Enrolled in a Program ##

### Table of Contents ###

1. [Introduction](#1-introduction)
2. [User Story Description](#2-user-story-description)
3. [Functional Requirements](#3-functional-requirements)
4. [Non-Functional Requirements](#4-non-functional-requirements)
5. [Acceptance Criteria](#5-acceptance-criteria)
6. [Business Rules](#6-business-rules)
7. [Event Flow](#7-event-flow)
8. [Testing and Validation](#8-testing-and-validation)
9. [Identified Risks](#9-identified-risks)
10. [Conclusion](#10-conclusion)



### 1. Introduction ###
This document defines the functional and non-functional requirements for **User Story 21**, providing a technical basis for development and validation of the feature.



### 2. User Story Description ###
**Role:** Academic Services Collaborator (ASC)  
**Requirement:** Ability to retrieve the number of students enrolled in a specific programme edition.



### 3. Functional Requirements ###
**FR1:** System shall allow the ASC to input a `ProgrammeEditionID`.  
**FR2:** System shall retrieve the number of students enrolled in the given programme edition.  
**FR3:** System shall return an integer representing the count.  
**FR4:** System shall reject `null` or invalid programme edition identifiers.



### 4. Non-Functional Requirements ###
**NFR1:** Response must be computed in under 1 second.  
**NFR2:** System shall be capable of handling concurrent requests.  
**NFR3:** Read-only operation — no data shall be modified.  
**NFR4:** Logging must be enabled for traceability.



### 5. Acceptance Criteria ###
-  ASC can provide a valid `ProgrammeEditionID`
-  System returns a correct count of enrolled students
-  System returns `0` if no students are enrolled
-  System throws meaningful error if input is null or invalid
-  The operation is read-only and consistent



### 6. Business Rules ###
- A programme edition must be **previously created** and valid
- Enrolments must be **confirmed** to be counted
- Only **ASC** roles are authorized to access this information



### 7. Event Flow ###
1. ASC provides a `ProgrammeEditionID`
2. System validates input:
    - Non-null
    - Exists in repository
3. System queries `ProgrammeEditionEnrolmentRepository`
4. Returns number of enrolled students



### 8. Testing and Validation ###
-  Valid input returns correct count
-  No students enrolled → returns `0`
-  Null input → throws exception
-  Invalid ID → handled gracefully
-  Multiple concurrent queries → no conflict



### 9. Risk Analysis ###

| Risk Category | Mitigation Strategy |
|---------------|---------------------|
| Null input | Input validation and error handling |
| Inconsistent data | Ensure repository data integrity |
| Performance under load | Optimize repository access |
| Unauthorized access | Enforce role-based access control |



### 10. Conclusion ###
This feature enables Academic Services to monitor programme enrolment efficiently and reliably.  
With proper validation, performance, and risk mitigation in place, the functionality supports essential academic operations.

