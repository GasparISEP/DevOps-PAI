package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnrolmentStatusTest {

    @Test
    void should_return_true_when_enrolment_is_active() {

        // arrange
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(true);

        // act & assert
        assertTrue(enrolmentStatus.isEnrolmentActive(), "Enrolment should be active");
    }

    @Test
    void should_return_false_when_enrolment_is_inactive() {

        // arrange
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(false);

        // act & assert
        assertFalse(enrolmentStatus.isEnrolmentActive(), "Enrolment should be inactive");
    }
}