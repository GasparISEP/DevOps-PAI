package PAI.initializer;

import PAI.VOs.*;
import PAI.controller.US16_EnrolAStudentInACourseEditionController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CourseEditionEnrolmentInitializerTest {

    @Mock
    private US16_EnrolAStudentInACourseEditionController controller;

    @InjectMocks
    private CourseEditionEnrolmentInitializer initializer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testLoadCourseEditionEnrolments() throws Exception {
        // Arrange

        //act
        initializer.loadCourseEditionEnrolments(controller).run();

        // assert
        verify(controller, atLeastOnce()).enrolStudentInCourseEdition(any(), any(), any(), any(), any());
    }


    @Test
    void testLoadCourseEditionEnrolments_ShouldPrintStackTraceWhenControllerIsNull() throws Exception {
        //arrange
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {

            initializer.loadCourseEditionEnrolments(null).run();
        } finally {

            System.setErr(originalErr);
        }

        String output = errContent.toString();

        // Assert
        assertTrue(output.contains("NullPointerException"), "Expected stack trace with NullPointerException");
    }

    @Test
    void shouldPrintStackTraceWhenControllerThrows() throws Exception {
        // Arrange: force controller to throw
        doThrow(new RuntimeException("boom"))
                .when(controller).enrolStudentInCourseEdition(any(CourseEditionEnrolmentGeneratedID.class) , any(StudentID.class), any(CourseEditionID.class), any(Date.class), any(EnrolmentStatus.class));

        // Capture System.err
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            // Act: run the CommandLineRunner
            CommandLineRunner runner = initializer.loadCourseEditionEnrolments(controller);
            runner.run(new String[]{});
        } finally {
            // Restore
            System.setErr(originalErr);
        }

        // Assert: stack trace printed
        String output = errContent.toString();
        assertTrue(output.contains("boom"), "Expected exception message in stderr");
    }

    @Test
    void testLoadCourseEditionEnrolments_FileNotFound() {
        // arrange
        try {
            new CourseEditionEnrolmentInitializer().loadCourseEditionEnrolments(controller).run();
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
            assertEquals("Arquivo CSV não encontrado no classpath!", e.getMessage());
        }
    }

    @Test
    void shouldPrintLoadingTimeAtTheEnd() throws Exception {
        CourseEditionEnrolmentInitializer initializer = new CourseEditionEnrolmentInitializer();

        // Captura do System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            initializer.loadCourseEditionEnrolments(mock(US16_EnrolAStudentInACourseEditionController.class)).run();
        } catch (Exception ignored) {

        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("CourseEditionEnrolment loading time:"));
    }
}

