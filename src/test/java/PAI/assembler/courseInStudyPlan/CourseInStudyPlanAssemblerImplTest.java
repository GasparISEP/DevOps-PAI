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
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        when(serviceDTO.semester()).thenReturn(1);
        when(serviceDTO.curricularYear()).thenReturn(2);
        when(serviceDTO.courseAcronym()).thenReturn("DSOFT");
        when(serviceDTO.courseName()).thenReturn("Desenvolvimento de Software");
        when(serviceDTO.programmeAcronym()).thenReturn("LEI");
        when(serviceDTO.studyPlanDate()).thenReturn("2023-09-01");
        when(serviceDTO.duration()).thenReturn(1);
        when(serviceDTO.credits()).thenReturn(6.0);

        when(serviceDTO.generatedID()).thenReturn(generatedID.getId());

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
}
