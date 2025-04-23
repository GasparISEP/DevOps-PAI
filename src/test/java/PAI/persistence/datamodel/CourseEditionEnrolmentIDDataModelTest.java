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
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, null);
        });
        assertEquals("Course Edition ID cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnAnExceptionWhenStudentIdIsNull() {
        //arrange
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        //act & assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CourseEditionEnrolmentIDDataModel(null, doubleCourseEditionIDDataModel);
        });
        assertEquals("Student ID cannot be null!", exception.getMessage());
    }

    @Test
    void shouldReturnAValidCourseEditionEnrolmentIDDataModel_constructorWithParameters() {
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        //act & assert
        CourseEditionEnrolmentIDDataModel ceeIDDataModel =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);
    }

    //testing find course edition method

    @Test
    void shouldReturnACourseEditionIDDataModelWithTheCourseEditionID (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        //act
        CourseEditionIDDataModel result = ceeIDDataModel.findCourseEditionID();

        //assert
        assertEquals(result, doubleCourseEditionIDDataModel);
    }

    // testing find student id test

    @Test
    void shouldReturnIntWithTheCourseEditionID (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        //act
        StudentIDDataModel result = ceeIDDataModel.findStudentID();

        //assert
        assertEquals(doubleStudentIDDataModel, result);
    }

    // testing equals method

    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 = ceeIDDataModel1;

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTheObjectIsNull (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        //act
        boolean result = ceeIDDataModel.equals(null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        CourseEditionEnrolment courseEditionEnrolment = mock (CourseEditionEnrolment.class);

        //act
        boolean result = ceeIDDataModel1.equals(courseEditionEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals (){
        //arrange
        StudentIDDataModel doubleStudentIDDataModel1 = mock(StudentIDDataModel.class);
        StudentIDDataModel doubleStudentIDDataModel2 = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel2 = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel1, doubleCourseEditionIDDataModel1);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel2, doubleCourseEditionIDDataModel2);

        //act
        boolean result = ceeIDDataModel1.equals(ceeIDDataModel2);

        //assert
        assertFalse(result);
    }

    //testing hashcode method

    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);

        //Act
        int result = ceeIDDataModel.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        StudentIDDataModel doubleStudentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel, doubleCourseEditionIDDataModel);
        int ceeIDDataModel2 = ceeIDDataModel1.hashCode();

        //Act
        int result = ceeIDDataModel1.hashCode();

        //Assert
        assertEquals(ceeIDDataModel2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        StudentIDDataModel doubleStudentIDDataModel1 = mock(StudentIDDataModel.class);
        StudentIDDataModel doubleStudentIDDataModel2 = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel doubleCourseEditionIDDataModel2 = mock(CourseEditionIDDataModel.class);

        CourseEditionEnrolmentIDDataModel ceeIDDataModel1 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel1, doubleCourseEditionIDDataModel1);
        CourseEditionEnrolmentIDDataModel ceeIDDataModel2 =
                new CourseEditionEnrolmentIDDataModel(doubleStudentIDDataModel2, doubleCourseEditionIDDataModel2);

        //Act
        int result = ceeIDDataModel1.hashCode();

        //Assert
        assertNotEquals(ceeIDDataModel2.hashCode(), result);
    }
}