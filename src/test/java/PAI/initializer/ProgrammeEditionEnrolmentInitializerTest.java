package PAI.initializer;

import PAI.VOs.*;
import PAI.service.programmeEditionEnrolment.IProgrammeEditionEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProgrammeEditionEnrolmentInitializerTest {

    @Mock
    private IProgrammeEditionEnrolmentService service;

    @InjectMocks
    private ProgrammeEditionEnrolmentInitializer initializer;

    private File tempCsv;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        tempCsv = File.createTempFile("ProgrammeEditionEnrolmentInitializerTest", ".csv");
        tempCsv.deleteOnExit();
    }

    private void writeCsvContent(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempCsv))) {
            writer.write(content);
        }
    }

    @Test
    void testLoadProgrammeEditionEnrolments() throws Exception {
        String csvContent = "studentNumber,programmeAcronym,schoolYearUUID,enrolmentUUID\n" +
                "1234567,LTI," + UUID.randomUUID() + "," + UUID.randomUUID() + "\n";
        writeCsvContent(csvContent);

        initializer.loadProgrammeEditionEnrolment(service, tempCsv.getAbsolutePath());

        verify(service, times(1)).enrolStudentInProgrammeEdition(any(StudentID.class), any(ProgrammeEditionID.class));
    }

    @Test
    void shouldPrintStackTraceWhenServiceIsNull() throws Exception {
        String csvContent = "studentNumber,programmeAcronym,schoolYearUUID,enrolmentUUID\n" +
                "1234567,LTI," + UUID.randomUUID() + "," + UUID.randomUUID() + "\n";
        writeCsvContent(csvContent);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            initializer.loadProgrammeEditionEnrolment(null, tempCsv.getAbsolutePath());
        } finally {
            System.setErr(originalErr);
        }

        String output = errContent.toString();
        assertTrue(output.contains("NullPointerException"), "Expected stack trace with NullPointerException");
    }

    @Test
    void shouldHandleServiceException() throws Exception {
        String csvContent = "studentNumber,programmeAcronym,schoolYearUUID,enrolmentUUID\n" +
                "1234567,LTI," + UUID.randomUUID() + "," + UUID.randomUUID() + "\n";
        writeCsvContent(csvContent);

        doThrow(new RuntimeException("exploded"))
                .when(service).enrolStudentInProgrammeEdition(any(StudentID.class), any(ProgrammeEditionID.class));

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            initializer.loadProgrammeEditionEnrolment(service, tempCsv.getAbsolutePath());
        } finally {
            System.setErr(originalErr);
        }

        String output = errContent.toString();
        assertTrue(output.contains("exploded"), "Expected exception message in stderr");
    }

    @Test
    void shouldPrintLoadingTime() throws Exception {
        String csvContent = "studentNumber,programmeAcronym,schoolYearUUID,enrolmentUUID\n" +
                "1234567,LTI," + UUID.randomUUID() + "," + UUID.randomUUID() + "\n";
        writeCsvContent(csvContent);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            initializer.loadProgrammeEditionEnrolment(service, tempCsv.getAbsolutePath());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("ProgrammeEditionEnrolment loading time:"));
    }
}
