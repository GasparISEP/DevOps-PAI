package PAI.mapper.Course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.domain.course.ICourseFactory;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void should_convert_CourseDataModel_to_Course() throws Exception {
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
    void should_throw_exception_if_courseDataModel_is_null(){
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        //Act
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDomain((CourseDataModel) null));

    }

    @Test
    void should_convert_DataModelName_to_DomainName() throws Exception {
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
        ICourseFactory courseFactory = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);

        //Act
        assertThrows(NullPointerException.class, () -> courseMapperImpl.toDataModel(null));

    }

    @Test
    void should_convert_Course_Name_to_CourseDataModel_Name() throws Exception {
        //Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");
        Course course = courseFactory.createCourse(name,acronym);
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);
        //Act
        String courseDataModel_name = courseDataModel.get_name();

        //Assert
        assertEquals("Programação", courseDataModel_name );

    }
    @Test
    void should_convert_Course_Acronym_to_CourseDataModel_Acronym() throws Exception {
        //Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
        ICourseMapper courseMapperImpl = new CourseMapperImpl(courseFactory);

        Name name = new Name ("Programação");
        Acronym acronym = new Acronym("LEI");

        Course course = courseFactory.createCourse(name,acronym);
        CourseDataModel courseDataModel = courseMapperImpl.toDataModel(course);
        //Act
        String courseDataModel_acronym = courseDataModel.get_acronym();

        //Assert
        assertEquals("LEI", courseDataModel_acronym );

    }

    //SUT = ClassCourseMapper ListOfCourse_toDataModel Method

    @Test
    void should_convert_list_of_CourseDataModel_to_list_of_Course() {
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
    void should_return_null_if_CourseDataModel_is_invalid() {
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(null, null);
        CourseDataModel invalidDataModel = new CourseDataModel(courseIDDataModel, null, null);
        List<CourseDataModel> dataModelList = List.of(invalidDataModel);

        // Act
        Iterable<Course> result = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertNull(result);

    }
    @Test
    void should_return_true_if_the_iteration_returns_an_element() {
        // Arrange
        ICourseFactory courseFactory = new CourseFactoryImpl();
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
        ICourseFactory courseFactory = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactory);
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel("LEI", "Programacao");
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI");
        List<CourseDataModel> dataModelList = List.of(courseDataModel);

        // Act
        Iterable<Course> courseList = courseMapperImpl.toDomain(dataModelList);

        // Assert
        assertEquals("Programacao", courseList.iterator().next().getName().getName());
    }
}