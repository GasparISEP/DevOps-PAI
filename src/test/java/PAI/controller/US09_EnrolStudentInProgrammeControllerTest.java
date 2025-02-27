package PAI.controller;

import PAI.domain.*;
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
    private AccessMethodRepository _accessMethodRepository;
    private static AccessMethod _accessMethod;
    private ProgrammeList _programmeList;
    private static Programme _programme;
    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;
    private Address address1;
    private US09_EnrolStudentInProgrammeController _controller;

    @BeforeEach
    void setUp() throws Exception {
        _studentRepository = mock(StudentRepository.class);
        _accessMethodRepository = mock(AccessMethodRepository.class);
        _programmeList = mock(ProgrammeList.class);
        _programmeEnrolmentRepository = mock(ProgrammeEnrolmentRepository.class);
        _student = mock(Student.class);
        _accessMethod = mock(AccessMethod.class);
        _programme = mock(Programme.class);


        // Register some students
        address1 = mock(Address.class);
        _studentRepository.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);

        Address address2 = mock(Address.class);
        _studentRepository.registerStudent(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);

//        // Register some access methods
//        AccessMethod am1 = new AccessMethod("Maiores 23");
//        _accessMethodRepository.registerAccessMethod("Maiores 23");
//
//        AccessMethod am2 = new AccessMethod("CNA");
//        _accessMethodRepository.registerAccessMethod("CNA");
//
//        // Register some programmes
//        DegreeType master = mock(DegreeType.class);
//        Department CSE = mock(Department.class);
//        Teacher teacher = mock(Teacher.class);
//        _programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
    }

    @Test
    void shouldCreateUS09Controller() {
        //arrange

        //act
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);

    }

    @Test
    void shouldReturnExceptionIfStudentRepositoryIsNull() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(null, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfAccessMethodRepositoryIsNull() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, null, _programmeList, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfProgrammeListIsNull() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, null, _programmeEnrolmentRepository));
    }

    @Test
    void shouldReturnExceptionIfProgrammeEnrolmentRepositoryIsNull() {
        //arrange

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, null));
    }

    @Test
    void shouldReturnStudentByUniqueNumber() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getStudentByUniqueNumber(1)).thenReturn(Optional.of(_student));

        //act
        Optional<Student> result = _controller.getStudentByUniqueNumber(1);

        //assert
        assertEquals(result, Optional.of(_student));
    }

    @Test
    void shouldReturnAccessMethodByName() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getAccessMethodByName("Maiores 23")).thenReturn(Optional.of(_accessMethod));

        //act
        Optional<AccessMethod> result = _controller.getAccessMethodByName("Maiores 23");

        //assert
        assertEquals(result, Optional.of(_accessMethod));
    }

    @Test
    void shouldReturnProgrammeByName() {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.getProgrammeByName("xpto")).thenReturn(Optional.of(_programme));

        //act
        Optional<Programme> result = _controller.getProgrammeByName("xpto");


        //assert
        assertEquals(result, Optional.of(_programme));
    }

    @Test
    void shouldEnrolStudentInProgramme() throws Exception {
        //arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
        when(_controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024")).thenReturn(true);

        //act + assert
        assertTrue(_controller.enrolStudent(_student, _accessMethod, _programme, "12-12-2024"));
    }


    static Stream<Arguments> shouldThrowExceptionWithDifferentInvalidInputsParameterizedTest() {
        return Stream.of(
                Arguments.of(null, _accessMethod, _programme, "12-12-2024"),
                Arguments.of(_student, null, _programme, "12-12-2024"),
                Arguments.of(_student, _accessMethod, null, "12-12-2024"),
                Arguments.of(_student, _accessMethod, _programme, null),
                Arguments.of(_student, _accessMethod, _programme, "")
        );
    }

    @ParameterizedTest
    @MethodSource("shouldThrowExceptionWithDifferentInvalidInputsParameterizedTest")
    void shouldThrowExceptionWithDifferentInputs(Student student, AccessMethod am1, Programme p1, String date) {
        // arrange
        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);

        // act + assert
        assertThrows(Exception.class, () -> _controller.enrolStudent(student, am1, p1, date));
    }
}








//    @Test
//    void shouldReturnStudentByUniqueNumber() {
//        //arrange
//        Optional<Student> optionalStudent = Optional.of(_student);
//        when(_studentRepository.getStudentByUniqueNumber(1)).thenReturn(optionalStudent);
//
//        //act
//        Optional<Student> resultStudent = _studentRepository.getStudentByUniqueNumber(1);
//
//        //assert
//        assertTrue(resultStudent.isPresent());
//        assertEquals(_student, resultStudent.get());
//    }
//
//    @Test
//    void testGetStudentByUniqueNumber_notFound() {
//        // Define the behavior of the mock repository
//        when(_studentRepository.getStudentByUniqueNumber(2)).thenReturn(Optional.empty());
//
//        // Call the method under test
//        Optional<Student> result = _studentRepository.getStudentByUniqueNumber(2);
//
//        // Verify the result
//        assertFalse(result.isPresent());
//    }

//    @Test
//    void testEnrolStudentSuccessfully() throws Exception {
//        // Arrange
//        Optional<Student> studentOpt = _controller.getStudentByUniqueNumber(1);
//        Optional<AccessMethod> accessMethodOpt = _controller.getAccessMethodByName("Maiores 23");
//        Optional<Programme> programmeOpt = _controller.getProgrammeByName("Computer Engineering");
//
//        Student student = studentOpt.get();
//        AccessMethod accessMethod = accessMethodOpt.get();
//        Programme programme = programmeOpt.get();
//
//        // Act
//        boolean result = _controller.enrolStudent(student, accessMethod, programme, "12-12-2025");
//
//        // Assert
//        assertTrue(result);
//    }

//    @Test
//    void testEnrolStudentWithNullParameters() {
//        // Arrange
//        Optional<Student> studentOpt = _controller.getStudentByUniqueNumber(1);
//        Optional<AccessMethod> accessMethodOpt = _controller.getAccessMethodByName("Maiores 23");
//        Optional<Programme> programmeOpt = _controller.getProgrammeByName("Computer Engineering");
//
//        assertTrue(studentOpt.isPresent());
//        assertTrue(accessMethodOpt.isPresent());
//        assertTrue(programmeOpt.isPresent());
//
//        Student student = studentOpt.get();
//        AccessMethod accessMethod = accessMethodOpt.get();
//        Programme programme = programmeOpt.get();
//
//        // Act + Assert
//        assertThrows(Exception.class, () -> _controller.enrolStudent(null, accessMethod, programme, "12-12-2025"));
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, null, programme, "12-12-2025"));
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, accessMethod, null, "12-12-2025"));
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, accessMethod, programme, null));
//    }

//    @Test
//    void testEnrolStudentWithInvalidDateFormat() {
//        // Arrange
//        Optional<Student> studentOpt = _controller.getStudentByUniqueNumber(1);
//        Optional<AccessMethod> accessMethodOpt = _controller.getAccessMethodByName("Maiores 23");
//        Optional<Programme> programmeOpt = _controller.getProgrammeByName("Computer Engineering");
//
//        assertTrue(studentOpt.isPresent());
//        assertTrue(accessMethodOpt.isPresent());
//        assertTrue(programmeOpt.isPresent());
//
//        Student student = studentOpt.get();
//        AccessMethod accessMethod = accessMethodOpt.get();
//        Programme programme = programmeOpt.get();
//
//        // Act + Assert
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, accessMethod, programme, "invalid-date"));
//    }

//    @Test
//    void testEnrolStudentFailure() throws Exception {
//        // Arrange
//        Optional<Student> studentOpt = _controller.getStudentByUniqueNumber(1);
//        Optional<AccessMethod> accessMethodOpt = _controller.getAccessMethodByName("Maiores 23");
//        Optional<Programme> programmeOpt = _controller.getProgrammeByName("Computer Engineering");
//
//        assertTrue(studentOpt.isPresent());
//        assertTrue(accessMethodOpt.isPresent());
//        assertTrue(programmeOpt.isPresent());
//
//        Student student = studentOpt.get();
//        AccessMethod accessMethod = accessMethodOpt.get();
//        Programme programme = programmeOpt.get();
//
//        // Simulate enrolment failure by creating a new instance of EnrolmentRepository that always returns false
//        ProgrammeEnrolmentRepository failingEnrolmentRepository = new ProgrammeEnrolmentRepository() {
//            @Override
//            public boolean enrolStudents(Student s, AccessMethod am, Programme p, String date) {
//                return false;
//            }
//        };
//
//        US09_EnrolStudentInProgrammeController controllerWithFailingRepo = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, failingEnrolmentRepository);
//
//        // Act
//        boolean result = controllerWithFailingRepo.enrolStudent(student, accessMethod, programme, "12-12-2025");
//
//        // Assert
//        assertFalse(result);
//    }

