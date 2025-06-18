package PAI.assembler.studentGrade;

import PAI.VOs.*;
import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;
import PAI.domain.repositoryInterfaces.courseEditionEnrolment.ICourseEditionEnrolmentRepository;
import PAI.dto.studentGrade.GradeAStudentCommand;
import PAI.dto.studentGrade.GradeAStudentRequestMinimalDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentGradeEnrolmentAssemblerImplTest {

    private ICourseEditionEnrolmentRepository enrolmentRepository;
    private StudentGradeEnrolmentAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        enrolmentRepository = mock(ICourseEditionEnrolmentRepository.class);
        assembler = new StudentGradeEnrolmentAssemblerImpl(enrolmentRepository);
    }

    @Test
    void testToCommand_SuccessfullyBuildsCommand() throws Exception {
        // Arrange
        int studentNumber = 1000001;
        double gradeValue = 17.0;
        UUID generatedID = UUID.randomUUID();

        ProgrammeID programmeID = new ProgrammeID(new Acronym("LEI"));
        CourseID courseID = new CourseID(new Acronym("M1234"), new Name("Engenharia de Software"));
        Date studyPlanDate = new Date("01-09-2023");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, new SchoolYearID(UUID.randomUUID()));
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        CourseEditionEnrolment enrolment = mock(CourseEditionEnrolment.class);
        when(enrolment.knowCourseEdition()).thenReturn(courseEditionID);
        when(enrolmentRepository.findByGeneratedID(new CourseEditionEnrolmentGeneratedID(generatedID)))
                .thenReturn(Optional.of(enrolment));

        GradeAStudentRequestMinimalDTO dto = new GradeAStudentRequestMinimalDTO(
                studentNumber,
                gradeValue,
                generatedID.toString()
        );

        // Act
        GradeAStudentCommand command = assembler.toCommand(dto);

        // Assert
        assertNotNull(command);
        assertEquals(studentNumber, command.studentID().getUniqueNumber());
        assertEquals(gradeValue, command.grade().knowGrade());
        assertEquals(courseEditionID, command.courseEditionID());
    }

    @Test
    void testToCommand_InvalidGrade_ThrowsException() {
        // Arrange
        GradeAStudentRequestMinimalDTO dto = new GradeAStudentRequestMinimalDTO(
                1000001,
                -5.0, // nota inválida
                UUID.randomUUID().toString()
        );

        // Act + Assert
        assertThrows(RuntimeException.class, () -> assembler.toCommand(dto));
    }

    @Test
    void testToCommand_EnrolmentNotFound_ThrowsException() {
        // Arrange
        UUID generatedID = UUID.randomUUID();
        GradeAStudentRequestMinimalDTO dto = new GradeAStudentRequestMinimalDTO(
                1000001,
                18.0,
                generatedID.toString()
        );

        when(enrolmentRepository.findByGeneratedID(any())).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> assembler.toCommand(dto));
    }
}
