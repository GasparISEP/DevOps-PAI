package PAI.mapper;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.mapper.CourseID.CourseIDMapperImpl;
import PAI.mapper.CourseID.ICourseIDMapper;
import PAI.persistence.datamodel.CourseIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseIDMapperImplTest {

    @Test
    void shouldReturnNotNullCourseID() {
        // Arrange

        CourseIDDataModel courseIDDataModel = mock(CourseIDDataModel.class);
        when(courseIDDataModel.getAcronym()).thenReturn("DSOFT");
        when(courseIDDataModel.getName()).thenReturn("Software Development");

        ICourseIDMapper courseIDMapper = new CourseIDMapperImpl();

        // Act
        CourseID courseID = courseIDMapper.toDomain(courseIDDataModel);

        // Assert
        assertNotNull(courseID);
    }

    @Test
    void shouldThrowExceptionWhenCourseIDDataModelIsNull() {
        // Arrange
        ICourseIDMapper courseIDMapper = new CourseIDMapperImpl();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseIDMapper.toDomain(null));
    }

    @Test
    void shouldReturnNotNullCourseIDDataModel() {
        // Arrange
        Acronym acronymDouble = mock(Acronym.class);
        Name nameDouble = mock(Name.class);
        CourseID courseID = mock(CourseID.class);
        when(courseID.getAcronym()).thenReturn(acronymDouble);
        when(courseID.getName()).thenReturn(nameDouble);

        ICourseIDMapper courseIDMapper = new CourseIDMapperImpl();

        // Act
        CourseIDDataModel courseIDDataModel = courseIDMapper.toDataModel(courseID);

        // Assert
        assertNotNull(courseIDDataModel);
    }

    @Test
    void shouldThrowExceptionWhenCourseIDIsNull() {
        // Arrange
        ICourseIDMapper courseIDMapper = new CourseIDMapperImpl();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> courseIDMapper.toDataModel(null));
    }
}