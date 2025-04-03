# System Requirements and Specifications - US13 #

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

This document specifies the requirements for User Story 13 (PAI project), including:

- Functional and non-functional requirements
- Acceptance criteria
- Business rules
- Risk analysisProviding a complete framework for development and quality assurance.

## 2. User Story Description
**Role:** Human Resources Collaborator (HRC) 
**Requirement:** Ability to register a new teacher in the system, including all relevant
data.

## 3. Functional Requirements
**FR1:** System shall enable HRC to register a new teacher in the system   
**FR2:** Each teacher must have a unique identifier (Acronym) generated upon creation    
**FR3:** System must persist successfully registered teacher data   
**FR4:** System shall provide confirmation to HRC upon successful registration  

## 4. Non-Functional Requirements
**NFR1:** Prevent duplicate student registrations (data integrity)  
**NFR2:** Support concurrent registrations without data conflicts  
**NFR3:** Adhere to clean code, maintainability, and performance standards  
**NFR4:** Maintain fast response times during configuration process

## 5. Acceptance Criteria
- [ ] HRC can register student successfully
- [ ] Teacher must have a unique acronym and unique NIF
- [ ] System validates uniqueness before allowing creation
- [ ] System rejects invalid configuration attempts
- [ ] System prompts for correction of missing/incorrect data
- [ ] Persistent storage upon successful validation

## 6. Business Rules
- Uniqueness constraint: No duplicate Teachers
- Mandatory fields: Teacher ID, name, email, date of birth, NIF, unique number
- Student academic email is generated based on Teacher acronym
- A teacher information must always be recorded and stored

## 7. Event Flow
1. HRC initiates teacher registration.
2. System prompts for teacher categories and department selection.
3. HRC selects a teacher category and department.
4. System requests teacher details (acronym, name, email, NIF, phone, address, academic background, working percentage, date, and country code).
5. HRC gives details (acronym, name, email, NIF, phone, address, academic background, working percentage, date, and country code).
6. System validates and converts inputs into value objects.
4. System converts teacher data into Value Objects (acronym, name, email, NIF, phone, address, academic background, working percentage, date, and country code).
5. On validation success:
    - Creates Address
    - Creates Teacher
    - Generates a Teacher ID
    - Generates a Teacher Academic Email
    - If no duplicate is found, the new teacher is saved in the repository
    - Register the Teacher Career Progression
    - Saves the registration
    - Confirms creation to HRC

## 8. Testing and Validation
- [ ] Creation with valid data
- [ ] Prevention of creation without mandatory field
- [ ] Duplicate entry prevention

## 9. Risk Analysis
| Risk Category  | Mitigation Strategy                        |  
|---------------|-------------------------------------|  
| Data Integrity | Implement uniqueness validation checks in StudentID VO and repository |  
| Performance    | Optimize concurrent transaction handling |  
| Scalability   | Design for increasing data volume |  
| Human Error   | Implement input validation and guidance |  


## 10. Conclusion
The solution ensures:  
✔ Data integrity through validation  
✔ Reliability via robust architecture  
✔ Usability with a guided configuration workflow

#### Risk mitigation and thorough testing will guarantee feature success.