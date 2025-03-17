
package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.GradeStudentRepository;
import PAI.repository.CourseEditionEnrolmentRepository;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US22_IWantToGradeAStudentInACourseEditionTest {
    @Test
    void gradeStudentInRepository () {
        //arrange
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        //act
        US22_IWantToGradeAStudentInACourseEdition G1 = mock(US22_IWantToGradeAStudentInACourseEdition.class);
        //assert
        assertNotNull(G1);
    }

    @Test
    void iWantToGradeAStudentInACourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory= mock(GradeStudentListFactory.class);
        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn(mockGradeList);

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = mock(US22_IWantToGradeAStudentInACourseEdition.class);

        Student student1 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);
        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        when(controller.iWantToGradeAStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(Optional.of(gradeStudent1));

        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudent1.knowGrade()).thenReturn(20.0);
        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);
        when(enrollment1.isEnrollmentActive()).thenReturn(true);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrollment(student1, courseEdition1)).thenReturn(enrollment1);
        when(enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1)).thenReturn(true);

        //act
        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1);
        Optional<GradeStudent> optc1 = controller.iWantToGradeAStudent(20,"10-10-2025",student1,courseEdition1);

        //assert
        assertTrue(optc1.isPresent());
    }

    @Test
    void iWantToGradeAStudentInACourseEditionIntegrationTest () throws Exception {
        // Arrange
        GradeStudentFactory gradeStudentFactory = new GradeStudentFactoryImpl();
        GradeStudentListFactory gradeStudentListFactory = new GradeStudentListFactoryImpl();
        GradeStudentRepository gradeStudentRepository = new GradeStudentRepository(gradeStudentFactory, gradeStudentListFactory);

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl courseEditionEnrolmentListFactoryImpl = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepository enrollmentRepository = new CourseEditionEnrolmentRepository(courseEditionEnrolmentFactoryImpl, courseEditionEnrolmentListFactoryImpl);
        CourseFactory courseFactory = new CourseFactoryImpl();
        IProgrammeCourseListFactory IProgrammeCourseListFactory = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactory courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactory studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactory studyPlanFactory = new StudyPlanFactoryImpl();
        TeacherCareerProgressionFactoryImpl teacherCareerProgressionFactoryImpl = new TeacherCareerProgressionFactoryImpl();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactoryImpl();
        AddressFactory addressFactory = new AddressFactoryImpl();

        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository, enrollmentRepository);

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactory,"12-03-2025", tc1, 70,
                dpt1, teacherCareerProgressionFactoryImpl, teacherCareerProgressionListFactory);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, IProgrammeCourseListFactory, courseInStudyPlanFactory,studyPlanListFactory,
                studyPlanFactory,courseFactory);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition courseEdition1 = new CourseEdition(c1, pE1);
        Student student1 = new Student("1234567", "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1);

        // Act
        Optional<GradeStudent> result = controller.iWantToGradeAStudent(20, "10-10-2025", student1, courseEdition1);

        // Assert
        assertTrue(result.isPresent(), "A nota deveria ser criada corretamente.");
    }

    @Test
    void iWantToCheckIfStudentIsNotEnrolledInCourseEdition () throws Exception {
        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((mockGradeList));

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller1 = mock(US22_IWantToGradeAStudentInACourseEdition.class);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrollment(student1, courseEdition1)).thenReturn(enrollment1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);
        GradeStudent gradeStudent2 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(8, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student2, courseEdition1)).thenReturn(gradeStudent2);

        when(gradeStudent1.knowGrade()).thenReturn(8.0);
        when(gradeStudent2.knowGrade()).thenReturn(20.0);

        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(gradeStudent2.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(student1, courseEdition1)).thenReturn(false);

        // act
        Boolean result = controller1.isStudentEnrolledInCourseEdition(student2,courseEdition1);

        //assert
        assertFalse(result);
    }


    @Test
    void iWantoToGradeAStudentInCourseEditionNotEnrolledInCourseEdition () throws Exception {

        //arrange
        GradeStudentFactory gradeStudentFactory = mock(GradeStudentFactory.class);
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((mockGradeList));

        CourseEditionEnrolmentFactoryImpl courseEditionEnrolmentFactoryImpl = mock (CourseEditionEnrolmentFactoryImpl.class);
        CourseEditionEnrolmentRepository enrollmentRepository = mock(CourseEditionEnrolmentRepository.class);

        GradeStudentRepository gradeStudentRepository = mock(GradeStudentRepository.class);

        US22_IWantToGradeAStudentInACourseEdition controller1 = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository,enrollmentRepository);

        Student student1 = mock(Student.class);
        Student student2 = mock(Student.class);
        CourseEdition courseEdition1 = mock(CourseEdition.class);

        CourseEditionEnrolment enrollment1 = mock(CourseEditionEnrolment.class);

        when(enrollment1.knowStudent()).thenReturn(student1);
        when(enrollment1.knowCourseEdition()).thenReturn(courseEdition1);

        when(courseEditionEnrolmentFactoryImpl.createCourseEditionEnrollment(student1, courseEdition1)).thenReturn(enrollment1);

        enrollmentRepository.enrollStudentInACourseEdition(student1, courseEdition1);

        GradeStudent gradeStudent1 = mock(GradeStudent.class);


        when(gradeStudentFactory.newGradeStudent(20, "10-10-2025", student1, courseEdition1)).thenReturn(gradeStudent1);
        when(gradeStudent1.knowGrade()).thenReturn(20.0);
        when(gradeStudent1.hasThisCourseEdition(courseEdition1)).thenReturn(true);
        when(controller1.isStudentEnrolledInCourseEdition(student1, courseEdition1)).thenReturn(false);


        // act
        Optional <GradeStudent> opt1 = controller1.iWantToGradeAStudent(20,"20/11/2024",student2,courseEdition1);

        //assert
        assertTrue(opt1.isEmpty());
    }



    @Test
    void iWantToGradeAStudentInACourseEditionWithNullGradeStudentRepo () throws IllegalArgumentException {

        //arrange
        GradeStudentRepository gradeStudentRepository1 = null;
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository1 = mock(CourseEditionEnrolmentRepository.class);


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepository1, courseEditionEnrolmentRepository1);
        });
        assertEquals("Repository cannot be null", exception.getMessage());

    }

    @Test
    void iWantToGradeAStudentInACourseEditionWithNullCourseEditionEnrollRepo () throws IllegalArgumentException {

        //arrange
        GradeStudentListFactory gradeStudentListFactory = mock(GradeStudentListFactory.class);

        List<GradeStudent> mockGradeList = spy(new ArrayList<>());

        when(gradeStudentListFactory.newArrayList()).thenReturn((mockGradeList));

        GradeStudentRepository list = mock(GradeStudentRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepository1 = null;


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US22_IWantToGradeAStudentInACourseEdition(list, courseEditionEnrolmentRepository1);
        });
        assertEquals("Repository cannot be null", exception.getMessage());

    }

    @Test
    public void shouldReturnOptionalWithGradeStudentIfGradeStudentWasAddedSuccessfully() throws Exception{

        //arrange

        GradeStudentRepository gradeStudentRepositoryDouble = mock(GradeStudentRepository.class);
        CourseEditionEnrolmentRepository courseEditionEnrolmentRepositoryDouble = mock(CourseEditionEnrolmentRepository.class);
        US22_IWantToGradeAStudentInACourseEdition controller = new US22_IWantToGradeAStudentInACourseEdition(gradeStudentRepositoryDouble, courseEditionEnrolmentRepositoryDouble);

        Student studentDouble = mock(Student.class);
        CourseEdition courseEditionDouble = mock(CourseEdition.class);
        GradeStudent gradeStudentDouble = mock(GradeStudent.class);
        when(courseEditionEnrolmentRepositoryDouble.isStudentEnrolledInCourseEdition(studentDouble,courseEditionDouble)).thenReturn(true);
        when(gradeStudentRepositoryDouble.addGradeToStudent(12,"13-03-2025",studentDouble,courseEditionDouble)).thenReturn(Optional.of(gradeStudentDouble));

        //act
        Optional<GradeStudent> optional = controller.iWantToGradeAStudent(12,"13-03-2025",studentDouble,courseEditionDouble);

        //assert

        assertTrue(optional.isPresent());
    }
}

