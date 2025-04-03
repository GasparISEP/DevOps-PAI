# US01_RequirementsAndSpecifications
## Configure Teacher Categories (TeacherCategory) using Domain-Driven Design (DDD)

---

## Overview
This document describes the complete refactoring of the **TeacherCategory** functionality based on **Domain-Driven Design (DDD)** principles.  
The goal is to replace a procedural and fragile implementation with a cohesive, testable, and domain-aligned model.

---

## Motivation for Refactoring
The original implementation presented:
- Extensive use of **primitive Strings**
- Lack of layer separation
- Validation and creation logic embedded in the constructor
- Violation of DDD principles such as aggregates, VOs, and factories

### Why create version v2?
- Encapsulation of business rules
- Clear separation of responsibilities (SRP)
- Strong identity with `UUID`
- Ease of unit testing
- Preparation for real persistence

---

## User Story Description
**As an Administrator**, I want to configure Teacher Categories so that the system can use them in teacher registration and progression.

---

## Functional Requirements
- **FR01:** Allow creation of a new teacher category with a valid name
- **FR02:** Check if the category already exists before saving
- **FR03:** Confirm success upon creation

## Non-Functional Requirements
- **NFR01:** Strong domain validation
- **NFR02:** Layered architecture (Application, Domain, Infrastructure)
- **NFR03:** Support for isolated unit testing

---

## DDD Modeling

### Value Objects
- `Name`: Valid name (3–100 characters, starts with uppercase, no digits)
- `TeacherCategoryID`: Immutable UUID for identity

### Aggregate Root
- `TeacherCategory`:
    - Fields: `id: TeacherCategoryID`, `name: Name`
    - Methods:
        - `identity()`
        - `sameAs()`
        - `equals()` and `hashCode()` based on ID
        - `getIdValue()`, `getNameValue()`

### Factories
- `ITeacherCategoryFactory`: Creation interface
- `TeacherCategoryFactoryImpl`: Creates `TeacherCategory` with new UUID
- `ITeacherCategoryListFactory`: List factory interface (mock/testing)
- `TeacherCategoryListFactoryImpl`: Returns empty `List<TeacherCategory>`

### Repositories
- Interface: `ITeacherCategoryRepository`
    - Methods: `save()`, `findAll()`, `ofIdentity()`, `existsByName()`, `findByName()`
- Implementation: `TeacherCategoryRepositoryImpl` (in-memory)

### Controller
- `US01_ConfigureTeacherCategoryController`:
    - Receives a `String`
    - Constructs VO `Name`
    - Validates for duplicates
    - Creates using the Factory
    - Saves using the Repository
    - Returns boolean status

---

## Class Diagram Summary

- `TeacherCategory` implements `AggregateRoot`
- `TeacherCategoryID` implements `DomainId`
- `Name`, `TeacherCategoryID` implement `ValueObject`
- Controller uses Repository + Factory
- Repository implements `IRepository<ID, T>`

### Relationships:
- Factory uses `Name` and generates `TeacherCategoryID`
- Repository handles persistence and queries
- Controller orchestrates the entire flow

---

## Sequence Diagram (SSD)

### Flow:
1. Admin → UI: Enters category name
2. UI → Controller: Calls `configureTeacherCategory(String)`
3. Controller creates VO `Name`
4. Checks for duplicates via repository
5. Creates new `TeacherCategory` with `TeacherCategoryID`
6. Saves with repository
7. Returns success response to UI/Admin

---

## Implemented Tests

### Value Object `Name`
- Null name
- Invalid name
- Valid name
- `equals()` and `hashCode()`

### Entity `TeacherCategory`
- Valid/invalid creation
- ID-based comparison
- Consistent hashCode if ID is the same

### Controller
- Success: creates category
- Error: duplicate name
- Error: invalid name

### Repository
- Save
- FindAll
- OfIdentity
- ExistsByName
- FindByName

---

## Architectural Justification
Applying DDD enabled:
- ✔ Clear separation of concerns
- ✔ Domain validation encapsulated in VOs
- ✔ Controlled creation via Factory
- ✔ Persistence through Repository interface
- ✔ Rich and expressive domain model

---

## Conclusion
The new version of `TeacherCategory` offers:
- Scalability for new requirements
- Robust domain validation
- Isolated unit testability
- Clear architecture and domain modeling

---

## Appendices

### Diagrams
- Class Diagram: `US01_ConfigureTeacherCategoryInSystem_ClassDiagramDDD.puml`
- Sequence Diagram: `US01_ConfigureTeacherCategoriesInSystem_SequenceDiagramDDD.puml`