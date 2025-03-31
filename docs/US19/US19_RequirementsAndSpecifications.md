# System Requirements and Specifications - US19 #

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
This document specifies the requirements for User Story 19 (PAI project), including:
- Functional and non-functional requirements
- Acceptance criteria
- Business rules
- Risk analysis  
  Providing a complete framework for development and quality assurance.

## 2. User Story Description
**Role:** Academic Services Collaborator (ASC)  
**Requirement:** Ability to create a course edition in the system

## 3. Functional Requirements
**FR1:** System shall enable ASCs to create course editions  
**FR2:** Each course edition must belong to exactly one (programme edition + course in study plan) combination  
**FR3:** System must persist successfully created course edition data  
**FR4:** System shall provide creation confirmation to ASC

## 4. Non-Functional Requirements
**NFR1:** Prevent duplicate course edition creation (data integrity)  
**NFR2:** Support concurrent creation without data conflicts  
**NFR3:** Adhere to clean code, maintainability, and performance standards  
**NFR4:** Maintain fast response times during creation

## 5. Acceptance Criteria
- [ ] ASC can create course editions for current school year
- [ ] Course edition uniquely linked to one programme edition + one study plan course
- [ ] System validates existence of:
    - Selected programme edition
    - Selected study plan course
- [ ] System rejects invalid creation attempts
- [ ] System prompts for correction of missing/incorrect data
- [ ] Persistent storage upon successful validation
- [ ] Prevention of duplicate entries (same programme edition + course combination)

## 6. Business Rules
- Uniqueness constraint: No duplicate course editions per (programme edition + course) pair
- Mandatory fields: Programme edition + course in study plan
- Optional field: RUC (responsible teacher)

## 7. Event Flow
1. ASC initiates creation
2. System guides through selection process:
    - Degree type → Programme → Programme edition → Course In Study Plan
3. System validates course in study plan compatibility
4. System checks for existing registrations
5. On validation success:
    - Creates course edition
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

#### Risk mitigation and thorough testing will guarantee feature success.  