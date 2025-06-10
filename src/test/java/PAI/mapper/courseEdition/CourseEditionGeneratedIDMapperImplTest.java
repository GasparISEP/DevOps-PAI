package PAI.mapper.courseEdition;

import PAI.VOs.CourseEditionGeneratedID;
import PAI.persistence.datamodel.courseEdition.CourseEditionGeneratedIDDataModel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseEditionGeneratedIDMapperImplTest {

    @Test
    void shouldMapCourseEditionGeneratedIDToDataModel() {

        //arrange
        ICourseEditionGeneratedIDMapper mapper = new CourseEditionGeneratedIDMapperImpl();
        CourseEditionGeneratedID courseEditionGeneratedID = mock(CourseEditionGeneratedID.class);
        when(courseEditionGeneratedID.toString()).thenReturn(UUID.randomUUID().toString());

        //act
        CourseEditionGeneratedIDDataModel datamodel=mapper.toDataModel(courseEditionGeneratedID);

        //assert
        assertNotNull(datamodel);
    }

    @Test
    void shouldThrowExceptionIfInputCourseEditionGeneratedIDIsNull() {

        //arrange
        ICourseEditionGeneratedIDMapper mapper = new CourseEditionGeneratedIDMapperImpl();
        CourseEditionGeneratedID courseEditionGeneratedID = null;

        //act +assert
        assertThrows(IllegalArgumentException.class, () -> {
            CourseEditionGeneratedIDDataModel IDDataModel = mapper.toDataModel(courseEditionGeneratedID);
        });
    }


    @Test
    void shouldCorrectlyReturnCourseEditionGeneratedID() {

        //arrange
        ICourseEditionGeneratedIDMapper mapper = new CourseEditionGeneratedIDMapperImpl();
        CourseEditionGeneratedIDDataModel datamodel = mock(CourseEditionGeneratedIDDataModel.class);

        when(datamodel.getId()).thenReturn(UUID.randomUUID().toString());

        //act
        CourseEditionGeneratedID courseEditionGeneratedID = mapper.toDomain(datamodel);

        //assert
        assertNotNull(courseEditionGeneratedID);
    }


    @Test
    void shouldThrowExceptionWhenDataModelIsNull() {

        //arrange
        ICourseEditionGeneratedIDMapper mapper = new CourseEditionGeneratedIDMapperImpl();
        CourseEditionGeneratedIDDataModel datamodel = null;

        //act + assert
        assertThrows(Exception.class, () -> {
            CourseEditionGeneratedID courseEditionGeneratedID = mapper.toDomain(datamodel);
        });
    }

}