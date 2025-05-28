package PAI.assembler.courseEdition;

import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.dto.courseEdition.CourseEditionResponseDTO;
import org.junit.jupiter.api.Test;

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
        CourseEditionServiceAssemblerImpl assembler = new CourseEditionServiceAssemblerImpl();

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            assembler.toResponseDTO(null);
        });
    }

}