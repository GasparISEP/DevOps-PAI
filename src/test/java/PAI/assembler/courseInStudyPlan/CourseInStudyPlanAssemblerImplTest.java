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
import java.util.UUID;

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
        assertEquals("Engineering", command.programmeName().getNameWithNumbersAndSpecialChars());
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
        UUID mockUUID = UUID.randomUUID();
        when(serviceDTO.generatedID()).thenReturn(mockUUID);

        when(serviceDTO.semester()).thenReturn(1);
        when(serviceDTO.curricularYear()).thenReturn(2);
        when(serviceDTO.courseAcronym()).thenReturn("DSOFT");
        when(serviceDTO.courseName()).thenReturn("Desenvolvimento de Software");
        when(serviceDTO.programmeAcronym()).thenReturn("LEI");
        when(serviceDTO.studyPlanDate()).thenReturn("2023-09-01");
        when(serviceDTO.duration()).thenReturn(1);
        when(serviceDTO.credits()).thenReturn(6.0);


        CourseInStudyPlanAssemblerImpl assembler = new CourseInStudyPlanAssemblerImpl();

        // Act
        CourseInStudyPlanResponseDTO response = assembler.toDTO(serviceDTO);

        // Assert
        assertNotNull(response);
        assertInstanceOf(CourseInStudyPlanResponseDTO.class, response);
        assertEquals("DSOFT", response.courseAcronym());
        assertEquals("LEI", response.programmeAcronym());
        assertEquals(6.0, response.credits());
        assertEquals(mockUUID, response.generatedID());
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
    void testToDTOfromDomain() {
        // Arrange
        CourseInStudyPlan course = mock(CourseInStudyPlan.class);
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        DurationCourseInCurricularYear duration = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts credits = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        NameWithNumbersAndSpecialChars programmeName = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        PAI.VOs.Date date = mock(PAI.VOs.Date.class);
        java.time.LocalDate localDate = java.time.LocalDate.of(2023, 9, 1);

        when(course.getSemester()).thenReturn(semester);
        when(semester.toInt()).thenReturn(1);
        when(course.getCurricularYear()).thenReturn(curricularYear);
        when(curricularYear.toInt()).thenReturn(2);
        when(course.getCourseID()).thenReturn(courseID);
        when(courseID.getCourseAcronymValue()).thenReturn("DSOFT");
        when(courseID.getCourseNameValue()).thenReturn("Desenvolvimento de Software");
        when(course.getStudyplanID()).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getProgrammeAcronym()).thenReturn("LEI");
        when(studyPlanID.getDate()).thenReturn(date);
        when(date.getLocalDate()).thenReturn(localDate);
        when(course.getDurationOfCourse()).thenReturn(duration);
        when(duration.getDuration()).thenReturn(1);
        when(course.getQuantityOfCreditsEcts()).thenReturn(credits);
        when(credits.toDouble()).thenReturn(6.0);
        when(course.getGeneratedID()).thenReturn(generatedID);
        when(generatedID.getId()).thenReturn(java.util.UUID.randomUUID());
        when(course.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getAcronym()).thenReturn(acronym);
        when(acronym.getAcronym()).thenReturn("CSD");

        CourseInStudyPlanAssemblerImpl assembler = new CourseInStudyPlanAssemblerImpl();

        // Act
        CourseInStudyPlanResponseDTO response = assembler.toDTOfromDomain(course);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.semester());
        assertEquals(2, response.curricularYear());
        assertEquals("DSOFT", response.courseAcronym());
        assertEquals("Desenvolvimento de Software", response.courseName());
        assertEquals("LEI", response.programmeAcronym());
        assertEquals("2023-09-01", response.studyPlanDate());
        assertEquals(1, response.duration());
        assertEquals(6.0, response.credits());
        assertEquals("CSD", response.programmeID());
    }

    @Test
    void ToDTOfromDomain_shouldThrowException_whenCourseInStudyPlanIsNull() {
        // Arrange
        CourseInStudyPlanAssemblerImpl assembler = new CourseInStudyPlanAssemblerImpl();

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> assembler.toDTOfromDomain(null));
        assertEquals("CourseInStudyPlan entity cannot be null", exception.getMessage());
    }
}
