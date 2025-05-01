package PAI.service;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.domain.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.factory.IStudentGradeFactory;
import PAI.factory.IStudentGradeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeServiceImplTest {

    @Test
    public void shouldCreatConstructor(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act
        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl( studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);

        //assert
        assertNotNull(studentGradeServiceImpl);
    }

    @Test
    public void shouldNotCreatConstructorWithNullFactory(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);





        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(null, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, null,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullCourseEditionEnrolmentRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,null,courseEditionRepository,programmeEditionRepository,schoolYearRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullCourseEditionRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,null,programmeEditionRepository,schoolYearRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullProgrammeEditionRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,null,schoolYearRepository));
    }

    @Test
    public void shouldNotCreatConstructorWithNullSchoolYearnRepository(){
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);


        //act + assert
        assertThrows(Exception.class, () -> new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,null));
    }

    @Test
    public void shouldAddNewStudentGrade() throws Exception {
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade studentGrade = mock(StudentGrade.class);
        when(courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID,courseEditionID)).thenReturn(true);
        when(studentGradeFactory.newGradeStudent(grade, date, studentID, courseEditionID)).thenReturn(studentGrade);
        when(studentGradeRepository.save(studentGrade)).thenReturn(studentGrade);

        //act
        StudentGrade result = studentGradeServiceImpl.newStudentGrade(grade,date,studentID,courseEditionID);

        //assert

        assertEquals(studentGrade, result);

    }
    @Test
    public void shouldThrowAnExceptionWhenNotEnrolled() throws Exception {
        //arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);

        Grade grade = mock(Grade.class);
        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        when(courseEditionEnrolmentRepository.isStudentEnrolledInCourseEdition(studentID,courseEditionID)).thenReturn(false);
        // assert
        assertThrows(Exception.class, () -> studentGradeServiceImpl.newStudentGrade(grade, date, studentID, courseEditionID));

    }

    @Test
    public void shouldGetAverageGradeOfCourseEditionOf15() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);


        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2,studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(15, averageGrade, 0.01);
    }


    @Test
    public void shouldNotGetAverageGradeOfCourseEditionBecauseHave0Students() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.getAverageGrade(courseEditionID1Double);

        // Assert
        assertEquals(null, averageGrade);
    }

    @Test
    public void shouldGetApprovalRateOf100() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);


        // Act
        double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(100, averageGrade, 0.01);
    }

    @Test
    public void shouldGetApprovalRateOf50() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        Grade grade = mock(Grade.class);
        Grade grade1 = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(8.0);
        when(grade1.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade);
        when(studentGrade2.get_grade()).thenReturn(grade1);
        when(studentGrade3.get_grade()).thenReturn(grade1);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID1Double)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);


        // Act
        double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(50, averageGrade, 0.01);
    }

    @Test
    public void shouldNotGetApprovalRateWith0Student() throws Exception {

        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);


        StudentGradeServiceImpl studentGradeServiceImpl = new StudentGradeServiceImpl(studentGradeFactory, studentGradeRepository,courseEditionEnrolmentRepository,courseEditionRepository,programmeEditionRepository,schoolYearRepository);


        // Act
        Double averageGrade = studentGradeServiceImpl.knowApprovalRate(courseEditionID1Double);

        // Assert
        assertEquals(0, averageGrade);
    }

    @Test
    public void shouldReturnTrueWhenDateWithinRange() throws Exception {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        Date gradeDate = mock(Date.class);
        LocalDate gradeLocalDate = LocalDate.of(2025, 5, 1);
        when(gradeDate.getLocalDate()).thenReturn(gradeLocalDate);

        CourseEdition courseEdition = mock(CourseEdition.class);
        ProgrammeEditionID progId = mock(ProgrammeEditionID.class);
        when(courseEdition.getProgrammeEditionID()).thenReturn(progId);
        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEdition.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearID);
        when(programmeEditionRepository.ofIdentity(progId))
                .thenReturn(Optional.of(programmeEdition));

        SchoolYear schoolYear = mock(SchoolYear.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        LocalDate startLocal = LocalDate.of(2025, 1, 1);
        LocalDate endLocal   = LocalDate.of(2025, 12, 31);
        when(startDate.getLocalDate()).thenReturn(startLocal);
        when(endDate.getLocalDate()).thenReturn(endLocal);
        when(schoolYear.getStartDate()).thenReturn(startDate);
        when(schoolYear.getEndDate()).thenReturn(endDate);
        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));

        StudentGradeServiceImpl service = new StudentGradeServiceImpl(
                studentGradeFactory,
                studentGradeRepository,
                courseEditionEnrolmentRepository,
                courseEditionRepository,
                programmeEditionRepository,
                schoolYearRepository
        );

        // Act
        boolean result = service.isDateGradeInRangeWithSchoolYear(courseEditionID, gradeDate);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenDateBeforeStart() throws Exception {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        Date gradeDate = mock(Date.class);
        LocalDate gradeLocalDate = LocalDate.of(2024, 12, 31);
        when(gradeDate.getLocalDate()).thenReturn(gradeLocalDate);

        CourseEdition courseEdition = mock(CourseEdition.class);
        ProgrammeEditionID progId = mock(ProgrammeEditionID.class);
        when(courseEdition.getProgrammeEditionID()).thenReturn(progId);
        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEdition.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearID);
        when(programmeEditionRepository.ofIdentity(progId))
                .thenReturn(Optional.of(programmeEdition));

        SchoolYear schoolYear = mock(SchoolYear.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        LocalDate startLocal = LocalDate.of(2025, 1, 1);
        LocalDate endLocal   = LocalDate.of(2025, 12, 31);
        when(startDate.getLocalDate()).thenReturn(startLocal);
        when(endDate.getLocalDate()).thenReturn(endLocal);
        when(schoolYear.getStartDate()).thenReturn(startDate);
        when(schoolYear.getEndDate()).thenReturn(endDate);
        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));

        StudentGradeServiceImpl service = new StudentGradeServiceImpl(
                studentGradeFactory,
                studentGradeRepository,
                courseEditionEnrolmentRepository,
                courseEditionRepository,
                programmeEditionRepository,
                schoolYearRepository
        );

        // Act
        boolean result = service.isDateGradeInRangeWithSchoolYear(courseEditionID, gradeDate);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenDateAfterEnd() throws Exception {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        ICourseEditionEnrolmentRepository courseEditionEnrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        Date gradeDate = mock(Date.class);
        LocalDate gradeLocalDate = LocalDate.of(2026, 1, 1);
        when(gradeDate.getLocalDate()).thenReturn(gradeLocalDate);

        CourseEdition courseEdition = mock(CourseEdition.class);
        ProgrammeEditionID progId = mock(ProgrammeEditionID.class);
        when(courseEdition.getProgrammeEditionID()).thenReturn(progId);
        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEdition.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearID);
        when(programmeEditionRepository.ofIdentity(progId))
                .thenReturn(Optional.of(programmeEdition));

        SchoolYear schoolYear = mock(SchoolYear.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        LocalDate startLocal = LocalDate.of(2025, 1, 1);
        LocalDate endLocal   = LocalDate.of(2025, 12, 31);
        when(startDate.getLocalDate()).thenReturn(startLocal);
        when(endDate.getLocalDate()).thenReturn(endLocal);
        when(schoolYear.getStartDate()).thenReturn(startDate);
        when(schoolYear.getEndDate()).thenReturn(endDate);
        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));

        StudentGradeServiceImpl service = new StudentGradeServiceImpl(
                studentGradeFactory,
                studentGradeRepository,
                courseEditionEnrolmentRepository,
                courseEditionRepository,
                programmeEditionRepository,
                schoolYearRepository
        );

        // Act
        boolean result = service.isDateGradeInRangeWithSchoolYear(courseEditionID, gradeDate);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldFindExistingStudentGradeMatch() throws Exception {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade grade1 = mock(StudentGrade.class);
        when(grade1.hasThisStudentID(student)).thenReturn(true);
        when(grade1.hasThisCourseEditionID(courseEditionID)).thenReturn(true);

        StudentGrade grade2 = mock(StudentGrade.class);

        when(studentGradeRepository.findAll()).thenReturn(List.of(grade1, grade2));

        StudentGradeServiceImpl service = new StudentGradeServiceImpl(
                studentGradeFactory,
                studentGradeRepository,
                mock(ICourseEditionEnrolmentRepository.class),
                mock(ICourseEditionRepository.class),
                mock(IProgrammeEditionRepository.class),
                mock(ISchoolYearRepository.class)
        );

        // Act
        boolean result = service.hasStudentAlreadyGradeAtThisCourseEdition(student, courseEditionID);

        // Assert
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenNoMatchingStudentGrade() throws Exception {
        // Arrange
        IStudentGradeFactory studentGradeFactory = mock(IStudentGradeFactory.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade grade1 = mock(StudentGrade.class);
        when(grade1.hasThisStudentID(student)).thenReturn(false);
        when(grade1.hasThisCourseEditionID(courseEditionID)).thenReturn(true);

        StudentGrade grade2 = mock(StudentGrade.class);
        when(grade2.hasThisStudentID(student)).thenReturn(true);
        when(grade2.hasThisCourseEditionID(courseEditionID)).thenReturn(false);

        when(studentGradeRepository.findAll()).thenReturn(List.of(grade1, grade2));

        StudentGradeServiceImpl service = new StudentGradeServiceImpl(
                studentGradeFactory,
                studentGradeRepository,
                mock(ICourseEditionEnrolmentRepository.class),
                mock(ICourseEditionRepository.class),
                mock(IProgrammeEditionRepository.class),
                mock(ISchoolYearRepository.class)
        );

        // Act
        boolean result = service.hasStudentAlreadyGradeAtThisCourseEdition(student, courseEditionID);

        // Assert
        assertFalse(result);
    }
}