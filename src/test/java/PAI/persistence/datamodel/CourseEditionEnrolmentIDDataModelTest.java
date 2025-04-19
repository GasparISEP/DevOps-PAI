package PAI.persistence.datamodel;

import PAI.domain.CourseEditionEnrolment;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentIDDataModelTest {

    // testing constructor
    @Test
    void shouldReturnAValidCourseEditionEnrolmentIDDataModel_constructorWithoutParameters() {
        //arrange


        //act & assert
        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel();
    }

    @Test
    void shouldReturnAnExceptionWhenCourseEditionIdIsNull() {
        //arrange
        String studentID = "1000000";

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDDataModel(studentID, null);
        });
        assertEquals("Course Edition ID cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenStudentIdIsEmpty() {
        //arrange
        String studentID = "";
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDDataModel(studentID, courseEditionIDDataModel);
        });
        assertEquals("Student ID cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenStudentIdIsNull() {
        //arrange
        String studentID = null;
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDDataModel(studentID, courseEditionIDDataModel);
        });
        assertEquals("Student ID cannot be null or empty!", exception.getMessage());
    }

    @Test
    void shouldReturnAValidCourseEditionEnrolmentIDDataModel_constructorWithParameters() {
        //arrange
        String studentID = "1999999";
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        //act & assert
        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel(studentID, courseEditionIDDataModel);
    }

    //testing find course edition method

    @Test
    void shouldReturnACourseEditionIDDataModelWithTheCourseEditionID (){
        //arrange
        String studentID = "1234567";
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel(studentID, courseEditionIDDataModel);

        //act
        CourseEditionIDDataModel result = ceeIDDataModel.findCourseEditionID();

        //assert
        assertEquals(result, courseEditionIDDataModel);
    }

    // testing find student id test

    @Test
    void shouldReturnIntWithTheCourseEditionID (){
        //arrange
        String studentID = "1999999";
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel(studentID, courseEditionIDDataModel);

        //act
        String result = ceeIDDataModel.findStudentID();

        //assert
        assertEquals("1999999", result);
    }

    // testing equals method

    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory (){
        //arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 = ceeIDDataModel1;

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals (){
        //arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTheObjectIsNull (){
        //arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);

        //act
        boolean result = ceeIDDataModel.equals(null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass (){
        //arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);
        CourseEditionEnrolment courseEditionEnrolment = mock (CourseEditionEnrolment.class);

        //act
        boolean result = ceeIDDataModel1.equals(courseEditionEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals (){
        //arrange
        CourseEditionIDDataModel courseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel2 = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel1);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 = new CourseEditionEnrolmentIDDataModel("1234568", courseEditionIDDataModel2);

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertFalse(result);
    }

    //testing hashcode method

    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);

        //Act
        int result = ceeIDDataModel.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel);
        int ceeIDDataModel2 = ceeIDDataModel1.hashCode();

        //Act
        int result = ceeIDDataModel1.hashCode();

        //Assert
        assertEquals(ceeIDDataModel2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        CourseEditionIDDataModel courseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel2 = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 = new CourseEditionEnrolmentIDDataModel("1234567", courseEditionIDDataModel1);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 = new CourseEditionEnrolmentIDDataModel("1234568", courseEditionIDDataModel2);

        //Act
        int result = ceeIDDataModel1.hashCode();

        //Assert
        assertNotEquals(ceeIDDataModel2.hashCode(), result);
    }
}