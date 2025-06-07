package PAI.persistence.datamodel.courseEditionEnrolment;

import PAI.VOs.Description;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentGeneratedIDDataModelTest {

    @Test
    void shouldCreateDataModelWithEmptyConstructor() {
        //Arrange

        //Act
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel();

        //Assert
        assertNotNull(ceeGeneratedIDDM);
    }

    @Test
    void shouldCreateDataModeWithValidString() {
        //Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");

        //Act
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Assert
        assertNotNull(ceeGeneratedIDDM);
    }

    @Test
    void shouldReturnStringIDWithGetter(){
        //Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Act
        UUID result = ceeGeneratedIDDM.getGeneratedID();

        //Assert
        assertEquals(idValue, result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM1 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM2 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Act + Assert
        assertTrue(ceeGeneratedIDDM1.equals(ceeGeneratedIDDM2));
    }

    @Test
    void shouldReturnTrueIfGeneratedIDDataModelIsComparedToItself() {
        // Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        // Act
        boolean result = ceeGeneratedIDDM.equals(ceeGeneratedIDDM);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfGeneratedIDDataModelIsComparedToNull() {
        // Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        // Act
        boolean result = ceeGeneratedIDDM.equals(null);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfGeneratedIDDataModelIsComparedToADifferentInstance() {
        //Arrange
        Description description = mock(Description.class);
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Act
        boolean result = ceeGeneratedIDDM.equals(description);

        //Assert
        assertFalse(result);
    }

    @Test
    void testingHashCode() {
        //Arrange
        UUID idValue = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        UUID idValue2 = UUID.fromString("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        UUID idValue3 = UUID.fromString("d41fd21b-a2e3-45b2-bc01-2bc95f5fbe79");

        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM1 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM2 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue2);
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM3 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue3);

        //Act + Assert
        assertEquals(ceeGeneratedIDDM1.hashCode(), ceeGeneratedIDDM2.hashCode());
        assertNotEquals(ceeGeneratedIDDM1.hashCode(), ceeGeneratedIDDM3.hashCode());
    }
}