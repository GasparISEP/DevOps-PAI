package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.assembler.accessMethod.AccessMethodAssemblerImpl;
import PAI.assembler.teacher.TeacherAssemblerImpl;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionAssemblerImplTest {

    private final CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

    @Test
    void toCommand_ShouldMapCorrectly() {
        // Arrange
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeName()).thenReturn("Software Engineering");
        when(dto.programmeAcronym()).thenReturn("SE");
        when(dto.schoolYearID()).thenReturn(UUID.randomUUID());
        when(dto.courseAcronym()).thenReturn("CS101");
        when(dto.courseName()).thenReturn("Intro to Programming");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.of(2023, 9, 1));

        // Act
        CreateCourseEditionCommand command = assembler.toCommand(dto);

        // Assert
        assertEquals(dto.programmeName(), command.programmeName());
        assertEquals(dto.programmeAcronym(), command.programmeAcronym());
        assertEquals(dto.schoolYearID(), command.schoolYearID());
        assertEquals(dto.courseAcronym(), command.courseAcronym());
        assertEquals(dto.courseName(), command.courseName());
        assertEquals(dto.studyPlanImplementationDate(), command.studyPlanImplementationDate());
    }

    @Test
    void shouldThrowExceptionWhenCourseEditionRequestDTOIsNull() {
        // Arrange
        CourseEditionAssemblerImpl courseEditionAssembler = new CourseEditionAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            courseEditionAssembler.toCommand(null);
        });
    }

    @Test
    void toResponseDTO_ShouldMapCorrectly() throws Exception {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

        CourseEdition courseEdition = mock(CourseEdition.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        UUID uuid = UUID.randomUUID();
        String programmeName = "Software Engineering";
        String programmeAcronym = "SE";
        String courseAcronym = "CS101";
        String courseName = "Intro to Programming";
        LocalDate studyPlanDate = LocalDate.of(2025, 5, 25);

        when(programmeID.getProgrammeName()).thenReturn(programmeName);
        when(programmeID.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(schoolYearID.getSchoolYearID()).thenReturn(uuid);
        when(studyPlanID.getLocalDate()).thenReturn(studyPlanDate);
        when(courseID.getCourseAcronymValue()).thenReturn(courseAcronym);
        when(courseID.getCourseNameValue()).thenReturn(courseName);

        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);

        // Act
        CourseEditionResponseDTO dto = assembler.toResponseDTO(courseEdition);

        // Assert
        assertEquals(courseEditionID.toString(), dto.courseEditionID());
        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(uuid, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanDate, dto.studyPlanImplementationDate());
    }

    @Test
    void shouldThrowExceptionCourseEditionIsNull() {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTO(null);
        });
    }
}