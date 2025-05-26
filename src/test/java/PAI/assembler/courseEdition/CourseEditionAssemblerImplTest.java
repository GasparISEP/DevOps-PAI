package PAI.assembler.courseEdition;

import PAI.VOs.*;
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
        CourseEditionRequestDTO dto = new CourseEditionRequestDTO(
                "Software Engineering",
                "SE",
                UUID.randomUUID(),
                "CS101",
                "Intro to Programming",
                LocalDate.of(2023, 9, 1)
        );

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

//    @Test
//    void toResponseDTO_ShouldMapCorrectly_WithMocks() {
//        // Arrange
//        CourseEditionAssemblerImpl assembler = new CourseEditionAssemblerImpl();
//
//        CourseEdition courseEdition = mock(CourseEdition.class);
//        ProgrammeEditionID programmeEditionID = mock(ProgrammeEditionID.class);
//        ProgrammeID programmeID = mock(ProgrammeID.class);
//        SchoolYearID schoolYearID = mock(SchoolYearID.class);
//        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
//        CourseID courseID = mock(CourseID.class);
//        StudyPlanID studyPlanID = mock(StudyPlanID.class);
//
//        // Fake values
//        UUID uuid = UUID.randomUUID();
//        String programmeName = "Software Engineering";
//        String programmeAcronym = "SE";
//        String courseAcronym = "CS101";
//        String courseName = "Intro to Programming";
//        LocalDate date = LocalDate.of(2023, 9, 1);
//
//        // VOs
//        Acronym courseAcronymVO = mock(Acronym.class);
//        Name courseNameVO = mock(Name.class);
//
//        // Setup mocks
//        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);
//        when(courseEdition.identity()).thenReturn(courseEditionID);
//        when(courseEdition.identity().toString()).thenReturn(uuid.toString());
//
//        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
//        when(programmeEditionID.getProgrammeID()).thenReturn(programmeID);
//        when(programmeEditionID.getSchoolYearID()).thenReturn(schoolYearID);
//
//        when(programmeID.getProgrammeName()).thenReturn(programmeName);
//
//        when(programmeID.getProgrammeAcronym()).thenReturn(programmeAcronym);
//
//        when(schoolYearID.getSchoolYearID()).thenReturn(uuid);
//
//        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);
//        when(courseInStudyPlanID.getCourseID()).thenReturn(courseID);
//        when(courseInStudyPlanID.getStudyPlanID()).thenReturn(studyPlanID);
//
//        when(courseID.getAcronym()).thenReturn(courseAcronymVO);
//        when(courseAcronymVO.toString()).thenReturn(courseAcronym);
//
//        when(courseID.getName()).thenReturn(courseNameVO);
//        when(courseNameVO.toString()).thenReturn(courseName);
//
//        when(studyPlanID.getLocalDate()).thenReturn(date);
//
//        // Act
//        CourseEditionResponseDTO dto = assembler.toResponseDTO(courseEdition);
//
//        // Assert
//        assertEquals(uuid.toString(), dto.courseEditionID());
//        assertEquals(programmeName, dto.programmeName());
//        assertEquals(programmeAcronym, dto.programmeAcronym());
//        assertEquals(uuid, dto.schoolYearID());
//        assertEquals(courseAcronym, dto.courseAcronym());
//        assertEquals(courseName, dto.courseName());
//        assertEquals(date, dto.studyPlanImplementationDate());
//    }
}