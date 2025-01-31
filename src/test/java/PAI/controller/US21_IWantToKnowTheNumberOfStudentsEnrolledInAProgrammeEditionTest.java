package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEditionTest {

    @Test
    void shouldCreateControllerWhenRepositoriesAreValid(){
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();


        // Act
        US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition controller =
                new US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionEnrollmentRepo, programmeEditionRepository);

        // Assert
        assertNotNull(controller);

    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionRepositoryIsNull(){
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionEnrollmentRepo, null));

    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionEnrollmentRepositoryIsNull(){
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(null, programmeEditionRepository));

    }

    @Test
    void shouldReturnCorrectNumberOfStudentsEnrolledInAProgrammeEdition() throws Exception{
        // Arrange
        ProgrammeEditionEnrollmentRepo programmeEditionEnrollmentRepo = new ProgrammeEditionEnrollmentRepo();
        ProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepository();

        Address add1 = new Address("Rua do Caminho", "4554-565", "Porto", "Portugal");
        Student st1 = new Student(1, "João Silva", "123456789", "221234567", "joao123@gmail.com", add1);
        SchoolYear sy1 = new SchoolYear("adeus", "20-01-2024", "23-02-2024");
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
        CourseRepository courseRepository = new CourseRepository();
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
        ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
        LocalDate currentDate = LocalDate.now();

        int NumberOfStudentsEnrolledInAProgrammeEdition = 1;

        // Act
        US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition controlador1 = new US21_IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(programmeEditionEnrollmentRepo, programmeEditionRepository);

        programmeEditionEnrollmentRepo.enrollStudentInProgrammeEdition(st1, pe1, currentDate);
        int result = controlador1.IWantToKnowTheNumberOfStudentsEnrolledInAProgrammeEdition(pe1);

        // Assert
        assertEquals(NumberOfStudentsEnrolledInAProgrammeEdition, result);
    }
}