package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CourseInStudyPlanFactoryImplTest {

    @Test
    void shouldCreateFactoryConstrutorNewCourseInStudyPlan() throws Exception {

        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyplanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        //act
        ICourseInStudyPlanFactory courseInStudyPlanFactory_2 = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlan courseInStudyPlan_DDD = courseInStudyPlanFactory_2.newCourseInStudyPlan(semester, curricularYear, courseID, studyplanID, durationOfCourse, quantityOfCreditsEcts, programmeID);

        //assert
        assertNotNull(courseInStudyPlan_DDD);
    }


    @Test
    void shouldCreateFactoryConstrutorNewCourseInStudyPlanFromDataModel() throws Exception {

        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyplanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);
        CourseInStudyPlanID courseInStudyPlanID = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanGeneratedID generatedID = mock(CourseInStudyPlanGeneratedID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        //act
        ICourseInStudyPlanFactory courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlan courseInStudyPlan = courseInStudyPlanFactory.newCourseInStudyPlanFromDataModel(courseInStudyPlanID, generatedID, semester, curricularYear, courseID, studyplanID, durationOfCourse, quantityOfCreditsEcts, programmeID);

        //assert
        assertNotNull(courseInStudyPlan);
    }

    @Test
    void should_ThrowException_When_QuantityOfCreditsEctsIsZero() {
        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseQuantityCreditsEcts(0.0);
        });

        assertEquals("quantityCreditsEcts can only have a value between 1 and 60", exception.getMessage());
    }

    @Test
    void should_ThrowException_When_NumberOfCreditsAsMoreThanOneDecimal(){
        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseQuantityCreditsEcts(10.012);
        });

        assertEquals("quantityCreditsEcts can only have 1 decimal place", exception.getMessage());
    }

    @Test
    void should_ThrowException_When_CommandIsNull() {
        // act & assert
        ICourseInStudyPlanFactory courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            courseInStudyPlanFactory.newCourseInStudyPlan(null);
        });

        assertEquals("Command cannot be null.", exception.getMessage());
    }
    @Test
    void should_CreateCourseInStudyPlan_When_AllValuesAreValid() throws Exception {
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityCreditsEcts = new CourseQuantityCreditsEcts(30.0);
        ProgrammeID programmeID = mock(ProgrammeID.class);

        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlan courseInStudyPlan = factory.newCourseInStudyPlan(
                semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityCreditsEcts, programmeID);

        assertNotNull(courseInStudyPlan);
    }
}