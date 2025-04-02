# got



## US23: As a Programme Director, I want to know the approval percentage of a course edition of my programme.



        ### 1. Introduction

This document provides a detailed specification and analysis of User Story 23 from the PAI project. It outlines the functional and
non-functional requirements, business rules, and associated risks, serving as a foundation for the development and validation of this
functionality.


### 2. User Story (US) Description

As a Programme Director, I want to know the approval percentage of a specific course edition belonging to a specific programme.
This should ensure that a course edition has students that were evaluated within that academic context and received a specific grade.

This functionality is crucial for managing academic evaluations and ensuring that students are correctly associated with their respective
course editions.



        ### 3. Functional Requirements

- The system shall allow the Programme Director to search for and select a course in a study plan edition by course edition ID.

- The Programme Director shall be able to have access to all the grades in a course edition and be able to calculate the approval percentage.

        - The Programme Director shall select a course edition from the existing options.

- The system shall verify if the course edition has student enrolled in it.

        - The system shall verify which students are enrolled in that course edition and view their grades.

        - The system shall count the quantity of grades greater or equal to 10 and divide them by the total quantity of grades .

        - If not, the system shall informe the user that either that course edition doesn't have any students in it or that there's no grades available.

- The system shall retrieve the approval percentage of that course edition.



### 4. Non-Functional Requirements

- The system must allow simultaneous grading operations while maintaining data consistency and uniqueness, preventing duplicate records and concurrency conflicts.

        - Best practices in software development, including encapsulation, clean code structure, and performance optimization, shall be followed.


### 5. Acceptance Criteria

- The system shall first identify the course edition, and then verify, from a given list of student grades, if those grade are from students enrolled in that course edition.

- A valid grade shall be successfully accessed in the selected course edition.

- Those grades will then be added together while storing the number of grades belonging to said course edition.

- The system shall then calculate the average grade for that course edition.



### 6. Business Rules

- The course edition should have a compound identifier.

- The grades must always be recorded and stored in a list of student grades.

- A student must be enrolled in course edition editions in order to be evaluated.

- A student can only be enrolled in a course edition if he is already enrolled in the related course edition.

- A student can only have one grade per moment of evaluation.

- A grade must be between 0 and 20.

- The date of the evaluation must follow the format DD/MM/YYYY

        ### 7. Main Entities

To better understand the key entities involved in this user story, the following entities are defined:

        - **Student:** A person who is enrolled in a programme edition and set of course editions, participating in the academic programme offered by the institution.

        - **Course Edition** A specific instance of a course conducted in a particular school year.       

        - **Student Grade** Represents the assessment of a student in a specific course edition. It includes the grade obtained, the assessment date, the evaluated student, and the corresponding course edition. The grade must be between 0 and 20, and the date must follow the format DD/MM/YYYY.


### 8. Event Flow

- The Programme Director initiates the viewing process for approval percentage in a course edition.

- The system prompts the Programme Director to provide the course edition's identifier.

        - The system presents a list of available course editions, and the Programme Director selects one.

- The system then goes through a list of grades and finds the one belonging to the corresponding course edition.

- The system, count the quantity of grades equal or grater to 10 and in other variable count the number of grades.

        - The system completes the process of calculating the approval percentage for a given course eiditon and shows it to the Programme Director.



### 9. Testing and Validation


- **T1:** Test null list of student grades

Ensure that the system correctly identifies and rejects the process if there's no list of grades.


- **T2:** Test programme edition not found

Ensure that the system correctly identifies and rejects enrolment attempts when the programme edition not found.


- **T3:** Test valid list of student grades in a course edition

Verify that the system successfully allows for the average grade of a course edition to be viewed given that the
input data is correct and the enrolment conditions are met.


### 10. Identified Risks

- Manual grading processes may introduce human errors if adequate validation checks are not implemented.



        ### 10. Conclusion

Calculating the approval percentage of students in course editions is a critical aspect of academic administration.
This document formally defines the expected behavior and constraints of the process, ensuring that process is executed in
a consistent, secure, and reliable manner. By addressing functional and non-functional aspects, along with potential risks,
this analysis supports the implementation of a robust solution that meets the needs of academic service staff while maintaining
data integrity and alignment with programme structures.