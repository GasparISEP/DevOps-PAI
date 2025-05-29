package PAI.initializer;

import PAI.controller.US13_RegisterTeacherAndRelevantDataController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class TeacherInitializerTest {

    @Mock
    private US13_RegisterTeacherAndRelevantDataController controller;

    @InjectMocks
    private TeacherInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInitializeAndRegisterTeachersFromCsv() throws Exception {
        // act
        initializer.loadDataRegisterTeacher(controller).run();

        // assert
        verify(controller).registerTeacher(
                "AAA", "AAA", "AAA@isep.ipp.pt", "112233445", "911234569",
                "Bachelor in Astronomy", "Rua Numero 1", "4000-100",
                "Porto", "Portugal", "AAA", "01-02-2022",
                "c201a88d-e139-4aea-80f7-5d342c7fac2a", 25, "+351"
        );

    }
}