# System Requirements and Specification

## US06: As an Administrator, I want to change/update the Department Director of a department

### 1. Introduction

This document presents the analysis for User Story 06, outlining the requirements and specifications involved in allowing an `Administrator` to **change/update** the `Department Director` of a `department`.


### 2. Functional Requirements

**FR1**: The system must allow the specification of the **Department** and chosen **Teacher** to become the **Department Director**.

**FR2**: The system must verify that the given **Teacher** is a valid Teacher (i.e. not null)

**FR3**: The system must verify that the given **Department** is a valid Department (i.e. not null and already created previously and present in the **DepartmentRepository**)

**FR4**: The system must only be able to perform a change/update in **Department Director** if there is already an existent Director (**FurtherDirector**) present in that **Department**

### 3. Non-Functional Requirements

There are no strict non-functional requirements at this stage, but it is crucial to maintain good software design principles. This includes adhering to encapsulation, ensuring a well-structured and readable codebase, and considering performance efficiency.

### 4. Event Flow

- The **Administrator** initiates the process by requesting to change/update the **Department Director** of a **Department**.

- The system asks the **Administrator** to provide the **department** and **teacher** to become the new **department director**.

- The **Administrator** submits the **department** and **teacher** data.

-  The system **validates the entered data** and if all data is valid, the system prompts the **Administrator** to confirm the requested action.
- Following confirmation by the **Administrator**, the system sends back a message confirming the change/update of the **Department Director**


### 5. Acceptance Criteria
**AC1**: The system must confirm that the **DepartmentRepository** is not null to perform the required task.

**AC2**: The system must only allow the introduction of non-null **Department** and **Teacher**.

**AC3**: The system must verify that there is already a **Department Director** in the specified **Department**.

**AC4**: The system must not allow a **Teacher** not belonging to the specified **Department** to become the **Department Director**.

### 6. Testing and Validation

**T1**: Test successfully changing **department director** if when all fields are valid.
- Verifies that the system changes the department director with all valid inputs including department and teacher.

**T2**: Test not changing the director with null **department**
- Verifies that an exception is thrown when Department is null.

**T3**: Test not changing the director with null **teacher**
- Verifies that an exception is thrown when Teacher is null.

**T4**: Test not changing the director if the specified **teacher** does not belong to the specified **department**
- Verifies that an exception is thrown when the teacher (to become the **further director**) does not belong to the department.

### 7. Conclusion
This document outlines the requirements, event flow, and validation criteria for updating a Department Director. By ensuring strict data validation and adherence to functional requirements, the system guarantees that only eligible Teachers can be assigned as Department Directors. The outlined acceptance criteria and test cases help maintain system reliability and prevent invalid updates. 