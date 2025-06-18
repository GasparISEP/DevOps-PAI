package PAI.assembler.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionEnrolmentAssemblerImplTest {

    private CourseEditionEnrolmentAssemblerImpl assembler;

    @BeforeEach
    void setUp() {
        assembler = new CourseEditionEnrolmentAssemblerImpl();
    }

    @Test
    void toStudentID_shouldReturnCorrectStudentID() throws Exception {
        // Arrange
        int studentUniqueNumber = 1100000;

        // Act
        StudentID studentID = assembler.toStudentID(studentUniqueNumber);

        // Assert
        assertNotNull(studentID);
        assertEquals(1100000, studentID.getUniqueNumber());
    }

    @Test
    void toCourseEditionID_shouldReturnCorrectCourseEditionID() throws Exception {
        // Arrange
        CourseEditionEnrolmentDto dto = mock(CourseEditionEnrolmentDto.class);
        when(dto.programmeAcronym()).thenReturn("LEIC");
        when(dto.schoolYearId()).thenReturn("123e4567-e89b-12d3-a456-426614174000");
        when(dto.courseAcronym()).thenReturn("ESOFT");
        when(dto.courseName()).thenReturn("Engineering Software");
        when(dto.studyPlanDate()).thenReturn("01-01-2024");

        // Act
        CourseEditionID courseEditionID = assembler.toCourseEditionID(dto);

        // Assert
        assertNotNull(courseEditionID);
        assertEquals(dto.programmeAcronym(), courseEditionID.getProgrammeEditionID().getProgrammeID().getAcronym().getValue());
        assertEquals(dto.schoolYearId(), courseEditionID.getProgrammeEditionID().getSchoolYearID().getSchoolYearID().toString());
        assertEquals(dto.courseAcronym(), courseEditionID.getCourseInStudyPlanID().getCourseID().getAcronym().getValue());
        assertEquals(dto.courseName(), courseEditionID.getCourseInStudyPlanID().getCourseID().getCourseNameValue());
        assertEquals(dto.studyPlanDate(), courseEditionID.getCourseInStudyPlanID().getStudyPlanID().getDate().toString());
    }
}