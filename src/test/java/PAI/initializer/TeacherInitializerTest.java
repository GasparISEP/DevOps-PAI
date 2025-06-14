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
                "AAA", "Alexandra Castro", "AAA@isep.ipp.pt", "112233445", "911234569",
                "Bachelor in Astronomy", "Rua Número 1", "4000-100",
                "Porto", "Portugal", "AAU", "01-02-2022",
                "4b68bc54-1a5f-4d90-af6f-43d65e3a166d", 25, "+351"
        );

        verify(controller).registerTeacher(
                "AAB", "Raquel Pinho-Nogueira", "AAB@isep.ipp.pt", "112233446", "911234570",
                "Master in Astronomy", "Praceta Número 2", "4000-101",
                "Porto", "Portugal", "AAA", "02-02-2022",
                "582f96e9-16c1-4dec-a871-9353b59ceb6a", 100, "+351"
        );

        verify(controller).registerTeacher(
                "AAC", "Alexandra Costa", "AAC@isep.ipp.pt", "112233447", "911234571",
                "Master in History", "Rua Número 3", "4000-102","Porto","Portugal","AAF",
                "03-02-2022","f937e46e-8b73-4975-93fa-8dfb7640cebc",75,"+351"
        );
    }
}