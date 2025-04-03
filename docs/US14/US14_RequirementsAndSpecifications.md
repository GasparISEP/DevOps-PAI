# System Requirements and Specifications - US24 #

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
This document outlines the requirements for User Story 14 (PAI project), covering:
- Functional and non-functional specifications
- Acceptance criteria
- Business rules
- Risk assessment
  The goal is to provide a structured foundation for the implementation and quality assurance of the feature that allows updating a teacher's category in the system.

## 2. User Story Description
**User Role:** Human Resources Collaborator (HRC)
**Objective:** Update a teacher's category in the system.

## 3. Functional Requirements
**FR1:** The system must allow the HRC to update the category of a teacher by specifying their acronym, category, and the date of the update.

**FR2:** The system must validate the provided teacher acronym and teacher category.

**FR3:** The system must ensure that the teacher and category exist in the system before performing the update.

**FR4:** If a valid teacher and category are provided, the system should update the teacher's career progression with the new category and date.

**FR5:** The system must ensure that no career progression is duplicated for the same teacher and category on the same date.

## 4. Non-Functional Requirements
**NFR1:** Ensure the system handles concurrent updates to teacher data efficiently. 

**NFR2:** Maintain data consistency across teacher and career progression records.

**NFR3:** The system must have a low response time when processing category updates.

**NFR4:** Ensure the system follows secure coding practices to protect teacher data.

## 5. Acceptance Criteria
- [ ] The HRC must be able to input the teacher's acronym, category, and date for the update.
- [ ] If the teacher's acronym is invalid or not found, the system must throw an appropriate error message.
- [ ] If the teacher category is invalid or not found, the system must throw an appropriate error message.
- [ ] The system must correctly update the teacher's category and career progression.
- [ ] The system must prevent the creation of duplicate career progression records for the same teacher and category on the same date.
- [ ] The HRC should receive confirmation that the update was successful.

## 6. Business Rules
- Each teacher must have a unique acronym.
- A teacher's career progression must be updated with a new category on a valid date.
- The system must ensure the teacher's career progression is not duplicated on the same date.

## 7. Event Flow
1. The HRC requests to update the teacher's category.
2. The system prompts the HRC for the teacher's acronym. 
3. The HRC provides the teacher's acronym. 
4. The system verifies the teacher's acronym and retrieves the teacher. 
5. The system prompts the HRC for the teacher's category. 
6. The HRC provides the teacher's new category. 
7. The system verifies the category and retrieves the teacher category. 
8. The system prompts the HRC for the date of the category update. 
9. The HRC provides the date. 
10. The system validates the date and checks if there is any existing career progression for the teacher with the same category on the same date. 
11. If everything is valid, the system updates the teacher's category in the career progression. 
12. The system confirms the update and displays a success message to the HRC.

## 8. Testing and Validation
- [ ] **Unit Tests:** Ensure individual components, such as the controller and repository methods, are tested for expected behavior, especially validation logic and category update functionality.
- [ ] **Integration Tests:** Validate the interaction between the controller, teacher repository, category repository, and career progression repository.
- [ ] **Error Handling Validation:** Ensure that errors, such as invalid teacher acronyms, categories, or dates, are properly handled and communicated to the user with appropriate messages.
- [ ] **User Acceptance Testing (UAT):** Conduct UAT with HRCs to confirm that the feature meets their needs and expectations for updating teacher categories.

## 9. Risk Analysis
| Risk Category  | Mitigation Strategy                     |  
|---------------|-----------------------------------------|  
| Data Integrity | Implement strict validation for teacher acronyms and categories. |  
| Duplicate Records   | Ensure validation for duplicate career progressions on the same date.|  
| Usability   | Provide clear error messages for invalid inputs and update success.   |  
| Performance  | Optimize database queries for efficient category updates. |  


## 10. Conclusion
This feature ensures:

✔ Accurate and secure teacher category updates.

✔ Efficient handling of teacher data and career progression updates.

✔ Clear user interactions, with appropriate error handling and validation.

#### Proper validation and testing will ensure successful implementation of the feature.