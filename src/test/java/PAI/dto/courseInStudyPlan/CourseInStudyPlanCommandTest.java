package PAI.dto.courseInStudyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseInStudyPlanCommandTest {

    @Test
    void testStudyPlanID() {

        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Date implementationDate = new Date("01-09-2024");
        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                mock(Semester.class),
                mock(CurricularYear.class),
                mock(Acronym.class),
                mock(Name.class),
                programmeID,
                new Date("01-09-2024"),
                mock(DurationCourseInCurricularYear.class),
                mock(CourseQuantityCreditsEcts.class)
        );

        // act
        StudyPlanID studyPlanID = command.studyPlanID();

        // assert
        assertEquals(programmeID, studyPlanID.getProgrammeID());
        assertEquals(implementationDate.getLocalDate(), studyPlanID.getLocalDate());
    }
}