package PAI.assembler.courseEdition;
import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionAssemblerImplTest {

    private final CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

    private List<CourseEditionServiceResponseDTO> createInputList() {
        UUID courseEditionGeneratedUUID = UUID.randomUUID();
        UUID schoolYearUUID = UUID.randomUUID();

        CourseEditionServiceResponseDTO serviceDTO = new CourseEditionServiceResponseDTO(
                courseEditionGeneratedUUID,
                "ME",
                schoolYearUUID,
                "M1",
                "Math I",
                LocalDate.of(2023, 9, 1),
                "1",
                Long.toString(1001L)
        );
        return List.of(serviceDTO);
    }

    @Test
    void toCommand_shouldConvertRequestDTOToCommand() {
        // Arrange
        CourseEditionRequestDTO dto = mock(CourseEditionRequestDTO.class);
        when(dto.programmeAcronym()).thenReturn("ENG");
        when(dto.schoolYearID()).thenReturn(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        when(dto.courseAcronym()).thenReturn("CS101");
        when(dto.courseName()).thenReturn("Computer Science");
        when(dto.studyPlanImplementationDate()).thenReturn(LocalDate.of(2024, 5, 1));

        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();

        // Act
        CreateCourseEditionCommand command = assembler.toCommand(dto);

        // Assert
        assertNotNull(command);

        assertEquals("ENG", command.programmeAcronym().getAcronym());
        assertEquals(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), command.schoolYearID().getSchoolYearID());
        assertEquals("CS101", command.courseAcronym().getAcronym());
        assertEquals("Computer Science", command.courseName().getName());
        assertEquals(LocalDate.of(2024, 5, 1), command.studyPlanImplementationDate().getLocalDate());
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
        ProgrammeID programmeIDInStudyPlan = mock(ProgrammeID.class);

        UUID uuid = UUID.randomUUID();
        String programmeAcronym = "SE";
        String courseAcronym = "CS101";
        String courseName = "Intro to Programming";
        String teacher = "AMG";



        LocalDate studyPlanDate = LocalDate.of(2025, 5, 25);

        when(programmeID.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(schoolYearID.getSchoolYearID()).thenReturn(uuid);
        when(studyPlanID.getLocalDate()).thenReturn(studyPlanDate);
        when(courseID.getCourseAcronymValue()).thenReturn(courseAcronym);
        when(courseID.getCourseNameValue()).thenReturn(courseName);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeIDInStudyPlan);
        when(programmeIDInStudyPlan.getProgrammeAcronym()).thenReturn(programmeAcronym);

        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);

        String expectedFormattedID = URLEncoder.encode(
                programmeAcronym + "-" +
                        uuid + "_" +
                        courseAcronym + "-" +
                        courseName + "-" +
                        programmeAcronym + "-" +
                        studyPlanDate,
                StandardCharsets.UTF_8
        );

        CourseEditionServiceResponseDTO serviceResponseDTO = new CourseEditionServiceResponseDTO(
                UUID.randomUUID(),
                programmeAcronym,
                uuid,
                courseAcronym,
                courseName,
                studyPlanDate,
                expectedFormattedID,
                teacher

        );

        // Act
        CourseEditionResponseDTO dto = assembler.toResponseDTO(serviceResponseDTO);

        // Assert
        assertEquals(expectedFormattedID, dto.courseEditionID());
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

        List<CourseEditionID> emptyList = Collections.emptyList();
        // Act
        List<CourseEditionResponseIDDTO> dtoList = assembler.toResponseIDDTOList(emptyList);

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
        List<CourseEditionResponseIDDTO> result = assembler.toResponseIDDTOList(List.of(courseEditionID));

        // Assert
        assertEquals(1, result.size());
        CourseEditionResponseIDDTO dto = result.get(0);
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
        when(dto.programmeAcronym()).thenReturn("");
        when(dto.courseAcronym()).thenReturn("");
        when(dto.courseName()).thenReturn("");
        when(dto.studyPlanImplementationDate()).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> assembler.toCourseInStudyPlanID(dto));
    }
    @Test
    void fromDtoToCourseEditionGeneratedID_ShouldThrowException_WhenInvalidSelectedCourseEditionGeneratedIdDTO() {
        // Arrange
        SelectedCourseEditionGeneratedIdDTO dto = mock(SelectedCourseEditionGeneratedIdDTO.class);
        // Act & Assert
        assertThrows(Exception.class, () -> assembler.fromDtoToCourseEditionGeneratedID(dto));
    }
    @Test
    void fromDtoToCourseEditionGeneratedID_ShouldReturnCourseEditionGeneratedID() {
        // Arrange
        UUID generatedID = UUID.randomUUID();
        SelectedCourseEditionGeneratedIdDTO dto = mock(SelectedCourseEditionGeneratedIdDTO.class);

        when(dto.courseEditionID()).thenReturn(generatedID);
        // Act
        CourseEditionGeneratedID result = assembler.fromDtoToCourseEditionGeneratedID(dto);
        //assert
        assertNotNull(result);
        assertEquals(result.getCourseEditionGeneratedID(), generatedID);
    }
    @Test
    void toResponseDTOList_ShouldThrowException_WhenInvalidCourseEditionRequestDTO () {
        // Arrange

        List<CourseEditionID> courseEditionIDs = null;
        // Act & Assert
        assertThrows(Exception.class, () -> assembler.toResponseIDDTOList(courseEditionIDs));
    }
    @Test
    void toProgrammeEditionID_shouldThrowException_whenNull() {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> assembler.toProgrammeEditionID(null));
        assertEquals("CourseEditionRequestDTO cannot be null", exception.getMessage());
    }
    @Test
    void toCourseInStudyPlanID_shouldThrowException_whenNull() {
        // Arrange
        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();
        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> assembler.toCourseInStudyPlanID(null));

        assertEquals("CourseEditionRequestDTO cannot be null", exception.getMessage());
    }

    @Test
    void toResponseIDDTO_ShouldMapCorrectly() {
        // Arrange
        UUID schoolYearUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String programmeAcronym = "ENG";
        String courseAcronym = "CS101";
        String courseName = "Intro to Computer Science";
        LocalDate studyPlanDate = LocalDate.of(2025, 1, 15);
        String generatedID = "ENG-123e4567-e89b-12d3-a456-426614174000_CS101-Intro to Computer Science-ENG-2025-01-15";
        String teacherAcronym = "AAA";

        CourseEditionServiceResponseDTO serviceDTO = new CourseEditionServiceResponseDTO(
                UUID.randomUUID(),
                programmeAcronym,
                schoolYearUUID,
                courseAcronym,
                courseName,
                studyPlanDate,
                generatedID,
                teacherAcronym
        );

        // Act
        CourseEditionResponseIDDTO responseDTO = assembler.toResponseIDDTO(serviceDTO);

        // Assert
        assertNotNull(responseDTO);
        assertEquals(programmeAcronym, responseDTO.programmeAcronym());
        assertEquals(schoolYearUUID, responseDTO.schoolYearID());
        assertEquals(courseAcronym, responseDTO.courseAcronym());
        assertEquals(courseName, responseDTO.courseName());
        assertEquals(studyPlanDate, responseDTO.studyPlanImplementationDate());
        assertEquals(generatedID, responseDTO.courseEditionID());
    }

    @Test
    void toResponseIDDTO_ShouldThrowExceptionWhenInputIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseIDDTO((CourseEditionServiceResponseDTO) null);
        });
    }

    @Test
    void shouldReturnListWithSameSize_whenCallingToCourseEditionResponseDTOList() {
        List<CourseEditionServiceResponseDTO> inputList = createInputList();
        List<CourseEditionResponseDTO> result = assembler.toCourseEditionResponseDTOList(inputList);

        assertEquals(inputList.size(), result.size());
    }

    @Test
    void shouldMapFieldsCorrectly_whenCallingToCourseEditionResponseDTOList() {
        List<CourseEditionServiceResponseDTO> inputList = createInputList();
        List<CourseEditionResponseDTO> result = assembler.toCourseEditionResponseDTOList(inputList);
        CourseEditionResponseDTO dto = result.get(0);
        CourseEditionServiceResponseDTO serviceDTO = inputList.get(0);

        assertEquals(serviceDTO.courseEditionGeneratedID(), dto.courseEditionGeneratedID());
        assertEquals(serviceDTO.programmeAcronym(), dto.programmeAcronym());
        assertEquals(serviceDTO.schoolYearID(), dto.schoolYearID());
        assertEquals(serviceDTO.courseAcronym(), dto.courseAcronym());
        assertEquals(serviceDTO.courseName(), dto.courseName());
        assertEquals(serviceDTO.studyPlanImplementationDate(), dto.studyPlanImplementationDate());
        assertEquals(serviceDTO.courseEditionID(), dto.courseEditionID());
        assertEquals(serviceDTO.teacherID(), dto.teacherID());
    }

    @Test
    void shouldReturnEmptyList_whenCallingToCourseEditionResponseDTOListWithEmptyInput() {
        List<CourseEditionResponseDTO> result = assembler.toCourseEditionResponseDTOList(List.of());

        assertTrue(result.isEmpty());
    }
}