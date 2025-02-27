package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class US09_EnrolStudentInProgrammeControllerTest {
//
//    private StudentRepository _studentRepository;
//    private AccessMethodFactory _accessMethodFactory;
//    private AccessMethodRepository _accessMethodRepository;
//    private ProgrammeList _programmeList;
//    private ProgrammeEnrolmentRepository _programmeEnrolmentRepository;
//    private US09_EnrolStudentInProgrammeController _controller;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        _studentRepository = new StudentRepository();
//        _accessMethodFactory = new AccessMethodFactory();
//        _accessMethodRepository = new AccessMethodRepository(_accessMethodFactory);
//        ProgrammeFactory programmeFactory = mock(ProgrammeFactory.class);
//        _programmeList = new ProgrammeList(programmeFactory);
//        _programmeEnrolmentRepository = new ProgrammeEnrolmentRepository();
//        _controller = new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
//
//        // Register some students
//        Address address1 = new Address("Praceta do Sol, nº19", "3745-144", "Tomar", "Portugal");
//        _studentRepository.registerStudent(1, "Rita", "123456789", "963741258", "rita@gmail.com", address1);
//
//        Address address2 = new Address("Avenida de Braga, nº17", "4450-897", "Coimbra", "Portugal");
//        _studentRepository.registerStudent(2, "Pedro", "159753824", "963996987", "pedro@gmail.com", address2);
//
//        // Register some access methods
//        AccessMethod am1 = new AccessMethod("Maiores 23");
//        _accessMethodRepository.registerAccessMethod("Maiores 23");
//
//        AccessMethod am2 = new AccessMethod("CNA");
//        _accessMethodRepository.registerAccessMethod("CNA");
//
//        // Register some programmes
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        TeacherCategory assistantProfessor = new TeacherCategory("Assistant Professor");
//        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua São Tomé Porto", "4249-015", "Porto", "Portugal", "20-12-2010", assistantProfessor, 100, CSE);
//        _programmeList.registerProgramme("Computer Engineering", "CE", 20, 6, master, CSE, teacher);
//    }
//
//    @Test
//    void testEnrolStudentSuccessfully() throws Exception {
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
//        // Act
//        boolean result = _controller.enrolStudent(student, accessMethod, programme, "12-12-2025");
//
//        // Assert
//        assertTrue(result);
//    }
//
//    @Test
//    void testConstructorWithNullStudentRepository() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new US09_EnrolStudentInProgrammeController(null, _accessMethodRepository, _programmeList, _programmeEnrolmentRepository);
//        });
//        assertEquals("studentRepository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void testConstructorWithNullAccessMethodRepository() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new US09_EnrolStudentInProgrammeController(_studentRepository, null, _programmeList, _programmeEnrolmentRepository);
//        });
//        assertEquals("accessMethodRepository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void testConstructorWithNullProgrammeList() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, null, _programmeEnrolmentRepository);
//        });
//        assertEquals("programmeList cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void testConstructorWithNullEnrolmentRepository() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            new US09_EnrolStudentInProgrammeController(_studentRepository, _accessMethodRepository, _programmeList, null);
//        });
//        assertEquals("enrolmentRepository cannot be null.", exception.getMessage());
//    }
//
//    @Test
//    void testEnrolStudentWithUnregisteredAccessMethod() {
//        // Arrange
//        Optional<Student> studentOpt = _controller.getStudentByUniqueNumber(1);
//        Optional<AccessMethod> accessMethodOpt = _controller.getAccessMethodByName("UnregisteredMethod"); // Unregistered access method
//        Optional<Programme> programmeOpt = _controller.getProgrammeByName("Computer Engineering");
//
//        assertTrue(studentOpt.isPresent());
//        assertFalse(accessMethodOpt.isPresent()); // Ensure the access method is not registered
//        assertTrue(programmeOpt.isPresent());
//
//        Student student = studentOpt.get();
//        Programme programme = programmeOpt.get();
//
//        // Act + Assert
//        assertThrows(Exception.class, () -> _controller.enrolStudent(student, new AccessMethod("UnregisteredMethod"), programme, "12-12-2025"));
//    }
//
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
//
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
//
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
}