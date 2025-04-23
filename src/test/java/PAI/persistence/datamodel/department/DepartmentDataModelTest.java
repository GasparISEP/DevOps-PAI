package PAI.persistence.datamodel.department;

import PAI.persistence.datamodel.TeacherIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentDataModelTest {

    //testing empty constructor
    @Test
    void shouldReturnEmptyConstructor() {
        //arrange
        //act
        DepartmentDataModel dataModel = new DepartmentDataModel();
        //assert
        assertNotNull(dataModel);
    }

    //testing constructor with name and acronym attributes
    @Test
    void  shouldReturnValidDepartmentDataModelWhenNameAndAcronymAreProvided() {
        //arrange
        String name= "Software Engineer Department";
        String acronym= "SED";
        //act
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        //assert
        assertNotNull(dataModel);
    }

    //testing constructor with name, acronym, and director
    @Test
    void shouldReturnValidDepartmentDataModelWhenNameAcronymAndDirectorAreProvided() {
        //arrange
        String name= "Software Engineer Department";
        String acronym= "SED";
        TeacherIDDataModel teacherIDDataModel= mock(TeacherIDDataModel.class);
        //act
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym, teacherIDDataModel);
        //assert
        assertNotNull(dataModel);
    }

    //testing get methods
    @Test
    void shouldReturnDepartmentDataModelID() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        // Act
        DepartmentIDDataModel idDataModel = dataModel.getId();
        // Assert
        assertEquals(acronym, idDataModel.getDepartmentID());
    }

    @Test
    void shouldReturnDepartmentDataModelName() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        // Act
        String nameDataModel = dataModel.getName();
        // Assert
        assertEquals("Software Engineer Department",nameDataModel);
    }

    @Test
    void shouldReturnDepartmentDataModelAcronym() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        // Act
        String acronymDataModel = dataModel.getAcronym();
        // Assert
        assertEquals("SED",acronymDataModel);
    }

    @Test
    void shouldReturnDepartmentDataModelDirectorID() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        TeacherIDDataModel directorID = mock(TeacherIDDataModel.class);
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym, directorID);
        // Act
        TeacherIDDataModel directorIdDataModel = dataModel.getDirectorId();
        // Assert
        assertEquals(directorID,directorIdDataModel);
    }

    //testing set method
    @Test
    void shouldSetDirectorIdWhenValidDirectorIsProvided() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel model = new DepartmentDataModel(name, acronym);
        TeacherIDDataModel directorID = mock(TeacherIDDataModel.class);
        // Act
        model.setDirectorId(directorID);
        // Assert
        assertEquals(directorID, model.getDirectorId());
    }

    //testing equals method
    @Test
    void shouldReturnTrueWhenTwoObjectsHaveTheSameReferenceMemory() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        // Act & Assert
        assertTrue(dataModel.equals(dataModel));
    }

    @Test
    void shouldReturnTrueWhenTwoObjectsAreEquals() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel1 = new DepartmentDataModel(name, acronym);
        DepartmentDataModel dataModel2 = new DepartmentDataModel(name, acronym);
        // Act & Assert
        assertTrue(dataModel1.equals(dataModel2));
    }

    @Test
    void shouldReturnFalseWhenTheObjectIsNull() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel = new DepartmentDataModel(name, acronym);
        // Act & Assert
        assertFalse(dataModel.equals(null));
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotInstanceOfTheSameClass() {
        // Arrange
        String name = "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel model = new DepartmentDataModel(name, acronym);
        String otherObject = "Not a department";
        // Act & Assert
        assertFalse(model.equals(otherObject));
    }

    @Test
    void shouldReturnFalseWhenTwoObjectsAreNotEquals() {
        // Arrange
        String name1= "Software Engineer Department";
        String acronym1 = "SED";
        String name2= "Biomedical Engineer Department";
        String acronym2= "BED";
        DepartmentDataModel dataModel1 = new DepartmentDataModel(name1, acronym1);
        DepartmentDataModel dataModel2 = new DepartmentDataModel(name2, acronym2);
        // Act & Assert
        assertFalse(dataModel1.equals(dataModel2));
    }

    //testing hash code method
    @Test
    void shouldReturnAHashCodeForOneId() {
        // Arrange
        String name= "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel model = new DepartmentDataModel(name, acronym);
        // Act
        int hashCode = model.hashCode();
        // Assert
        assertNotNull(hashCode);
    }

    @Test
    void shouldReturnTheSameHashCodeForTwoIDs() {
        // Arrange
        String name= "Software Engineer Department";
        String acronym = "SED";
        DepartmentDataModel dataModel1 = new DepartmentDataModel(name, acronym);
        DepartmentDataModel dataModel2 = new DepartmentDataModel(name, acronym);

        // Act
        int hashCode1 = dataModel1.hashCode();
        int hashCode2 = dataModel2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void shouldReturnADifferentHashCodeForTwoIDs() {
        // Arrange
        String name1= "Software Engineer Department";
        String acronym1 = "SED";
        String name2= "Biomedical Engineer Department";
        String acronym2= "BED";
        DepartmentDataModel dataModel1 = new DepartmentDataModel(name1, acronym1);
        DepartmentDataModel dataModel2 = new DepartmentDataModel(name2, acronym2);
        // Act
        int hashCode1 = dataModel1.hashCode();
        int hashCode2 = dataModel2.hashCode();
        // Assert
        assertNotEquals(hashCode1, hashCode2);
    }
}