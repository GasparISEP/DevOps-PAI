package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentListFactoryImplTest {

    @Test
    void shouldCreateArrayList() {
        //arrange

        //act
        ProgrammeEnrolmentListFactoryImpl programmeEnrolmentListFactoryImpl = new ProgrammeEnrolmentListFactoryImpl();

        //assert
        assertNotNull(programmeEnrolmentListFactoryImpl);
    }

}