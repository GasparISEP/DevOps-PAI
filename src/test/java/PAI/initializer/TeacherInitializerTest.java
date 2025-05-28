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
                "AAA", "AAA", "teacherAAA@isep.ipp.pt", "112233445", "911234569",
                "Mestrado em Engenharia Informática", "Rua Numero 1", "4000-100",
                "Porto", "Portugal", "DEI", "01-02-2022",
                "c201a88d-e139-4aea-80f7-5d342c7fac2a", 20, "+351"
        );

        verify(controller).registerTeacher(
                "AAB", "AAB", "teacherAAB@isep.ipp.pt", "112233446", "911234570",
                "Mestrado em Engenharia Eletrotécnica", "Praceta Numero 2", "4000-101",
                "Porto", "Portugal", "DEM", "02-02-2022",
                "bc4640e6-b793-4db3-9a5f-baa5006453a1", 50, "+351"
        );

        verify(controller).registerTeacher(
                "AAC", "AAC", "teacherAAC@isep.ipp.pt", "112233447", "911234571",
                "Mestrado em Engenharia de Sistemas", "Rua Numero 3", "4000-102",
                "Porto", "Portugal", "DEI", "03-02-2022",
                "aeb0be5c-1b9f-4f51-ace3-35a572a52c2e", 100, "+351"
        );
    }
}