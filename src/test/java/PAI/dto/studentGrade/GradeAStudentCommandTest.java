package PAI.dto.studentGrade;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class GradeAStudentCommandTest {

    @Test
    void shouldCreateGradeAStudentCommand() throws Exception {
        // Arrange
        Grade grade = new Grade(12.5);
        Date date = new Date("01-01-2023");
        StudentID studentID = new StudentID(1241321);

        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock(CourseInStudyPlanID.class);

        CourseEditionID courseEditionID = new CourseEditionID(programmeEditionIDDouble, courseInStudyPlanIDDouble);

        // Act
        GradeAStudentCommand command = new GradeAStudentCommand(grade, date, studentID, courseEditionID);
        GradeAStudentCommand expected = new GradeAStudentCommand(grade, date, studentID, courseEditionID);

        // Assert
        assertEquals(expected, command);
    }
}