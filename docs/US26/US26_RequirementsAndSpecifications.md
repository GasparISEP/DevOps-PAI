# System Requirements and Specification

## US26: As a Department Director, I want to get the number of students enrolled in all programmes associated with my department, in a given school year

### 1. Introduction

This document presents the analysis for User Story 26, outlining the requirements and specifications involved in allowing a `Department Director` to access the `number of students` enrolled in all `Programmes` belonging to his `Department` in a specific `School Year.


### 2. Functional Requirements

**FR1**: The system must allow the specification of the **Department** and chosen **School Year**.

**FR2**: The system must verify that the given **School Year** is a valid School Year (i.e. not null and already created previously)

**FR3**: The system must verify that the given **Department** is a valid Department (i.e. not null and already created previously)

**FR4**: The system must exclude cases where a **Student** is enrolled in more than one **Programme** from the specified **Department** at the given **School Year** (i.e. If a student is enrolled in multiple programmes within the specified department in a given school year, they must be counted only once)

### 3. Non-Functional Requirements

There are no specific non-functional requirements imposed at this stage.
However, it is essential to adhere to software design best practices such as encapsulation, clear code structure, and performance optimization.

### 4. Event Flow

- The **Department Director** initiates the process by requesting to access the **number of students** enrolled in all programmes associated with his **department**, in a given **school year**.

- The system asks the **Department Director** to provide the **department** and **school year**.

- The **Department Director** submits the **department** and **school year**.

-  The system **validates the entered data** and if all data is valid, the system returns the **total student count**.


### 5. Acceptance Criteria
**AC1**: The system must confirm that all necessary repositories (**SchoolYearRepository**, **DepartmentRepository** and **ProgrammeEditionEnrolmentRepository**) to perform the task were previously created and not null.

**AC2**: The system must only allow the introduction of non-null **Department** and **School year**.

**AC3**: The system must verify that the introduced **Department** and **School Year** were already previously created and registered in their respective repositories.

**AC4**: The system must only count once a **Student** that is enrolled in more than one **Programme** from the specified **Department** at the given **School Year**.

### 6. Testing and Validation

**T1**: Test getting student count when **department** and **school year** are not null and present in their repositories.
- Verifies that the system successfully counts the students with all valid inputs including department and school year.

**T2**: Test not getting student count with null **department**
- Verifies that an exception is thrown when Department is null.

**T3**: Test not getting student count with null **school year**
- Verifies that an exception is thrown when School Year is null.

**T4**: Test not getting student count if **department** does not exist
- Verifies that an exception is thrown when Department is not present in the Department Repository.

**T5**: Test not getting student count if **school year** does not exist
- Verifies that an exception is thrown when School Year is not present in the School Year Repository.

**T6**: Test getting the correct student count when student is enrolled in two or more **programmes** from specified **department** at the given **school year**
- Ensures that a student with multiple enrollments within the same department and school year counts as a single student.

### 7. Conclusion
This document analyzes the requirements and expected behaviors for the implementation of the "getting the number of students enrolled in Programmes associated with a specific Department in a given School Year" functionality. It details all the requirements necessary to ensure that the implementation is clear and meets the needs of the Department Director. It outlines the expected inputs, validations, and system behaviors to ensure the accurate retrieval of the student count.