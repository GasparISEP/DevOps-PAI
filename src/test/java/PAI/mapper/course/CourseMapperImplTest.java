package PAI.mapper.course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.mapper.courseID.ICourseIDMapper;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseMapperImplTest {

    //SUT = ClassCourseMapper
    @Test
    void should_return_Exception_when_Factory_is_null() {
        //Arrange
        ICourseIDMapper courseIDMapperDouble = mock(ICourseIDMapper.class);
        //Act and Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseMapperImpl(null, courseIDMapperDouble));
    }

    @Test
    void shouldThrowExceptionIfCourseIDMapperIsNull() {
        //Arrange
        ICourseFactory courseFactoryDouble = mock(ICourseFactory.class);

        //Act + assert
        assertThrows(IllegalArgumentException.class,() -> new CourseMapperImpl(courseFactoryDouble, null));

    }

    @Test
    void shouldConvertCourseDataModelToDomainCourse() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapper = mock(ICourseIDMapper.class);

        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory, courseIDMapper);

        String acronymString = "DSOFT";
        String nameString = "Software Development";

        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        when(courseDataModel.getCourseID()).thenReturn(courseIDDataModel);
        when(courseDataModel.getAcronym()).thenReturn(acronymString);
        when(courseDataModel.getName()).thenReturn(nameString);

        CourseID courseID = mock(CourseID.class);
        when(courseIDMapper.toDomain(courseIDDataModel)).thenReturn(courseID);

        Name name = new Name(nameString);
        Acronym acronym = new Acronym(acronymString);

        Course course = mock(Course.class);
        when(courseFactory.createCourse(courseID, name, acronym)).thenReturn(course);

        // Act
        Course result = courseMapperImpl.toDomain(courseDataModel);

        // Assert
        assertNotNull(result);
        assertEquals(course, result);

        // Verificações extra
        verify(courseIDMapper).toDomain(courseIDDataModel);
        verify(courseFactory).createCourse(courseID, name, acronym);
    }

    @Test
    void shouldConvertCourseToCourseDataModel () throws Exception {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapper = mock(ICourseIDMapper.class);
        ICourseMapper courseMapper = new CourseMapperImpl(courseFactory, courseIDMapper);

        String acronymString = "DSOFT";
        String nameString = "Software Development";

        Course courseDouble = mock(Course.class);
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseID = mock(CourseID.class);
        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);

        when(courseDouble.getAcronym()).thenReturn(acronym);
        when(courseDouble.getName()).thenReturn(name);
        when(courseDouble.identity()).thenReturn(courseID);

        when(acronym.getAcronym()).thenReturn(acronymString);
        when(name.getName()).thenReturn(nameString);
        when(courseIDMapper.toDataModel(courseID)).thenReturn(courseIDDataModel);

        // Act
        CourseDataModel courseDataModel = courseMapper.toDataModel(courseDouble);

        // Assert
        assertNotNull(courseDataModel);
        assertEquals(nameString, courseDataModel.getName());
        assertEquals(acronymString, courseDataModel.getAcronym());
    }


    @Test
    void should_convert_list_of_CourseDataModel_to_list_of_Course_with_mocks() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock(ICourseIDMapper.class);
        ICourseMapper courseMapper = new CourseMapperImpl(courseFactory, courseIDMapperDouble);

        String acronymS = "ALC";
        String nameS = "Alchemy";
        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        when(courseDataModel.getAcronym()).thenReturn(acronymS);
        when(courseDataModel.getName()).thenReturn(nameS);

        Course course = mock(Course.class);
        when(courseFactory.createCourse(any(Name.class),any(Acronym.class))).thenReturn(course);

        List<CourseDataModel> courselist = List.of(courseDataModel);

        //Act
        Iterable<Course> result = courseMapper.toDomain(courselist);

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());

    }

    @Test
    void shouldThrowExceptionWhenCourseDataModelIsNull() {
        // Arrange
        ICourseFactory courseFactoryDouble = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock(ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble, courseIDMapperDouble);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDomain((CourseDataModel) null));
    }

    @Test
    void shouldThrowExceptionWhenCourseDataModelListIsNull() {
        // Arrange
        ICourseFactory courseFactoryDouble = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock(ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble, courseIDMapperDouble);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDomain((Iterable<CourseDataModel>) null));
    }

    @Test
    void should_throw_exception_if_course_is_null(){
        // Arrange
        ICourseFactory courseFactoryDouble = mock (ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock (ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble, courseIDMapperDouble);

        //Act
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDataModel(null));

    }

    @Test
    void should_convert_Course_Name_to_CourseDataModel_Name() throws Exception {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapper = mock(ICourseIDMapper.class);
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory, courseIDMapper);

        Course course = mock(Course.class);

        Name name = new Name("Software Development");
        Acronym acronym = new Acronym("DSOFT");

        CourseID courseID = mock(CourseID.class);
        when(course.identity()).thenReturn(courseID);
        when(course.getName()).thenReturn(name);
        when(course.getAcronym()).thenReturn(acronym);

        // Act
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);

        // Assert
        assertNotNull(courseDataModel);
        assertEquals("Software Development", courseDataModel.getName());
    }

    //SUT = ClassCourseMapper ListOfCourse_toDataModel Method

    @Test
    void should_convert_list_of_CourseDataModel_to_list_of_Course() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock (ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory, courseIDMapperDouble);
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel("LEI", "Programacao");
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        List<CourseDataModel> dataModelList = List.of(courseDataModel);

        // Act
        Iterable<Course> courseList = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertNotNull(courseList);

    }

    @Test
    void should_return_true_if_the_iteration_returns_an_element() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapperDouble = mock (ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory, courseIDMapperDouble);
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel("LEI", "Programacao");
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        List<CourseDataModel> dataModelList = List.of(courseDataModel);

        // Act
        Iterable<Course> courseList = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertTrue(courseList.iterator().hasNext());

    }


    @Test
    void should_get_Name_Of_Course() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        ICourseIDMapper courseIDMapper = mock(ICourseIDMapper.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory, courseIDMapper);

        CourseIDDataModel courseIDDataModel = new CourseIDDataModel("DSOFT", "Software Development");
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Software Development", "DSOFT");
        List<CourseDataModel> dataModelList = List.of(courseDataModel);

        CourseID courseID = mock(CourseID.class);
        when(courseIDMapper.toDomain(courseIDDataModel)).thenReturn(courseID);

        Acronym acronym = new Acronym("DSOFT");
        Name name = new Name("Software Development");

        Course course = mock(Course.class);
        when(course.getName()).thenReturn(name);

        when(courseFactory.createCourse(courseID, name, acronym)).thenReturn(course);

        // Act
        Iterable<Course> courseList = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertEquals("Software Development", courseList.iterator().next().getName().getName());
    }


    @Test
    void shouldReturnNullIfCourseIsNull() throws Exception{
    // Arrange
    ICourseFactory courseFactoryDouble = mock (ICourseFactory.class);
    ICourseIDMapper courseIDMapperDouble = mock (ICourseIDMapper.class);
    CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble, courseIDMapperDouble);
    CourseDataModel courseDataModelDouble = mock(CourseDataModel.class);

    when(courseDataModelDouble.getName()).thenReturn("Software Development");
    when(courseDataModelDouble.getAcronym()).thenReturn("DSOFT");
    when (courseMapperImpl.toDomain(courseDataModelDouble)).thenReturn(null);

    // Act
    Course result = courseMapperImpl.toDomain(courseDataModelDouble);

    // Assert
    assertNull(result);
}

}