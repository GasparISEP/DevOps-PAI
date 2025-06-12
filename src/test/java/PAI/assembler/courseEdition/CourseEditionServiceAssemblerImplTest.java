package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionServiceAssemblerImplTest {

    @Test
    void toResponseDTO_ShouldMapCorrectly() throws Exception {
        // Arrange
        CourseEditionServiceAssemblerImpl assembler = new CourseEditionServiceAssemblerImpl();

        CourseEdition courseEdition = mock(CourseEdition.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        UUID uuid = UUID.randomUUID();
        String programmeAcronym = "SE";
        String courseAcronym = "CS101";
        String courseName = "Intro to Programming";
        LocalDate studyPlanDate = LocalDate.of(2025, 5, 25);

        when(programmeID.getProgrammeAcronym()).thenReturn(programmeAcronym);
        when(schoolYearID.getSchoolYearID()).thenReturn(uuid);
        when(courseID.getCourseAcronymValue()).thenReturn(courseAcronym);
        when(courseID.getCourseNameValue()).thenReturn(courseName);
        when(studyPlanID.getLocalDate()).thenReturn(studyPlanDate);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);

        ProgrammeEditionID programmeEditionID = new ProgrammeEditionID(programmeID, schoolYearID);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionID, courseInStudyPlanID);

        when(courseEdition.identity()).thenReturn(courseEditionID);
        when(courseEdition.getProgrammeEditionID()).thenReturn(programmeEditionID);
        when(courseEdition.getCourseInStudyPlanID()).thenReturn(courseInStudyPlanID);

        // Act
        CourseEditionResponseDTO dto = assembler.toResponseDTO(courseEdition);

        // Assert
        String expectedFormattedID = URLEncoder.encode(
                programmeAcronym + "-" +
                        uuid + "_" +
                        courseAcronym + "-" +
                        courseName + "-" +
                        programmeAcronym + "-" +
                        studyPlanDate.toString(),
                StandardCharsets.UTF_8
        );

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
        CourseEditionServiceAssemblerImpl assembler = new CourseEditionServiceAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTO(null);
        });
    }

}