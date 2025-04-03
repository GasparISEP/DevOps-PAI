# System Requirements and Specification

# US27: As an ASC, I want to register a programme in the system, including the study plan


### 1. Introduction

This document presents a comprehensive overview of User Story 27 from the PAI project.
It describes the key functional and non-functional requirements, relevant business rules, and potential risks, providing essential guidance for the implementation and evaluation of this feature.


### 2. User Story (US) Description

As an ASC, I want to register a programme in the system, including the study plan, so that both the academic structure and its implementation timeline are correctly registered and aligned.

This functionality enables the administrative staff to register academic offerings while ensuring each programme has an associated study plan, facilitating academic planning and future student enrolment.


### 3. Functional Requirements

- The system shall allow the ASC to initiate the registration of a new programme.

- ASC shall provide the following mandatory information:
    -	Programme name
    -	Acronym
    -	Number of ECTS
    -	Number of semesters
    -	Degree type (e.g., BSc, MSc)
    -	Associated department
    -	Programme director (teacher ID)

- The system shall validate that none of the required fields are null.

- The system shall generate a unique ProgrammeID composed of the programme’s name and acronym.

- If a programme with the same ProgrammeID already exists, the system shall reject the registration.

- Upon successful registration, the programme shall be stored in the system’s programme repository.

- After registering the programme, the system shall prompt for the implementation date and automatically create a study plan using:
    - ProgrammeID
    - Implementation date
    - Duration in years (calculated from the number of semesters)
    - Total ECTS credits (from the programme)


### 4. Non-Functional Requirements

- **Scalability**: The system shall support an increasing number of programmes and study plans over time without performance degradation.

- **Security**:  Only authorized roles (e.g., ASC) shall be permitted to register programmes and study plans.

- **Reliability**: The system shall prevent the registration of duplicate or invalid entries.

- **Maintainability**: The programme and study plan registration logic shall be easily adaptable to future changes.


### 5. Acceptance Criteria

- All mandatory fields must be validated.

- A ProgrammeID shall be correctly generated from name and acronym.

- The programme shall only be registered if the ProgrammeID is unique.

- A study plan shall only be created if the ProgrammeID is valid and implementation date is provided.

- The study plan shall be created using the correct duration and ECTS from the programme.

- Duplicate programmes or study plans shall be rejected with meaningful errors.

- The controller shall return a boolean indicating success or failure of both operations.


### 6. Business Rules

- A programme is uniquely identified by a ProgrammeID, which is composed of the NameWithNumbersAndSpecialChars and Acronym value objects.

- All programmes must be registered with a valid department and programme director.

- The number of ECTS and semesters must be specified and valid.

- Each study plan is uniquely identified by a combination of ProgrammeID and implementation date.

- Duplicate programme entries (same ProgrammeID) are not permitted.

- A programme cannot exist without an associated study plan.


### 7. Main Entities

To clarify the core domain elements involved in this user story, the following entities are defined:

- **ProgrammeDDD:** A structured academic offering consisting of a name, acronym, degree type, number of semesters and ECTS, department, and a programme director.

- **ProgrammeID:** A domain identifier formed by combining the programme’s name and acronym, ensuring uniqueness.

- **Department:** The academic division to which the programme belongs.

- **TeacherID:** The identifier of the teacher assigned as programme director.

- **DegreeTypeID:** Represents the academic level/type of the programme (e.g., Bachelor, Master).

- **StudyPlanDDD:** Represents a plan tied to a programme, including implementation date, duration, and ECTS.

- **StudyPlanID:** A unique identifier combining ProgrammeID and implementation date.


### 8. Event Flow

- The ASC accesses the programme registration interface.

- The ASC enters all required details for the new programme.

- The system validates the input fields.

- The system generates a ProgrammeID using the provided name and acronym.

- The system checks for existing programmes with the same ProgrammeID.

- If no duplicates exist, the programme is instantiated and stored in the repository.

- The system then requests the implementation date.

- The system derives the duration and ECTS from the registered programme.

- A StudyPlanDDD is created and stored in the repository if unique. 

- The system confirms the successful registration.


### 9. Testing and Validation

- **T1:** Registration with null repository
  Ensure the system throws an exception if the repository is null during controller instantiation.

- **T2:** Successful programme registration and associated study plan
  Validate that a valid programme is successfully stored in the repository and do the same for study plan.

- **T3:** Duplicate programme registration or study plan
  Verify that the same ProgrammeID cannot be registered more than once.
  Study plan already exists for same ProgrammeID and implementation date.

- **T4:** Missing required field
  Confirm that the system throws an exception if any mandatory field is missing.

- **T5:** Valid repository contains registered programme
  Ensure that the programme appears in the list of all programmes after successful registration.
  Ensure that the study plan appears in the list of all study plans after successful registration.

  
### 10. Identified Risks

- Missing validation could allow invalid or incomplete programmes/study plans to be registered, compromising the academic catalogue.

- Failure to prevent duplicates could lead to redundancy and ambiguity in programme or study plan data.

- Missing linkage between programme and study plan could break consistency.

- Lack of proper role-based access control might enable unauthorized entities to register academic offerings.


### 11. Conclusion

This user story addresses the combined need to register a programme and its corresponding study plan, ensuring both structural and temporal dimensions of academic offerings are correctly recorded.
The system ensures proper validation, traceability, and integrity while supporting extensibility and safe operations.