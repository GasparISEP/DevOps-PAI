package PAI.dto.courseInStudyPlan;

import static org.junit.jupiter.api.Assertions.*;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import java.util.Currency;

class CourseInStudyPlanCommandTest {

    @Test
    void shouldCreateValidCourseInStudyPlanCommand() throws Exception {
        // Arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("LP");
        Name courseName = new Name("Linguagens de Programação");
        Acronym programmeAcronym = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informática");
        Date studyPlanDate = new Date("01-01-2020");
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(6.0);

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
    void shouldCreateCommandWithMinimumValues() throws Exception {
        // Arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        Acronym courseAcronym = new Acronym("LP");
        Name courseName = new Name("Linguagens de Programação");
        Acronym programmeAcronym = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informática");
        Date studyPlanDate = new Date("01-01-2020");
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(1.0);

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
