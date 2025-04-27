package PAI.mapper.course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.domain.course.ICourseFactory;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import PAI.repository.courseRepository.ICourseRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseMapperImplTest {

    //SUT = ClassCourseMapper
    @Test
    void should_return_Exception_when_Factory_is_null() {
        //Arrange
        //Act and Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseMapperImpl(null));
    }

    //SUT = ClassCourseMapper toDomain Method

    @Test
    void should_convert_CourseDataModel_to_DomainCourse_with_MOCK() throws Exception {

        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        String acronymS = "ALC";
        String nameS = "Alchemy";
        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        when(courseDataModel.getAcronym()).thenReturn(acronymS);
        when(courseDataModel.getName()).thenReturn(nameS);

        Acronym acronym = new Acronym(acronymS);
        Name name = new Name(nameS);
        Course courseS = mock(Course.class);

        when(courseFactory.createCourse(name,acronym)).thenReturn(courseS);

        //Act
        Course course = courseMapperImpl.toDomain(courseDataModel);
        // Assert
        assertNotNull(course);
    }

    @Test
    void should_convert_Course_to_CourseDataModel_with_MOCK() throws Exception {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        String acronymS = "ALC";
        String nameS = "Alchemy";
        Acronym acronym = new Acronym(acronymS );
        Name name = new Name(nameS);
        CourseID courseID = new CourseID(acronym,name);

        Course courseDouble = mock(Course.class);
        when(courseDouble.identity()).thenReturn(courseID);

        //Act
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(courseDouble);

        //Assert
        assertNotNull(courseDataModel);
        assertEquals(nameS, courseDataModel.getName());
        assertEquals(acronymS, courseDataModel.getAcronym());

    }
    @Test
    void should_convert_list_of_CourseDataModel_to_list_of_Course_with_mocks() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        String acronymS = "ALC";
        String nameS = "Alchemy";
        CourseDataModel courseDataModel = mock(CourseDataModel.class);
        when(courseDataModel.getAcronym()).thenReturn(acronymS);
        when(courseDataModel.getName()).thenReturn(nameS);

        Course course = mock(Course.class);
        when(courseFactory.createCourse(any(Name.class),any(Acronym.class))).thenReturn(course);

        List<CourseDataModel> courselist = List.of(courseDataModel);

        //Act
        Iterable<Course> result = courseMapperImpl.toDomain(courselist);

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());

    }

    @Test
    void shouldThrowExceptionWhenCourseDataModelIsNull() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDomain((CourseDataModel) null));
    }

    @Test
    void shouldThrowExceptionWhenCourseDataModelListIsNull() {
        // Arrange
        ICourseFactory courseFactory = mock(ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDomain((Iterable<CourseDataModel>) null));
    }


    @Test
    void should_convert_CourseDataModel_to_Course() throws Exception {
        // Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        //Act
        Course course = courseMapperImpl.toDomain(courseDataModel);
        // Assert
        assertNotNull(course);
    }

    @Test
    void should_convert_DataModelName_to_DomainName() throws Exception {
        // Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        String name = course.getName().getName();
        // Assert
        assertEquals("Programacao", name);
    }

    @Test
    void should_convert_DataModelAcronym_to_DomainAcronym() throws Exception {
        // Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        String acronym = course.getAcronym().getAcronym();
        // Assert
        assertEquals("LEI", acronym);
    }

    //SUT = ClassCourseMapper toDataModel Method

    @Test
    void should_convert_Course_to_CourseDataModel() throws Exception {
        //Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");

        Course course = courseFactory.createCourse(name,acronym);

        //Act
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);

        //Assert
        assertNotNull(courseDataModel);

    }
    @Test
    void should_throw_exception_if_course_is_null(){
        // Arrange
        ICourseFactory courseFactoryDouble = mock (ICourseFactory.class);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble);

        //Act
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDataModel(null));

    }

    @Test
    void should_convert_Course_Name_to_CourseDataModel_Name() throws Exception {
        //Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = courseFactory.createCourse(name,acronym);
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);
        //Act
        String courseDataModel_name = courseDataModel.getName();

        //Assert
        assertEquals("Programação", courseDataModel_name );

    }
    @Test
    void should_convert_Course_Acronym_to_CourseDataModel_Acronym() throws Exception {
        //Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");

        Course course = courseFactory.createCourse(name,acronym);
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);
        //Act
        String courseDataModel_acronym = courseDataModel.getAcronym();

        //Assert
        assertEquals("LEI", courseDataModel_acronym );

    }

    //SUT = ClassCourseMapper ListOfCourse_toDataModel Method

    @Test
    void should_convert_list_of_CourseDataModel_to_list_of_Course() {
        // Arrange
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
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
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
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
        ICourseRepository courseRepositoryDouble = mock(ICourseRepository.class);
        ICourseFactory courseFactory = new CourseFactoryImpl(courseRepositoryDouble);
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel("LEI", "Programacao");
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        List<CourseDataModel> dataModelList = List.of(courseDataModel);

        // Act
        Iterable<Course> courseList = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertEquals("Programacao", courseList.iterator().next().getName().getName());
    }


    @Test
    void shouldReturnNullIfCourseIsNull() throws Exception{
    // Arrange
    ICourseFactory courseFactoryDouble = mock (ICourseFactory.class);
    CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryDouble);
    CourseDataModel courseDataModelDouble = mock(CourseDataModel.class);

    when(courseDataModelDouble.getName()).thenReturn("Programacao");
    when(courseDataModelDouble.getAcronym()).thenReturn("LEI");
    when (courseMapperImpl.toDomain(courseDataModelDouble)).thenReturn(null);

    // Act
    Course result = courseMapperImpl.toDomain(courseDataModelDouble);

    // Assert
    assertNull(result);
}

}