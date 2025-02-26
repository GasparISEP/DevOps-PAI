package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrollmentOfAStudentInACourseEdition_ControllerTest {
        //US28
        @Test
        public void removeExistingEnrollment() throws Exception {
            // arrange
            CourseEditionEnrollmentRepository repository= mock (CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student student = mock (Student.class);
            CourseEdition courseEdition = mock (CourseEdition.class);

            //repository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
            when (repository.removeEnrollment(student,courseEdition)).thenReturn(true);

            // act
            boolean result = controller.removeStudentEnrolment(student, courseEdition);

            // assert
            assertTrue(result, "Enrollment should be removed successfully.");
            assertFalse(repository.findByStudentAndEdition(student, courseEdition).isPresent());
        }

        @Test
        public void removeNonExistingEnrollment() throws Exception {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            DegreeType master = new DegreeType("Master", 240);
            Department CSE = new Department("CSE", "Computer Science Engineer");
            TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
            Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106",
                    "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal",
                    "20-12-2010",
                    assistantProfessor, 100, CSE);
            Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
            Course course = new Course("Programming 101", "P101", 6.0, 1);
            SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
            ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
            CourseEdition courseEdition = new CourseEdition(course, programmeEdition);


            Student student = new Student(1, "John", "223445667", "222333444", "123@gmail.com",
                    new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));

            // act and assert
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrolment(student, courseEdition);
            });

            // assert
            assertEquals("Enrollment does not exist.", exception.getMessage());

            // check if the repository is still empty or if the enrollment has not been removed
            Optional<CourseEditionEnrollment> removedEnrollment = repository.findByStudentAndEdition(student, courseEdition);
            assertTrue(removedEnrollment.isEmpty(), "The enrollment should not exist.");
        }

        @Test
        void removeEnrollment_WithNullCourseEditionOrStudent_ShouldThrowException() throws Exception {
            // arrange
            CourseEditionEnrollmentFactory factoryDouble = mock (CourseEditionEnrollmentFactory.class);
            CourseEditionEnrollmentRepository repository= new CourseEditionEnrollmentRepository (factoryDouble);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student st1 = new Student(1, "John", "223445667", "222333444", "123@gmail.com",
                    new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
            Department d1 = new Department("DCE", "Department of Computer Engineering");
            SchoolYear sy1 = new SchoolYear("2024/2025", "14-10-2024", "30-06-2025");
            DegreeType dt1 = new DegreeType("Master", 30);
            TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
            Teacher pd1 = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto",
                    "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, d1);
            Programme p1 = new Programme("SWITCH DEV", "SDV", 30, 1, dt1, d1, pd1);
            Course c1 = new Course("Development", "DEV", 5, 1);
            ProgrammeEdition pe1 = new ProgrammeEdition(p1, sy1);
            CourseEdition ce1 = new CourseEdition(c1, pe1);

            // act and assert
            IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrolment(null, ce1);
            });
            assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());

            thrown = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrolment(st1, null);
            });
            assertEquals("Student and CourseEdition cannot be null", thrown.getMessage());
        }

        @Test
        public void removeEnrollmentTwice_ShouldThrowExceptionOnSecondAttempt() throws Exception {
            // arrange
            CourseEditionEnrollmentRepository repository= mock (CourseEditionEnrollmentRepository.class);
            US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrollmentOfAStudentInACourseEdition_Controller(repository);

            Student student = mock (Student.class);
            CourseEdition courseEdition = mock (CourseEdition.class);

            //repository.enrollStudentInACourseEdition(student, courseEdition, LocalDate.now());
            when (repository.removeEnrollment(student,courseEdition)).thenReturn(true);

            // act: Remove enrollment the first time
            boolean firstRemoval = controller.removeStudentEnrolment(student, courseEdition);

            // assert first removal
            assertTrue(firstRemoval, "The first removal should succeed.");

            doThrow(new IllegalArgumentException("Enrollment does not exist."))
                    .when(repository).removeEnrollment(student, courseEdition);

            // act and assert: Try removing again and expect an exception
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                controller.removeStudentEnrolment(student, courseEdition);
            });

            // assert second removal throws correct exception
            assertEquals("Enrollment does not exist.", exception.getMessage());
        }
    }