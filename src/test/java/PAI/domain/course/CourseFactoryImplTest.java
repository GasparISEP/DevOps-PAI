package PAI.domain.course;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseFactoryImplTest {

    @Test
    void shouldCreateValidCourse() throws InstantiationException {
        //arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);

        try (MockedConstruction<Course> mockCourseDDD = mockConstruction(Course.class, (mock, context) ->{
        })) {
            //act
            Course course = courseFactoryImpl.createCourse(name, acronym);
            //assert
            assertNotNull(course);
        }
    }

    @Test
    void mockingConstructorThrowingException(){
        //arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);
        //Use try-with-resources to mock construction and throw an exception
        try (MockedConstruction<Course> mockCourseDDD = mockConstruction(Course.class, (mock, context) ->{
            //Define behavior: throwing an exception when a new instance of Location is created
            throw new RuntimeException(new InstantiationException("Course constructor failed"));
        })) {
            //Act: trying to create accessMethod will throw the exception
            try {
                courseFactoryImpl.createCourse(name, acronym);
                fail("Expect exception not thrown");
            } catch (Exception e) {
                //Assertion to check if the exception is thrown
                assertTrue(e.getCause().getMessage().contains("Course constructor failed"));
            }
        }
    }

    @Test
    void shouldCreateValidCourseWithParameters() throws InstantiationException {
        //arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);

        try (MockedConstruction<Course> mockCourseDDD = mockConstruction(Course.class, (mock, context) ->{
        })) {
            //act
            Course course = courseFactoryImpl.createCourse(courseID, name, acronym);
            //assert
            assertNotNull(course);
        }
    }

    @Test
    void shouldThrowExceptionWhenCourseIDIsNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        Name name = mock(Name.class);
        Acronym acronym = mock(Acronym.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(null, name, acronym));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseID courseID = mock(CourseID.class);
        Acronym acronym = mock(Acronym.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(courseID, null, acronym));
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseID courseID = mock(CourseID.class);
        Name name = mock(Name.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(courseID, name, null));
    }

    @Test
    void shouldThrowExceptionWhenNameAndAcronymAreNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(null, null));
    }

    @Test
    void shouldThrowExceptionWhenNameIsNullAndAcronymIsNotNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        Acronym acronym = mock(Acronym.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(null, acronym));
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNullAndNameIsNotNull() {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        Name name = mock(Name.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> courseFactoryImpl.createCourse(name, null));
    }

}