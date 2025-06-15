package PAI.initializer;

import PAI.controller.US08_IWantToRegisterAStudentInTheSystemController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class StudentInitializerTest {

    @Mock
    private US08_IWantToRegisterAStudentInTheSystemController controller;

    @InjectMocks
    private StudentInitializer initializer;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/Student_Data.csv";
    }

    @Test
    void shouldInitializeAndRegisterStudentsFromCsv() throws Exception {
        // act
        initializer.loadStudents(controller, _csvPath);

        // assert
        verify(controller).registerStudent(
                 "Student AAA", "112233445", "Portugal", "+351", "911234567",
                "studentAAA@gmail.com", "Rua Numero 1", "4000-100", "Porto", "Portugal"
        );

        verify(controller).registerStudent(
                 "Student AAB", "112233446", "Portugal", "+351", "911234568",
                "studentAAB@gmail.com", "Praceta Numero 2", "4000-101", "Porto", "Portugal"
        );

        verify(controller).registerStudent(
                 "Student AAC", "112233447", "Portugal", "+351", "911234569",
                "studentAAC@gmail.com", "Rua Numero 3", "4000-102", "Porto", "Portugal"
        );

    }
}