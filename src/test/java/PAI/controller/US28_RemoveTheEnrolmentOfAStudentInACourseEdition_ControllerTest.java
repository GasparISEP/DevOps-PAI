package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US28_RemoveTheEnrolmentOfAStudentInACourseEdition_ControllerTest {

    // System should allow the successful removal of a student enrolled in a course edition
    @Test
        void removeExistingEnrollment_ShouldReturnTrue() {
            // Arrange
            CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
            US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock(Student.class);
            CourseEdition mockCourseEdition = mock(CourseEdition.class);

            when(mockRepository.removeEnrolment(mockStudent, mockCourseEdition)).thenReturn(true);

            // Act
            boolean result = controller.removeStudentEnrolment(mockStudent, mockCourseEdition);

            // Assert
            assertTrue(result, "Enrollment should be removed successfully.");
            verify(mockRepository).removeEnrolment(mockStudent, mockCourseEdition);
        }

    // Ensures that the system does not allow the removal of a non-existent enrollment
    @Test
        void removeNonExistingEnrollment_ShouldReturnFalse() {
            // Arrange
            CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
            US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock (Student.class);
            CourseEdition mockCourseEdition = mock (CourseEdition.class);

            // Act
            boolean result = controller.removeStudentEnrolment(mockStudent, mockCourseEdition);

            // Assert
            assertFalse(result, "Removing a non existing enrollment should return false.");
            verify(mockRepository).removeEnrolment(mockStudent, mockCourseEdition); // Ensure no enrollment creation occurs
        }

    // If the student or course edition information is missing (null), the system should reject the operation.
    @Test
        void removeEnrollment_WithNullCourseEditionOrStudent_ShouldReturnFalse(){
            // Arrange
            CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
            US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

            Student mockStudent = mock (Student.class);
            CourseEdition mockCourseEdition = mock (CourseEdition.class);

            // Act and assert
        // test for the case where Student is null.
        boolean result1 = controller.removeStudentEnrolment(null, mockCourseEdition);
        assertFalse(result1, "Removing a non existing enrollment should return false.");

        // test for the case where CourseEdition is null
        boolean result2 = controller.removeStudentEnrolment(mockStudent, null);
        assertFalse(result2, "Removing a non existing enrollment should return false.");

        }

    // Confirms that removing the same enrollment multiple times should only succeed on the first attempt, while subsequent attempts should be denied
    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt() {
        // Arrange
        CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);

        when(mockRepository.removeEnrolment(mockStudent, mockCourseEdition)).thenReturn(true)
                .thenReturn(false);
        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudent, mockCourseEdition);

        // Assert first removal is successful
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");

        // Act again: Try removing a second time
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudent, mockCourseEdition);

        // Assert second removal fails
        assertFalse(secondRemoval, "The second removal should not succeed.");
        verify(mockRepository, times(2)).removeEnrolment(mockStudent, mockCourseEdition);
    }

    // Ensures that the system does not allow the removal of an enrollment that has already been deactivated
    @Test
    void removeAlreadyInactiveEnrollment_ShouldReturnFalse() {
        // Arrange
        CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        Student mockStudent = mock(Student.class);
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        CourseEditionEnrolment mockCee = mock(CourseEditionEnrolment.class);

        when(mockRepository.findByStudentAndEdition(mockStudent, mockCourseEdition))
                .thenReturn(Optional.of(mockCee)); // Simulates that the repository found the enrollment

        when(mockCee.isEnrollmentActive()).thenReturn(false); // Enrollment is already inactive

        // Act: Try removing an already inactive enrollment
        boolean result = controller.removeStudentEnrolment(mockStudent, mockCourseEdition);

        // Assert
        assertFalse(result, "Removing an already inactive enrollment should return false.");
        verify(mockCee, never()).deactivateEnrollment(); // Ensure deactivateEnrollment is not called
    }

    // Ensures that different students enrolled in the same course edition can be removed without issues
    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        Student mockStudent1 = mock(Student.class);
        Student mockStudent2 = mock(Student.class);

        when(mockRepository.removeEnrolment(mockStudent1, mockCourseEdition)).thenReturn(true);
        when(mockRepository.removeEnrolment(mockStudent2, mockCourseEdition)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudent1, mockCourseEdition);
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudent2, mockCourseEdition);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
        verify(mockRepository).removeEnrolment(mockStudent1, mockCourseEdition);
        verify(mockRepository).removeEnrolment(mockStudent2, mockCourseEdition);
    }

    // Ensures that a student can be removed from multiple course editions correctly
    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth() {
        // Arrange
        CourseEditionEnrolmentRepository mockRepository = mock(CourseEditionEnrolmentRepository.class);
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(mockRepository);

        CourseEdition mockCourseEdition1 = mock(CourseEdition.class);
        CourseEdition mockCourseEdition2 = mock(CourseEdition.class);
        Student mockStudent = mock(Student.class);

        when(mockRepository.removeEnrolment(mockStudent, mockCourseEdition1)).thenReturn(true);
        when(mockRepository.removeEnrolment(mockStudent, mockCourseEdition2)).thenReturn(true);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(mockStudent, mockCourseEdition1);
        boolean secondRemoval = controller.removeStudentEnrolment(mockStudent, mockCourseEdition2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
        verify(mockRepository).removeEnrolment(mockStudent, mockCourseEdition1);
        verify(mockRepository).removeEnrolment(mockStudent, mockCourseEdition2);
    }


    // Integration Tests
    @Test
    void removeExistingEnrollment_ShouldReturnTrue_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        repository.enrolStudentInACourseEdition(student, courseEdition);

        // Act
        boolean result = controller.removeStudentEnrolment(student, courseEdition);

        // Assert
        assertTrue(result, "Enrollment should be removed successfully.");
    }

    @Test
    void removeNonExistingEnrollment_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);

        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        // Act
        boolean result = controller.removeStudentEnrolment(student, courseEdition);

        // Assert
        assertFalse(result, "Removing a non existing enrollment should return false.");
    }

    @Test
    void removeEnrollment_WithNullCourseEditionOrStudent_ShouldReturnFalse_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        // Act and assert

        // test for the case where Student is null
        boolean result1 = controller.removeStudentEnrolment(null, courseEdition);
        assertFalse(result1, "Removing a non existing enrollment should return false.");

        // test for the case where CourseEdition is null.
        boolean result2 = controller.removeStudentEnrolment(student, null);
        assertFalse(result2, "Removing a non existing enrollment should return false.");
    }

    @Test
    void removeEnrollmentTwice_ShouldReturnFalseOnSecondAttempt_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        repository.enrolStudentInACourseEdition(student, courseEdition);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(student, courseEdition);
        boolean secondRemoval = controller.removeStudentEnrolment(student, courseEdition);

        // Assert
        assertTrue(firstRemoval, "Enrollment should be removed successfully.");
        assertFalse(secondRemoval, "The second removal should not succeed.");
    }

    @Test
    void removeMultipleStudentsFromSameCourseEdition_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student1 = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Student student2 = new Student("1762242", "John", "223445667", "222553444", "567@gmail.com",
        new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);
        CourseEdition courseEdition = new CourseEdition(course, programmeEdition);

        repository.enrolStudentInACourseEdition(student1, courseEdition);
        repository.enrolStudentInACourseEdition(student2, courseEdition);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(student1, courseEdition);
        boolean secondRemoval = controller.removeStudentEnrolment(student2, courseEdition);

        // Assert
        assertTrue(firstRemoval, "First student's enrollment should be removed successfully.");
        assertTrue(secondRemoval, "Second student's enrollment should be removed successfully.");
    }

    @Test
    void removeStudentFromMultipleCourseEditions_ShouldReturnTrueForBoth_IntegrationTest() throws Exception {
        // Arrange
        CourseEditionEnrolmentRepository repository = new CourseEditionEnrolmentRepository(new CourseEditionEnrolmentFactoryImpl(), new CourseEditionEnrolmentListFactoryImpl());
        US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller controller = new US28_RemoveTheEnrolmentOfAStudentInACourseEdition_Controller(repository);
        Student student = new Student("1765342", "John", "223445667", "222333444", "123@gmail.com",
                new Address("Rua do Caminho", "4554-565", "Porto", "Portugal"));
        Department department = new Department("CSE", "Computer Science Engineer");
        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        ITeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        IAddressFactory addressFactory = new AddressFactoryImpl();
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678",
                "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015",
                "Porto", "Portugal", addressFactory, "25-12-2024", assistantProfessor, 100, department,
                teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        DegreeType master = new DegreeType("Master", 240);
        Programme programme = new Programme("Computer Engineering", "CE", 20, 6, master, department, teacher,
                new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        SchoolYear schoolYear = new SchoolYear("2025-2026", "01-09-2025", "31-07-2026");
        ProgrammeEdition programmeEdition = new ProgrammeEdition(programme, schoolYear);
        Course course = new Course("Programming 101", "P101", 6.0, 1);
        programme.addCourseToAProgramme(course);
        CourseEdition courseEdition1 = new CourseEdition(course, programmeEdition);
        Programme programme2 = new Programme("Civil Engineering", "CE", 20, 6, master, department, teacher,
        new ProgrammeCourseListFactoryImpl(), new CourseInStudyPlanFactoryImpl(),
                new StudyPlanListFactoryImpl(), new StudyPlanFactoryImpl(), new CourseFactoryImpl());
        ProgrammeEdition programmeEdition2 = new ProgrammeEdition(programme2, schoolYear);
        Course course2 = new Course("Programming 102", "P102", 6.0, 1);
        programme2.addCourseToAProgramme(course2);
        CourseEdition courseEdition2 = new CourseEdition(course2, programmeEdition2);

        repository.enrolStudentInACourseEdition(student, courseEdition1);
        repository.enrolStudentInACourseEdition(student, courseEdition2);

        // Act
        boolean firstRemoval = controller.removeStudentEnrolment(student, courseEdition1);
        boolean secondRemoval = controller.removeStudentEnrolment(student, courseEdition2);

        // Assert
        assertTrue(firstRemoval, "Student should be removed from the first course edition.");
        assertTrue(secondRemoval, "Student should be removed from the second course edition.");
    }
}