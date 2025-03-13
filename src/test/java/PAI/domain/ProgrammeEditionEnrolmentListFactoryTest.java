package PAI.domain;

import PAI.factory.ProgrammeEditionEnrollmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentListFactoryTest {

    @Test
    void whenNewListProgrammeEditionEnrollmentInvoked_ThenSetShouldBeEmpty() {
        // arrange

            ProgrammeEditionEnrollmentListFactoryImpl factory = new ProgrammeEditionEnrollmentListFactoryImpl();

            // act
            Set<ProgrammeEditionEnrollment> enrollmentSet = factory.newListProgrammeEditionEnrollment();

            // assert
            assertEquals(HashSet.class, enrollmentSet.getClass());
    }

}