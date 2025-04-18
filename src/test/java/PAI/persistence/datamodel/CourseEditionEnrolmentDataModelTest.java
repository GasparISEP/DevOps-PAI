package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentDataModelTest {

    //testing empty constructor

    @Test
    void shouldReturnAnEmptyCourseEditionEnrolmentDataModel() {
        //arrange

        //act & assert
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel();
    }

    @Test
    void shouldReturnACourseEditionEnrolmentDataModelWithAttributes() {
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = true;

        //act & assert
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);
    }

    //testing find id method

    @Test
    void shouldReturnTheIdOfCourseEditionEnrolmentDataModel() {
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        CourseEditionEnrolmentIDDataModel result = courseEditionEnrolmentDataModel.findId();

        //assert
        assertEquals(courseEditionEnrolmentIDDataModel, result);
    }

    //testing find enrolmentDate method

    @Test
    void shouldReturnTheEnrolmentDateOfCourseEditionEnrolmentDataModel() {
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        String result = courseEditionEnrolmentDataModel.findEnrolmentDate();

        //assert
        assertEquals(enrolmentDate, result);
    }

    //testing find isActive method

    @Test
    void shouldReturnTheTrueWhenCourseEditionEnrolmentIsActive() {
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTheFalseWhenCourseEditionEnrolmentIsNotActive() {
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = false;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //assert
        assertFalse(result);
    }

    //testing equals method

    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory (){
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        String enrolmentDate = "2025-04-18";
        boolean isActive = false;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel, enrolmentDate, isActive);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel1;

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals (){
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = new CourseEditionEnrolmentDataModel (courseEditionEnrolmentIDDataModel1,"2024-12-23", true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTheObjectIsNull (){
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass (){
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);
        ProgrammeEditionEnrolmentDataModel programmeEditionEnrolmentDataModel = mock (ProgrammeEditionEnrolmentDataModel.class);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(programmeEditionEnrolmentDataModel);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals (){
        //arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel2 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = new CourseEditionEnrolmentDataModel (courseEditionEnrolmentIDDataModel2,"2024-12-24", true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertFalse(result);
    }

    //testing hashcode method

    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);
        int courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel1.hashCode();

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertEquals(courseEditionEnrolmentDataModel2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentIDDataModel courseEditionEnrolmentIDDataModel2 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 = new CourseEditionEnrolmentDataModel(courseEditionEnrolmentIDDataModel1, "2024-12-23", true);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = new CourseEditionEnrolmentDataModel (courseEditionEnrolmentIDDataModel2,"2024-12-24", true);

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertNotEquals(courseEditionEnrolmentDataModel2.hashCode(), result);
    }
}