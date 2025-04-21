package PAI.mapper.courseEdition;

import PAI.domain.CourseEdition;
import PAI.persistence.datamodel.courseEdition.CourseEditionDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionMapperImplTest {

    @Test
    void shouldReturnNullWhenToDomainIsCalled() {
        // Arrange
        CourseEditionDataModel courseEditionDataModel = mock(CourseEditionDataModel.class);
        CourseEditionMapperImpl courseEditionMapper = new CourseEditionMapperImpl();

        // Act
        CourseEdition courseEdition = courseEditionMapper.toDomain(courseEditionDataModel);

        // Assert
        assertNull(courseEdition);
    }

    @Test
    void shouldReturnNullWhenToDataModelIsCalled() {
        // Arrange
        CourseEdition courseEdition = mock(CourseEdition.class);
        CourseEditionMapperImpl courseEditionMapper = new CourseEditionMapperImpl();

        // Act
        CourseEditionDataModel courseEditionDataModel = courseEditionMapper.toDataModel(courseEdition);

        // Assert
        assertNull(courseEditionDataModel);
    }

}