package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class StudentGradeIDDataModelTest {
    @Test
    void shouldCreateDataModelWithEmptyConstructor(){
        //arrange
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel();
        //assert
        assertNotNull(studentGradeIDDataModel);
    }
    @Test
    void shouldCreateDataModelWithStudentIDAndCourseIDDM(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        //assert
        assertNotNull(studentGradeIDDataModel);
    }

    @Test
    void shouldNotCreateDataModelWhenStudentIDIsNull(){
        //arrange
        StudentIDDataModel studentIDDataModel = null;
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        //assert
        assertThrows(IllegalArgumentException.class, ()-> new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel));
    }

    @Test
    void shouldNotCreateDataModelWhenCourseEditionIDDIsNull(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = null;
        //assert
        assertThrows(IllegalArgumentException.class, ()-> new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel));
    }
    @Test
    void shouldReturnStudentIDDataModel (){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        //act
        StudentIDDataModel result = studentGradeIDDataModel.get_studentIDDataModel();
        //assert
        assertEquals(studentIDDataModel,result);
    }

    @Test
    void shouldReturnCourseEditionIDDataModel (){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        //act
        CourseEditionIDDataModel result = studentGradeIDDataModel.get_courseEditionIDDataModel();
        //assert
        assertEquals(courseEditionIDDataModel,result);
    }

    //equals

    @Test
    void ShouldReturnTrueWhenInSameLoc(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        StudentGradeIDDataModel studentGradeIDDataModel1 = studentGradeIDDataModel;
        //act
        boolean result = studentGradeIDDataModel.equals(studentGradeIDDataModel1);
        //assert
        assertTrue(result);
    }

    @Test
    void ShouldReturnFalseWhenFromDifferentInstances(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        Object o = mock(Object.class);
        //act
        boolean result = studentGradeIDDataModel.equals(o);
        //assert
        assertFalse(result);
    }

    @Test
    void ShouldReturnTrueWhenSameContent(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        StudentGradeIDDataModel studentGradeIDDataModel1 = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        //act
        boolean result = studentGradeIDDataModel.equals(studentGradeIDDataModel1);
        //assert
        assertTrue(result);
    }

    @Test
    void ShouldReturnFalseWhenDifferentContent(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        StudentIDDataModel studentIDDataModel1 = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel1 = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        StudentGradeIDDataModel studentGradeIDDataModel1 = new StudentGradeIDDataModel(studentIDDataModel1,courseEditionIDDataModel1);
        //act
        boolean result = studentGradeIDDataModel.equals(studentGradeIDDataModel1);
        //assert
        assertFalse(result);
    }
    @Test
    void returnValidHashCode(){
        //arrange
        StudentIDDataModel studentIDDataModel = mock(StudentIDDataModel.class);
        CourseEditionIDDataModel courseEditionIDDataModel = mock(CourseEditionIDDataModel.class);
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel(studentIDDataModel,courseEditionIDDataModel);
        //act
        int result = studentGradeIDDataModel.hashCode();
        //assert
        assertEquals(studentIDDataModel.hashCode()+courseEditionIDDataModel.hashCode(),result);
    }

}