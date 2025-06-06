package PAI.persistence.datamodel.courseEditionEnrolment;

import PAI.VOs.Description;
import org.junit.jupiter.api.Test;

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
        String idValue = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";

        //Act
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Assert
        assertNotNull(ceeGeneratedIDDM);
    }

    @Test
    void shouldReturnStringIDWithGetter(){
        //Arrange
        String idValue = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Act
        String result = ceeGeneratedIDDM.getId();

        //Assert
        assertEquals(idValue, result);
    }

    @Test
    void shouldReturnTrueIfObjectsAreTheSame() {
        //Arrange
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM1 = new CourseEditionEnrolmentGeneratedIDDataModel("e3b7e64f-1f42-4626-8e75-f4c244fabf29");
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM2 = new CourseEditionEnrolmentGeneratedIDDataModel("e3b7e64f-1f42-4626-8e75-f4c244fabf29");

        //Act + Assert
        assertTrue(ceeGeneratedIDDM1.equals(ceeGeneratedIDDM2));
    }

    @Test
    void shouldReturnTrueIfGeneratedIDDataModelIsComparedToItself() {
        // Arrange
        String idValue = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        // Act
        boolean result = ceeGeneratedIDDM.equals(ceeGeneratedIDDM);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfGeneratedIDDataModelIsComparedToNull() {
        // Arrange
        String idValue = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
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
        String idValue = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM = new CourseEditionEnrolmentGeneratedIDDataModel(idValue);

        //Act
        boolean result = ceeGeneratedIDDM.equals(description);

        //Assert
        assertFalse(result);
    }

    @Test
    void testingHashCode() {
        //Arrange
        String idValue1 = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
        String idValue2 = "e3b7e64f-1f42-4626-8e75-f4c244fabf29";
        String idValue3 = "d41fd21b-a2e3-45b2-bc01-2bc95f5fbe79";

        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM1 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue1);
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM2 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue2);
        CourseEditionEnrolmentGeneratedIDDataModel ceeGeneratedIDDM3 = new CourseEditionEnrolmentGeneratedIDDataModel(idValue3);

        //Act + Assert
        assertEquals(ceeGeneratedIDDM1.hashCode(), ceeGeneratedIDDM2.hashCode());
        assertNotEquals(ceeGeneratedIDDM1.hashCode(), ceeGeneratedIDDM3.hashCode());
    }
}