package PAI.initializer;

import PAI.VOs.*;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentInitializerTest {

    @Mock
    private IProgrammeEditionEnrolmentService service;

    @InjectMocks
    private ProgrammeEditionEnrolmentInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadProgrammeEditionEnrolments() throws Exception {
        // Act
        initializer.loadProgrammeEditionEnrolments(service).run();

        // Assert: pelo menos uma chamada deve ter sido feita
        verify(service, atLeastOnce()).enrolStudentInProgrammeEdition(any(StudentID.class), any(ProgrammeEditionID.class));
    }

    @Test
    void shouldPrintStackTraceWhenServiceIsNull() throws Exception {
        // Capture stderr
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            initializer.loadProgrammeEditionEnrolments(null).run();
        } finally {
            System.setErr(originalErr);
        }

        String output = errContent.toString();
        assertTrue(output.contains("NullPointerException"), "Expected stack trace with NullPointerException");
    }

    @Test
    void shouldHandleServiceException() throws Exception {
        doThrow(new RuntimeException("exploded"))
                .when(service).enrolStudentInProgrammeEdition(any(StudentID.class), any(ProgrammeEditionID.class));

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            initializer.loadProgrammeEditionEnrolments(service).run();
        } finally {
            System.setErr(originalErr);
        }

        String output = errContent.toString();
        assertTrue(output.contains("exploded"), "Expected exception message in stderr");
    }

    @Test
    void shouldPrintLoadingTime() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            initializer.loadProgrammeEditionEnrolments(service).run();
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("ProgrammeEditionEnrolment loading time:"));
    }
}

