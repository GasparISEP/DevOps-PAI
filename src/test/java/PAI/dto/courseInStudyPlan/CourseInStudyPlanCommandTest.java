package PAI.dto.courseInStudyPlan;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseInStudyPlanCommandTest {

    @Test
    void shouldCreateValidCourseInStudyPlanCommand() {
        // Arrange
        int semester = 1;
        int curricularYear = 2;
        String courseAcronym = "LP";
        String courseName = "Linguagens de Programação";
        String programmeAcronym = "LEI";
        String programmeName = "Licenciatura em Engenharia Informática";
        String studyPlanDate = "01-01-2024";
        int duration = 1;
        double credits = 6.0;

        // Act
        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                duration,
                credits
        );

        // Assert
        assertNotNull(command);
        assertEquals(semester, command.semester());
        assertEquals(curricularYear, command.curricularYear());
        assertEquals(courseAcronym, command.courseAcronym());
        assertEquals(courseName, command.courseName());
        assertEquals(programmeAcronym, command.programmeAcronym());
        assertEquals(programmeName, command.programmeName());
        assertEquals(studyPlanDate, command.studyPlanDate());
        assertEquals(duration, command.duration());
        assertEquals(credits, command.credits());
    }

    @Test
    void shouldCreateCommandWithMinimumValues() {
        // Arrange
        int semester = 1;
        int curricularYear = 1;
        String courseAcronym = "A";
        String courseName = "B";
        String programmeAcronym = "C";
        String programmeName = "D";
        String studyPlanDate = "01-01-2020";
        int duration = 1;
        double credits = 1.0;

        // Act
        CourseInStudyPlanCommand command = new CourseInStudyPlanCommand(
                semester,
                curricularYear,
                courseAcronym,
                courseName,
                programmeAcronym,
                programmeName,
                studyPlanDate,
                duration,
                credits
        );

        // Assert
        assertNotNull(command);
        assertEquals(semester, command.semester());
        assertEquals(curricularYear, command.curricularYear());
        assertEquals(courseAcronym, command.courseAcronym());
        assertEquals(courseName, command.courseName());
        assertEquals(programmeAcronym, command.programmeAcronym());
        assertEquals(programmeName, command.programmeName());
        assertEquals(studyPlanDate, command.studyPlanDate());
        assertEquals(duration, command.duration());
        assertEquals(credits, command.credits());
    }
}
