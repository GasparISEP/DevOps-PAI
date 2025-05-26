package PAI.service.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.courseEdition.ICourseEditionRepository;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.repositoryInterfaces.studentGrade.IStudentGradeRepository;
import PAI.domain.schoolYear.SchoolYear;
import PAI.domain.studentGrade.IStudentGradeFactory;
import PAI.domain.studentGrade.StudentGrade;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.StudentGradeRequestDTO;
import PAI.dto.studentGrade.StudentGradeResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GradeAStudentServiceImplTest {

    private IStudentGradeFactory studentGradeFactory;
    private IStudentGradeRepository studentGradeRepo;
    private ICourseEditionRepository courseEditionRepo;
    private ICourseEditionEnrolmentRepository courseEditionEnrolmentRepo;
    private IProgrammeEditionRepository programmeEditionRepo;
    private ISchoolYearRepository schoolYearRepo;

    @BeforeEach
    void setUp() {
        studentGradeFactory = mock(IStudentGradeFactory.class);
        studentGradeRepo = mock(IStudentGradeRepository.class);
        courseEditionRepo = mock(ICourseEditionRepository.class);
        courseEditionEnrolmentRepo = mock(ICourseEditionEnrolmentRepository.class);
        programmeEditionRepo = mock(IProgrammeEditionRepository.class);
        schoolYearRepo = mock(ISchoolYearRepository.class);
    }


    @Test
    void constructorValid () {
        // Arrange + Act
        GradeAStudentServiceImpl constructor = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo, courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        // Assert
        assertNotNull(constructor);
    }

    @Test
    void shouldThrowExceptionDueToNullStudentGradeFactory() {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(null, studentGradeRepo, courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);
        });

        // Assert
        assertEquals("StudentGradeFactory cannot be null.", e.getMessage());
    }

    @Test
    void shouldNotBuildConstructorDueToNullStudentGradeRepository () {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(studentGradeFactory, null, courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);
        });

        // Assert
        assertEquals("StudentGradeRepository cannot be null.", e.getMessage());
    }

    @Test
    void shouldNotBuildConstructorDueToNullCourseEditionRepository () {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo, null, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);
        });

        // Assert
        assertEquals("CourseEditionRepository cannot be null.", e.getMessage());
    }

    @Test
    void shouldNotBuildConstructorDueToNullCourseEditionEnrolmentRepository () {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo, courseEditionRepo, null, programmeEditionRepo, schoolYearRepo);
        });

        // Assert
        assertEquals("CourseEditionEnrolmentRepository cannot be null.", e.getMessage());
    }

    @Test
    void shouldNotBuildConstructorDueToNullProgrammeEditionRepository () {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo, courseEditionRepo, courseEditionEnrolmentRepo, null, schoolYearRepo);
        });

        // Assert
        assertEquals("ProgrammeEditionRepository cannot be null.", e.getMessage());
    }

    @Test
    void shouldNotBuildConstructorDueToNullSchoolYearRepository () {
        // Arrange + Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo, courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, null);
        });

        // Assert
        assertEquals("SchoolYearRepository cannot be null.", e.getMessage());
    }

    @Test
    void gradeAStudentShouldGradeAStudentSuccessfully () throws Exception {
        // Arrange
        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        Grade gradeDouble = mock(Grade.class);
        Date dateDouble = mock(Date.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        SchoolYearID schoolYearIDDouble = mock(SchoolYearID.class);
        CourseID courseIDDouble = mock(CourseID.class);
        StudyPlanID studyPlanIDDouble = mock(StudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);

        GradeAStudentCommand commandDouble = mock(GradeAStudentCommand.class);

        when(commandDouble.grade()).thenReturn(gradeDouble);
        when(commandDouble.date()).thenReturn(dateDouble);
        when(commandDouble.studentID()).thenReturn(studentIDDouble);
        when(commandDouble.courseEditionID()).thenReturn(courseEditionIDDouble);

        StudentGrade studentGradeDouble = mock(StudentGrade.class);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(commandDouble.studentID(), commandDouble.courseEditionID())).thenReturn(true);
        when(studentGradeFactory.newGradeStudent(
                commandDouble.grade(), commandDouble.date(), commandDouble.studentID(), commandDouble.courseEditionID()
        )).thenReturn(studentGradeDouble);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(studentIDDouble, courseEditionIDDouble)).thenReturn(true);
        when(studentGradeRepo.containsOfIdentity(studentGradeDouble.identity())).thenReturn(false);
        when(studentGradeRepo.findAll()).thenReturn(new ArrayList<>());
        when(studentGradeRepo.save(studentGradeDouble)).thenReturn(studentGradeDouble);

        when(studentGradeDouble.get_studentID()).thenReturn(studentIDDouble);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);
        when(studentGradeDouble.knowGrade()).thenReturn(18.0);
        when(studentGradeDouble.get_date()).thenReturn(dateDouble);
        when(studentGradeDouble.get_courseEditionID()).thenReturn(courseEditionIDDouble);
        when(courseEditionIDDouble.toString()).thenReturn("courseEdition123");
        when(courseEditionIDDouble.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble);
        when(programmeEditionIDDouble.toString()).thenReturn("programmeEdition123");
        when(courseEditionIDDouble.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanIDDouble);
        when(courseInStudyPlanIDDouble.toString()).thenReturn("courseInStudyPlan123");
        when(programmeEditionIDDouble.getSchoolYearID()).thenReturn(schoolYearIDDouble);
        when(schoolYearIDDouble.toString()).thenReturn("schoolYear123");
        when(programmeEditionIDDouble.getProgrammeID()).thenReturn(programmeIDDouble);
        when(programmeIDDouble.toString()).thenReturn("programme123");
        when(courseInStudyPlanIDDouble.getCourseID()).thenReturn(courseIDDouble);
        when(courseIDDouble.toString()).thenReturn("course123");
        when(courseInStudyPlanIDDouble.getStudyPlanID()).thenReturn(studyPlanIDDouble);
        when(studyPlanIDDouble.toString()).thenReturn("studyPlan123");

        // Arranging the behaviour of the isDateInRangeWithSchoolYear method
        CourseEdition courseEditionDouble1 = mock(CourseEdition.class);
        ProgrammeEdition programmeEditionDouble1 = mock(ProgrammeEdition.class);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);

        when(courseEditionRepo.ofIdentity(courseEditionIDDouble)).thenReturn(Optional.of(courseEditionDouble1));
        when(courseEditionDouble1.getProgrammeEditionID()).thenReturn(programmeEditionIDDouble);
        when(programmeEditionRepo.ofIdentity(programmeEditionIDDouble)).thenReturn(Optional.of(programmeEditionDouble1));
        when(programmeEditionDouble1.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearIDDouble);
        when(schoolYearRepo.ofIdentity(schoolYearIDDouble)).thenReturn(Optional.of(schoolYearDouble1));

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        LocalDate gradeDate = LocalDate.of(2024, 6, 1);

        when(dateDouble.getLocalDate()).thenReturn(gradeDate);
        when(schoolYearDouble1.getStartDate()).thenReturn(mock(Date.class));
        when(schoolYearDouble1.getEndDate()).thenReturn(mock(Date.class));
        when(schoolYearDouble1.getStartDate().getLocalDate()).thenReturn(startDate);
        when(schoolYearDouble1.getEndDate().getLocalDate()).thenReturn(endDate);

        // Act
        StudentGradeResponseDTO result = service.gradeAStudent(commandDouble);

        // Assert
        assertNotNull(result);
    }
    @Test
    public void shouldGetApprovalRateOf100() throws Exception {
        // Arrange
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);

        Grade grade10 = mock(Grade.class);
        Grade grade20 = mock(Grade.class);

        when(grade10.knowGrade()).thenReturn(10.0);
        when(grade20.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade10);
        when(studentGrade2.get_grade()).thenReturn(grade20);
        when(studentGrade3.get_grade()).thenReturn(grade20);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID)).thenReturn(false);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        // Act
        double approvalRate = gradeAStudentService.knowApprovalRate(courseEditionID);

        // Assert
        assertEquals(100, approvalRate, 0.01);
    }

    @Test
    public void shouldGetApprovalRateOf50() throws Exception {
        // Arrange
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);

        Grade grade8 = mock(Grade.class);
        Grade grade20 = mock(Grade.class);

        when(grade8.knowGrade()).thenReturn(8.0);
        when(grade20.knowGrade()).thenReturn(20.0);

        when(studentGrade1.get_grade()).thenReturn(grade8);
        when(studentGrade2.get_grade()).thenReturn(grade20);
        when(studentGrade3.get_grade()).thenReturn(grade20);

        when(studentGrade1.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
        when(studentGrade3.hasThisCourseEditionID(courseEditionID)).thenReturn(false);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3));

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        // Act
        double approvalRate = gradeAStudentService.knowApprovalRate(courseEditionID);

        // Assert
        assertEquals(50, approvalRate, 0.01);
    }

    @Test
    public void shouldNotGetApprovalRateWith0Students() throws Exception {
        // Arrange
        CourseEditionID courseEditionID = mock(CourseEditionID.class);

        when(studentGradeRepo.findAll()).thenReturn(new ArrayList<>());

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        // Act
        double approvalRate = gradeAStudentService.knowApprovalRate(courseEditionID);

        // Assert
        assertEquals(0, approvalRate);
    }



}