
package PAI.factory;

import PAI.VOs.*;
import PAI.domain.CourseEdition;
import PAI.domain.SchoolYear;
import PAI.domain.StudentGrade;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.repository.CourseEditionRepositoryImpl;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.persistence.mem.programmeEdition.ProgrammeEditionRepositoryImpl;
import PAI.service.IStudentGradeService;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentGradeFactoryImplTest {

    @Test
    public void shouldCreateStudentGradeWhenAllValidWithoutSetup() throws Exception {
        // Arrange
        IStudentGradeService studentGradeService = mock(IStudentGradeService.class);
        Grade grade = mock(Grade.class);

        Date date = mock(Date.class);
        StudentID studentID = mock(StudentID.class);
        CourseEditionID courseEditionID = mock(CourseEditionID.class);


        when(studentGradeService.isDateGradeInRangeWithSchoolYear(courseEditionID, date))
                .thenReturn(true);
        when(studentGradeService.hasStudentAlreadyGradeAtThisCourseEdition(studentID, courseEditionID))
                .thenReturn(false);


        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(studentGradeService);

        // Act
        StudentGrade result = factory.newGradeStudent(grade, date, studentID, courseEditionID);

        // Assert
        assertNotNull(result, "Factory should return a StudentGrade when inputs are valid");
        assertEquals(grade, result.get_grade());
        assertEquals(date, result.get_date());
        assertEquals(studentID, result.get_studentID());
        assertEquals(courseEditionID, result.get_courseEditionID());

    }


//    @Test
//    public void shouldNotCreateConstructorWhenDateBeforeBeginningOfSchoolYear() throws Exception {
//        // Arrange
//        ICourseEditionRepository courseEditionRepository    = mock(CourseEditionRepositoryImpl.class);
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository      schoolYearRepository     = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//
//
//        CourseEditionID    courseEditionID    = mock(CourseEditionID.class);
//        CourseEdition      courseEdition      = mock(CourseEdition.class);
//        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
//        ProgrammeEdition   programmeEdition   = mock(ProgrammeEdition.class);
//        SchoolYearID       schoolYearID       = mock(SchoolYearID.class);
//        SchoolYear         schoolYear         = mock(SchoolYear.class);
//
//
//        when(courseEditionRepository.ofIdentity(courseEditionID))
//                .thenReturn(Optional.of(courseEdition));
//
//        when(courseEdition.getProgrammeEditionID())
//                .thenReturn(programmeEditionID);
//
//        when(programmeEditionRepository.ofIdentity(programmeEditionID))
//                .thenReturn(Optional.of(programmeEdition));
//        when(programmeEdition.findSchoolYearIDInProgrammeEdition())
//                .thenReturn(schoolYearID);
//
//        when(schoolYearRepository.ofIdentity(schoolYearID))
//                .thenReturn(Optional.of(schoolYear));
//
//        Date start     = mock(Date.class);
//        Date end       = mock(Date.class);
//        LocalDate sd   = mock(LocalDate.class);
//        LocalDate ed   = mock(LocalDate.class);
//
//        when(schoolYear.getStartDate()).thenReturn(start);
//        when(schoolYear.getEndDate()).thenReturn(end);
//        when(start.getLocalDate()).thenReturn(sd);
//        when(end.getLocalDate()).thenReturn(ed);
//
//        Date   gradeDate = mock(Date.class);
//        LocalDate gd     = mock(LocalDate.class);
//        when(gradeDate.getLocalDate()).thenReturn(gd);
//        when(gd.isBefore(sd)).thenReturn(true);
//        when(gd.isAfter(ed)).thenReturn(false);
//
//
//        StudentID student = mock(StudentID.class);
//        Grade     grade   = mock(Grade.class);
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
//
//        // Act & Assert
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, gradeDate, student, courseEditionID));
//        assertEquals("Date is out of Range", ex.getMessage());
//    }
//
//    @Test
//    public void shouldNotCreateConstructorWhenDateAfterEndOfSchoolYear() throws Exception {
//        // Arrange
//        ICourseEditionRepository     courseEditionRepository    = mock(CourseEditionRepositoryImpl.class);
//        IProgrammeEditionRepository  programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository        schoolYearRepository       = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//
//
//        CourseEditionID courseEditionID = mock(CourseEditionID.class);
//        CourseEdition courseEdition     = mock(CourseEdition.class);
//        when(courseEditionRepository.ofIdentity(courseEditionID))
//                .thenReturn(Optional.of(courseEdition));
//
//        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
//        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
//
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//        when(programmeEditionRepository.ofIdentity(programmeEditionID))
//                .thenReturn(Optional.of(programmeEdition));
//
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        when(programmeEdition.findSchoolYearIDInProgrammeEdition())
//                .thenReturn(schoolYearID);
//
//        SchoolYear schoolYear = mock(SchoolYear.class);
//        when(schoolYearRepository.ofIdentity(schoolYearID))
//                .thenReturn(Optional.of(schoolYear));
//
//        Date      start     = mock(Date.class);
//        Date      end       = mock(Date.class);
//        LocalDate sd        = mock(LocalDate.class);
//        LocalDate ed        = mock(LocalDate.class);
//        when(schoolYear.getStartDate()).thenReturn(start);
//        when(schoolYear.getEndDate()).thenReturn(end);
//        when(start.getLocalDate()).thenReturn(sd);
//        when(end.getLocalDate()).thenReturn(ed);
//
//        Date      gradeDate = mock(Date.class);
//        LocalDate gd        = mock(LocalDate.class);
//        when(gradeDate.getLocalDate()).thenReturn(gd);
//        when(gd.isBefore(sd)).thenReturn(false);
//        when(gd.isAfter(ed)).thenReturn(true);
//
//        StudentID student = mock(StudentID.class);
//        Grade     grade   = mock(Grade.class);
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        StudentGradeFactoryImpl factory =
//                new StudentGradeFactoryImpl(
//                        courseEditionRepository,
//                        programmeEditionRepository,
//                        schoolYearRepository,
//                        studentGradeRepository
//                );
//
//        // Act & Assert
//        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, gradeDate, student, courseEditionID));
//        assertEquals("Date is out of Range", ex.getMessage());
//    }
//
//
//    @Test
//    void shouldThrowExceptionWhenGradeIsNull() {
//        // Arrange
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
//        StudentID student = mock(StudentID.class);
//        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
//        Grade grade = mock(Grade.class);
//        Date dateDouble = mock(Date.class);
//
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                factory.newGradeStudent(null, dateDouble, student, courseEditionID1Double));
//        assertEquals("Grade cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenDateIsNull() {
//        // Arrange
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
//        StudentID student = mock(StudentID.class);
//        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
//        Grade grade = mock(Grade.class);
//
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                factory.newGradeStudent(grade, null, student, courseEditionID1Double));
//        assertEquals("Date cannot be null or empty!", exception.getMessage());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenStudentIsNull() {
//        // Arrange
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
//        CourseEditionID courseEditionID1Double = mock(CourseEditionID.class);
//        Grade grade = mock(Grade.class);
//        Date dateDouble = mock(Date.class);
//
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                factory.newGradeStudent(grade, dateDouble,null, courseEditionID1Double));
//        assertEquals("Student cannot be null", exception.getMessage());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenCourseEditionIsNull() {
//        // Arrange
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ICourseEditionRepository courseEditionRepository = mock(CourseEditionRepositoryImpl.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository,programmeEditionRepository,schoolYearRepository,studentGradeRepository);
//        StudentID student = mock(StudentID.class);
//        Date dateDouble = mock(Date.class);
//        Grade grade = mock(Grade.class);
//
//        when(grade.knowGrade()).thenReturn(10.0);
//
//        // Act & Assert
//        Exception exception = assertThrows(IllegalArgumentException.class, () ->
//                factory.newGradeStudent(grade, dateDouble,student, null));
//        assertEquals("Course Edition cannot be null", exception.getMessage());
//    }
//
//    @Test
//    public void shouldReturnTrueWhenStudentHasExistingGradeForCourseEdition() throws Exception {
//        // Arrange
//        ICourseEditionRepository      courseEditionRepo     = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository   programmeEditionRepo  = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository         schoolYearRepo        = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository       studentGradeRepo      = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepo, programmeEditionRepo, schoolYearRepo, studentGradeRepo);
//
//        StudentID       aluno       = mock(StudentID.class);
//        CourseEditionID edicao      = mock(CourseEditionID.class);
//
//        StudentGrade notaExistente = mock(StudentGrade.class);
//        when(notaExistente.hasThisStudentID(aluno)).thenReturn(true);
//        when(notaExistente.hasThisCourseEditionID(edicao)).thenReturn(true);
//
//        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(notaExistente));
//
//        // Act
//        boolean resultado = factory.hasStudentAlreadyGradeAtThisCourseEdition(aluno, edicao);
//
//        // Assert
//        assertTrue(resultado);
//
//    }
//
//    @Test
//    public void shouldReturnFalseWhenStudentHasExistingGradeForCourseEdition() throws Exception {
//        // Arrange
//        ICourseEditionRepository      courseEditionRepo     = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository   programmeEditionRepo  = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository         schoolYearRepo        = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository       studentGradeRepo      = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepo, programmeEditionRepo, schoolYearRepo, studentGradeRepo);
//
//        StudentID       aluno       = mock(StudentID.class);
//        CourseEditionID edicao      = mock(CourseEditionID.class);
//
//        StudentGrade notaExistente = mock(StudentGrade.class);
//        when(notaExistente.hasThisStudentID(aluno)).thenReturn(true);
//        when(notaExistente.hasThisCourseEditionID(edicao)).thenReturn(false);
//
//        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(notaExistente));
//
//        // Act
//        boolean resultado = factory.hasStudentAlreadyGradeAtThisCourseEdition(aluno, edicao);
//
//        // Assert
//        assertFalse(resultado);
//
//    }
//
//    @Test
//    public void shouldReturnFalseWhenStudentHasExistingGradeForCourseEdition_() throws Exception {
//        // Arrange
//        ICourseEditionRepository      courseEditionRepo     = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository   programmeEditionRepo  = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository         schoolYearRepo        = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository       studentGradeRepo      = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepo, programmeEditionRepo, schoolYearRepo, studentGradeRepo);
//
//        StudentID       aluno       = mock(StudentID.class);
//        CourseEditionID edicao      = mock(CourseEditionID.class);
//
//        StudentGrade notaExistente = mock(StudentGrade.class);
//        when(notaExistente.hasThisStudentID(aluno)).thenReturn(false);
//        when(notaExistente.hasThisCourseEditionID(edicao)).thenReturn(true);
//
//        when(studentGradeRepo.findAll()).thenReturn(Arrays.asList(notaExistente));
//
//        // Act
//        boolean resultado = factory.hasStudentAlreadyGradeAtThisCourseEdition(aluno, edicao);
//
//        // Assert
//        assertFalse(resultado);
//
//    }
//
//    @Test
//    void shouldReturnStudentGradeFromDataModel() throws Exception{
//
//        //arrange
//        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository iProgrammeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository iSchoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository iStudentGradeRepository = mock(IStudentGradeRepository.class);
//
//        StudentGradeFactoryImpl studentGradeFactory = new StudentGradeFactoryImpl(iCourseEditionRepository,iProgrammeEditionRepository,iSchoolYearRepository,iStudentGradeRepository);
//
//        Grade grade = mock(Grade.class);
//        Date date = mock(Date.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEditionID courseEditionID = mock(CourseEditionID.class);
//        StudentGradeID studentGradeID = mock(StudentGradeID.class);
//
//        //act
//        StudentGrade result = studentGradeFactory.newGradeStudentFromDataModel(grade,date,studentID,courseEditionID,studentGradeID);
//
//        //assert
//        assertNotNull(result);
//    }
//    @Test
//    void shouldThrowExceptionWhenStudentHasAlreadyGradeAndDateIsInRange() throws Exception {
//
//        // arrange
//        ICourseEditionRepository courseEditionRepository = mock(ICourseEditionRepository.class);
//        IProgrammeEditionRepository programmeEditionRepository = mock(IProgrammeEditionRepository.class);
//        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
//        IStudentGradeRepository studentGradeRepository = mock(IStudentGradeRepository.class);
//        StudentGradeFactoryImpl factory = new StudentGradeFactoryImpl(courseEditionRepository, programmeEditionRepository, schoolYearRepository, studentGradeRepository);
//        Grade grade = mock(Grade.class);
//        Date date = mock(Date.class);
//        StudentID studentID = mock(StudentID.class);
//        CourseEditionID courseEditionID = mock(CourseEditionID.class);
//        LocalDate localDate = LocalDate.of(2024, 4, 30);
//        when(date.getLocalDate()).thenReturn(localDate);
//        SchoolYear schoolYear = mock(SchoolYear.class);
//        when(schoolYear.getStartDate()).thenReturn(new Date(LocalDate.of(2024, 1, 1)));
//        when(schoolYear.getEndDate()).thenReturn(new Date(LocalDate.of(2024, 12, 31)));
//        CourseEdition courseEdition = mock(CourseEdition.class);
//        ProgrammeEdition programmeEdition = mock(ProgrammeEdition.class);
//        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        when(courseEditionRepository.ofIdentity(courseEditionID)).thenReturn(Optional.of(courseEdition));
//        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
//        when(programmeEditionRepository.ofIdentity(programmeEditionID)).thenReturn(Optional.of(programmeEdition));
//        when(programmeEdition.findSchoolYearIDInProgrammeEdition()).thenReturn(schoolYearID);
//        when(schoolYearRepository.ofIdentity(schoolYearID)).thenReturn(Optional.of(schoolYear));
//        StudentGrade existingGrade = mock(StudentGrade.class);
//        when(existingGrade.hasThisStudentID(studentID)).thenReturn(true);
//        when(existingGrade.hasThisCourseEditionID(courseEditionID)).thenReturn(true);
//        when(studentGradeRepository.findAll()).thenReturn(Arrays.asList(existingGrade));
//
//        // assert
//        assertThrows(IllegalArgumentException.class, () -> factory.newGradeStudent(grade, date, studentID, courseEditionID));
//    }
}

