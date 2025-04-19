package PAI.persistence.datamodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentGradeIDDataModelTest {
    @Test
    void shouldCreateDataModelWithEmptyConstructor(){
        //arrange
        StudentGradeIDDataModel studentGradeIDDataModel = new StudentGradeIDDataModel();
        //assert
        assertNotNull(studentGradeIDDataModel);
    }

}