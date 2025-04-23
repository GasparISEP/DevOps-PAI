
package PAI.factory;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.domain.Student;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.repository.CourseEditionRepositoryImpl;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryImpl;
import PAI.service.StudentGradeService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeFactoryImplTest {

    @Test
    public void shouldCreateConstructor() throws Exception {
        //Arrange
        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(ProgrammeEditionRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepository = mock(SchoolYearRepositoryImpl.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);


        CourseEdition courseEdition = mock(CourseEdition.class);
        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));


        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(courseEdition.getProgrammeEditionID())
                .thenReturn(programmeEditionID);


        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.ofIdentity(programmeEditionID))
                .thenReturn(Optional.of(programmeEdition));


        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(schoolYearID);


        SchoolYear schoolYear = mock(SchoolYear.class);
        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));


        Date Start = mock(Date.class);
        Date End   = mock(Date.class);
        LocalDate SD = mock(LocalDate.class);
        LocalDate ED = mock(LocalDate.class);
        when(schoolYear.getStartDate()).thenReturn(Start);
        when(schoolYear.getEndDate()).thenReturn(End);
        when(Start.getLocalDate()).thenReturn(SD);
        when(End.getLocalDate()).thenReturn(ED);


        Date gradeDate = mock(Date.class);
        LocalDate GD = mock(LocalDate.class);
        when(gradeDate.getLocalDate()).thenReturn(GD);
        when(GD.isBefore(SD)).thenReturn(false);
        when(GD.isAfter(ED)).thenReturn(false);


        StudentID student = mock(StudentID.class);
        Grade grade = mock(Grade.class);


        try (MockedConstruction<StudentGrade> mockConstruction = mockConstruction(StudentGrade.class, (mock, context) -> {
            Grade gradeAtual = (Grade) context.arguments().get(0);
            Date dateAtual = (Date) context.arguments().get(1);
            StudentID studentAtual = (StudentID) context.arguments().get(2);
            CourseEditionID courseEditionIDAtual = (CourseEditionID) context.arguments().get(3);

            when(gradeAtual.knowGrade()).thenReturn(11.0);
            when(mock.get_grade()).thenReturn(gradeAtual);
            when(mock.get_date()).thenReturn(gradeDate);
            when(mock.get_studentID()).thenReturn(studentAtual);
            when(mock.get_courseEditionID()).thenReturn(courseEditionIDAtual);

        })) {

            // act
            StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
            StudentGrade studentGrade = factory.newGradeStudent(grade, gradeDate, student, courseEditionID);

            // Assert
            assertEquals(1, mockConstruction.constructed().size());
            StudentGrade createdStudent = mockConstruction.constructed().get(0);

            assertEquals(grade, createdStudent.get_grade());
            assertEquals(gradeDate, createdStudent.get_date());
            assertEquals(student, createdStudent.get_studentID());
            assertEquals(courseEditionID, createdStudent.get_courseEditionID());
            assertNotNull(studentGrade);

        }

    }


    @Test
    public void shouldNotCreateConstructorWhenDateBeforeBeginningOfSchoolYear() throws Exception {
        // Arrange
        ICourseEditionRepository courseEditionRepository    = mock(CourseEditionRepositoryImpl.class);
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository      schoolYearRepository     = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);



        CourseEditionID    courseEditionID    = mock(CourseEditionID.class);
        CourseEdition      courseEdition      = mock(CourseEdition.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        ProgrammeEdition   programmeEdition   = mock(ProgrammeEdition.class);
        SchoolYearID       schoolYearID       = mock(SchoolYearID.class);
        SchoolYear         schoolYear         = mock(SchoolYear.class);


        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));

        when(courseEdition.getProgrammeEditionID())
                .thenReturn(programmeEditionID);

        when(programmeEditionRepository.ofIdentity(programmeEditionID))
                .thenReturn(Optional.of(programmeEdition));
        when(programmeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(schoolYearID);

        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));

        Date start     = mock(Date.class);
        Date end       = mock(Date.class);
        LocalDate sd   = mock(LocalDate.class);
        LocalDate ed   = mock(LocalDate.class);

        when(schoolYear.getStartDate()).thenReturn(start);
        when(schoolYear.getEndDate()).thenReturn(end);
        when(start.getLocalDate()).thenReturn(sd);
        when(end.getLocalDate()).thenReturn(ed);

        Date   gradeDate = mock(Date.class);
        LocalDate gd     = mock(LocalDate.class);
        when(gradeDate.getLocalDate()).thenReturn(gd);
        when(gd.isBefore(sd)).thenReturn(true);
        when(gd.isAfter(ed)).thenReturn(false);


        StudentID student = mock(StudentID.class);
        Grade     grade   = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, gradeDate, student, courseEditionID));
        assertEquals("Date is out of Range", ex.getMessage());
    }

    @Test
    public void shouldNotCreateConstructorWhenDateAfterEndOfSchoolYear() throws Exception {
        // Arrange
        ICourseEditionRepository     courseEditionRepository    = mock(CourseEditionRepositoryImpl.class);
        IProgrammeEditionRepository  programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository        schoolYearRepository       = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);



        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        CourseEdition courseEdition     = mock(CourseEdition.class);
        when(courseEditionRepository.ofIdentity(courseEditionID))
                .thenReturn(Optional.of(courseEdition));

        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);

        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
        when(programmeEditionRepository.ofIdentity(programmeEditionID))
                .thenReturn(Optional.of(programmeEdition));

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(programmeEdition.findSchoolYearIDInProgrammeEdition())
                .thenReturn(schoolYearID);

        SchoolYear schoolYear = mock(SchoolYear.class);
        when(schoolYearRepository.ofIdentity(schoolYearID))
                .thenReturn(Optional.of(schoolYear));

        Date      start     = mock(Date.class);
        Date      end       = mock(Date.class);
        LocalDate sd        = mock(LocalDate.class);
        LocalDate ed        = mock(LocalDate.class);
        when(schoolYear.getStartDate()).thenReturn(start);
        when(schoolYear.getEndDate()).thenReturn(end);
        when(start.getLocalDate()).thenReturn(sd);
        when(end.getLocalDate()).thenReturn(ed);

        Date      gradeDate = mock(Date.class);
        LocalDate gd        = mock(LocalDate.class);
        when(gradeDate.getLocalDate()).thenReturn(gd);
        when(gd.isBefore(sd)).thenReturn(false);
        when(gd.isAfter(ed)).thenReturn(true);

        StudentID student = mock(StudentID.class);
        Grade     grade   = mock(Grade.class);
        when(grade.knowGrade()).thenReturn(10.0);

        StudentGradeFactoryImpl factory =
                new StudentGradeFactoryImpl(
                        courseEditionRepository,
                        programmeEditionRepository,
                        schoolYearRepository,
                        studentGradeRepository
                );

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, gradeDate, student, courseEditionID));
        assertEquals("Date is out of Range", ex.getMessage());
    }


    @Test
    void shouldThrowExceptionWhenGradeIsNull() {
        // Arrange
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(null, dateDouble, student, courseEditionID1Double));
        assertEquals("Grade cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        // Arrange
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
        StudentID student = mock(StudentID.class);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, null, student, courseEditionID1Double));
        assertEquals("Date cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStudentIsNull() {
        // Arrange
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
        Grade grade = mock(Grade.class);
        Date dateDouble = mock(Date.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, dateDouble,null, courseEditionID1Double));
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionIsNull() {
        // Arrange
        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
        StudentID student = mock(StudentID.class);
        Date dateDouble = mock(Date.class);
        Grade grade = mock(Grade.class);

        when(grade.knowGrade()).thenReturn(10.0);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                factory.newGradeStudent(grade, dateDouble,student, null));
        assertEquals("Course Edition cannot be null", exception.getMessage());
    }

    @Test
    public void shouldReturnTrueWhenStudentHasExistingGradeForCourseEdition() throws Exception {
        // Arrange
        ICourseEditionRepository      courseEditionRepo     = mock(ICourseEditionRepository.class);
        IProgrammeEditionRepository   programmeEditionRepo  = mock(IProgrammeEditionRepository.class);
        ISchoolYearRepository         schoolYearRepo        = mock(ISchoolYearRepository.class);
        IStudentGradeRepository       studentGradeRepo      = mock(IStudentGradeRepository.class);

        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepo, programmeEditionRepo, schoolYearRepo, studentGradeRepo);

        StudentID       aluno       = mock(StudentID.class);
        CourseEditionID edicao      = mock(CourseEditionID.class);

        StudentGrade notaExistente = mock(StudentGrade.class);
        when(notaExistente.hasThisStudentID(aluno)).thenReturn(true);
        when(notaExistente.hasThisCourseEditionID(edicao)).thenReturn(true);

        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(notaExistente));

        // Act
        boolean resultado = factory.hasStudentAlreadyGradeAtThisCourseEdition(aluno, edicao);

        // Assert
        assertTrue(resultado);

    }

}

