package PAI.assembler.courseEdition;
import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionRequestDTO;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import PAI.dto.courseEdition.CreateCourseEditionCommand;
import PAI.dto.courseEdition.SelectedCourseEditionIdDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
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

    @Test
    void toResponseDTOList_ShouldMapCorrectly() {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

        // Act
        List<CourseEditionResponseDTO> dtoList = assembler.toResponseDTOList(Collections.emptyList());   

        // Assert
        assertEquals(0, dtoList.size());
    }

    @Test
    void toResponseDTOList_WithOneCourseEdition_ShouldMapCorrectly() throws Exception {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();
        CourseEditionID courseEditionID = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
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

        when(courseEditionID.toString()).thenReturn("CE-1");
        when(courseEditionID.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEditionID.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);

        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
        when(programmeEditionID.getSchoolYearID()).thenReturn(schoolYearID);

        when(programmeID.getProgrammeAcronym()).thenReturn(programmeAcronym);
        
        when(schoolYearID.getSchoolYearID()).thenReturn(uuid);

        when(courseInStudyPlanID.getCourseID()).thenReturn(courseID);
        when(courseInStudyPlanID.getStudyPlanID()).thenReturn(studyPlanID);

        when(courseID.getCourseNameValue()).thenReturn(courseName);
        when(courseID.getCourseAcronymValue()).thenReturn(courseAcronym);
        
        when(studyPlanID.getLocalDate()).thenReturn(studyPlanDate);

        // Act
        List<CourseEditionResponseDTO> result = assembler.toResponseDTOList(List.of(courseEditionID));

        // Assert
        assertEquals(1, result.size());
        CourseEditionResponseDTO dto = result.get(0);
        assertEquals("CE-1", dto.courseEditionID());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(uuid, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(studyPlanDate, dto.studyPlanImplementationDate());
    }

    @Test
    void shouldReturnCorrectTeacherID() {
        // arrange
        String teacherAcronym = "AMG";
        // act
        TeacherID teacherID = assembler.createTeacherID(teacherAcronym);
        // assert
        assertNotNull(teacherID);
    }

    @Test
    void fromDtoToCourseEditionIdShouldReturnValidCourseEditionID() throws Exception {
        // arrange
        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                LocalDate.of(2023, 9, 1)
        );
        // act
        CourseEditionID courseEditionID = assembler.fromDtoToCourseEditionID(dto);

        // assert
        assertNotNull(courseEditionID);
        assertNotNull(courseEditionID.getProgrammeEditionID());
        assertNotNull(courseEditionID.getCourseInStudyPlanID());

        assertEquals("SE", courseEditionID.getProgrammeEditionID().getProgrammeID().getProgrammeAcronym());
        assertEquals("CS101", courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseAcronymValue());
        assertEquals("Intro to Programming", courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue());
    }

    @Test
    void fromDtoToCourseEditionIDShouldThrowExceptionWhenFieldsAreInvalid() {
        SelectedCourseEditionIdDTO dto = new SelectedCourseEditionIdDTO(
                "", "", null, "", "", null
        );
        // act & assert
        assertThrows(Exception.class, () -> assembler.fromDtoToCourseEditionID(dto));
    }

    @Test
    void toProgrammeEditionID_ShouldMapCorrectly() throws Exception {
        // Arrange
        UUID schoolYearUUID = UUID.randomUUID();
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeName()).thenReturn("Software Engineering");
        when(dto.programmeAcronym()).thenReturn("SE");
        when(dto.schoolYearID()).thenReturn(schoolYearUUID);

        // Act
        ProgrammeEditionID result = assembler.toProgrammeEditionID(dto);

        // Assert
        assertNotNull(result);
        assertEquals("SE", result.getProgrammeID().getProgrammeAcronym());
        assertEquals(schoolYearUUID, result.getSchoolYearID().getSchoolYearID());
    }

    @Test
    void toProgrammeEditionID_ShouldThrowException_WhenInvalidData() {
        // Arrange
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeName()).thenReturn("");
        when(dto.programmeAcronym()).thenReturn("");
        when(dto.schoolYearID()).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> assembler.toProgrammeEditionID(dto));
    }

    @Test
    void toCourseInStudyPlanID_ShouldMapCorrectly() throws Exception {
        // Arrange
        LocalDate implementationDate = LocalDate.of(2024, 1, 1);
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeName()).thenReturn("Software Engineering");
        when(dto.programmeAcronym()).thenReturn("SE");
        when(dto.courseAcronym()).thenReturn("CS101");
        when(dto.courseName()).thenReturn("Intro to Programming");
        when(dto.studyPlanImplementationDate()).thenReturn(implementationDate);

        // Act
        CourseInStudyPlanID result = assembler.toCourseInStudyPlanID(dto);

        // Assert
        assertNotNull(result);
        assertEquals("CS101", result.getCourseID().getCourseAcronymValue());
        assertEquals("Intro to Programming", result.getCourseID().getCourseNameValue());
        assertEquals("SE", result.getStudyPlanID().getProgrammeID().getProgrammeAcronym());
        assertEquals(implementationDate, result.getStudyPlanID().getLocalDate());
    }

    @Test
    void toCourseInStudyPlanID_ShouldThrowException_WhenInvalidData() {
        // Arrange
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeName()).thenReturn("");
        when(dto.programmeAcronym()).thenReturn("");
        when(dto.courseAcronym()).thenReturn("");
        when(dto.courseName()).thenReturn("");
        when(dto.studyPlanImplementationDate()).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> assembler.toCourseInStudyPlanID(dto));
    }
}