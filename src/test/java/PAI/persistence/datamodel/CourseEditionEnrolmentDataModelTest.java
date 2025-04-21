package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = true;

        //act & assert
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);
    }

    //testing find id method

    @Test
    void shouldReturnTheIdOfCourseEditionEnrolmentDataModel() {
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = true;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        CourseEditionEnrolmentIDDataModel result = courseEditionEnrolmentDataModel.findId();

        //assert
        assertEquals(doubleCourseEditionEnrolmentIDDataModel, result);
    }

    //testing find enrolmentDate method

    @Test
    void shouldReturnTheEnrolmentDateOfCourseEditionEnrolmentDataModel() {
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = true;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        LocalDate result = courseEditionEnrolmentDataModel.findEnrolmentDate();

        //assert
        assertEquals(enrolmentDate, result);
    }

    //testing find isActive method

    @Test
    void shouldReturnTheTrueWhenCourseEditionEnrolmentIsActive() {
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = true;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTheFalseWhenCourseEditionEnrolmentIsNotActive() {
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = false;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        //act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //assert
        assertFalse(result);
    }

    //testing equals method

    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory (){
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel = mock(CourseEditionEnrolmentIDDataModel.class);
        LocalDate enrolmentDate = LocalDate.of(2025, 4, 18);
        boolean isActive = false;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel, enrolmentDate, isActive);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel1;

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals (){
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 =
                new CourseEditionEnrolmentDataModel (doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2024, 12, 18), true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTheObjectIsNull (){
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(null);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass (){
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        ProgrammeEditionEnrolmentDataModel programmeEditionEnrolmentDataModel = mock (ProgrammeEditionEnrolmentDataModel.class);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(programmeEditionEnrolmentDataModel);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals (){
        //arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel2 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 =
                new CourseEditionEnrolmentDataModel (doubleCourseEditionEnrolmentIDDataModel2,
                        LocalDate.of(2025, 4, 17), true);

        //act
        boolean result = courseEditionEnrolmentDataModel1.equals(courseEditionEnrolmentDataModel2);

        //assert
        assertFalse(result);
    }

    //testing hashcode method

    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        int courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel1.hashCode();

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertEquals(courseEditionEnrolmentDataModel2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel1 = mock(CourseEditionEnrolmentIDDataModel.class);
        CourseEditionEnrolmentIDDataModel doubleCourseEditionEnrolmentIDDataModel2 = mock(CourseEditionEnrolmentIDDataModel.class);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel1 =
                new CourseEditionEnrolmentDataModel(doubleCourseEditionEnrolmentIDDataModel1,
                        LocalDate.of(2025, 4, 18), true);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 =
                new CourseEditionEnrolmentDataModel (doubleCourseEditionEnrolmentIDDataModel2,
                        LocalDate.of(2025, 4, 17), true);

        //Act
        int result = courseEditionEnrolmentDataModel1.hashCode();

        //Assert
        assertNotEquals(courseEditionEnrolmentDataModel2.hashCode(), result);
    }
}