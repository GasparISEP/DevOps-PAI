package PAI.persistence.datamodel;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseIDDataModelTest {

    @Test
    void shouldCreateCourseIDDataModel() {
        // Arrange + Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel();

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldCreateCourseIDDataModelWithCourseID() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);
        // Act
        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Assert
        assertNotNull(CourseIDDataModel);
    }

    @Test
    void shouldReturnTrueIfObjectsAreEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        Object CourseIDDataModel2 = CourseIDDataModel;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnID() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Act
        String result = CourseIDDataModel.getId();

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnTrueIfCourseIDDataModelEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);


        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(courseIDDouble);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEquals() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        Acronym acronym2 = mock(Acronym.class);
        Name name2 = mock(Name.class);
        CourseID CourseIDDouble2 = mock(CourseID.class);
        when(CourseIDDouble2.getAcronym()).thenReturn(acronym2);
        when(CourseIDDouble2.getName()).thenReturn(name2);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(CourseIDDouble2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithNull() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = null;

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentClass() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);


        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        Object CourseIDDataModel2 = new Object();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfCourseIDDataModelEqualsWithSameAcronym() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        Name name2 = mock(Name.class);
        CourseID courseIDDouble2 = mock(CourseID.class);
        when(courseIDDouble2.getAcronym()).thenReturn(acronym);
        when(courseIDDouble2.getName()).thenReturn(name2);


        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(courseIDDouble2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfCourseIDDataModelEqualsWithSameName() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        Acronym acronym2 = mock(Acronym.class);
        CourseID courseIDDouble2 = mock(CourseID.class);
        when(courseIDDouble2.getAcronym()).thenReturn(acronym2);
        when(courseIDDouble2.getName()).thenReturn(name);


        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel(courseIDDouble2);

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCourseIDDataModelNotEqualsWithDifferentObject() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);
        CourseIDDataModel CourseIDDataModel2 = new CourseIDDataModel();

        // Act
        boolean result = CourseIDDataModel.equals(CourseIDDataModel2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnHashCodeNotNull() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Act
        int result = CourseIDDataModel.hashCode();

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldReturnAcronymForGetAcronymMethod() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Act
        String result = CourseIDDataModel.getAcronym();

        // Assert
        assertNotNull(result);
    }


    @Test
    void shouldReturnAcronymForGetNameMethod() {
        // Arrange
        Acronym acronym = mock(Acronym.class);
        Name name = mock(Name.class);
        CourseID courseIDDouble = mock(CourseID.class);
        when(courseIDDouble.getAcronym()).thenReturn(acronym);
        when(courseIDDouble.getName()).thenReturn(name);

        CourseIDDataModel CourseIDDataModel = new CourseIDDataModel(courseIDDouble);

        // Act
        String result = CourseIDDataModel.getName();

        // Assert
        assertNotNull(result);
    }
}