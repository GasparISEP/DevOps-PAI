package PAI.assembler.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanServiceAssemblerImplTest {

    private CourseInStudyPlanServiceAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CourseInStudyPlanServiceAssemblerImpl();
    }

    @Test
    void toServiceDTO_mapsAllFieldsCorrectly() {
        // Arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date date = mock(Date.class);
        DurationCourseInCurricularYear duration = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts credits = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanGeneratedID generatedId = mock(CourseInStudyPlanGeneratedID.class);

        CourseInStudyPlan course = mock(CourseInStudyPlan.class);
        UUID uuid = mock(UUID.class);
        CourseInStudyPlanGeneratedID genID = mock(CourseInStudyPlanGeneratedID.class);
        when(genID.getId()).thenReturn(uuid);
        when(course.getGeneratedID()).thenReturn(genID);

        when(course.getSemester()).thenReturn(semester);
        when(semester.toInt()).thenReturn(2);

        when(course.getCurricularYear()).thenReturn(curricularYear);
        when(curricularYear.toInt()).thenReturn(3);

        when(course.getCourseID()).thenReturn(courseID);
        when(courseID.getCourseAcronymValue()).thenReturn("CS101");
        when(courseID.getCourseNameValue()).thenReturn("Intro to CS");

        when(course.getStudyplanID()).thenReturn(studyPlanID);
        when(studyPlanID.getProgrammeID()).thenReturn(programmeID);
        when(programmeID.getProgrammeAcronym()).thenReturn("ENG");

        when(studyPlanID.getDate()).thenReturn(date);
        when(date.getLocalDate()).thenReturn(java.time.LocalDate.of(2025, 5, 27));

        when(course.getDurationOfCourse()).thenReturn(duration);
        when(duration.getDuration()).thenReturn(1);

        when(course.getQuantityOfCreditsEcts()).thenReturn(credits);
        when(credits.toDouble()).thenReturn(6.0);

        when(course.getGeneratedID()).thenReturn(generatedId);

        // Act
        CourseInStudyPlanServiceDTO dto = assembler.toServiceDTO(course);

        // Assert
        assertNotNull(dto);
        assertEquals(2, dto.semester());
        assertEquals(3, dto.curricularYear());
        assertEquals("CS101", dto.courseAcronym());
        assertEquals("Intro to CS", dto.courseName());
        assertEquals("ENG", dto.programmeAcronym());
        assertEquals("2025-05-27", dto.studyPlanDate());
        assertEquals(1, dto.duration());
        assertEquals(6.0, dto.credits());
    }

    @Test
    void toServiceDTO_throwsException_whenCourseIsNull() {
        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            assembler.toServiceDTO(null);
        });
        assertEquals("CourseInStudyPlan entity cannot be null", thrown.getMessage());
    }

}
