# System Requirements and Specification

## US15: As a HRC, I want to update the teacher’s working percentage in the system.

### 1. Introduction

This document provides a comprehensive specification and analysis for User Story 15 of the PAI project.

It defines the functional and non-functional requirements, acceptance criteria, business rules, and potential risks, offering a solid foundation for the development and validation of this feature.

### 2. User Story (US) Description

As a Human Resources Collaborator (HRC), I want to update a teacher’s working percentage in the system so that the recorded workload remains accurate and up to date.

A teacher has a category and a working percentage, which is typically 50% or 100% but can also have other values. The system must track all changes in the teacher’s career, ensuring that modifications to the working percentage are properly recorded.

Teacher Career Progression involves possible changes in both the teacher’s category and working percentage. This feature is essential for maintaining a precise history of career adjustments within the system.

### 3. Functional Requirements

- The system shall provide a list of teachers, allowing the HRC to select a teacher. Once selected, only the teacher’s acronym shall be used for further processing.

- The HRC shall be able to update the teacher’s working percentage, ensuring that the new value is different from the previous one.

- The working percentage must be a value between 0 and 100.

- The system shall store the updated working percentage along with the registration date, teacher ID, and teacher category ID.

- The system shall maintain a record of all working percentage changes in the **TeacherCareerProgression** history.

### 4. Non-Functional Requirements

- The update process shall be optimized to ensure minimal response time and efficient handling of multiple requests.

- All changes shall be securely stored to maintain data integrity and historical accuracy.

- The system shall follow best practices in software development, including clean code principles and proper data validation.  

### 5. Acceptance Criteria

- The system shall successfully update the teacher’s working percentage if the provided date is later than the last recorded **TeacherCareerProgression (TCP)** date.

- The new working percentage must be different from the existing value in the last recorded TCP.

- The system shall prevent updates in the following cases:
    - If the teacher does not exist in the system.
    - If the provided working percentage is the same as the last recorded TCP value.
    - If the provided working percentage is outside the valid range (0 to 100).
    - If the provided date is not later than the last recorded TCP date.

- Upon a successful update, the system shall store the new working percentage along with the update date.  

### 6. Business Rules

- The working percentage (WP) can be any value between 0 and 100, inclusive.

- The teacher's working percentage can be updated multiple times in a day, but it must always be different from the previous value. This allows for the correction of input errors.

- A new **TeacherCareerProgression (TCP)** record is created every time the working percentage is updated. The previous record is never overwritten or replaced.  

### 7. Main Entities

- **Teacher**: Represents a faculty member who is responsible for teaching courses and could also be appointed as Director of a Programme or a Department. Each teacher has a unique identifier (TeacherID) and other relevant personal details (e.g., name, contact information, address). A teacher may have multiple **TeacherCareerProgression (TCP)** records over time, reflecting changes in their career.

- **TeacherCareerProgression (TCP)**: Represents an update in a teacher's career, including changes to their category and/or working percentage. Each update includes the date of the change. The system automatically creates an initial career progression record when a teacher is registered. A **TCP** record stores the following information:
    - TeacherID (reference to the teacher)
    - Working Percentage (WP)
    - Date of the update
    - TeacherCategoryID (reference to the teacher's category at the time of the update)

- **TeacherCategory**: Represents a classification within the university that divides educators into distinct groups based on rank. A teacher’s category may change over time, reflecting updates in their role or responsibilities. Each teacher category has a unique identifier (TeacherCategoryID) and can be associated with multiple teachers.  

### 8. Event Flow

1. **Initiating the Update**
    - The process begins when the HRC requests to update the teacher's working percentage. The system retrieves a list of teachers, and the HRC selects a specific teacher.

2. **Selecting the Teacher**
    - The HRC selects the teacher by their acronym.  

3. **Validation of the New Working Percentage and Date**
    - The HRC inputs a new working percentage (WP) and the date for the update. The system validates the input to ensure:
        - The working percentage is between 0 and 100.
        - The new WP is different from the existing WP in the teacher’s last recorded TCP.
        - The date is in the correct format and is later than the last recorded TCP update date.

4. **Performing the Update**
    - If all validations pass, the system creates a new **TeacherCareerProgression (TCP)** record with the new working percentage, date, teacher category ID, and teacher ID.

5. **Confirmation or Error Handling**
    - If the update is successful, the system sends a success message indicating the teacher’s working percentage was updated successfully.
    - If any validation fails (e.g., invalid WP, invalid date format, or no change in WP), the system sends an error message specifying the issue.

6. **Completion**
    - After a successful update, the teacher’s career progression history is updated, and the system is ready for the next operation.  

### 9. Testing and Validation  

- **T1:** Should return true when the working percentage is successfully updated in the Teacher Career Progression.

  **Description:** This test ensures that the system correctly updates the teacher's working percentage when all conditions are met, such as valid input, a valid date, and a different working percentage from the last record.

  **Expected Outcome:** The update process should complete successfully, and the system should return `true`.

- **T2:** Should return false if the date is null when updating the working percentage.

  **Description:** This test ensures that the system properly handles a case where the provided date for the update is `null`.

  **Expected Outcome:** The system should return `false` to indicate that the operation failed due to invalid input.

- **T3:** Should return false if the working percentage is null when updating the working percentage.

  **Description:** This test ensures that the system handles a `null` working percentage, which is not a valid input for updating the teacher's career progression.

  **Expected Outcome:** The system should return `false` to indicate that the operation failed due to invalid input.

- **T4:** Should return false if the teacher ID is null when updating the working percentage.

  **Description:** This test verifies that the system does not proceed with an update if the teacher ID is `null`.

  **Expected Outcome:** The system should return `false` because the operation cannot proceed without a valid teacher ID.

- **T5:** Should return false when the given date is before the last TCP date to update the working percentage.

  **Description:** This test verifies that the system rejects updates if the given date is earlier than the date of the last recorded Teacher Career Progression for the teacher.

  **Expected Outcome:** The system should return `false` because the provided date is not valid for the update (must be later or equal to the last recorded date).

- **T6:** Should return false when the given working percentage is the same as the last TCP working percentage.

  **Description:** This test ensures that the system does not update the working percentage if the new value is the same as the existing one, maintaining data integrity and preventing redundant updates.

  **Expected Outcome:** The system should return `false`, indicating that the update did not happen because the working percentage remains unchanged.  

### 10. Identified Risks

- **Data Integrity Risks**: There is a risk of inconsistent or incorrect data being recorded if proper validations are not in place when updating teacher career progressions. Errors in the working percentage or teacher category could result in incorrect records, impacting future queries or reports.

- **System Performance**: With a growing number of updates to teacher career progressions, performance could become an issue, especially if the system does not efficiently handle large volumes of historical data.

- **Loss of Historical Data**: If the system fails to track and retain historical data on teacher career progressions, there could be a risk of losing valuable historical information on teachers' career paths.  

### 11. Conclusion

The management of working percentage updates in Teacher Career Progression is a vital component in ensuring that teacher data is consistently tracked and maintained throughout their career. This document has formally outlined the expected behavior for updating working percentages and managing historical career progression data for teachers. By addressing both functional and non-functional requirements, the analysis ensures the integrity of teacher career records and guarantees that changes are handled in a controlled and reliable manner.

The implementation of this solution provides academic staff with a robust system for tracking career progression, supporting accurate data reporting, and offering valuable insights into teachers' career trajectories. It also ensures that updates are only processed under valid conditions, maintaining the system’s reliability and consistency.








 



