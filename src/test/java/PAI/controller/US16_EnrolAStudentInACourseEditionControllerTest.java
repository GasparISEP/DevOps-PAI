package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US16_EnrolAStudentInACourseEditionControllerTest {

    //testing constructor of US16 controller
    @Test
    void shouldReturnExceptionIfProgrammeEditionEnrollmentRepositoryInterfaceIsNull (){
        //arrange
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock (ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock (ICourseEditionRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(doubleCeeRepositoryInterface, null, doubleCourseEditionRepositoryInterface);
        });

        //assert
        assertEquals("Programme edition enrolment repository interface cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionRepositoryIsNull (){
        //arrange
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock (CourseEditionEnrolmentRepositoryImpl.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(doubleCeeRepositoryInterface, doublePeeRepositoryInterface, null);
        });

        //assert
        assertEquals("Course edition repository cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnExceptionIfCourseEditionEnrollmentRepositoryIsNull (){
        //arrange
        ICourseEditionRepository doubleCeRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new US16_EnrolAStudentInACourseEditionController(null, doublePeeRepositoryInterface, doubleCeRepositoryInterface);
        });

        //assert
        assertEquals("Course edition enrolment repository interface cannot be null!", exception.getMessage());
    }

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnEmptyListWhenStudentIsNull(){
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled (){
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentId = mock(StudentID.class);
        ProgrammeEditionID doublePee1Id = mock (ProgrammeEditionID.class);
        ProgrammeEditionID doublePee2Id = mock (ProgrammeEditionID.class);
        ProgrammeEditionID doublePee3Id = mock (ProgrammeEditionID.class);


        when (doublePeeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of(doublePee1Id,doublePee2Id,doublePee3Id));

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(3, result.size());
    }

    @Test
    void shouldReturnAnEmptyListWhenStudentIsNotEnrolledInAnyProgrammeEdition (){
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentId = mock(StudentID.class);


        when (doublePeeRepositoryInterface.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId)).
                thenReturn(List.of());

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionsThatStudentIsEnrolled(doubleStudentId);

        //assert
        assertEquals(0, result.size());
    }


    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition(){
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        ProgrammeEditionID doubleProgrammeEditionID = mock (ProgrammeEditionID.class);
        CourseEditionID doubleCourseEdition1 = mock (CourseEditionID.class);
        CourseEditionID doubleCourseEdition2 = mock (CourseEditionID.class);

        when (doubleCourseEditionRepositoryInterface.findCourseEditionsByProgrammeEdition(doubleProgrammeEditionID)).thenReturn(List.of(doubleCourseEdition1, doubleCourseEdition2));

        //act
        List<CourseEditionID> result = controller.findCourseEditionsByProgrammeEdition(doubleProgrammeEditionID);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition method
    @Test
    void shouldReturnTrueIfIsAValidCourseEditionEnrollment () {
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);

        when (doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID, doubleCEID)).thenReturn (true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInSameCourseEdition () {
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID1 = mock (StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);
        StudentID doubleStudentID2 = mock (StudentID.class);

        when (doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID)).thenReturn (true);
        controller.enrolStudentInCourseEdition(doubleStudentID1, doubleCEID);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID2,doubleCEID)).thenReturn (true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStudentID2, doubleCEID);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInDifferentCourseEditions () {
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID1 = mock (StudentID.class);
        CourseEditionID doubleCEID1 = mock(CourseEditionID.class);
        StudentID doubleStudentID2 = mock (StudentID.class);
        CourseEditionID doubleCEID2 = mock(CourseEditionID.class);

        when (doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID1, doubleCEID1)).thenReturn (true);
        controller.enrolStudentInCourseEdition(doubleStudentID1, doubleCEID1);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID2,doubleCEID2)).thenReturn (true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStudentID2, doubleCEID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenSameStudentEnrollInDifferentCourseEditions () {
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStID1 = mock (StudentID.class);
        CourseEditionID doubleCeID1 = mock (CourseEditionID.class);
        CourseEditionID doubleCeID2 = mock (CourseEditionID.class);

        when (doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStID1, doubleCeID1)).thenReturn (true);
        controller.enrolStudentInCourseEdition(doubleStID1, doubleCeID1);

        when(doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStID1,doubleCeID2)).thenReturn (true);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStID1, doubleCeID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenStudentIsAlreadyEnrolledInCourseEdition () {
        //arrange
        ICourseEditionRepository doubleCourseEditionRepositoryInterface = mock(ICourseEditionRepository.class);
        IProgrammeEditionEnrolmentRepository doublePeeRepositoryInterface = mock (IProgrammeEditionEnrolmentRepository.class);
        ICourseEditionEnrolmentRepository doubleCeeRepositoryInterface = mock(ICourseEditionEnrolmentRepository.class);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(
                doubleCeeRepositoryInterface, doublePeeRepositoryInterface, doubleCourseEditionRepositoryInterface);

        StudentID doubleStudentID = mock (StudentID.class);
        CourseEditionID doubleCEID = mock(CourseEditionID.class);

        when (doubleCeeRepositoryInterface.enrolStudentInACourseEdition(doubleStudentID, doubleCEID)).thenReturn(false);

        //act
        boolean result = controller.enrolStudentInCourseEdition(doubleStudentID, doubleCEID);

        //assert
        assertFalse(result);
    }

    //integration tests

    //testing find Programme Editions that Student is Enrolled Method
    @Test
    void shouldReturnOptionalEmptyWhenStudentIsNull_IntegrationTest(){
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionsThatStudentIsEnrolled(null);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnAListOfProgrammeEditionsThatStudentIsEnrolled_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        StudentID studentID1 = new StudentID(1234567);

        SchoolYearID schoolYearID2 = new SchoolYearID();

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID2);

        NameWithNumbersAndSpecialChars name2 = new NameWithNumbersAndSpecialChars("Programme2");
        Acronym acronym2 = new Acronym("P2");
        ProgrammeID programmeID2 = new ProgrammeID(name2,acronym2);
        ProgrammeEditionID programmeEditionID2 = new ProgrammeEditionID(programmeID2, schoolYearID2);

        peeRepository.enrolStudentInProgrammeEdition(studentID1,programmeEditionID1);
        peeRepository.enrolStudentInProgrammeEdition(studentID1,programmeEditionID2);

        //act
        List<ProgrammeEditionID> result = controller.findProgrammeEditionsThatStudentIsEnrolled(studentID1);

        //assert
        assertEquals(2, result.size());
    }

    //testing find Course Editions by Programme Edition Method
    @Test
    void shouldReturnAListOfCourseEditionsThatBelongsToAProgrammeEdition_IntegrationTest() throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        TeacherAcronym acronymTeacher = new TeacherAcronym("ABC");
        Name nameTeacher = new Name ("Joe Doe");
        Email emailTeacher = new Email ("ABC@isep.ipp.pt");
        Country countryTeacher = new Country("Portugal");
        NIF nifTeacher = new NIF("123456789", countryTeacher);
        PhoneNumber phoneNumberTeacher = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4444-789");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        AddressVO address = new AddressVO(street, postalCode, location, country);
        DepartmentAcronym acronym= new DepartmentAcronym("DEI");
        Name name= new Name("Departmento Engenharia Informática");
        Department department = new Department(acronym, name);
        Teacher t1 = new Teacher(
                acronymTeacher, nameTeacher, emailTeacher, nifTeacher, phoneNumberTeacher,
                academicBackground, address, department);

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        DegreeType degreeType = new DegreeType("Bachelor", 25);

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, department, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);

        CourseID courseID1 = new CourseID();
        CourseID courseID2 = new CourseID();

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        Course course1 = new Course("Desenvolvimento de Software", "DSOFT", 30, 1);
        Course course2 = new Course("Base de Dados", "BASDAD", 30, 1);

        programme1.addCourseToAProgramme(course1);
        programme1.addCourseToAProgramme(course2);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID2,programmeEditionID1);

        //act
        List<CourseEditionID> result = controller.findCourseEditionsByProgrammeEdition(programmeEditionID1);

        //assert
        assertEquals (2, result.size());
    }

    //testing enroll a student in a course edition
    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInSameCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1234367);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        TeacherAcronym acronymTeacher = new TeacherAcronym("ABC");
        Name nameTeacher = new Name ("Joe Doe");
        Email emailTeacher = new Email ("ABC@isep.ipp.pt");
        Country countryTeacher = new Country("Portugal");
        NIF nifTeacher = new NIF("123456789", countryTeacher);
        PhoneNumber phoneNumberTeacher = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4444-789");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        AddressVO address = new AddressVO(street, postalCode, location, country);
        DepartmentAcronym acronym= new DepartmentAcronym("DEI");
        Name name= new Name("Departmento Engenharia Informática");
        Department department = new Department(acronym, name);
        Teacher t1 = new Teacher(
                acronymTeacher, nameTeacher, emailTeacher, nifTeacher, phoneNumberTeacher,
                academicBackground, address, department);

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        DegreeType degreeType = new DegreeType("Bachelor", 25);

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, department, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        programme1.addCourseToAProgramme(course1);


        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);

        CourseID courseID1 = new CourseID();

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);
        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        ceeRepository.enrolStudentInACourseEdition(studentID2,ceID1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(studentID1,ceID1);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenSameStudentEnrollInDifferentCourseEditions_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        StudentID studentID1 = new StudentID(1234567);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        TeacherAcronym acronymTeacher = new TeacherAcronym("ABC");
        Name nameTeacher = new Name ("Joe Doe");
        Email emailTeacher = new Email ("ABC@isep.ipp.pt");
        Country countryTeacher = new Country("Portugal");
        NIF nifTeacher = new NIF("123456789", countryTeacher);
        PhoneNumber phoneNumberTeacher = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4444-789");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        AddressVO address = new AddressVO(street, postalCode, location, country);
        DepartmentAcronym acronym= new DepartmentAcronym("DEI");
        Name name= new Name("Departmento Engenharia Informática");
        Department department = new Department(acronym, name);
        Teacher t1 = new Teacher(
                acronymTeacher, nameTeacher, emailTeacher, nifTeacher, phoneNumberTeacher,
                academicBackground, address, department);

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        DegreeType degreeType = new DegreeType("Bachelor", 25);

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, department, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        programme1.addCourseToAProgramme(course1);


        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        CourseID courseID1 = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);
        CourseID courseID2 = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        CourseEditionID ceID2 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID2);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        ceeRepository.enrolStudentInACourseEdition(studentID1,ceID1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(studentID1,ceID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenDifferentStudentsEnrollInDifferentCourseEditions_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        StudentID studentID1 = new StudentID(1234567);
        StudentID studentID2 = new StudentID(1234568);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        TeacherAcronym acronymTeacher = new TeacherAcronym("ABC");
        Name nameTeacher = new Name ("Joe Doe");
        Email emailTeacher = new Email ("ABC@isep.ipp.pt");
        Country countryTeacher = new Country("Portugal");
        NIF nifTeacher = new NIF("123456789", countryTeacher);
        PhoneNumber phoneNumberTeacher = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4444-789");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        AddressVO address = new AddressVO(street, postalCode, location, country);
        DepartmentAcronym acronym= new DepartmentAcronym("DEI");
        Name name= new Name("Departmento Engenharia Informática");
        Department department = new Department(acronym, name);
        Teacher t1 = new Teacher(
                acronymTeacher, nameTeacher, emailTeacher, nifTeacher, phoneNumberTeacher,
                academicBackground, address, department);

        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        DegreeType degreeType = new DegreeType("Bachelor", 25);

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, department, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        programme1.addCourseToAProgramme(course1);


        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);
        CourseID courseID1 = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);

        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        Date date2 = new Date ("12-12-2024");
        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID1,date2);
        CourseID courseID2 = new CourseID();
        CourseInStudyPlanID courseInStudyPlanID2 = new CourseInStudyPlanID (courseID2,studyPlanID2);

        CourseEditionID ceID2 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID2);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        ceeRepository.enrolStudentInACourseEdition(studentID1,ceID1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(studentID2,ceID2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsAlreadyEnrolledInCourseEdition_IntegrationTest () throws Exception {
        //arrange
        ProgrammeEditionEnrolmentFactoryImpl peeFactory = new ProgrammeEditionEnrolmentFactoryImpl();
        ProgrammeEditionEnrolmentListFactoryImpl peeListFactory = new ProgrammeEditionEnrolmentListFactoryImpl();
        ProgrammeEditionEnrolmentRepository peeRepository = new ProgrammeEditionEnrolmentRepository(peeFactory, peeListFactory);

        CourseEditionEnrolmentFactoryImpl ceeFactory = new CourseEditionEnrolmentFactoryImpl();
        CourseEditionEnrolmentListFactoryImpl ceeListFactory = new CourseEditionEnrolmentListFactoryImpl();
        CourseEditionEnrolmentRepositoryImpl ceeRepository = new CourseEditionEnrolmentRepositoryImpl(ceeFactory, ceeListFactory);

        CourseEditionFactoryImpl_2 ceFactory = new CourseEditionFactoryImpl_2();
        CourseEditionListFactoryImpl_2 ceListFactory = new CourseEditionListFactoryImpl_2();
        CourseEditionRepositoryImpl courseEditionRepository = new CourseEditionRepositoryImpl(ceFactory, ceListFactory);

        US16_EnrolAStudentInACourseEditionController controller = new US16_EnrolAStudentInACourseEditionController(ceeRepository, peeRepository, courseEditionRepository);

        StudentID studentID = new StudentID(1234567);

        Course course1 = new Course ("Informatics", "INF", 6, 1);

        TeacherAcronym acronymTeacher = new TeacherAcronym("ABC");
        Name nameTeacher = new Name ("Joe Doe");
        Email emailTeacher = new Email ("ABC@isep.ipp.pt");
        Country countryTeacher = new Country("Portugal");
        NIF nifTeacher = new NIF("123456789", countryTeacher);
        PhoneNumber phoneNumberTeacher = new PhoneNumber("+351", "912345678");
        AcademicBackground academicBackground = new AcademicBackground("Doutoramento em Engenharia Informatica, 2005, ISEP");
        Street street = new Street("Rua das Flores");
        PostalCode postalCode = new PostalCode("4444-789");
        Location location = new Location("Porto");
        Country country = new Country("Portugal");
        AddressVO address = new AddressVO(street, postalCode, location, country);
        DepartmentAcronym acronym= new DepartmentAcronym("DEI");
        Name name= new Name("Departmento Engenharia Informática");
        Department department = new Department(acronym, name);
        Teacher t1 = new Teacher(
                acronymTeacher, nameTeacher, emailTeacher, nifTeacher, phoneNumberTeacher,
                academicBackground, address, department);


        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        DegreeType degreeType = new DegreeType("Bachelor", 25);

        Programme programme1 = new Programme(
                "Computer Engineering", "CE", 20, 6, degreeType, department, t1,
                programmeCourseListFactoryImpl1, courseInStudyPlanFactory,
                studyPlanListFactory, studyPlanFactory, courseFactoryImpl
        );

        programme1.addCourseToAProgramme(course1);

        NameWithNumbersAndSpecialChars name1 = new NameWithNumbersAndSpecialChars("Programme1");
        Acronym acronym1 = new Acronym("P1");
        ProgrammeID programmeID1 = new ProgrammeID(name1,acronym1);
        SchoolYearID schoolYearID1 = new SchoolYearID();
        ProgrammeEditionID programmeEditionID1 = new ProgrammeEditionID(programmeID1, schoolYearID1);

        Date date1 = new Date ("12-12-2023");
        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID1,date1);

        CourseID courseID1 = new CourseID();

        CourseInStudyPlanID courseInStudyPlanID1 = new CourseInStudyPlanID (courseID1,studyPlanID1);
        CourseEditionID ceID1 = new CourseEditionID(programmeEditionID1, courseInStudyPlanID1);

        courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID1,programmeEditionID1);
        ceeRepository.enrolStudentInACourseEdition(studentID,ceID1);

        //act
        boolean result = controller.enrolStudentInCourseEdition(studentID,ceID1);

        //assert
        assertFalse(result);
    }
}