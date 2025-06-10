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
import PAI.dto.studentGrade.GradeAStudentResponseDTO;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        when(studentGradeFactory.createGradeStudent(
                commandDouble.grade(), commandDouble.date(), commandDouble.studentID(), commandDouble.courseEditionID()
        )).thenReturn(studentGradeDouble);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(studentIDDouble, courseEditionIDDouble)).thenReturn(true);
        when(studentGradeRepo.containsOfIdentity(studentGradeDouble.identity())).thenReturn(false);
        when(studentGradeRepo.findAll()).thenReturn(new ArrayList<>());
        when(studentGradeRepo.save(studentGradeDouble)).thenReturn(studentGradeDouble);

        when(studentGradeDouble.getStudentID()).thenReturn(studentIDDouble);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);
        when(studentGradeDouble.knowGrade()).thenReturn(18.0);
        when(studentGradeDouble.getDate()).thenReturn(dateDouble);
        when(studentGradeDouble.getCourseEditionID()).thenReturn(courseEditionIDDouble);
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

        // Represents a StudentGrade already existing in the database
        StudentGrade existingStudentGrade = mock(StudentGrade.class);

        Iterable<StudentGrade> mockIterable = mock(Iterable.class);
        Iterator<StudentGrade> mockIterator = mock(Iterator.class);

        // Represents the findAll method in the repository
        when(studentGradeRepo.findAll()).thenReturn(mockIterable);

        // Setup iterator
        when(mockIterable.iterator()).thenReturn(mockIterator);

        // First call to hasNext() is true = there is an element
        // Second call to hasNext() is false = has no more elements
        when(mockIterator.hasNext()).thenReturn(true, false);

        // When next() is called, return our existing grade
        when(mockIterator.next()).thenReturn(existingStudentGrade);

        // Both conditions are true which throws exception
        when(existingStudentGrade.hasThisStudentID(studentIDDouble)).thenReturn(false);
        when(existingStudentGrade.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        // Act
        GradeAStudentResponseDTO result = service.gradeAStudent(commandDouble);

        // Assert
        assertNotNull(result);
    }

    @Test
    void gradeAStudentShouldGradeAStudentSuccessfully_StudentAlreadyHasAGradeButInDifferentCourseEdition () throws Exception {
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
        when(studentGradeFactory.createGradeStudent(
                commandDouble.grade(), commandDouble.date(), commandDouble.studentID(), commandDouble.courseEditionID()
        )).thenReturn(studentGradeDouble);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(studentIDDouble, courseEditionIDDouble)).thenReturn(true);
        when(studentGradeRepo.containsOfIdentity(studentGradeDouble.identity())).thenReturn(false);
        when(studentGradeRepo.findAll()).thenReturn(new ArrayList<>());
        when(studentGradeRepo.save(studentGradeDouble)).thenReturn(studentGradeDouble);

        when(studentGradeDouble.getStudentID()).thenReturn(studentIDDouble);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);
        when(studentGradeDouble.knowGrade()).thenReturn(18.0);
        when(studentGradeDouble.getDate()).thenReturn(dateDouble);
        when(studentGradeDouble.getCourseEditionID()).thenReturn(courseEditionIDDouble);
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

        // Represents a StudentGrade already existing in the database
        StudentGrade existingStudentGrade = mock(StudentGrade.class);

        Iterable<StudentGrade> mockIterable = mock(Iterable.class);
        Iterator<StudentGrade> mockIterator = mock(Iterator.class);

        // Represents the findAll method in the repository
        when(studentGradeRepo.findAll()).thenReturn(mockIterable);

        // Setup iterator
        when(mockIterable.iterator()).thenReturn(mockIterator);

        // First call to hasNext() is true = there is an element
        // Second call to hasNext() is false = has no more elements
        when(mockIterator.hasNext()).thenReturn(true, false);

        // When next() is called, return our existing grade
        when(mockIterator.next()).thenReturn(existingStudentGrade);

        // Both conditions are true which throws exception
        when(existingStudentGrade.hasThisStudentID(studentIDDouble)).thenReturn(true);
        when(existingStudentGrade.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        // Act
        GradeAStudentResponseDTO result = service.gradeAStudent(commandDouble);

        // Assert
        assertNotNull(result);
    }

    @Test
    void gradeAStudentShouldGradeAStudentSuccessfully_CourseEditionAlreadyHasGradeButFromDifferentStudent () throws Exception {
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
        when(studentGradeFactory.createGradeStudent(
                commandDouble.grade(), commandDouble.date(), commandDouble.studentID(), commandDouble.courseEditionID()
        )).thenReturn(studentGradeDouble);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(studentIDDouble, courseEditionIDDouble)).thenReturn(true);
        when(studentGradeRepo.containsOfIdentity(studentGradeDouble.identity())).thenReturn(false);
        when(studentGradeRepo.findAll()).thenReturn(new ArrayList<>());
        when(studentGradeRepo.save(studentGradeDouble)).thenReturn(studentGradeDouble);

        when(studentGradeDouble.getStudentID()).thenReturn(studentIDDouble);
        when(studentIDDouble.getUniqueNumber()).thenReturn(1234567);
        when(studentGradeDouble.knowGrade()).thenReturn(18.0);
        when(studentGradeDouble.getDate()).thenReturn(dateDouble);
        when(studentGradeDouble.getCourseEditionID()).thenReturn(courseEditionIDDouble);
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

        // Represents a StudentGrade already existing in the database
        StudentGrade existingStudentGrade = mock(StudentGrade.class);

        Iterable<StudentGrade> mockIterable = mock(Iterable.class);
        Iterator<StudentGrade> mockIterator = mock(Iterator.class);

        // Represents the findAll method in the repository
        when(studentGradeRepo.findAll()).thenReturn(mockIterable);

        // Setup iterator
        when(mockIterable.iterator()).thenReturn(mockIterator);

        // First call to hasNext() is true = there is an element
        // Second call to hasNext() is false = has no more elements
        when(mockIterator.hasNext()).thenReturn(true, false);

        // When next() is called, return our existing grade
        when(mockIterator.next()).thenReturn(existingStudentGrade);

        // Both conditions are true which throws exception
        when(existingStudentGrade.hasThisStudentID(studentIDDouble)).thenReturn(true);
        when(existingStudentGrade.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        // Act
        GradeAStudentResponseDTO result = service.gradeAStudent(commandDouble);

        // Assert
        assertNotNull(result);
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToCommandBeingNull () {
        // Arrange
        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.gradeAStudent(null);
        });
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToCommandGradeBeingNull () {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        when(command.grade()).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.gradeAStudent(command);
        });
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToCommandDateBeingNull () {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        when(command.grade()).thenReturn(mock(Grade.class));
        when(command.date()).thenReturn(null);

        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Date cannot be null", ex.getMessage());
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToCommandStudentIDBeingNull () {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        when(command.grade()).thenReturn(mock(Grade.class));
        when(command.date()).thenReturn(mock(Date.class));
        when(command.studentID()).thenReturn(null);

        // Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Student ID cannot be null", ex.getMessage());
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToCommandCourseEditionIDBeingNull () {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        when(command.grade()).thenReturn(mock(Grade.class));
        when(command.date()).thenReturn(mock(Date.class));
        when(command.studentID()).thenReturn(mock(StudentID.class));
        when(command.courseEditionID()).thenReturn(null);

        // Act
        Exception ex =assertThrows(IllegalArgumentException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Course Edition ID cannot be null", ex.getMessage());
    }

    @Test
    void gradeAStudentMethodShouldThrowExceptionDueToStudentNotBeingEnrolledInCourseEdition () throws Exception {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        when(command.grade()).thenReturn(mock(Grade.class));
        when(command.date()).thenReturn(mock(Date.class));
        when(command.studentID()).thenReturn(mock(StudentID.class));
        when(command.courseEditionID()).thenReturn(mock(CourseEditionID.class));

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(command.studentID(), command.courseEditionID())).thenReturn(false);

        // Act
        Exception ex = assertThrows(BusinessRuleViolationException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Not possible to add grade as student is not enrolled in this course edition", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionDueToDateOfGradeBeingAfterTheCourseEditionSchoolYear () throws Exception {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        Grade mockGrade = mock(Grade.class);
        Date mockDate = mock(Date.class);
        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Mock the entities needed for the date of grade validation
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        SchoolYearID mockSchoolYearID = mock(SchoolYearID.class);
        SchoolYear mockSchoolYear = mock(SchoolYear.class);
        Date mockStartDate = mock(Date.class);
        Date mockEndDate = mock(Date.class);

        when(command.grade()).thenReturn(mockGrade);
        when(command.date()).thenReturn(mockDate);
        when(command.studentID()).thenReturn(mockStudentID);
        when(command.courseEditionID()).thenReturn(mockCourseEditionID);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(command.studentID(), command.courseEditionID())).thenReturn(true);

        when(courseEditionRepo.ofIdentity(mockCourseEditionID))
                .thenReturn(Optional.of(mockCourseEdition));
        when(mockCourseEdition.getProgrammeEditionID())
                .thenReturn(mockProgrammeEditionID);
        when(programmeEditionRepo.ofIdentity(mockProgrammeEditionID))
                .thenReturn(Optional.of(mockProgrammeEdition));
        when(mockProgrammeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(mockSchoolYearID);
        when(schoolYearRepo.ofIdentity(mockSchoolYearID))
                .thenReturn(Optional.of(mockSchoolYear));
        when(mockSchoolYear.getStartDate()).thenReturn(mockStartDate);
        when(mockSchoolYear.getEndDate()).thenReturn(mockEndDate);

        LocalDate schoolYearStart = LocalDate.of(2024, 9, 1);
        LocalDate schoolYearEnd = LocalDate.of(2025, 6, 30);
        LocalDate gradeDate = LocalDate.of(2025, 8, 15);

        when(mockStartDate.getLocalDate()).thenReturn(schoolYearStart);
        when(mockEndDate.getLocalDate()).thenReturn(schoolYearEnd);
        when(mockDate.getLocalDate()).thenReturn(gradeDate);

        // Act
        Exception ex = assertThrows(BusinessRuleViolationException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Not possible to add grade as it's date is not inside the school year's range", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionDueToDateOfGradeBeingBeforeTheCourseEditionSchoolYear () throws Exception {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        Grade mockGrade = mock(Grade.class);
        Date mockDate = mock(Date.class);
        StudentID mockStudentID = mock(StudentID.class);
        CourseEditionID mockCourseEditionID = mock(CourseEditionID.class);

        // Mock the entities needed for the date of grade validation
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        SchoolYearID mockSchoolYearID = mock(SchoolYearID.class);
        SchoolYear mockSchoolYear = mock(SchoolYear.class);
        Date mockStartDate = mock(Date.class);
        Date mockEndDate = mock(Date.class);

        when(command.grade()).thenReturn(mockGrade);
        when(command.date()).thenReturn(mockDate);
        when(command.studentID()).thenReturn(mockStudentID);
        when(command.courseEditionID()).thenReturn(mockCourseEditionID);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(command.studentID(), command.courseEditionID())).thenReturn(true);

        when(courseEditionRepo.ofIdentity(mockCourseEditionID))
                .thenReturn(Optional.of(mockCourseEdition));
        when(mockCourseEdition.getProgrammeEditionID())
                .thenReturn(mockProgrammeEditionID);
        when(programmeEditionRepo.ofIdentity(mockProgrammeEditionID))
                .thenReturn(Optional.of(mockProgrammeEdition));
        when(mockProgrammeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(mockSchoolYearID);
        when(schoolYearRepo.ofIdentity(mockSchoolYearID))
                .thenReturn(Optional.of(mockSchoolYear));
        when(mockSchoolYear.getStartDate()).thenReturn(mockStartDate);
        when(mockSchoolYear.getEndDate()).thenReturn(mockEndDate);

        LocalDate schoolYearStart = LocalDate.of(2024, 9, 1);
        LocalDate schoolYearEnd = LocalDate.of(2025, 6, 30);
        LocalDate gradeDate = LocalDate.of(2023, 8, 15);

        when(mockStartDate.getLocalDate()).thenReturn(schoolYearStart);
        when(mockEndDate.getLocalDate()).thenReturn(schoolYearEnd);
        when(mockDate.getLocalDate()).thenReturn(gradeDate);

        // Act
        Exception ex = assertThrows(BusinessRuleViolationException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Not possible to add grade as it's date is not inside the school year's range", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionDueToStudentAlreadyHavingBeenGradedInRespectiveCourseEdition() throws Exception {
        // Arrange
        GradeAStudentCommand command = mock(GradeAStudentCommand.class);

        GradeAStudentServiceImpl service = new GradeAStudentServiceImpl(
                studentGradeFactory, studentGradeRepo, courseEditionRepo,
                courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo
        );

        // Mock the attributes from the command
        Grade mockGrade = mock(Grade.class);
        Date mockDate = mock(Date.class);
        StudentID studentIDDouble = mock(StudentID.class);
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        // Determine the behaviour of methods called on the command
        when(command.grade()).thenReturn(mockGrade);
        when(command.date()).thenReturn(mockDate);
        when(command.studentID()).thenReturn(studentIDDouble);
        when(command.courseEditionID()).thenReturn(courseEditionIDDouble);

        when(courseEditionEnrolmentRepo.isStudentEnrolledInCourseEdition(studentIDDouble, courseEditionIDDouble))
                .thenReturn(true);

        // Mock the entites needed for the School Year validation
        CourseEdition mockCourseEdition = mock(CourseEdition.class);
        ProgrammeEditionID mockProgrammeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEdition mockProgrammeEdition = mock(ProgrammeEdition.class);
        SchoolYearID mockSchoolYearID = mock(SchoolYearID.class);
        SchoolYear mockSchoolYear = mock(SchoolYear.class);
        Date mockStartDate = mock(Date.class);
        Date mockEndDate = mock(Date.class);

        // Setup the calls for grade within School Year validation
        when(courseEditionRepo.ofIdentity(courseEditionIDDouble))
                .thenReturn(Optional.of(mockCourseEdition));
        when(mockCourseEdition.getProgrammeEditionID())
                .thenReturn(mockProgrammeEditionID);
        when(programmeEditionRepo.ofIdentity(mockProgrammeEditionID))
                .thenReturn(Optional.of(mockProgrammeEdition));
        when(mockProgrammeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(mockSchoolYearID);
        when(schoolYearRepo.ofIdentity(mockSchoolYearID))
                .thenReturn(Optional.of(mockSchoolYear));
        when(mockSchoolYear.getStartDate()).thenReturn(mockStartDate);
        when(mockSchoolYear.getEndDate()).thenReturn(mockEndDate);

        // Setup the dates so that grade date is within the school year range (of the course edition)
        LocalDate schoolYearStart = LocalDate.of(2024, 9, 1);
        LocalDate schoolYearEnd = LocalDate.of(2025, 6, 30);
        LocalDate gradeDate = LocalDate.of(2025, 3, 15);

        when(mockStartDate.getLocalDate()).thenReturn(schoolYearStart);
        when(mockEndDate.getLocalDate()).thenReturn(schoolYearEnd);
        when(mockDate.getLocalDate()).thenReturn(gradeDate);

        // Represents a StudentGrade already existing in the database
        StudentGrade existingStudentGrade = mock(StudentGrade.class);

        Iterable<StudentGrade> mockIterable = mock(Iterable.class);
        Iterator<StudentGrade> mockIterator = mock(Iterator.class);

        // Represents the findAll method in the repository
        when(studentGradeRepo.findAll()).thenReturn(mockIterable);

        // Setup iterator
        when(mockIterable.iterator()).thenReturn(mockIterator);

        // First call to hasNext() is true = there is an element
        // Second call to hasNext() is false = has no more elements
        when(mockIterator.hasNext()).thenReturn(true, false);

        // When next() is called, return our existing grade
        when(mockIterator.next()).thenReturn(existingStudentGrade);

        // Both conditions are true which throws exception
        when(existingStudentGrade.hasThisStudentID(studentIDDouble)).thenReturn(true);
        when(existingStudentGrade.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(true);

        // Act
        Exception ex = assertThrows(BusinessRuleViolationException.class, () -> {
            service.gradeAStudent(command);
        });

        // Assert
        assertEquals("Not possible to add grade as student has already been graded in this course edition", ex.getMessage());
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

        when(studentGrade1.getGrade()).thenReturn(grade10);
        when(studentGrade2.getGrade()).thenReturn(grade20);
        when(studentGrade3.getGrade()).thenReturn(grade20);

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

        when(studentGrade1.getGrade()).thenReturn(grade8);
        when(studentGrade2.getGrade()).thenReturn(grade20);
        when(studentGrade3.getGrade()).thenReturn(grade20);

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

    @Test
    public void shouldGetCourseEditionAverageOf12() throws Exception {
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        StudentGrade studentGrade4 = mock(StudentGrade.class);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3, studentGrade4));

        when(studentGrade1.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);
        when(studentGrade3.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(true);
        when(studentGrade4.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        when(studentGrade1.getGrade()).thenReturn(new Grade(10));
        when(studentGrade2.getGrade()).thenReturn(new Grade(12));
        when(studentGrade3.getGrade()).thenReturn(new Grade(14));
        when(studentGrade4.getGrade()).thenReturn(new Grade(20));

        when(studentGrade1.knowGrade()).thenReturn(10.0);
        when(studentGrade2.knowGrade()).thenReturn(12.0);
        when(studentGrade3.knowGrade()).thenReturn(14.0);
        when(studentGrade4.knowGrade()).thenReturn(20.0);

        //Act
        double result = gradeAStudentService.getAverageGrade(courseEditionIDDouble);

        //Assert
        assertEquals(12.0, result);
    }

    @Test
    public void shouldGetCourseEditionAverageOf12WhenOnly2StudentsHaveCourseEdition() throws Exception {
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        StudentGrade studentGrade4 = mock(StudentGrade.class);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3, studentGrade4));

        when(studentGrade1.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(true);
        when(studentGrade2.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);
        when(studentGrade3.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(true);
        when(studentGrade4.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        when(studentGrade1.getGrade()).thenReturn(new Grade(10));
        when(studentGrade2.getGrade()).thenReturn(new Grade(12));
        when(studentGrade3.getGrade()).thenReturn(new Grade(14));
        when(studentGrade4.getGrade()).thenReturn(new Grade(20));

        when(studentGrade1.knowGrade()).thenReturn(10.0);
        when(studentGrade2.knowGrade()).thenReturn(12.0);
        when(studentGrade3.knowGrade()).thenReturn(14.0);
        when(studentGrade4.knowGrade()).thenReturn(20.0);

        //Act
        double result = gradeAStudentService.getAverageGrade(courseEditionIDDouble);

        //Assert
        assertEquals(12.0, result);
    }

    @Test
    public void shouldReturnNullWhenNumberOfStudentsGradesIsZero() throws Exception {
        //Arrange
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);

        GradeAStudentServiceImpl gradeAStudentService = new GradeAStudentServiceImpl(studentGradeFactory, studentGradeRepo,
                courseEditionRepo, courseEditionEnrolmentRepo, programmeEditionRepo, schoolYearRepo);

        StudentGrade studentGrade1 = mock(StudentGrade.class);
        StudentGrade studentGrade2 = mock(StudentGrade.class);
        StudentGrade studentGrade3 = mock(StudentGrade.class);
        StudentGrade studentGrade4 = mock(StudentGrade.class);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(studentGrade1, studentGrade2, studentGrade3, studentGrade4));

        when(studentGrade1.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);
        when(studentGrade2.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);
        when(studentGrade3.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);
        when(studentGrade4.hasThisCourseEditionID(courseEditionIDDouble)).thenReturn(false);

        when(studentGrade1.getGrade()).thenReturn(new Grade(10));
        when(studentGrade2.getGrade()).thenReturn(new Grade(12));
        when(studentGrade3.getGrade()).thenReturn(new Grade(14));
        when(studentGrade4.getGrade()).thenReturn(new Grade(20));

        when(studentGrade1.knowGrade()).thenReturn(10.0);
        when(studentGrade2.knowGrade()).thenReturn(12.0);
        when(studentGrade3.knowGrade()).thenReturn(14.0);
        when(studentGrade4.knowGrade()).thenReturn(20.0);

        //Act
        Double result = gradeAStudentService.getAverageGrade(courseEditionIDDouble);

        //Assert
        assertEquals(null, result);
    }
}