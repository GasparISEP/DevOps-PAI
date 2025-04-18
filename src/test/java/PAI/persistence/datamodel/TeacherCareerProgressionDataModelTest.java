package PAI.persistence.datamodel;

import PAI.VOs.Acronym;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TeacherCareerProgressionDataModelTest {

    private TeacherCareerProgressionDataModel createTeacherCareerProgressionDataModel(TeacherCareerProgressionIDDataModel id) {
        UUID teacherCategoryId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int workingPercentage = 100;
        LocalDate date = LocalDate.of(2024, 10, 5);
        String teacherId = "ABC";

        return new TeacherCareerProgressionDataModel(id, teacherCategoryId, workingPercentage, date, teacherId);
    }

    @Test
    void shouldCreateTeacherCareerProgressionDataModelWithoutInputs(){
        // Arrange

        // Act
        new TeacherCareerProgressionDataModel();

        // Assert
    }

    @Test
    void shouldCreateTeacherCareerProgressionDataModelWithValidInputs(){
        // Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        UUID teacherCategoryId = UUID.randomUUID();
        int workingPercentage = 100;
        LocalDate date = LocalDate.of(2024, 10, 5);
        String teacherId = "ABC";

        // Act
        new TeacherCareerProgressionDataModel(id, teacherCategoryId, workingPercentage, date, teacherId);

        // Assert
    }

    @Test
    void shouldReturnTeacherCareerProgressionIDWhenCallGetID(){
        // Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        // Act
        TeacherCareerProgressionIDDataModel result = TCP1.getId();
        // Assert
        assertEquals(result, id);
    }

    @Test
    void shouldReturnUUIDWhenCallGetTeacherCategoryId(){
        // Arrange
        UUID expectedUUID = UUID.fromString("11111111-1111-1111-1111-111111111111");
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        // Act
        UUID result = TCP1.getTeacherCategoryId();
        // Assert
        assertEquals(result, expectedUUID);
    }

    @Test
    void shouldReturnIntWhenCallGetWorkingPercentage(){
        // Arrange
        int expectedWorkingPercentage = 100;
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        // Act
        int result = TCP1.getWorkingPercentage();
        // Assert
        assertEquals(expectedWorkingPercentage, result);
    }

    @Test
    void shouldReturnLocalDateWhenCallGetDate(){
        // Arrange
        LocalDate expectedDate = LocalDate.of(2024, 10, 5);
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        // Act
        LocalDate result = TCP1.getDate();
        // Assert
        assertEquals(expectedDate, result);
    }

    @Test
    void shouldReturnStringWhenCallGetTeacherId(){
        // Arrange
        String expectedTeacherId = "ABC";
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        // Act
        String result = TCP1.getTeacherId();
        // Assert
        assertEquals(expectedTeacherId, result);
    }

    @Test
    void shouldReturnTrueWhenComparingWithItself() {
        //Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        //Act
        boolean result = TCP1.equals(TCP1);
        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparingWithDifferentObjectType() {
        //Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        Object diferentObject = new Object();
        //Act
        boolean result = TCP1.equals(diferentObject);
        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfTeacherCareerProgressionDataModelsAreEqual() {
        //arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        TeacherCareerProgressionDataModel TCP2 = createTeacherCareerProgressionDataModel(id);
        //act
        boolean result = TCP1.equals(TCP2);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherCareerProgressionDataModelsAreNotEqual() {
        //arrange
        TeacherCareerProgressionIDDataModel id1 = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id1);
        TeacherCareerProgressionIDDataModel id2 = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP2 = createTeacherCareerProgressionDataModel(id2);
        //act
        boolean result = TCP1.equals(TCP2);
        //assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnEqualWhenTwoObjectsHaveTheSameHashCode() {
        //Arrange
        TeacherCareerProgressionIDDataModel id = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id);
        TeacherCareerProgressionDataModel TCP2 = createTeacherCareerProgressionDataModel(id);
        //Act & Assert
        assertEquals(TCP1.hashCode(), TCP2.hashCode());
    }

    @Test
    public void shouldReturnNotEqualWhenTwoObjectsHaveDifferentHashCodes() {
        //Arrange
        TeacherCareerProgressionIDDataModel id1 = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP1 = createTeacherCareerProgressionDataModel(id1);
        TeacherCareerProgressionIDDataModel id2 = mock(TeacherCareerProgressionIDDataModel.class);
        TeacherCareerProgressionDataModel TCP2 = createTeacherCareerProgressionDataModel(id2);
        //Act & Assert
        assertNotEquals(TCP1.hashCode(), TCP2.hashCode());
    }
}