package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentListFactoryTest {

    @Test
    void whenNewListProgrammeEditionEnrollmentInvoked_ThenSetShouldBeEmpty() {
        // arrange

            ProgrammeEditionEnrolmentListFactory factory = new ProgrammeEditionEnrolmentListFactory();

            // act
            Set<ProgrammeEditionEnrollment> enrollmentSet = factory.newListProgrammeEditionEnrollment();

            // assert
            assertEquals(HashSet.class, enrollmentSet.getClass());
    }

}