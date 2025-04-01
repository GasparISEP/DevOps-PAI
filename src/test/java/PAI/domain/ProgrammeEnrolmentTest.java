package PAI.domain;

import PAI.VOs.*;
import PAI.domain.accessMethodDDD.AccessMethodDDDFactoryImpl;
import PAI.domain.programme.ProgrammeDDD;
import PAI.VOs.Location;
import PAI.factory.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeEnrolmentTest {

    // Creation of actual attributes for tests without isolation
    private class AttributesForTestsWithoutIsolation {
        Address _address;
        AddressVO _addressVO;
        StudentID _studentID;
        Name _name;
        NIF _nif;
        PhoneNumber _phone;
        Email _email;
        StudentAcademicEmail _academicEmail;
        Student _student;
        AccessMethod _accessMethod;
        AccessMethodID _accessMethodID;
        DegreeType _degreeType;
        DegreeType_ID _degreeTypeID;
        Department _department;
        IAddressFactory _addressFactory;
        ITeacherCareerProgressionFactory _tcpFactory;
        ITeacherCareerProgressionListFactory _tcpListFactory;
        Teacher _teacher;
        IProgrammeCourseListFactory _programmeCourseListFactory;
        ProgrammeDDD _programme;
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
            _name = new Name("Rita");
            _nif = new NIF("123456789", _country);
            _phone = new PhoneNumber("+351", "963741258");
            _email = new Email("rita@gmail.com");
            _academicEmail = new StudentAcademicEmail(_studentID);
            _student = new Student(_studentID, _name, _nif, _phone, _email, _address, _academicEmail);
            _accessMethod = new AccessMethod("M1");
            _accessMethodID = new AccessMethodID();
            _degreeType = new DegreeType("Master", 240);
            _degreeTypeID = new DegreeType_ID("Master");
            _department = new Department("CSE", "Computer Science Engineer");
            _addressFactory = new AddressFactoryImpl();
            _tcpFactory = new TeacherCareerProgressionFactoryImpl();
            _tcpListFactory = new TeacherCareerProgressionListFactoryImpl();
            _date = new Date("20-12-2010");
            _wp = new WorkingPercentage(100);
            _teacherID = TeacherID.createNew();
            _teacherAcronym = new TeacherAcronym("ABC");
            _phoneNumber = new PhoneNumber("+351", "912 345 678");
            _academicBackground = new AcademicBackground("Doutoramento em Engenharia Informática, 2005, ISEP");
            _tcID = new TeacherCategoryID();
            _teacher = new Teacher(_teacherAcronym, _name, _email, _nif, _phoneNumber, _academicBackground, _addressVO, _department);
            _programmeCourseListFactory = new ProgrammeCourseListFactoryImpl();
            _I_courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
            _I_studyPlanListFactory = new StudyPlanListFactoryImpl();
            _I_studyPlanFactory = new StudyPlanFactoryImpl();
            _I_courseFactory = new CourseFactoryImpl();
            _programme = new ProgrammeDDD(new NameWithNumbersAndSpecialChars("Computer Engineering"), new Acronym("CE"),
                    new QuantEcts(20), new QuantSemesters(6), _degreeTypeID, _department, _teacherID);}
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
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);
    }

    @Test
    void constructorAlwaysCreatesAnObjectWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act
        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //assert
        assertNotNull(programmeEnrolment);
    }

    @Test
    void shouldReturnExceptionIfStudentNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date));
    }

    @Test
    void shouldReturnExceptionIfStudentNullWithIsolation () {
        //arrange
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(null, accessMethodDouble, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(attributes._student.identity(), null, attributes._programme.getProgrammeID(), attributes._date));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodNullWithIsolation () {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, null, programmeDouble, dateDouble));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullWithoutIsolation () throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID,null, attributes._date));
    }

    @Test
    void shouldReturnExceptionIfProgrammeNullWithIsolation () {
        //arrange
        Object[] doubles = createDoublesForTestsWithIsolation();
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        Date dateDouble = (Date) doubles[3];

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, null, dateDouble));
    }

    @Test
    void invalidDateDoesNotCreateObjectAndThrowsExceptionWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, null));
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        Student student2 = new Student(attributes._studentID, attributes._name, attributes._nif, attributes._phone, attributes._email, attributes._address, attributes._academicEmail);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(student2.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameStudent(student2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfStudentIsTheSameFromEnrolmentWithIsolation() {
        // arrange
        StudentID studentDoubleID = mock(StudentID.class);
        Student studentDouble = mock(Student.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        when(studentDouble.identity()).thenReturn(studentDoubleID);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDoubleID, accessMethodDouble, programmeDouble, dateDouble);

        // act
        boolean result = programmeEnrolment.hasSameStudent(studentDouble);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        StudentID studentID = new StudentID(1234568);
        Name name = new Name("Pedro");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("159753824", country);
        PhoneNumber phone = new PhoneNumber("+351", "963996987");
        Email email = new Email("pedro@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student2 = new Student(studentID, name, nif, phone, email, attributes._address, academicEmail);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameStudent(student2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfStudentIsNotTheSameFromEnrolmentWithIsolation() {
        //arrange

        StudentID studentDouble = mock(StudentID.class);
        Student studentDouble2 = mock(Student.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolmentDouble = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

        //act
        boolean result = programmeEnrolmentDouble.hasSameStudent(studentDouble2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfEnrolmentHasTheSameStudentAndTheSameProgrammeWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);

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
        Name name = new Name("Pedro");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("159753824", country);
        PhoneNumber phone = new PhoneNumber("+351", "963996987");
        Email email = new Email("pedro@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);

        Student student2 = new Student(studentID, name, nif, phone, email, attributes._address, academicEmail);
        Student student1 = new Student(attributes._studentID, attributes._name, attributes._nif, attributes._phone, attributes._email, attributes._address, attributes._academicEmail);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(student1.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasDifferentStudentsButTheSameProgrammeWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        StudentID studentDouble2 = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble, dateDouble);

        when(studentDouble.isEquals(studentDouble2)).thenReturn(false);
       // when(programmeDouble.equals(programmeDouble)).thenReturn(true);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        TeacherID teacherIDDouble =  attributes._teacherID;

        ProgrammeDDD programme2 = new ProgrammeDDD(new NameWithNumbersAndSpecialChars("Computer Engineering"), new Acronym("CE"),
                new QuantEcts(20), new QuantSemesters(6), new DegreeType_ID("asd"), new Department("ACM", "Maths"), teacherIDDouble);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, programme2.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasTheSameStudentButDifferentProgrammesWithIsolation() {
        // arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble2, dateDouble);

        Mockito.when(studentDouble.isEquals(studentDouble)).thenReturn(true);
        //Mockito.when(programmeDouble.equals(programmeDouble2)).thenReturn(false);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithoutIsolation() throws Exception {
        //arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();

        StudentID studentID = new StudentID(1345678);
        Name name = new Name("Pedro");
        String countryName = "Portugal";
        Country country = new Country(countryName);
        NIF nif = new NIF("159753824", country);
        PhoneNumber phone = new PhoneNumber("+351", "963996987");
        Email email = new Email("pedro@gmail.com");
        StudentAcademicEmail academicEmail = new StudentAcademicEmail(studentID);
        TeacherID teacherIDDouble =  attributes._teacherID;

        Student student2 = new Student(studentID, name, nif, phone, email, attributes._address, academicEmail);

        ProgrammeDDD programme2 = new ProgrammeDDD(new NameWithNumbersAndSpecialChars("Computer Engineering"), new Acronym("CE"),
                new QuantEcts(20), new QuantSemesters(6), new DegreeType_ID("asd"), new Department("ACM", "Maths"), teacherIDDouble);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(student2.identity(), attributes._accessMethodID, programme2.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfEnrolmentHasBothDifferentStudentsAndDifferentProgrammesWithIsolation() {
        // arrange
        StudentID studentDouble = mock(StudentID.class);
        StudentID studentDouble2 = mock(StudentID.class); // Different student
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeID programmeDouble = mock(ProgrammeID.class);
        ProgrammeID programmeDouble2 = mock(ProgrammeID.class); // Different programme
        Date dateDouble = mock(Date.class);

        ProgrammeEnrolment programmeEnrolment1 = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDouble, dateDouble);
        ProgrammeEnrolment programmeEnrolment2 = new ProgrammeEnrolment(studentDouble2, accessMethodDouble, programmeDouble2, dateDouble);

        // act
        boolean result = programmeEnrolment1.hasSameEnrolment(programmeEnrolment2);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithoutIsolation() throws Exception {
        //Arrange
        AttributesForTestsWithoutIsolation attributes = createActualAttributesForTestsWithoutIsolation();
        TeacherID teacherIDDouble =  attributes._teacherID;

        ProgrammeDDD programme2 = new ProgrammeDDD(new NameWithNumbersAndSpecialChars("Computer Engineering"), new Acronym("CE"),
                new QuantEcts(20), new QuantSemesters(6), new DegreeType_ID("asd"), new Department("ACM", "Maths"), teacherIDDouble);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, programme2.getProgrammeID(), attributes._date);

        //Act
        boolean result = programmeEnrolment.hasSameProgramme2(programme2.getProgrammeID());

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnTrueIfProgrammesAreTheSameWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        ProgrammeDDD programmeDouble = mock(ProgrammeDDD.class);
        Date dateDouble = mock(Date.class);
        ProgrammeID programmeDoubleID = mock(ProgrammeID.class);
        when(programmeDouble.getProgrammeID()).thenReturn(programmeDoubleID);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDoubleID, dateDouble);

        //act
        boolean result = programmeEnrolment.hasSameProgramme2(programmeDouble.getProgrammeID());

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
        Teacher teacher = new Teacher("ASC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "+351 912 345 678", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", attributes._addressFactory, date, tcID, wp, teacherID, dpt, attributes._tcpFactory, attributes._tcpListFactory);

        Programme programme2 = new Programme("Space Engineering", "SE", 20, 6, dt, dpt, teacher, attributes._programmeCourseListFactory, attributes._I_courseInStudyPlanFactory, attributes._I_studyPlanListFactory, attributes._I_studyPlanFactory, attributes._I_courseFactory);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(attributes._student.identity(), attributes._accessMethodID, attributes._programme.getProgrammeID(), attributes._date);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programme2);

        //assert
        assertFalse(result);
    }
    */

    @Test
    void shouldReturnFalseIfProgrammesAreNotTheSameWithIsolation() {
        //arrange
        StudentID studentDouble = mock(StudentID.class);
        AccessMethodID accessMethodDouble = mock(AccessMethodID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeID programmeDoubleID = mock(ProgrammeID.class);
        Programme programmeDouble = mock(Programme.class);

        ProgrammeEnrolment programmeEnrolment = new ProgrammeEnrolment(studentDouble, accessMethodDouble, programmeDoubleID, dateDouble);

        when(programmeDouble.isEquals(programmeDouble)).thenReturn(false);

        //act
        boolean result = programmeEnrolment.hasSameProgramme(programmeDouble);

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnStudentIDfromGetter() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getStudentID().equals(studentIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnAccessMethodIDfromGetter() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getAccessMethodID().equals(accessMethodIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeIDfromGetter() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getProgrammeID().equals(programmeIDDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnDatefromGetter() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.getDate().equals(dateDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnProgrammeEnrolmentID() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);
        ProgrammeEnrolmentID expectedPeID = pe1.identity();

        //Act
        ProgrammeEnrolmentID result = pe1.identity();

        //Assert
        assertEquals(expectedPeID, result);
    }

    @Test
    void shouldReturnTrueForSameProgrammeEnrolment() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.sameAs(pe1);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseForDifferentProgrammeEnrolment() {
        //Arrange
        StudentID studentIDDouble = mock(StudentID.class);
        AccessMethodID accessMethodIDDouble = mock(AccessMethodID.class);
        ProgrammeID programmeIDDouble = mock(ProgrammeID.class);
        Date dateDouble = mock(Date.class);
        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);
        ProgrammeEnrolment pe2 = new ProgrammeEnrolment(studentIDDouble, accessMethodIDDouble, programmeIDDouble, dateDouble);

        //Act
        boolean result = pe1.sameAs(pe2);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnHashCodeProgrammeEnrolment() {
        //Arrange
        StudentID studentID = new StudentID(1241204);
        AccessMethodID accessMethodID = new AccessMethodID();
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars("ola");
        Acronym acronym = new Acronym("HI");
        ProgrammeID programmeID = new ProgrammeID(name, acronym);
        Date dateDouble = new Date(LocalDate.now());

        ProgrammeEnrolment pe1 = new ProgrammeEnrolment(studentID, accessMethodID, programmeID, dateDouble);
        ProgrammeEnrolment pe2 = new ProgrammeEnrolment(studentID, accessMethodID, programmeID, dateDouble);

        //Act + Assert
        assertNotEquals(pe1.hashCode(), pe2.hashCode());
    }
}