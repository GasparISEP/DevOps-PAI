package PAI.domain.programme;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ProgrammeTest {

    @Test
    void createAProgramme() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //assert
        assertNotNull(programme);
    }

    @Test
    void shouldReturnFalseWhenProgrammeIsNotInDepartment() {
        // arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        DepartmentID department2 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        // act
        boolean result = programme.isInDepartment(department2);
        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueWhenProgrammeIsInDepartment() {
        // arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department, programmeDirectorID, programmeID);

        // act
        boolean result = programme.isInDepartment(department);
        // assert
        assertTrue(result);
    }

    @Test
    void shouldNotCreateProgrammeIfNameIsNull() {
        //arrange
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(null, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("Programme name cannot be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfAcronymIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, null, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("Acronym must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfQtyEctsIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, null, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("Number of ECTS must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfQtySemestersIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, qtyEcts, null, degreeTypeID, department1, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("Quantity of Semesters must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDegreeTypeIDIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, qtyEcts, qtySemesters, null, department1, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("DegreeTypeID must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDepartmentIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, null, programmeDirectorID, programmeID);
        });
        //assert
        assertEquals("DepartmentID must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfDirectorIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, null, programmeID);
        });
        //assert
        assertEquals("TeacherID must not be null", exception.getMessage());
    }

    @Test
    void shouldNotCreateProgrammeIfProgrammeIDIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, null);
        });
        //assert
        assertEquals("ProgrammeID must not be null", exception.getMessage());
    }

    //equals

    @Test
    void equalsProgrammeReturnTrue() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertTrue(result);
    }

    @Test
    void equalsProgrammeReturnFalseWhenProgrammeIsDifferent() {
        //arrange
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2 = mock(Acronym.class);
        QuantEcts qtyEcts1 = mock(QuantEcts.class);
        QuantEcts qtyEcts2 = mock(QuantEcts.class);
        QuantSemesters qtySemesters1 = mock(QuantSemesters.class);
        QuantSemesters qtySemesters2 = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID1 = mock(DegreeTypeID.class);
        DegreeTypeID degreeTypeID2 = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        Programme CE = new Programme(name1, acronym1, qtyEcts1, qtySemesters1, degreeTypeID1, department1, programmeDirectorID, programmeID1);
        Programme CEE = new Programme(name1, acronym2, qtyEcts2, qtySemesters2, degreeTypeID2, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertFalse(result);
    }

    @Test
    void equalsProgrammeReturnFalseWhenProgrammeIsNull() {
        //arrange
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts qtyEcts1 = mock(QuantEcts.class);
        QuantSemesters qtySemesters1 = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID1 = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name1, acronym1, qtyEcts1, qtySemesters1, degreeTypeID1, department1, programmeDirectorID, programmeID);
        Programme CEE = null;
        //act
        boolean result = CE.equals(CEE);
        //assert
        assertFalse(result);
    }

    @Test
    void isEqualsReturnTrue() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.isEquals(CE.getProgrammeID());
        //assert
        assertTrue(result);
    }

    @Test
    void isEqualsReturnFalse() {
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
        ProgrammeID programmeID = mock(ProgrammeID.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID1);
        Programme CEE = new Programme(name1, acronym1, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        TeacherID teacher1 = null;
        //act
        boolean result = CE.newProgrammeDirector(teacher1);
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnQtyEcts() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = new QuantEcts(6);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        QuantEcts result = CE.getQuantEcts();
        //assert
        assertEquals(qtyEcts, result);
    }

    @Test
    void shouldReturnQtySemesters() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = new QuantSemesters(6);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        QuantSemesters result = CE.getQuantSemesters();
        //assert
        assertEquals(qtySemesters, result);
    }

    @Test
    void shouldReturnTrueIfNameIsIdentical() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisProgrammeName(CEE.getProgrammeName());
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNameIsNotIdentical() {
        //arrenge
        NameWithNumbersAndSpecialChars name1 = mock(NameWithNumbersAndSpecialChars.class);
        NameWithNumbersAndSpecialChars name2 = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name1, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name2, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisProgrammeName(CEE.getProgrammeName());
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnAcronym() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        Acronym result = CE.getAcronym();
        //assert
        assertEquals(acronym, result);
    }

    @Test
    void shouldReturnDegreeTypeID() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        DegreeTypeID result = CE.getDegreeTypeID();
        //assert
        assertEquals(degreeTypeID, result);
    }

    @Test
    void shouldReturnDepartment() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        DepartmentID result = CE.getDepartment();
        //assert
        assertEquals(department1, result);
    }

    @Test
    void shouldReturnProgrammeDirectorID() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);


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
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);


        assertNotEquals(programmeID1, programme.identity());
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
        ProgrammeID programmeID = mock(ProgrammeID.class);

        Programme programme = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);

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
        ProgrammeID programmeID = mock(ProgrammeID.class);

        Programme programme1 = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        ProgrammeID programmeID = mock(ProgrammeID.class);

        Programme programme1 = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        ProgrammeID programmeID = mock(ProgrammeID.class);

        Programme programme1 = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
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
        ProgrammeID expectedId = new ProgrammeID(name, acronym);
        Programme programme = new Programme(name, acronym, mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), expectedId);

        //act + assert
        assertEquals(expectedId, programme.identity());
    }

    @Test
    void newProgrammeDirectorShouldUpdateDirector() throws Exception {

        TeacherID newDirector = mock(TeacherID.class);
        Programme programme = new Programme(mock(NameWithNumbersAndSpecialChars.class), mock(Acronym.class), mock(QuantEcts.class), mock(QuantSemesters.class), mock(DegreeTypeID.class), mock(DepartmentID.class), mock(TeacherID.class), mock(ProgrammeID.class));

        programme.newProgrammeDirector(newDirector);
        assertEquals(newDirector, programme.getProgrammeDirectorID());
    }

    @Test
    void shouldReturnTrueIfAcronymIsIdentical() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisProgrammeAcronym(CEE.getAcronym());
        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfAcronymIsNotIdentical() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        Acronym acronym2     = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym1, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym2, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisProgrammeAcronym(CEE.getAcronym());
        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfDegreeTypeIDIsIdentical() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisDegreeTypeID(CEE.getDegreeTypeID());
        //assert
        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfDegreeTypeIDIsNotIdentical() {
        //arrenge
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        Acronym acronym1 = mock(Acronym.class);
        QuantEcts qtyEcts = mock(QuantEcts.class);
        QuantSemesters qtySemesters = mock(QuantSemesters.class);
        DegreeTypeID degreeTypeID = mock(DegreeTypeID.class);
        DegreeTypeID degreeTypeID1 = mock(DegreeTypeID.class);
        DepartmentID department1 = mock(DepartmentID.class);
        TeacherID programmeDirectorID = mock(TeacherID.class);
        ProgrammeID programmeID = mock(ProgrammeID.class);
        Programme CE = new Programme(name, acronym1, qtyEcts, qtySemesters, degreeTypeID, department1, programmeDirectorID, programmeID);
        Programme CEE = new Programme(name, acronym1, qtyEcts, qtySemesters, degreeTypeID1, department1, programmeDirectorID, programmeID);
        //act
        boolean result = CE.hasThisDegreeTypeID(CEE.getDegreeTypeID());
        //assert
        assertFalse(result);
    }

}
