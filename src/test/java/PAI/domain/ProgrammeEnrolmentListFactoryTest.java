package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentListFactoryTest {

    @Test
    void shouldCreateArrayList() {
        //arrange

        //act
        ProgrammeEnrolmentListFactory programmeEnrolmentListFactory = new ProgrammeEnrolmentListFactory();

        //assert
        assertNotNull(programmeEnrolmentListFactory);
    }

}