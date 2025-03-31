package PAI.domain;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.factory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentTest {

    // Creation of actual attributes for tests without isolation
    private class AttributesForTestsWithoutIsolation {
        Address _address;
        AddressVO _addressVO;
        StudentID _studentID;
        Student _student;
        AccessMethod _accessMethod;
        DegreeType _degreeType;
        Department _department;
        TeacherCategory _teacherCategory;
        IAddressFactory _addressFactory;
        ITeacherCareerProgressionFactory _tcpFactory;
        ITeacherCareerProgressionListFactory _tcpListFactory;
        Teacher _teacher;
        IProgrammeCourseListFactory _programmeCourseListFactory;
        Programme _programme;
        ICourseInStudyPlanFactory _I_courseInStudyPlanFactory;
        IStudyPlanListFactory _I_studyPlanListFactory;
        IStudyPlanFactory _I_studyPlanFactory;
        ICourseFactory _I_courseFactory;
        Date _date;
        TeacherCategoryID _tcID;
        WorkingPercentage _wp;
        Street _street;
        PostalCode _postalCode;
        Location _location;
        Country _country;
        TeacherID _teacherID;
        TeacherAcronym _teacherAcronym;
        Name _name;
        Email _email;
        NIF _nif;
        PhoneNumber _phoneNumber;
        AcademicBackground _academicBackground;

        AttributesForTestsWithoutIsolation() throws Exception {
            _street = new Street("Praceta do Sol, nº19");
            _postalCode = new PostalCode("3745-144");
            _location = new Location("Tomar");
            _country = new Country("Portugal");
            _addressVO = new AddressVO(_street, _postalCode, _location, _country);
            _address = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
            _studentID = new StudentID(1234567);
            _student = new Student(_studentID, "Rita", "123456789", "963741258", "rita@gmail.com", _address);
            _accessMethod = new AccessMethod("M1");
            _degreeType = new DegreeType("Master", 240);
            _department = new Department("CSE", "Computer Science Engineer");
            _addressFactory = new AddressFactoryImpl();
            _tcpFactory = new TeacherCareerProgressionFactoryImpl();
            _tcpListFactory = new TeacherCareerProgressionListFactoryImpl();
            _date = new Date("20-12-2010");
            _wp = new WorkingPercentage(100);
            _teacherID = TeacherID.createNew();
            _teacherAcronym = new TeacherAcronym("ABC");
            _name = new Name("Joe Doe");
            _email = new Email("abc@isep.ipp.pt");
            _nif = new NIF("123456789");
            _phoneNumber = new PhoneNumber("+351", "912 345 678");
            _academicBackground = new AcademicBackground("Doutoramento em Engenharia Informática, 2005, ISEP");
            _tcID = new TeacherCategoryID();
            _teacher = new Teacher(_teacherAcronym, _name, _email, _nif, _phoneNumber, _academicBackground, _addressVO, _department);
            _programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
            _I_courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            _I_studyPlanListFactory = new StudyPlanListFactoryImpl();
            _I_studyPlanFactory = new StudyPlanFactoryImpl();
            _I_courseFactory = new CourseFactoryImpl();
            _programme = new Programme("Computer Engineering", "CE", 20, 6, _degreeType, _department, _teacher, _programmeCourseListFactory, _I_courseInStudyPlanFactory, _I_studyPlanListFactory, _I_studyPlanFactory, _I_courseFactory);
        }
    }


    // Method to initialize attributes for tests without isolation
    private AttributesForTestsWithoutIsolation createActualAttributesForTestsWithoutIsolation() throws Exception {
        return new AttributesForTestsWithoutIsolation();
    }

    // Method to create doubles for tests with isolation
    private Object[] createDoublesForTestsWithIsolation() {
        Student student = mock(Student.class);
        AccessMethod am = mock(AccessMethod.class);
        Programme programme = mock(Programme.class);
        Date date = mock(Date.class);
        return new Object[]{student, am, programme, date};
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, attributes._date);
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
    }

    @Test
    void shouldReturnExceptionIfStudentNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, attributes._accessMethod, attributes._programme, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfStudentNullWithIsolation () {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, accessMethodDouble, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(attributes._student, null, attributes._programme, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullWithIsolation () {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, null, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(attributes._student, attributes._accessMethod, null, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullWithIsolation () {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Date dateDouble = (Date) doubles[3];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, null, dateDouble));
    }

    @Test
    void invalidDateDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, null));
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Student student2 = new Student(attributes._studentID, "Rita", "123456789", "963741258", "rita@gmail.com", attributes._address);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameStudent(student2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.equals(studentDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment.hasSameStudent(studentDouble);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        StudentID studentID = new StudentID(1234568);
        Student student2 = new Student(studentID, "Pedro", "159753824", "963996987", "pedro@gmail.com", attributes._address);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameStudent(student2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        Student studentDouble2 = mock(Student.class);
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble2)).thenReturn(false);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble)).thenReturn(true);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        StudentID studentID = new StudentID(1345678);

        Student student2 = new Student(studentID, "Pedro", "159753824", "963996987", "pedro@gmail.com", attributes._address);
        Student student1 = new Student(attributes._studentID, "Rita", "123456789", "963741258", "rita@gmail.com", attributes._address);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1, attributes._accessMethod, attributes._programme,attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, attributes._accessMethod, attributes._programme,attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        Student studentDouble2 = mock(Student.class);
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble2)).thenReturn(false);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, attributes._degreeType, attributes._department, attributes._teacher, attributes._programmeCourseListFactory, attributes._I_courseInStudyPlanFactory, attributes._I_studyPlanListFactory, attributes._I_studyPlanFactory, attributes._I_courseFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, programme2,attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.equals(studentDouble)).thenReturn(true);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameEnrolment(programmeEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        StudentID studentID = new StudentID(1345678);

        Student student2 = new Student(studentID, "Pedro", "159753824", "963996987", "pedro@gmail.com", attributes._address);

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, attributes._degreeType, attributes._department, attributes._teacher, attributes._programmeCourseListFactory, attributes._I_courseInStudyPlanFactory, attributes._I_studyPlanListFactory, attributes._I_studyPlanFactory, attributes._I_courseFactory);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2, attributes._accessMethod, programme2,attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble)).thenReturn(false);
        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameEnrolment(programmeEnrolment);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Programme programme2 = new Programme("Computer Engineering", "CE", 20, 6, attributes._degreeType, attributes._department, attributes._teacher, attributes._programmeCourseListFactory, attributes._I_courseInStudyPlanFactory, attributes._I_studyPlanListFactory, attributes._I_studyPlanFactory, attributes._I_courseFactory);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme,attributes._date);
        //act
        boolean result = programmeEnrolment.hasSameProgramme(programme2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertTrue(result);
    }

    /*
    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Date date = new Date("20-12-2010");
         WorkingPercentage wp = new WorkingPercentage(100);
        TeacherID teacherID = TeacherID.createNew();
        TeacherCategoryID tcID = new TeacherCategoryID();
        DegreeType dt = new DegreeType("Master", 240);
        Department dpt = new Department("CSE", "Space Science Engineer");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", attributes._addressFactory, date, tcID, wp, teacherID, dpt, attributes._tcpFactory, attributes._tcpListFactory);

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, dt, dpt, teacher, attributes._programmeCourseListFactory, attributes._I_courseInStudyPlanFactory, attributes._I_studyPlanListFactory, attributes._I_studyPlanFactory, attributes._I_courseFactory);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student, attributes._accessMethod, attributes._programme, attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programme2);

        //assert
        assertFalse(result);
    }
    */

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameWithIsolation() {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        Student studentDouble = (Student) doubles[0];
        AccessMethod accessMethodDouble = (AccessMethod) doubles[1];
        Programme programmeDouble = (Programme) doubles[2];
        Date dateDouble = (Date) doubles[3];

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertFalse(result);
    }
}