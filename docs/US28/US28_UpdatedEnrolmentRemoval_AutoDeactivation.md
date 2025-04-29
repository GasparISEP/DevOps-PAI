# **System Requirements & Specification: Updated Enrolment Removal Logic**
## **Auto-Deactivation of ProgrammeEditionEnrolment when No Active CourseEditionEnrolments Remain**

## Table of Contents
1. [Purpose](#purpose)
2. [Updated Feature Description](#updated-feature-description)
3. [Functional Requirements](#functional-requirements)
4. [Non-Functional Requirements](#non-functional-requirements)
5. [Test Cases](#test-cases)
6. [Event Flow](#event-flow)
7. [Conclusion](#conclusion)

---

## Purpose

This document outlines the requirements for **User Story 28** which introduces an updated logic for enrolment removal. The feature now includes the auto-deactivation of a **ProgrammeEditionEnrolment** when no active **CourseEditionEnrolments** remain for a student. This ensures that if a student is no longer enrolled in any active course edition within a programme edition, their enrolment at the programme edition level will be automatically deactivated.

Additionally, there is consideration for future evolution: instead of auto-deactivating the enrolment, a potential future update could introduce an **"Irregular Enrolment"** status, allowing more flexible handling of student enrolments.

---

## Updated Feature Description

The previous version of this feature simply marked a student's enrolment in a **Course Edition** as "inactive." The updated approach ensures that if a student no longer has any active enrolments in a **Course Edition** within a **Programme Edition**, their enrolment at the programme level will also be deactivated. This maintains consistency in programme-level data and accurately reflects the student's participation in the academic programme.

**Future Evolution**: While the current implementation focuses on auto-deactivation, future iterations could introduce an **Irregular Enrolment** status. Instead of relying solely on a boolean active/inactive, the enrolment status could evolve into an enumeration (e.g., Active, Inactive, Irregular). This would provide additional flexibility, particularly for students who are in a special status and allowing more nuanced enrolment states.


---

## Functional Requirements

- The system shall allow the ASC to search for and select a student and a **Course Edition**.
- The system shall allow the ASC to request the removal of a student’s enrolment from a **Course Edition**.
- The system shall automatically mark the student’s **Programme Edition Enrolment** as **inactive** when no active **Course Edition Enrolments** remain for that student.
- The system shall prevent the removal of an enrolment if it is already inactive.
- The system shall validate that the enrolment exists before proceeding with the removal (i.e., the student must be enrolled in the selected **Course Edition**).
- The system must support batch removal of multiple enrolments across different **Course Editions** for multiple students.
- Upon successful removal, the system must confirm the operation to the ASC.

---

## Non-Functional Requirements

- **Data Integrity**: The system must ensure no data is lost during the removal process, and all related enrolment statuses (both course and programme levels) are updated correctly.
- **Encapsulation and Modularity**: The logic for status changes should be modular, allowing future updates (such as the introduction of the **Irregular Enrolment** status).
- **Maintainability**: The system should be easy to maintain and extend, particularly when future enhancements are needed (e.g., support for **Irregular Enrolment**).

---

## Test Cases


#### **Simple Tests**

| Test | Description |
|------|-------------|
| **T1: Remove Active Enrolment from a Course Edition** | Removing an active enrolment from a **Course Edition** should deactivate only the specific course enrolment. |
| **T2: Remove Last Active Course Edition Enrolment** | When the last active **Course Edition Enrolment** is removed, the system should also deactivate the student’s **Programme Edition Enrolment**. |
| **T3: Enrolment Already Inactive** | If an enrolment is already marked as inactive, the system should prevent its removal. |

#### **Multiple Removals of the Same Student**

| Test | Description |
|------|-------------|
| **M1: Attempt to Remove the Same Enrolment Multiple Times** | If the ASC tries to remove the same enrolment for a student multiple times, the operation should succeed only on the first attempt, and subsequent attempts should be rejected. |
| **M2: Remove a Student from Two Course Editions, But Leave Other Active Enrolments** | When a student is removed from two **Course Editions**, but still has other active enrolments, only the courses being removed should be deactivated, while the student remains enrolled in other active courses. |
| **M3: Remove Last Two Active Course Editions for a Student** | When the last two active **Course Edition Enrolments** are removed for a student, both the **Course Editions** and the **Programme Edition** should be deactivated. |
| **M4: Mixed Sequence of Removals for a Student (Active and Inactive Enrolments)** | A mixed sequence of removal operations (both active and inactive enrolments) should be handled correctly, with only active enrolments being deactivated. |

#### **Multiple Removals for Several Students**

| Test                                                                                            | Description |
|-------------------------------------------------------------------------------------------------|-------------|
| **M5: Remove Multiple Enrolments of Students in a Course Edition**                              | Multiple students can be removed from the same **Course Edition** without affecting their **Programme Edition Enrolment** (as long as they are still enrolled in other active courses in the same programme). |
| **M6: Remove Multiple Students Enrolments, One Student Loses All Active Course Editions** | When removing multiple students from a **Course Edition**, if one of the students no longer has any active **Course Edition Enrolments** within the same **Programme Edition**, the system should also deactivate that student’s **Programme Edition Enrolment**, while keeping the others unchanged. |
| **M7: Remove Enrolments of Multiple Students with Mixed Statuses (Active and Inactive)**        | The system should correctly process the removal of multiple students with a mix of active and inactive enrolments, deactivating only the active ones. |
| **M8: Remove All Students from a Programme Edition**                                            | When all students are removed from a **Programme Edition**, the system should deactivate the **Programme Edition** for all students, as they are no longer enrolled in any active course edition. |

#### **Null Tests**

| Test | Description |
|------|-------------|
| **N1: Null Student** | If the student is null, the system should reject the removal operation. |
| **N2: Null Course Edition** | If the Course Edition is null, the system should reject the removal operation. |
| **N3: Enrolment Does Not Exist** | If the enrolment does not exist, the system should reject the removal operation. |

---

## Event Flow

1. The ASC initiates the process to remove a student's enrolment from a **Course Edition**.
2. The system prompts the ASC to specify which student and **Course Edition** are involved.
3. The ASC provides the necessary details (student and course edition).
4. The system verifies the enrolment exists and is active before proceeding with removal.
5. If the enrolment is active, the system marks the **Course Edition Enrolment** as **inactive**.
6. If no active **Course Edition Enrolments** remain for the student, the system automatically deactivates the **Programme Edition Enrolment**.
*(In future implementations, this step could be adapted to set the status to "Irregular" instead of deactivating, allowing more nuanced enrolment states.)*
7. The system confirms the successful operation to the ASC.

---

## Conclusion

The updated logic for **enrolment removal** introduces automatic deactivation of the **Programme Edition Enrolment** when no active **Course Edition Enrolments** remain. This ensures better consistency and data integrity across the system. There is also potential for future enhancement to introduce an **Irregular Enrolment** status, adding more flexibility to the system, allowing more nuanced enrolment states. This document provides a solid foundation for both the development and testing phases, ensuring that the solution is robust, scalable, and easy to maintain.
