package PAI.persistence.datamodel;


import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProgrammeEnrolmentIDDataModelTest {

    @Test
    public void testNoArgsConstructor() {
        //act
        ProgrammeEnrolmentIDDataModel enrolmentID = new ProgrammeEnrolmentIDDataModel();

        //assert
        assertNull(enrolmentID.getStudentID());
        assertNull(enrolmentID.getProgrammeID());
    }


    @Test
    public void testCreationAndGetters() {

        //arrange
        StudentIDDataModel studentIDDouble = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeIDDouble = mock(ProgrammeIDDataModel.class);

        //act
        ProgrammeEnrolmentIDDataModel enrolmentID = new ProgrammeEnrolmentIDDataModel(studentIDDouble, programmeIDDouble);

        //assert
        assertEquals(studentIDDouble, enrolmentID.getStudentID());
        assertEquals(programmeIDDouble, enrolmentID.getProgrammeID());
    }

    @Test
    public void testEqualsObjectsWithSameData() {

        //arrange
        StudentIDDataModel studentID = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);

        //act
        ProgrammeEnrolmentIDDataModel id1 = new ProgrammeEnrolmentIDDataModel(studentID, programmeID);
        ProgrammeEnrolmentIDDataModel id2 = new ProgrammeEnrolmentIDDataModel(studentID, programmeID);

        //assert
        assertEquals(id1, id2);
    }

    @Test
    public void testEqualsObjectsWithDifferentData() {

        //arrange
        StudentIDDataModel studentID1 = mock(StudentIDDataModel.class);
        StudentIDDataModel studentID2 = mock(StudentIDDataModel.class);

        ProgrammeIDDataModel programmeID1 = mock(ProgrammeIDDataModel.class);
        ProgrammeIDDataModel programmeID2 = mock(ProgrammeIDDataModel.class);

        //act
        ProgrammeEnrolmentIDDataModel id1 = new ProgrammeEnrolmentIDDataModel(studentID1, programmeID1);
        ProgrammeEnrolmentIDDataModel id2 = new ProgrammeEnrolmentIDDataModel(studentID2, programmeID2);

        //assert
        assertNotEquals(id1, id2);
    }

    @Test
    public void testEqualsSameInstance() {
        //arrange
        StudentIDDataModel studentID = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);

        //act
        ProgrammeEnrolmentIDDataModel id = new ProgrammeEnrolmentIDDataModel(studentID, programmeID);

        //assert
        assertEquals(id, id);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        //arrange
        StudentIDDataModel studentID = mock(StudentIDDataModel.class);
        ProgrammeIDDataModel programmeID = mock(ProgrammeIDDataModel.class);

        //act
        ProgrammeEnrolmentIDDataModel id = new ProgrammeEnrolmentIDDataModel(studentID, programmeID);

        //assert
        assertNotEquals(id, "NotAProgrammeEnrolmentIDDataModel"); // instanceof false
    }


    @Test
    public void testHashCodeConsistency() {

        //arrange
        StudentIDDataModel studentID1 = new StudentIDDataModel(1234567);
        StudentIDDataModel studentID2 = new StudentIDDataModel(1234567);

        String nameDM = "Ola";
        String acronymDM = "OLA";

        ProgrammeIDDataModel programmeID1 = new ProgrammeIDDataModel(nameDM, acronymDM);

        String nameDM2 = "Ola";
        String acronymDM2 = "OLA";

        ProgrammeIDDataModel programmeID2 = new ProgrammeIDDataModel(nameDM2, acronymDM2);

        //act
        ProgrammeEnrolmentIDDataModel id1 = new ProgrammeEnrolmentIDDataModel(studentID1, programmeID1);
        ProgrammeEnrolmentIDDataModel id2 = new ProgrammeEnrolmentIDDataModel(studentID2, programmeID2);

        //assert
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}