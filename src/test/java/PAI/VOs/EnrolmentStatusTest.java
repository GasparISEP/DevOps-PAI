package PAI.VOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnrolmentStatusTest {

    @Test
    void should_return_true_when_enrolment_is_Active() {

        // arrange
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(true);

        // act & assert
        assertTrue(enrolmentStatus.isEnrolmentActive(), "Enrolment should be Active");
    }

    @Test
    void should_return_false_when_enrolment_is_Inactive() {

        // arrange
        EnrolmentStatus enrolmentStatus = new EnrolmentStatus(false);

        // act & assert
        assertFalse(enrolmentStatus.isEnrolmentActive(), "Enrolment should be Inactive");
    }
}