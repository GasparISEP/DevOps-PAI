package PAI.assembler.courseInStudyPlan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.VOs.*;

class CourseInStudyPlanAssemblerImplTest {

    private CourseInStudyPlanAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CourseInStudyPlanAssemblerImpl();
    }

    @Test
    void toCommand_shouldConvertRequestDTOToCommand() throws Exception {
        // Arrange
        CourseInStudyPlanRequestDTO request = mock(CourseInStudyPlanRequestDTO.class);
        when(request.semester()).thenReturn(1);
        when(request.curricularYear()).thenReturn(2);
        when(request.courseAcronym()).thenReturn("CS101");
        when(request.courseName()).thenReturn("Computer Science");
        when(request.programmeAcronym()).thenReturn("ENG");
        when(request.programmeName()).thenReturn("Engineering");
        when(request.studyPlanDate()).thenReturn("01-05-2024");
        when(request.duration()).thenReturn(2);
        when(request.credits()).thenReturn(6.0);

        // Act
        CourseInStudyPlanCommand command = assembler.toCommand(request);

        // Assert
        assertNotNull(command);
        assertEquals(1, command.semester().toInt());
        assertEquals(2, command.curricularYear().toInt());
        assertEquals("CS101", command.courseAcronym().getAcronym());
        assertEquals("Computer Science", command.courseName().getName());
        assertEquals("ENG", command.programmeAcronym().getAcronym());
        assertEquals("Engineering", command.programmeName().getnameWithNumbersAndSpecialChars());
        assertEquals("01-05-2024", command.studyPlanDate().toString());
        assertEquals(2, command.duration().toInt());
        assertEquals(6.0, command.credits().toDouble());
    }

    @Test
    void toCommand_shouldThrowExceptionWhenRequestIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> assembler.toCommand(null));
    }

    @Test
    void toDTO_shouldReturnCorrectDTOFromDomainObject() throws Exception {
        // Arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(2);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);

        Acronym programmeAcronym = new Acronym("ENG");
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Engineering");
        ProgrammeID programmeID = new ProgrammeID(programmeName, programmeAcronym);

        Date studyPlanDate = new Date("01-05-2026");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(2);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(6.0);

        CourseInStudyPlan course = new CourseInStudyPlan(
                semester,
                curricularYear,
                courseID,
                studyPlanID,
                courseInStudyPlanID,
                duration,
                credits
        );

        // Act
        CourseInStudyPlanResponseDTO dto = assembler.toDTO(course);

        // Assert
        assertEquals(1, dto.semester());
        assertEquals(2, dto.curricularYear());
        assertEquals("CS101", dto.courseAcronym());
        assertEquals("Computer Science", dto.courseName());
        assertEquals("ENG", dto.programmeAcronym());
        assertEquals("Engineering", dto.programmeName());
        assertEquals(studyPlanDate.toString(), dto.studyPlanDate());
        assertEquals(2, dto.duration());
        assertEquals(6.0, dto.credits());
        assertEquals(courseID, dto.courseID());
        assertEquals(studyPlanID, dto.studyPlanID());
    }

    @Test
    void toDTO_shouldThrowExceptionWhenCourseInStudyPlanIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> assembler.toDTO(null));
    }
}
