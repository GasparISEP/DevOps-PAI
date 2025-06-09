package PAI.persistence.datamodel.courseEditionEnrolment;

import PAI.persistence.datamodel.programmeEditionEnrolment.ProgrammeEditionEnrolmentDataModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CourseEditionEnrolmentDataModelTest {

    private CourseEditionEnrolmentGeneratedIDDataModel _ceeGeneratedIDDataModelDouble;
    private CourseEditionEnrolmentIDDataModel _ceeIDDataModelDouble;
    private CourseEditionEnrolmentIDDataModel _ceeIDDataModelDouble2;
    private LocalDate _enrolmentDateDouble;
    private LocalDate _enrolmentDateDouble2;

    void createDoubles(){
        _ceeGeneratedIDDataModelDouble =mock(CourseEditionEnrolmentGeneratedIDDataModel.class);
        _ceeIDDataModelDouble = mock(CourseEditionEnrolmentIDDataModel.class);
        _ceeIDDataModelDouble2 = mock(CourseEditionEnrolmentIDDataModel.class);
        _enrolmentDateDouble = mock(LocalDate.class);
        _enrolmentDateDouble2 = mock(LocalDate.class);
    }

    @Test
    void shouldCreateCourseEditionEnrolmentDataModelFromEmptyConstructor() {
        //Arrange

        //Act
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel();

        //Assert
        assertNotNull(courseEditionEnrolmentDataModel);
    }

    @Test
    void shouldCreateCourseEditionEnrolmentDataModelFromConstructorWithAttributes() {
        //Arrange
        createDoubles();
        boolean Status = true;

        //Act
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Assert
        assertNotNull(courseEditionEnrolmentDataModel);
    }

    @Test
    void shouldThrowExceptionAndNotConstructDataModelIfGeneratedIDIsNull() {
        //Arrange
        createDoubles();
        boolean Status = true;

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentDataModel(null, _ceeIDDataModelDouble, _enrolmentDateDouble, Status));
    }

    @Test
    void shouldThrowExceptionAndNotConstructDataModelIfDomainIDIsNull() {
        //Arrange
        createDoubles();
        boolean Status = true;

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, null, _enrolmentDateDouble, Status));
    }

    @Test
    void shouldThrowExceptionAndNotConstructDataModelIfDateIsNull() {
        //Arrange
        createDoubles();
        boolean Status = true;

        //Act + Assert
        assertThrows(IllegalArgumentException.class,() -> new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, null, Status));
    }

    @Test
    void shouldReturnTheGeneratedIdOfCourseEditionEnrolmentDataModel() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        CourseEditionEnrolmentGeneratedIDDataModel result = courseEditionEnrolmentDataModel.findGeneratedID();

        //Assert
        assertEquals(_ceeGeneratedIDDataModelDouble, result);
    }

    @Test
    void shouldReturnTheIdOfCourseEditionEnrolmentDataModel() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        CourseEditionEnrolmentIDDataModel result = courseEditionEnrolmentDataModel.findId();

        //Assert
        assertEquals(_ceeIDDataModelDouble, result);
    }

    @Test
    void shouldReturnTheEnrolmentDateOfCourseEditionEnrolmentDataModel() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        LocalDate result = courseEditionEnrolmentDataModel.findEnrolmentDate();

        //Assert
        assertEquals(_enrolmentDateDouble, result);
    }

    @Test
    void shouldReturnTrueWhenCourseEditionEnrolmentIsActive() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCourseEditionEnrolmentIsNotActive() {
        //Arrange
        createDoubles();
        boolean Status = false;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        boolean result = courseEditionEnrolmentDataModel.isActive();

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory (){
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel;

        //Act
        boolean result = courseEditionEnrolmentDataModel.equals(courseEditionEnrolmentDataModel2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals (){
        //Arrange
        createDoubles();
        boolean Status = false;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        boolean result = courseEditionEnrolmentDataModel.equals(courseEditionEnrolmentDataModel2);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenComparingWithNull (){
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        boolean result = courseEditionEnrolmentDataModel.equals(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass (){
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        ProgrammeEditionEnrolmentDataModel programmeEditionEnrolmentDataModel = mock(ProgrammeEditionEnrolmentDataModel.class);

        //Act
        boolean result = courseEditionEnrolmentDataModel.equals(programmeEditionEnrolmentDataModel);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals (){
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 =
                new CourseEditionEnrolmentDataModel (_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble2,
                        LocalDate.of(2025, 4, 17), true);

        //Act
        boolean result = courseEditionEnrolmentDataModel.equals(courseEditionEnrolmentDataModel2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAHashCodeForOneId() {
        //Arrange
        createDoubles();
        boolean Status = true;

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        //Act
        int result = courseEditionEnrolmentDataModel.hashCode();

        //Assert
        assertNotNull (result);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);
        int courseEditionEnrolmentDataModel2 = courseEditionEnrolmentDataModel.hashCode();

        //Act
        int result = courseEditionEnrolmentDataModel.hashCode();

        //Assert
        assertEquals(courseEditionEnrolmentDataModel2, result);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        //Arrange
        createDoubles();
        boolean Status = true;
        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel = new CourseEditionEnrolmentDataModel(_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble, _enrolmentDateDouble, Status);

        CourseEditionEnrolmentDataModel courseEditionEnrolmentDataModel2 = new CourseEditionEnrolmentDataModel (_ceeGeneratedIDDataModelDouble, _ceeIDDataModelDouble2, _enrolmentDateDouble2, Status);

        //Act
        int result = courseEditionEnrolmentDataModel.hashCode();

        //Assert
        assertNotEquals(courseEditionEnrolmentDataModel2.hashCode(), result);
    }
}