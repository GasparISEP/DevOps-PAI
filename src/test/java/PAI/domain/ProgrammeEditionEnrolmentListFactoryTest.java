package PAI.domain;

import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolment;
import PAI.domain.programmeEditionEnrolment.ProgrammeEditionEnrolmentListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEditionEnrolmentListFactoryTest {

    @Test
    void whenNewListProgrammeEditionEnrolmentInvoked_ThenSetShouldBeEmpty() {
        // arrange

            ProgrammeEditionEnrolmentListFactoryImpl factory = new ProgrammeEditionEnrolmentListFactoryImpl();

            // act
            Set<ProgrammeEditionEnrolment> enrollmentSet = factory.newListProgrammeEditionEnrolment();

            // assert
            assertEquals(HashSet.class, enrollmentSet.getClass());
    }

}