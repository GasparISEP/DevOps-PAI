package PAI.mapper.Course;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.persistence.datamodel.CourseDataModel;
import PAI.persistence.datamodel.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseMapperImplTest {

    @Test
    void should_convert_CourseDataModel_to_Course() throws Exception {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryImpl);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI", 6.0, 1);
        //Act
        Course course = courseMapperImpl.toDomain(courseDataModel);
        // Assert
        assertNotNull(course);
    }

    @Test
    void should_convert_DataModelName_to_DomainName() throws Exception {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryImpl);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI", 6.0, 1);
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        String name = course.getName().getName();
        // Assert
        assertEquals("Programacao", name);
    }

    @Test
    void should_convert_DataModelAcronym_to_DomainAcronym() throws Exception {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryImpl);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI", 6.0, 1);
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        String acronym = course.getAcronym().getAcronym();
        // Assert
        assertEquals("LEI", acronym);
    }

    @Test
    void should_convert_DataModelQECTS_to_DomainQECTS() throws Exception {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryImpl);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI", 6.0, 1);
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        double courseQuantityCreditsEcts = course.getCourseQuantityCreditsEcts().getQuantity();
        // Assert
        assertEquals(6.0, courseQuantityCreditsEcts);
    }

    @Test
    void should_convert_DataModelDurationCourseInCurricularYear_to_DomainDurationCourseInCurricularYear() throws Exception {
        // Arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseMapperImpl courseMapperImpl = new CourseMapperImpl(courseFactoryImpl);
        CourseID courseID = new CourseID(new Acronym("LEI"), new Name("Programacao"));
        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(
                courseID.getAcronym().getAcronym(),
                courseID.getName().getName()
        );
        CourseDataModel courseDataModel = new CourseDataModel(courseIDDataModel, "Programacao", "LEI", 6.0, 1);
        Course course = courseMapperImpl.toDomain(courseDataModel);
        //Act
        int durationCourseInCurricularYear = course.getDurationCourseInCurricularYear().getDuration();
        // Assert
        assertEquals(1, durationCourseInCurricularYear);
    }
}