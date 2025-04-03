# System Requirements and Specification

## US12: As an ASC, I want to change/update the Programme Director of a programme




### 1. Introduction
   This document provides a comprehensive overview of User Story 12 from the PAI project. It outlines the key functional and non-functional requirements, relevant business rules, and potential risks, offering essential guidance for the implementation and evaluation of this feature.

### 2. User Story (US) Description
As a Programme Director, I want to change the Programme Director within the system to ensure that the individual responsible for the management of the programme is correctly registered.

This functionality is crucial for maintaining the organization and effective management of academic programs, ensuring that the responsibility for program leadership is properly assigned and recorded in the system.

### 3. Functional Requirements
- The system shall allow the Programme Director to search for and select a new Programme Director by entering the teacher’s name or ID.

- The system shall retrieve the current Programme and Teacher information to verify the compatibility of the selected teacher with the specific programme.

- The system shall validate whether the teacher is eligible to be the Programme Director for the selected Programme.

- The system shall update the Programme details with the new Programme Director’s information and record the change in the system.

- The system shall ensure that the change is successfully reflected and saved, providing feedback to the user upon completion.

### 4. Non-Functional Requirements
- Usability: The user interface should be intuitive, with clear options for selecting and confirming the new Programme Director.

- Performance: The system should process the change request and update the information in real time, ensuring no delays or errors during the operation.

- Security: The Programme Director role change should be logged for audit purposes, ensuring traceability of all modifications made to programme leadership.

- Data Integrity: The system should ensure that no conflicting Programme Director information is entered and that previous data remains intact for historical reference.  

### 5. Acceptance Criteria
- The system shall verify whether the selected Programme Director exists in the system.

- The system shall ensure that only valid teachers (those with the correct credentials and roles) can be assigned as Programme Directors.

- The system shall check if the Programme is correctly linked to the selected Programme Director.

- The system shall reject changes if the Programme or Teacher entity is null or does not exist.

- The system shall validate that the Programme exists before proceeding with the Programme Director change.

- The Programme Director change operation shall only proceed if both the Programme and Teacher references are valid and properly linked.

### 6. Business Rules
- Each Programme Director change shall have a unique entry associated with the Programme and Teacher identifiers.

- Changes to the Programme Director must be recorded and persistently stored.

- A Programme can only have one active Programme Director at a time.

- A Programme Director can only be assigned to a Programme that they are qualified for, based on institutional rules and criteria.

- A Programme Director cannot be assigned to a Programme if either the Programme or the Teacher does not exist or is not properly linked.

### 7. Main Entities
To clarify the core domain elements involved in this user story, the following entities are defined:

- Teacher: A faculty member eligible to be assigned as Programme Director, possessing the necessary qualifications.

- Programme: An academic programme that consists of multiple course editions and is led by a Programme Director.

- Programme Director: A specific role assigned to a teacher who is responsible for overseeing the Programme’s academic and administrative functions.

- TeacherID: A unique identifier representing the teacher in the system.

- ProgrammeID: A unique identifier representing a specific Programme in the system.

- Change Request: The action of changing the Programme Director within the system, associated with a particular Programme and Teacher.

### 8. Event Flow
1. The Programme Director initiates the change request to assign a new Programme Director.

2. The system prompts the Programme Director to provide the teacher’s identifier and the programme for which the change is to be made.

3. The system validates whether the teacher exists in the system and whether they are eligible to assume the role of Programme Director for the selected Programme.

4. The system checks if the selected Programme already has a Programme Director.

5. If no Programme Director is currently assigned, or if the current one is validly replaced, the system proceeds with the assignment.

6. The system updates the Programme entity with the new Programme Director details.

7. Upon successful completion, the system stores the change and provides a confirmation message to the Programme Director, indicating that the Programme Director change has been successfully recorded.  

### 9. Testing and Validation
- **T1: Invalid Programme Director reference**  
Ensure the system correctly rejects attempts to assign a Programme Director when the teacher entity is null or not registered in the system.

- **T2: Programme not found**  
Verify that the system properly prevents assigning a Programme Director when the selected programme does not exist.

- **T3: Programme Director already assigned**  
Confirm that the system does not allow the same Programme Director to be assigned more than once to the same Programme.

- **T4: Successful Programme Director assignment**  
Validate that a valid teacher can be assigned as Programme Director to a valid Programme when all preconditions are met (e.g., teacher exists, teacher is eligible, programme exists, etc.).

- **T5: Teacher eligibility check**  
Ensure the system rejects the assignment if the teacher does not meet the qualifications required to be Programme Director for the selected Programme.

- **T6: Programme Director assignment date null**  
Ensure the system rejects the Programme Director assignment if no valid date is provided or linked to the assignment.

- **T7: Retrieve correct Programme Director details**  
Verify that the correct Programme Director details are returned when requested for a Programme.

- **T8: Identity uniqueness**  
Validate that each Programme Director change entry is uniquely identified by the combination of Programme ID and Teacher ID, preventing duplicate assignments.

### 10. Identified Risks
- Manual entry of Programme Director information may lead to errors if proper validation mechanisms are not in place, such as incorrect teacher selection or missing programme data.

- Incorrect or duplicate Programme Director assignments could undermine the integrity of programme management and disrupt academic administration processes.

- Lack of strict access control may allow unauthorized users to modify Programme Director details, posing security and compliance risks for institutional governance.

- Teacher qualification mismatch could result in the assignment of an unqualified individual as Programme Director, potentially violating academic policies.

### 11. Conclusion
The process of assigning a Programme Director to a specific Programme is a critical element in managing academic programmes. This document outlines the expected behavior, constraints, and validations involved in Programme Director assignments, ensuring that changes are recorded consistently, securely, and accurately. By covering both functional and non-functional requirements, as well as potential risks, this analysis provides a solid foundation for implementing a reliable system that maintains data integrity and supports the responsibilities of academic staff.

