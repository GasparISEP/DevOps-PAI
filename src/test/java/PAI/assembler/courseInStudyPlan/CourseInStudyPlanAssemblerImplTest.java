package PAI.assembler.courseInStudyPlan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanResponseDTO;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanRequestDTO;
import PAI.VOs.*;

import java.time.LocalDate;

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
    void toDTO_shouldReturnDTO_whenServiceDTOIsValid() {
        // Arrange
        CourseInStudyPlanServiceDTO serviceDTO = mock(CourseInStudyPlanServiceDTO.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(serviceDTO.semester()).thenReturn(1);
        when(serviceDTO.curricularYear()).thenReturn(2);
        when(serviceDTO.courseAcronym()).thenReturn("DSOFT");
        when(serviceDTO.courseName()).thenReturn("Desenvolvimento de Software");
        when(serviceDTO.programmeAcronym()).thenReturn("LEI");
        when(serviceDTO.programmeName()).thenReturn("Engenharia Informática");
        when(serviceDTO.studyPlanDate()).thenReturn("2023-09-01");
        when(serviceDTO.duration()).thenReturn(1);
        when(serviceDTO.credits()).thenReturn(6.0);

        when(serviceDTO.courseId()).thenReturn(courseID);
        when(serviceDTO.studyPlanId()).thenReturn(studyPlanID);

        CourseInStudyPlanAssemblerImpl assembler = new CourseInStudyPlanAssemblerImpl();

        // Act
        CourseInStudyPlanResponseDTO response = assembler.toDTO(serviceDTO);

        // Assert
        assertNotNull(response);
        assertInstanceOf(CourseInStudyPlanResponseDTO.class, response);
        assertEquals("DSOFT", response.courseAcronym());
        assertEquals("LEI", response.programmeAcronym());
        assertEquals(6.0, response.credits());
    }

    @Test
    void toDTO_shouldThrowException_whenServiceDTOIsNull() {
        // Arrange
        CourseInStudyPlanAssemblerImpl assembler = new CourseInStudyPlanAssemblerImpl();

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> assembler.toDTO(null));
        assertEquals("Service DTO cannot be null", exception.getMessage());
    }

    @Test
    void toDTOFromEntity_shouldReturnCorrectResponseDTO() {
        // Arrange
        CourseInStudyPlan course = mock(CourseInStudyPlan.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        DurationCourseInCurricularYear duration = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts credits = mock(CourseQuantityCreditsEcts.class);
        PAI.VOs.Date date = mock(PAI.VOs.Date.class);

        when(course.getSemester()).thenReturn(semester);
        when(semester.toInt()).thenReturn(1);

        when(course.getCurricularYear()).thenReturn(curricularYear);
        when(curricularYear.toInt()).thenReturn(2);

        when(course.getCourseID()).thenReturn(courseID);
        when(courseID.getCourseAcronymValue()).thenReturn("CS101");
        when(courseID.getCourseNameValue()).thenReturn("Computer Science");

        when(course.getStudyplanID()).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getProgrammeAcronym()).thenReturn("ENG");
        when(programmeID.getProgrammeName()).thenReturn("Engineering");

        when(studyPlanID.getDate()).thenReturn(date);
        when(date.getLocalDate()).thenReturn(LocalDate.of(2025, 5, 27));

        when(course.getDurationOfCourse()).thenReturn(duration);
        when(duration.getDuration()).thenReturn(2);

        when(course.getQuantityOfCreditsEcts()).thenReturn(credits);
        when(credits.toDouble()).thenReturn(6.0);

        // Act
        CourseInStudyPlanResponseDTO dto = assembler.toDTOFromEntity(course);

        // Assert
        assertNotNull(dto);
        assertEquals(1, dto.semester());
        assertEquals(2, dto.curricularYear());
        assertEquals("CS101", dto.courseAcronym());
        assertEquals("Computer Science", dto.courseName());
        assertEquals("ENG", dto.programmeAcronym());
        assertEquals("Engineering", dto.programmeName());
        assertEquals("2025-05-27", dto.studyPlanDate());
        assertEquals(2, dto.duration());
        assertEquals(6.0, dto.credits());
        assertEquals(courseID, dto.courseId());
        assertEquals(studyPlanID, dto.studyPlanId());
    }
}
