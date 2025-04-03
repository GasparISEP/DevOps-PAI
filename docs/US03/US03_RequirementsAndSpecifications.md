# System Requirements and Specifications—US03 #

### Table of Contents

1. [Introduction](#1-introduction)
2. [User Story Description](#2-user-story-description)
3. [Functional Requirements](#3-functional-requirements)
4. [Non-Functional Requirements](#4-non-functional-requirements)
5. [Acceptance Criteria](#5-acceptance-criteria)
6. [Business Rules](#6-business-rules)
7. [Event Flow](#7-eventflow)
8. [Testing and Validation](#8-testing-and-validation)
9. [Identified Risks](#9-identified-risks)
10. [Conclusion](#10-conclusion)

## 1. Introduction
This document specifies the requirements for User Story 3 (PAI project), including:
- Functional and non-functional requirements
- Acceptance criteria
- Business rules
- Risk analysis  
  Providing a complete framework for development and quality assurance.

## 2. User Story Description
**Role:** Academic Services Collaborator (ASC)  
**Requirement:** Ability to add a Course to a specific Programme

## 3. Functional Requirements
**FR1:** System shall enable ASCs to add an existing Course to a Programme
**FR2:** Each Course must belong to exactly one Programme 
**FR3:** System must persist successfully the addition of Course to the required Programme
**FR4:** System shall provide creation confirmation to ASC

## 4. Non-Functional Requirements
**NFR1:** Prevent duplicate Course on the same Programme 
**NFR2:** Support concurrent creation without data conflicts  
**NFR3:** Adhere to clean code, maintainability, and performance standards  
**NFR4:** Maintain fast response times during addition of Course to Programme

## 5. Acceptance Criteria
- [ ] ASC can add Course to a Programme
- [ ] Course uniquely linked to a Programme 
- [ ] System validates existence of:
    - Selected Programme
    - Selected Study Plan
    - Selected Course
- [ ] System rejects invalid creation attempts
- [ ] Persistent storage upon successful validation
- [ ] Prevention of duplicate entries

## 6. Business Rules
- Uniqueness constraint: No duplicate Course can be added to the same Programme
- Mandatory fields: Semester, Curricular Year, Course, StudyPlan

## 7. Event Flow
1. ASC initiates asks to add a Course to a Programme
2. System guides through a selection process:
    - Programme
    - Study Plan of chosen Programme
    - Course
3. System validates, of Course, can be added to the Programme
4. System checks for existing registrations
5. On validation success:
    - Create Course in Study Plan, which stores to which Study Plan of specified Programme the Course will belong
    - Stores record
    - Confirms creation to ASC

## 8. Testing and Validation
- [ ] Creation with valid data
- [ ] Prevention of creation without mandatory fields
- [ ] Duplicate entry prevention

## 9. Risk Analysis
| Risk Category | Mitigation Strategy |  
|---------------|---------------------|  
| Data Integrity | Implement duplicate validation checks |  
| Performance | Optimize concurrent transaction handling |  
| Scalability | Design for increasing data volume |  
| Human Error | Implement input validation and guidance |  

## 10. Conclusion
The solution ensures:  
✔ Data integrity through validation  
✔ Reliability via robust architecture  
✔ Usability with guided workflows

#### Risk mitigation and thorough testing will guarantee to feature success.  