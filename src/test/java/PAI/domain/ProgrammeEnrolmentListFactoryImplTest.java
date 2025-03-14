package PAI.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentListFactoryImplTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        ProgrammeEnrolmentListFactory programmeEnrolmentListFactory = new ProgrammeEnrolmentListFactoryImpl();

        //act
        List<ProgrammeEnrolment> programmeEnrolmentList = programmeEnrolmentListFactory.newArrayList();

        //assert
        assertNotNull(programmeEnrolmentList);
    }

}