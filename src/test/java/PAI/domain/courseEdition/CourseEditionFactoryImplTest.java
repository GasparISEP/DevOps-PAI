package PAI.domain.courseEdition;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        CourseEditionGeneratedID courseEditionGeneratedIDDouble = mock(CourseEditionGeneratedID.class);

        //instructions
        try (MockedConstruction<CourseEdition> courseEditionDouble = mockConstruction(CourseEdition.class, (courseEditionMock, context) -> {
            CourseEditionID actualCourseEditionID = (CourseEditionID) context.arguments().get(0);
            CourseInStudyPlanID actualCourseInStudyPlanID = (CourseInStudyPlanID) context.arguments().get(1);
            ProgrammeEditionID actualProgrammeEditionID = (ProgrammeEditionID) context.arguments().get(2);
            CourseEditionGeneratedID actualCourseEditionGeneratedID = (CourseEditionGeneratedID) context.arguments().get(3);
            TeacherID actualTeacherID = (TeacherID) context.arguments().get(4);when(courseEditionMock.identity()).thenReturn(actualCourseEditionID);
            when(courseEditionMock.getCourseInStudyPlanID()).thenReturn(actualCourseInStudyPlanID);
            when(courseEditionMock.getProgrammeEditionID()).thenReturn(actualProgrammeEditionID);
            when(courseEditionMock.getRuc()).thenReturn(actualTeacherID);
            when(courseEditionMock.getCourseEditionGeneratedID()).thenReturn(actualCourseEditionGeneratedID);

        })) {

            //SUT
            ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

            // Act
            CourseEdition courseEdition = ICourseEditionFactory.createCourseEditionFromDataModel(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedIDDouble, teacherIDDouble);

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
        CourseEditionGeneratedID courseEditionGeneratedIDDouble = mock(CourseEditionGeneratedID.class);

            //instructions
        try (MockedConstruction<CourseEdition> courseEditionDouble = mockConstruction(CourseEdition.class, (courseEditionMock, context) -> {
            CourseEditionID actualCourseEditionID = (CourseEditionID) context.arguments().get(0);
            CourseInStudyPlanID actualCourseInStudyPlanID = (CourseInStudyPlanID) context.arguments().get(1);
            ProgrammeEditionID actualProgrammeEditionID = (ProgrammeEditionID) context.arguments().get(2);
            CourseEditionGeneratedID actualCourseEditionGeneratedID = (CourseEditionGeneratedID) context.arguments().get(3);
            TeacherID actualTeacherID = (TeacherID) context.arguments().get(4);
            when(courseEditionMock.identity()).thenReturn(actualCourseEditionID);
            when(courseEditionMock.getCourseInStudyPlanID()).thenReturn(actualCourseInStudyPlanID);
            when(courseEditionMock.getProgrammeEditionID()).thenReturn(actualProgrammeEditionID);
            when(courseEditionMock.getRuc()).thenReturn(actualTeacherID);
            when(courseEditionMock.getCourseEditionGeneratedID()).thenReturn(actualCourseEditionGeneratedID);
        })) {

            //SUT
            ICourseEditionFactory ICourseEditionFactory = new CourseEditionFactoryImpl();

        // Act
            CourseEdition courseEdition = ICourseEditionFactory.createCourseEditionFromDataModel(courseEditionIDDouble, courseInStudyPlanIDDouble, programmeEditionIDDouble, courseEditionGeneratedIDDouble, teacherIDDouble);

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