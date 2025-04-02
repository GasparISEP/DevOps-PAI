# System Requirements and Specification

# US11: As an ASC, I want to register a programme in the system


### 1. Introduction

This document presents a comprehensive overview of User Story 11 from the PAI project.
It describes the key functional and non-functional requirements, relevant business rules, and potential risks, providing essential guidance for the implementation and evaluation of this feature.


### 2. User Story (US) Description

As an ASC, I want to register a programme in the system, so that it becomes officially available for academic planning and future student enrolment.

This functionality is essential for enabling the administrative staff to define the structure of academic offerings, ensuring each programme is formally introduced into the institutional repository with all required metadata.


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


### 4. Non-Functional Requirements

- **Scalability**: The system shall support a growing number of programmes over time without degradation in performance.

- **Security**: Only authorized roles (e.g., ASC) shall be permitted to register a new programme.

- **Reliability**: The system shall prevent the registration of duplicate or invalid programmes.

- **Maintainability**: The programme registration logic shall be easily adaptable to accommodate future changes in programme structure or validation rules.


### 5. Acceptance Criteria

- All mandatory fields must be validated.
	
- A ProgrammeID shall be correctly generated from name and acronym.

- The programme shall only be registered if its ProgrammeID is not already present in the system.
	
- A successful registration shall persist the programme in the repository.
	
- Duplicate or null entries shall be rejected with proper error handling.
	
- The system shall throw appropriate exceptions if:
	- Any mandatory field is null. 
    - The repository instance is not available. 

- The controller shall return a boolean confirmation of success or failure.


### 6. Business Rules

- A programme is uniquely identified by a ProgrammeID, which is composed of the NameWithNumbersAndSpecialChars and Acronym value objects.

- All programmes must be registered with a valid department and programme director.

- The number of ECTS and semesters must be specified and valid.

- Duplicate programme entries (same ProgrammeID) are not permitted.


### 7. Main Entities

To clarify the core domain elements involved in this user story, the following entities are defined:

- **ProgrammeDDD:** A structured academic offering consisting of a name, acronym, degree type, number of semesters and ECTS, department, and a programme director.
	
- **ProgrammeID:** A domain identifier formed by combining the programme’s name and acronym, ensuring uniqueness.
		
- **Department:** The academic division to which the programme belongs.
		
- **TeacherID:** The identifier of the teacher assigned as programme director.
		
- **DegreeTypeID:** Represents the academic level/type of the programme (e.g., Bachelor, Master).


### 8. Event Flow

- The ASC accesses the programme registration interface.

- The ASC enters all required details for the new programme.

- The system validates the input fields.

- The system generates a ProgrammeID using the provided name and acronym.

- The system checks for existing programmes with the same ProgrammeID.

- If no duplicates exist, the programme is instantiated and stored in the repository.

- The system confirms the successful registration.


### 9. Testing and Validation

- **T1:** Registration with null repository
Ensure the system throws an exception if the repository is null during controller instantiation.
   
- **T2:** Successful programme registration
Validate that a valid programme is successfully stored in the repository.

- **T3:** Duplicate programme registration
Verify that the same ProgrammeID cannot be registered more than once.
 
- **T4:** Missing required field
Confirm that the system throws an exception if any mandatory field is missing.

- **T5:** Valid repository contains registered programme
Ensure that the programme appears in the list of all programmes after successful registration.


### 10. Identified Risks

- Missing validation could allow invalid or incomplete programmes to be registered, compromising the academic catalogue.

- Failure to prevent duplicates could lead to redundancy and ambiguity in programme data.

- Lack of proper role-based access control might enable unauthorized entities to register academic offerings.


### 11. Conclusion

Programme registration is a critical capability that ensures academic offerings are defined and maintained accurately.
This document specifies the expected behaviors and rules for safely introducing new programmes into the system, relying on a strong identity mechanism via ProgrammeID and ensuring data integrity through validation and duplication checks.
The solution is structured to support reliable, secure, and maintainable programme registration operations.