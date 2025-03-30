package PAI.controller;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.repository.AccessMethodRepository;
import PAI.repository.ProgrammeEnrolmentRepository;
import PAI.repository.ProgrammeRepository;
import PAI.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class US09_EnrolStudentInProgrammeControllerTest {

    private StudentRepository _studentRepository;
    private static Student _student;
    private StudentID _studentID;
    private AccessMethodRepository _accessMethodRepository;
    private static AccessMethod _accessMethod;
    private ProgrammeRepository _programmeList;
    private static Programme _programme;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;
    private Address address1;
    private Name _name;
    private NIF _nif;
    private PhoneNumber _phone;
    private Email _email;
    private StudentAcademicEmail _academicEmail;
    private US09_EnrolStudentInProgrammeController _controller;

    @BeforeEach
    void setUp() throws Exception {
        _studentRepository = mock(StudentRepository.class);
        _accessMethodRepository = mock(AccessMethodRepository.class);
        _programmeList = mock(ProgrammeRepository.class);
        _programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        _student = mock(Student.class);
        _accessMethod = mock(AccessMethod.class);
        _programme = mock(Programme.class);


        // Register some students
        address1 = mock(Address.class);
        _studentID = mock(StudentID.class);
        _name = mock(Name.class);
        _nif = mock(NIF.class);
        _phone = mock(PhoneNumber.class);
        _email = mock(Email.class);
        _academicEmail = mock(StudentAcademicEmail.class);
        _studentRepository.registerStudent(_studentID, _name, _nif, _phone, _email, address1, _academicEmail);

        Address address2 = mock(Address.class);
        StudentID mockStudentID2 = mock(StudentID.class);
        Name nameDouble2 = mock(Name.class);
        NIF nifDouble2 = mock(NIF.class);
        PhoneNumber phoneDouble2 = mock(PhoneNumber.class);
        Email emailDouble2 = mock(Email.class);
        StudentAcademicEmail academicEmailDouble2 = new StudentAcademicEmail(mockStudentID2);
        _studentRepository.registerStudent(mockStudentID2, nameDouble2, nifDouble2, phoneDouble2, emailDouble2, address2, academicEmailDouble2);
    }

    //quando vai buscar

    @Test
    void shouldCreateUS09ControllerTest() {
        //arrange

        //act
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);

    }

    @Test
    void shouldReturnExceptionIfStudentRepositoryIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(null, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodRepositoryIsNullTest() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, null, _programmeList, _programmeEnrolmentRepository));
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
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, null));
    }

    @Test
    void shouldReturnOptionalWithStudentByUniqueNumberTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getStudentByUniqueNumber(_studentID)).thenReturn(Optional.of(_student));

        //act
        Optional<Student> result = _controller.getStudentByUniqueNumber(_studentID);

        //assert
        assertEquals(result, Optional.of(_student));
    }

    @Test
    void shouldReturnOptionalEmpty_WhenGettingStudentWithUniqueNumberDoesNotExistTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getStudentByUniqueNumber(_studentID)).thenReturn(Optional.empty());

        //act
        Optional<Student> result = _controller.getStudentByUniqueNumber(_studentID);

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptional_WithAccessMethodByNameTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByName("Maiores 23")).thenReturn(Optional.of(_accessMethod));

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByName("Maiores 23");

        //assert
        assertEquals(result, Optional.of(_accessMethod));
    }

    @Test
    void shouldReturnOptionalEmptyWhenGettingAccessMethodByNameDoesNotExistTest() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByName("Maiores 23")).thenReturn(Optional.empty());

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByName("Maiores 23");

        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalProgrammeByNameTest() {
        //arrange
        NameWithNumbersAndSpecialChars name = mock(NameWithNumbersAndSpecialChars.class);

        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
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
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getProgrammeByName(name)).thenReturn(Optional.empty());

        //act
        Optional<Programme> result = _controller.getProgrammeByName(name);


        //assert
        assertTrue(result.isEmpty());
    }

//    @Test
//    void shouldEnrolStudentInProgrammeTest() throws Exception {
//        //arrange
//        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
//        when(_controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024")).thenReturn(true);
//
//        //act + assert
//        assertTrue(_controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024"));
//    }
//
//
//    static Stream<Arguments> shouldThrowExceptionWithDifferentInvalidInputsParameterizedTest() {
//        return Stream.of(
//                Arguments.of(null, _accessMethod, _programme, "12-12-2024"),
//                Arguments.of(_student, null, _programme, "12-12-2024"),
//                Arguments.of(_student, _accessMethod, null, "12-12-2024"),
//                Arguments.of(_student, _accessMethod, _programme, null),
//                Arguments.of(_student, _accessMethod, _programme, "")
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("shouldThrowExceptionWithDifferentInvalidInputsParameterizedTest")
//    void shouldThrowExceptionWithDifferentInputs(Student student, AccessMethod am1, Programme p1, String date) {
//        // arrange
//        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
//
//        // act + assert
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, am1, p1, date));
//    }
//
//    @Test
//    void shouldThrowExceptionWhenStudentIsAlreadyEnroledInProgrammeTest() throws Exception {
//        //arrange
//        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
//        when(_controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024")).thenThrow(new Exception("Student is already enrolled in the programme."));
//
//        //act + assert
//        assertThrows(Exception.class, () -> _controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024"));
//    }
}

