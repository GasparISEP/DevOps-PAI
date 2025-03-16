package PAI.domain;

import PAI.factory.ProgrammeEditionEnrolmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentListFactoryTest {

    @Test
    void whenNewListProgrammeEditionEnrollmentInvoked_ThenSetShouldBeEmpty() {
        // arrange

            ProgrammeEditionEnrolmentListFactoryImpl factory = new ProgrammeEditionEnrolmentListFactoryImpl();

            // act
            Set<ProgrammeEditionEnrolment> enrollmentSet = factory.newListProgrammeEditionEnrollment();

            // assert
            assertEquals(HashSet.class, enrollmentSet.getClass());
    }

}