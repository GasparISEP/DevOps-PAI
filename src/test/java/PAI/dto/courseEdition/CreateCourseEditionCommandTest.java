package PAI.dto.courseEdition;

import PAI.VOs.*;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateCourseEditionCommandTest {

    @Test
    void shouldCreateCommandSuccessfully() {
        //arrange
        NameWithNumbersAndSpecialChars programmeName = new NameWithNumbersAndSpecialChars("Software Development");
        Acronym programmeAcronym = new Acronym("MEI");
        SchoolYearID schoolYearID = new SchoolYearID(UUID.randomUUID());

        Acronym courseAcronym = new Acronym("AP");
        Name courseName = new Name("Advanced Programming");
        Date date = new Date("31-03-2025");

        //act
        CreateCourseEditionCommand dto = new CreateCourseEditionCommand(
                programmeName,
                programmeAcronym,
                schoolYearID,
                courseAcronym,
                courseName,
                date
        );

        //assert
        assertEquals(programmeName, dto.programmeName());
        assertEquals(programmeAcronym, dto.programmeAcronym());
        assertEquals(schoolYearID, dto.schoolYearID());
        assertEquals(courseAcronym, dto.courseAcronym());
        assertEquals(courseName, dto.courseName());
        assertEquals(date, dto.studyPlanImplementationDate());
    }
}