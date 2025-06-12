package PAI.assembler.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseInStudyPlanBusinessAssemblerImplTest {

    private CourseInStudyPlanBusinessAssemblerImpl assembler = new CourseInStudyPlanBusinessAssemblerImpl();

    @Test
    void toDTO_shouldReturnCorrectDTOFromDomainObject() throws Exception {
        // Arrange
        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(2);
        Acronym courseAcronym = new Acronym("CS101");
        Name courseName = new Name("Computer Science");
        CourseID courseID = new CourseID(courseAcronym, courseName);

        Acronym programmeAcronym = new Acronym("ENG");
        ProgrammeID programmeID = new ProgrammeID(programmeAcronym);

        Date studyPlanDate = new Date("01-05-2026");
        StudyPlanID studyPlanID = new StudyPlanID(programmeID, studyPlanDate);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(2);
        CourseQuantityCreditsEcts credits = new CourseQuantityCreditsEcts(6.0);
        CourseInStudyPlanGeneratedID generatedID = CourseInStudyPlanGeneratedID.randomID();

        CourseInStudyPlan course = new CourseInStudyPlan(
                semester,
                curricularYear,
                courseID,
                studyPlanID,
                courseInStudyPlanID,
                duration,
                credits,
                generatedID
        );

        // Act
        CourseInStudyPlanServiceDTO dto = assembler.toDTO(course);

        // Assert
        assertEquals(1, dto.semester());
        assertEquals(2, dto.curricularYear());
        assertEquals("CS101", dto.courseAcronym());
        assertEquals("Computer Science", dto.courseName());
        assertEquals("ENG", dto.programmeAcronym());
        assertEquals(studyPlanDate.toString(), dto.studyPlanDate());
        assertEquals(2, dto.duration());
        assertEquals(6.0, dto.credits());
        assertEquals(generatedID.getId(), dto.generatedID());
    }

    @Test
    void toDTO_shouldThrowExceptionWhenCourseInStudyPlanIsNull() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> assembler.toDTO(null));
    }

}