# System Requirements and Specifications—US07 #

### Table of Contents

1. [Introduction](#1-introduction)
2. [User Story Description](#2-user-story-description)
3. [Functional Requirements](#3-functional-requirements)
4. [Non-Functional Requirements](#4-non-functional-requirements)
5. [Acceptance Criteria](#5-acceptance-criteria)
6. [Business Rules](#6-business-rules)
7. [Event Flow](#7-event-flow)
8. [Testing and Validation](#8-testing-and-validation)
9. [Identified Risks](#9-risk-analysis)
10. [Conclusion](#10-conclusion)

## 1. Introduction
This document specifies the requirements for User Story 07 (PAI project), including:
- Functional and non-functional requirements
- Acceptance criteria
- Business rules
- Risk analysis  

## 2. User Story Description
**Role:** System Administrator (Admin)  
**Requirement:** Create a school year in the system.

## 3. Functional Requirements
**FR1:** The system must allow an Admin to create a School Year  
**FR2:** A School Year consists of a generated ID, description, a start date, and an end date  
**FR3:** The system must persist successfully created School Years data  
**FR4:** The system must confirm successful School Year creation to the Admin.

## 4. Non-Functional Requirements
**NFR1:** Prevent duplicate school year creation (data integrity)  
**NFR2:** Support concurrent creation without data conflicts  
**NFR3:** Adhere to clean code, maintainability, and performance standards  
**NFR4:** Maintain fast response times during creation

## 5. Acceptance Criteria
- [ ] Admin can create a school year successfully  
- [ ] A School Year must have a description, a start date and a local date  
- [ ] System validates uniqueness before allowing creation
- [ ] System rejects invalid creation attempts
- [ ] System prompts for correction of missing/incorrect data
- [ ] Persistent storage upon successful validation
- [ ] Prevention of duplicate entries (same start date + same end date)

## 6. Business Rules
- Uniqueness constraint: No duplicate School Years
- Mandatory fields: Description, Start Date, End Date
- Mandatory fields: End Date must be after Start Date
- Mandatory fields: Start Date and End Date must follow DD-MM-YYYY pattern

## 7. Event Flow
1. The admin initiates the creation of a school year.
2. The system collects description, startDate, and endDate.
3. A SchoolYear instance is created and validated.
4. The repository checks for duplicates.
5. On validation success:
    - Creates School Year
    - Generates a School Year ID
    - School Year is saved
    - Confirms creation to Admin

## 8. Testing and Validation
- [ ] Creation with valid data
- [ ] Prevention of creation without mandatory fields
- [ ] Duplicate entry prevention

## 9. Risk Analysis
| Risk Category  | Mitigation Strategy                      |  
|----------------|------------------------------------------|  
| Data Integrity | Implement duplicate validation checks    |  
| Performance    | Optimize concurrent transaction handling |  
| Scalability    | Design for increasing data volume        |  
| Human Error    | Implement input validation and guidance  |  

## 10. Conclusion
The solution ensures:  
✔ Data integrity through validation  
✔ Reliability via robust architecture  
✔ Usability with guided workflows

#### Risk mitigation and thorough testing will guarantee feature success.  