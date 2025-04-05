package PAI.controller;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.programme.Programme;
import PAI.repository.ProgrammeEnrolmentRepository;
import PAI.repository.StudentRepository;
import PAI.repository.accessMethodRepository.AccessMethodRepositoryImpl;
import PAI.repository.programmeRepository.ProgrammeDDDRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class US09_EnrolStudentInProgrammeControllerTest {

    private StudentRepository _studentRepository;
    private static Student _student;
    private StudentID _studentID;
    private AccessMethodRepositoryImpl _accessMethodRepository;
    private static AccessMethod _accessMethod;
    private AccessMethodID _accessMethodID;
    private ProgrammeDDDRepositoryImpl _programmeRepository;
    private static Programme _programme;
    private ProgrammeID _programmeID;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;
    private static Date _date;
    private Street _street;
    private PostalCode _postalCode;
    private Location _location;
    private Country _country;
    private Name _name;
    private NameWithNumbersAndSpecialChars _nameWithNumbersAndSpecialChars;
    private NIF _nif;
    private PhoneNumber _phone;
    private Email _email;
    private StudentAcademicEmail _academicEmail;
    private US09_EnrolStudentInProgrammeController _controller;

    @BeforeEach
    void setUp() throws Exception {
        _studentRepository = mock(StudentRepository.class);
        _accessMethodRepository = mock(AccessMethodRepositoryImpl.class);
        _programmeRepository = mock(ProgrammeDDDRepositoryImpl.class);
        _programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        _student = mock(Student.class);
        _accessMethod = mock(AccessMethod.class);
        _accessMethodID = mock(AccessMethodID.class);
        _nameWithNumbersAndSpecialChars = mock(NameWithNumbersAndSpecialChars.class);
        _programme = mock(Programme.class);
        _programmeID = mock(ProgrammeID.class);
        _date = mock(Date.class);

        // Register some students
        _street = mock(Street.class);
        _postalCode = mock(PostalCode.class);
        _location = mock(Location.class);
        _country = mock(Country.class);
        _studentID = mock(StudentID.class);
        _name = mock(Name.class);
        _nif = mock(NIF.class);
        _phone = mock(PhoneNumber.class);
        _email = mock(Email.class);
        _academicEmail = mock(StudentAcademicEmail.class);
        _studentRepository.registerStudent(_studentID, _name, _nif, _phone, _email, _street, _postalCode, _location, _country, _academicEmail);

        Street streetDouble2 = mock(Street.class);
        PostalCode postalCodeDouble2 = mock(PostalCode.class);
        Location locationDouble2 = mock(Location.class);
        Country countryDouble2 = mock(Country.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble2 = mock(Name.class);
        NIF nifDouble2 = mock(NIF.class);
        PhoneNumber phoneDouble2 = mock(PhoneNumber.class);
        Email emailDouble2 = mock(Email.class);
        StudentAcademicEmail academicEmailDouble2 = new StudentAcademicEmail(mockStudentID2);
        _studentRepository.registerStudent(mockStudentID2, nameDouble2, nifDouble2, phoneDouble2, emailDouble2, streetDouble2, postalCodeDouble2, locationDouble2, countryDouble2, academicEmailDouble2);
    }

    @Test
    void shouldCreateUS09ControllerTest() {
        //arrange

        //act
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);

    }

    @Test
    void shouldReturnExceptionIfStudentRepositoryIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(null, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodRepositoryIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, null, _programmeRepository, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, null, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnException_IfProgrammeEnrolmentRepositoryIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, null));
    }

    @Test
    void shouldReturnOptionalWithStudentByUniqueNumberTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getStudentByUniqueNumber(_studentID)).thenReturn(Optional.of(_student));

        //act
        Optional<Student> result = _controller.getStudentByUniqueNumber(_studentID);

        //assert
        assertEquals(result, Optional.of(_student));
    }

    @Test
    void shouldReturnOptionalEmpty_WhenGettingStudentWithUniqueNumberDoesNotExistTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getStudentByUniqueNumber(_studentID)).thenReturn(Optional.empty());

        //act
        Optional<Student> result = _controller.getStudentByUniqueNumber(_studentID);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptional_WithAccessMethodByNameTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByName(_nameWithNumbersAndSpecialChars)).thenReturn(Optional.of(_accessMethod));

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByName(_nameWithNumbersAndSpecialChars);

        //assert
        assertEquals(result, Optional.of(_accessMethod));
    }

    @Test
    void shouldReturnOptionalEmptyWhenGettingAccessMethodByNameDoesNotExistTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByName(_nameWithNumbersAndSpecialChars)).thenReturn(Optional.empty());

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByName(_nameWithNumbersAndSpecialChars);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptional_WithAccessMethodByIDTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByID(_accessMethodID)).thenReturn(Optional.of(_accessMethod));

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByID(_accessMethodID);

        //assert
        assertEquals(result, Optional.of(_accessMethod));
    }

    @Test
    void shouldReturnOptionalProgrammeByNameTest() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getProgrammeByName(name)).thenReturn(Optional.of(_programme));

        //act
        Optional<Programme> result = _controller.getProgrammeByName(name);


        //assert
        assertEquals(result, Optional.of(_programme));
    }

    @Test
    void shouldReturnOptionalEmptyWhenGettingProgrammeByNameTest() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getProgrammeByName(name)).thenReturn(Optional.empty());

        //act
        Optional<Programme> result = _controller.getProgrammeByName(name);


        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalProgrammeByIDTest() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.getProgrammeByID(_programmeID)).thenReturn(Optional.of(_programme));

        //act
        Optional<Programme> result = _controller.getProgrammeByID(_programmeID);


        //assert
        assertEquals(result, Optional.of(_programme));
    }

    @Test
    void shouldEnrolStudentInProgrammeTest() throws Exception {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        when(_controller.enrolStudentInProgramme(_studentID, _accessMethodID, _programmeID, _date)).thenReturn(true);

        //act + assert
        assertTrue(_controller.enrolStudentInProgramme(_studentID, _accessMethodID, _programmeID, _date));
    }

    @Test
    void shouldThrowExceptionWhenStudentIsNullTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {_controller.enrolStudentInProgramme(null, _accessMethodID, _programmeID, _date);});
        //assert
        assertEquals("Student cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAccessMethodIsNullTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {_controller.enrolStudentInProgramme(_studentID, null, _programmeID, _date);});
        //assert
        assertEquals("Access method cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeIsNullTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {_controller.enrolStudentInProgramme(_studentID, _accessMethodID, null, _date);});
        //assert
        assertEquals("Programme cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNullTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {_controller.enrolStudentInProgramme(_studentID, _accessMethodID, _programmeID, null);});
        //assert
        assertEquals("Date cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsEmptyTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeRepository, _programmeEnrolmentRepository);
        //act
        Exception exception = assertThrows(Exception.class, () -> {_controller.enrolStudentInProgramme(_studentID, _accessMethodID, _programmeID, null);});
        //assert
        assertEquals("Date cannot be null or empty", exception.getMessage());
    }
}

