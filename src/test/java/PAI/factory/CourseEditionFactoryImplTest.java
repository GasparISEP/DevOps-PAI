package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class CourseEditionFactoryImplTest {

    @Test
    void shouldCreateCourseEditionWhenConstructorIsCalled() throws Exception {
        //SUT = CourseEditionFactory
        //Arrange
            //Doubles' instantiation
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

        //instructions
        try (MockedConstruction<CourseEdition> courseEditionDouble = mockConstruction(CourseEdition.class, (courseEditionMock, context) -> {
            CourseEditionID actualCourseEditionID = (CourseEditionID) context.arguments().get(0);
            CourseInStudyPlanID actualCourseInStudyPlanID = (CourseInStudyPlanID) context.arguments().get(1);
            ProgrammeEditionID actualProgrammeEditionID = (ProgrammeEditionID) context.arguments().get(2);
            TeacherID actualTeacherID = (TeacherID) context.arguments().get(3);
            when(courseEditionMock.identity()).thenReturn(actualCourseEditionID);
            when(courseEditionMock.getCourseInStudyPlanID()).thenReturn(actualCourseInStudyPlanID);
            when(courseEditionMock.getProgrammeEditionID()).thenReturn(actualProgrammeEditionID);
            when(courseEditionMock.getRuc()).thenReturn(actualTeacherID);
        })) {

            //SUT
            ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

            // Act
            CourseEdition courseEdition = ICourseEditionFactory.createCourseEditionFromDataModel(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, teacherIDDouble);

            // Asserts
            assertNotNull(courseEdition);
            assertEquals(courseEditionIDDouble, courseEdition.identity());
            assertEquals(courseInStudyPlanIDDouble, courseEdition.getCourseInStudyPlanID());
            assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());
            assertEquals(teacherIDDouble, courseEdition.getRuc());

            List<CourseEdition> courseEditions = courseEditionDouble.constructed();
            assertEquals(1, courseEditions.size());

            assertEquals(courseEdition, courseEditions.get(0));
        }
    }

    @Test
    void shouldCreateCourseEditionWhenConstructorIsCalledWithCourseEditionID() throws Exception {
        //SUT = CourseEditionFactory
        //Arrange
            //Doubles' instantiation
        CourseEditionID courseEditionIDDouble = mock(CourseEditionID.class);
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
        TeacherID teacherIDDouble = mock(TeacherID.class);

            //instructions
        try (MockedConstruction<CourseEdition> courseEditionDouble = mockConstruction(CourseEdition.class, (courseEditionMock, context) -> {
            CourseEditionID actualCourseEditionID = (CourseEditionID) context.arguments().get(0);
            CourseInStudyPlanID actualCourseInStudyPlanID = (CourseInStudyPlanID) context.arguments().get(1);
            ProgrammeEditionID actualProgrammeEditionID = (ProgrammeEditionID) context.arguments().get(2);
            TeacherID actualTeacherID = (TeacherID) context.arguments().get(3);
            when(courseEditionMock.identity()).thenReturn(actualCourseEditionID);
            when(courseEditionMock.getCourseInStudyPlanID()).thenReturn(actualCourseInStudyPlanID);
            when(courseEditionMock.getProgrammeEditionID()).thenReturn(actualProgrammeEditionID);
            when(courseEditionMock.getRuc()).thenReturn(actualTeacherID);
        })) {

            //SUT
            ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

        // Act
            CourseEdition courseEdition = ICourseEditionFactory.createCourseEditionFromDataModel(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, teacherIDDouble);

        // Asserts
            assertNotNull(courseEdition);
            assertEquals(courseEditionIDDouble, courseEdition.identity());
            assertEquals(courseInStudyPlanIDDouble, courseEdition.getCourseInStudyPlanID());
            assertEquals(programmeEditionIDDouble, courseEdition.getProgrammeEditionID());
            assertEquals(teacherIDDouble, courseEdition.getRuc());

            List<CourseEdition> courseEditions = courseEditionDouble.constructed();
            assertEquals(1, courseEditions.size());

            assertEquals(courseEdition, courseEditions.get(0));
        }
    }

    @Test
    void shouldThrowExceptionWhenConstructorIsCalled(){
        //SUT = CourseEditionFactory
        //Arrange
            //Doubles' instantiation
        ProgrammeEditionID programmeEditionIDDouble = mock(ProgrammeEditionID.class);
        CourseInStudyPlanID courseInStudyPlanIDDouble = mock (CourseInStudyPlanID.class);
            //SUT
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

            //instructions
        try (MockedConstruction<CourseEdition> mock = mockConstruction(CourseEdition.class,(mocked, context) ->
        {
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("Course Edition constructor failed"));
        })) {

        //Act + Assert
            try {
                ICourseEditionFactory.createCourseEditionToDomain(courseInStudyPlanIDDouble, programmeEditionIDDouble);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("Course Edition constructor failed"));
            }
        }
    }

    @Test
    void shouldNotCreateCourseEdition() {
        //SUT = CourseEditionFactory
        //Arrange
            //SUT
        ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

        //Act + Assert
        assertThrows(Exception.class, () -> ICourseEditionFactory.createCourseEditionToDomain(null, null));
    }
}