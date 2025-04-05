package PAI.domain.programme;

import PAI.VOs.*;
import PAI.domain.Department;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProgrammeTest {

    @Test
    void createAProgramme () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Programme programme = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //assert
        assertNotNull(programme);
    }

    @Test
    void shouldReturnFalseWhenProgrammeIsNotInDepartment() throws IllegalArgumentException {
        // arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        DepartmentID department2 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme programme = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        // act
        boolean result = programme.isInDepartment(department2);
        // assert
        assertFalse(result);
    }

//    @Test
//    void shouldReturnTrueWhenProgrammeIsInDepartment() throws IllegalArgumentException {
//        // arrange
//        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
//        Acronym acronym = mock(Acronym.class);
//        QuantEcts qtyEcts = mock(QuantEcts.class);
//        QuantSemesters qtySemesters = mock(QuantSemesters.class);
//        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
//        DepartmentID department1 = mock(DepartmentID.class);
//        DepartmentID department = mock(DepartmentID.class);
//        TeacherID programmeDirectorID = mock(TeacherID.class);
//        ProgrammeDDD programme = new ProgrammeDDD(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);
//        when(department.equals(department1)).thenReturn(true);
//        // act
//        boolean result = programme.isInDepartment(department);
//        // assert
//        assertTrue(result);
//    }

    @Test
    void shouldNotCreateProgrammeIfNameIsNull() throws IllegalArgumentException {
        //arrange
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(null, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);});
        //assert
        assertEquals("Programme name cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfAcronymIsNull() throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, null, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID);});
        //assert
        assertEquals("Acronym must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfQtyEctsIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, acronym, null, qtySemesters, degreeTypeID, department1, programmeDirectorID);});
        //assert
        assertEquals("Number of ECTS must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfQtySemestersIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, acronym, qtyEcts, null, degreeTypeID, department1, programmeDirectorID);});
        //assert
        assertEquals("Quantity of Semesters must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDegreeTypeIDIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, acronym, qtyEcts, qtySemesters, null, department1, programmeDirectorID);});
        //assert
        assertEquals("DegreeTypeID must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDepartmentIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, null, programmeDirectorID);});
        //assert
        assertEquals("Department must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDirectorIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, null);});
        //assert
        assertEquals("Insert a valid Programme Director", exception.getMessage());
    }

    //equals

    @Test
    void equalsProgrammeReturnTrue () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        Programme CEE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertTrue(result);
    }

    @Test
    void equalsProgrammeReturnFalseWhenProgrammeIsDifferent () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts qtyEcts1 = mock(QuantEcts.class);
        QuantEcts qtyEcts2 = mock(QuantEcts.class);
        QuantSemesters qtySemesters1 = mock(QuantSemesters.class);
        QuantSemesters qtySemesters2 = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID1 = mock(DegreeTypeID.class);
        DegreeTypeID degreeTypeID2 = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        Department department2 = mock(Department.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name1, acronym1, qtyEcts1,qtySemesters1,degreeTypeID1,department1, programmeDirectorID);
        Programme CEE = new Programme(name2, acronym2, qtyEcts2,qtySemesters2,degreeTypeID2,department1, programmeDirectorID);
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertFalse(result);
    }

    @Test
    void equalsProgrammeReturnFalseWhenProgrammeIsNull () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts qtyEcts1 = mock(QuantEcts.class);
        QuantEcts qtyEcts2 = mock(QuantEcts.class);
        QuantSemesters qtySemesters1 = mock(QuantSemesters.class);
        QuantSemesters qtySemesters2 = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID1 = mock(DegreeTypeID.class);
        DegreeTypeID degreeTypeID2 = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        Department department2 = mock(Department.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name1, acronym1, qtyEcts1,qtySemesters1,degreeTypeID1,department1, programmeDirectorID);
        Programme CEE = null;
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertFalse(result);
    }

    @Test
    void isEqualsReturnTrue () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        boolean result = CE.isEquals(CE.getProgrammeID());
        //assert
        assertTrue(result);
    }

    @Test
    void isEqualsReturnFalse () throws IllegalArgumentException {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        Programme CEE = new Programme(name1, acronym1, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        boolean result = CE.isEquals(CEE.getProgrammeID());
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenChangeDirector() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        TeacherID teacher1 = mock(TeacherID.class);
        //act
        boolean result = CE.newProgrammeDirector(teacher1);
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenChangeDirectorIsNull() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        TeacherID teacher1 = null;
        //act
        boolean result = CE.newProgrammeDirector(teacher1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnQtyEcts() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = new QuantEcts(6);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        TeacherID teacher1 = mock(TeacherID.class);
        //act
        QuantEcts result = CE.getQuantEcts();
        //assert
        assertEquals(qtyEcts, result);
    }

    @Test
    void shouldReturnQtySemesters() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = new QuantSemesters(6);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        TeacherID teacher1 = mock(TeacherID.class);
        //act
        QuantSemesters result = CE.getQuantSemesters();
        //assert
        assertEquals(qtySemesters, result);
    }

    @Test
    void shouldReturnTrueIfNameIsIdentical() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        Programme CEE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        boolean result = CE.hasThisProgrammeName(CEE.getProgrammeName());
        //assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseIfNameIsNotIdentical() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name1, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        Programme CEE = new Programme(name2, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        boolean result = CE.hasThisProgrammeName(CEE.getProgrammeName());
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAcronym() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        Acronym result = CE.getAcronym();
        //assert
        assertEquals(acronym, result);
    }

    @Test
    void shouldReturnDegreeTypeID() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        DegreeTypeID result = CE.getDegreeTypeID();
        //assert
        assertEquals(degreeTypeID, result);
    }
    @Test
    void shouldReturnDepartment() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        DepartmentID result = CE.getDepartment();
        //assert
        assertEquals(department1, result);
    }

    @Test
    void shouldReturnProgrammeDirectorID() throws Exception {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        Programme CE = new Programme(name, acronym, qtyEcts,qtySemesters,degreeTypeID,department1, programmeDirectorID);
        //act
        TeacherID result = CE.getProgrammeDirectorID();
        //assert
        assertEquals(programmeDirectorID, result);
    }

    @Test
    void testIdentityWithSameID() {
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Programme programme = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);



        assertEquals(programmeID, programme.identity());
    }

    @Test
    void testIdentityWithDifferentID() {
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme programme = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);



        assertNotEquals(programmeID, programme.identity());
    }

    @Test
    void testSameAsReturnsTrueForSameObject() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        Programme programme = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);

        //act + assert
        assertTrue(programme.sameAs(programme));
    }

    @Test
    void testSameAsReturnsFalseForDifferentObjectFromSameClass() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        Programme programme1 = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);
        Programme programme2 = mock(Programme.class);
        //act + assert
        assertFalse(programme1.sameAs(programme2));
    }

    @Test
    void testSameAsReturnsFalseForNullObject() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        Programme programme1 = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);
        Programme programme2 = null;
        //act + assert
        assertFalse(programme1.sameAs(programme2));
    }

    @Test
    void testSameAsReturnsFalseForDifferentClassObject() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);

        Programme programme1 = new Programme(name,acronym, qtyEcts,qtySemesters,degreeTypeID, department1, programmeDirectorID);
        String string = "hello";
        //act + assert
        assertFalse(programme1.sameAs(string));
    }

    @Test
    void hasThisProgrammeNameShouldReturnFalseForNull() {
        //arrange
        Programme programme = mock(Programme.class);
        //act + assert
        assertFalse(programme.hasThisProgrammeName(null));
    }

    @Test
    void isEqualsShouldReturnFalseForNull() {
        //arrange
        Programme programme = mock(Programme.class);
        //act + assert
        assertFalse(programme.isEquals(null));
    }
    @Test
    void programmeIDShouldBeBasedOnNameAndAcronym() {
        //arrange
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("Computer Science");
        Acronym acronym = new Acronym("CS");
        Programme programme = new Programme(name, acronym, mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class));
        ProgrammeID expectedId = new ProgrammeID(name, acronym);
        //act + assert
        assertEquals(expectedId, programme.identity());
    }

    @Test
    void newProgrammeDirectorShouldUpdateDirector() throws Exception {

        TeacherID newDirector = mock(TeacherID.class);
        Programme programme = new Programme( mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class),mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class));

        programme.newProgrammeDirector(newDirector);
        assertEquals(newDirector, programme.getProgrammeDirectorID());
    }
}
