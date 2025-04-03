# System Requirements and Specifications - US08 #

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

This document specifies the requirements for User Story 08 (PAI project), including:

- Functional and non-functional requirements 
- Acceptance criteria 
- Business rules 
- Risk analysisProviding a complete framework for development and quality assurance.

## 2. User Story Description
**Role:** Academic Services Coordinator (ASC)  
**Requirement:** Ability to register a student in the system(create and register)

## 3. Functional Requirements
**FR1:** System shall enable ASC to register a student in the system  
**FR2:** Each student must have a unique identifier (unique number)  
**FR3:** System must persist successfully registered student data  
**FR4:** System shall provide confirmation to ASC upon successful registration

## 4. Non-Functional Requirements
**NFR1:** Prevent duplicate student registrations (data integrity)  
**NFR2:** Support concurrent registrations without data conflicts  
**NFR3:** Adhere to clean code, maintainability, and performance standards  
**NFR4:** Maintain fast response times during configuration process 

## 5. Acceptance Criteria
- [ ] ASC can register student successfully
- [ ] Student must have a unique number and unique NIF 
- [ ] System validates uniqueness before allowing creation
- [ ] System rejects invalid configuration attempts
- [ ] System prompts for correction of missing/incorrect data
- [ ] Persistent storage upon successful validation

## 6. Business Rules
- Uniqueness constraint: No duplicate Students
- Mandatory fields: Student ID, name, email, date of birth, NIF, unique number
- Student academic email is generated based on Student ID
- A student information must always be recorded and stored

## 7. Event Flow
1. ASC initiates student registration
2. System prompts for student details (uniqueNumber, name, NIF, phone, email, street, postalCode, location, country, academicEmail)
3. ASC provides student information
4. System converts student data into Value Objects (uniqueNumber, name, NIF, phone, email, street, postalCode, location, country, academicEmail)
5. On validation success:
    - Creates Address
    - Generates a Student ID
    - Generates a Student Academic Email
    - If no duplicate is found, the new student is saved in the repository
    - Confirms creation to ASC

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










