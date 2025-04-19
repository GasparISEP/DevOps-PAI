package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

class CourseEditionIDMapperImplTest {

    @Test
    void shouldReturnNullWhenConvertDataModelToDomain() throws Exception {
        // Arrange
        CourseEditionIDDataModel dataModel = mock(CourseEditionIDDataModel.class);
        CourseEditionIDMapperImpl mapper = new CourseEditionIDMapperImpl();

        // Act
        CourseEditionID cEID = mapper.toDomain(dataModel);

        // Assert
        assertNotNull(mapper);
        assertNull(cEID);
    }

    @Test
    void shouldReturnNullWhenConvertDomainToDataModel() throws Exception {
        // Arrange
        CourseEditionID cEID = mock(CourseEditionID.class);
        CourseEditionIDMapperImpl mapper = new CourseEditionIDMapperImpl();

        // Act
        CourseEditionIDDataModel dataModel  = mapper.toDataModel(cEID);

        // Assert
        assertNotNull(mapper);
        assertNull(dataModel);
    }
}