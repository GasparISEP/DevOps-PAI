package PAI.initializer;

import PAI.controller.US09_EnrolStudentInProgrammeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class ProgrammeEnrolmentInitializerTest {

    @Mock
    private US09_EnrolStudentInProgrammeController controller;

    @InjectMocks
    private ProgrammeEnrolmentInitializer initializer;

    private String _csvPath;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        _csvPath = "src/main/resources/ProgrammeEnrolment_Data.csv";
    }

    @Test
    void shouldInitializeAndEnrolStudentsFromCsv() throws Exception {
        // Act: run the CommandLineRunner returned by loadDataProgrammeEnrolment
        initializer.loadProgrammeEnrolment(controller, _csvPath);

        // Assert: verify the controller was called with the expected data from the CSV

        verify(controller).enrolStudentInProgramme(
                1310558,
                UUID.fromString("8e1aee43-2c2f-408f-95f6-5a6d1d3aefab"),
                "Software Engineering",
                "SWE",
                "20-02-2021"
        );

        verify(controller).enrolStudentInProgramme(
                1794265,
                UUID.fromString("d956f24e-693c-470f-a3b0-0ea361c1131e"),
                "Mechanical Engineering",
                "MEE",
                "03-06-2020"
        );

        verify(controller).enrolStudentInProgramme(
                1977653,
                UUID.fromString("9ce44c1c-01e7-4553-8c6b-7bcf7d63e7a5"),
                "Electrical Engineering",
                "ELE",
                "05-08-2021"
        );

        verify(controller).enrolStudentInProgramme(
                1664999,
                UUID.fromString("8372eb5a-e67e-4a2b-9c68-7ac6a7c4f4f0"),
                "Civil Engineering",
                "CIV",
                "09-05-2020"
        );
    }
}