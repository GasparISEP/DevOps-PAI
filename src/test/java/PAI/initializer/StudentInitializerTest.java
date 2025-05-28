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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeAndRegisterStudentsFromCsv() throws Exception {
        // act
        initializer.loadDataRegisterStudent(controller).run();

        // assert
        verify(controller).registerStudent(
                1102840, "Student AAA", "112233445", "Portugal", "+351", "911234567",
                "studentAAA@gmail.com", "Rua Numero 1", "4000-100", "Porto", "Portugal"
        );

        verify(controller).registerStudent(
                1102841, "Student AAB", "112233446", "Portugal", "+351", "911234568",
                "studentAAB@gmail.com", "Praceta Numero 2", "4000-101", "Porto", "Portugal"
        );

        verify(controller).registerStudent(
                1102842, "Student AAC", "112233447", "Portugal", "+351", "911234569",
                "studentAAC@gmail.com", "Rua Numero 3", "4000-102", "Porto", "Portugal"
        );

    }
}