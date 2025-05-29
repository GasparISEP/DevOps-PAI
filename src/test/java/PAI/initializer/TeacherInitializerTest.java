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

        verify(controller).registerTeacher(
                "AAB", "AAB", "AAB@isep.ipp.pt", "112233446", "911234570",
                "Master in Astronomy", "Praceta Numero 2", "4000-101",
                "Porto", "Portugal", "AAA", "02-02-2022",
                "bc4640e6-b793-4db3-9a5f-baa5006453a1", 50, "+351"
        );

        verify(controller).registerTeacher(
                "AAC", "AAC", "AAC@isep.ipp.pt", "112233447", "911234571",
                "PhD in Astronomy", "Rua Numero 3", "4000-102",
                "Porto", "Portugal", "AAA", "03-02-2022",
                "aeb0be5c-1b9f-4f51-ace3-35a572a52c2e", 100, "+351"
        );
    }
}