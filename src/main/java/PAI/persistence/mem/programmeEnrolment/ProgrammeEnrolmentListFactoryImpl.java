package PAI.persistence.mem.programmeEnrolment;

import PAI.domain.programmeEnrolment.ProgrammeEnrolment;

import java.util.ArrayList;

public class ProgrammeEnrolmentListFactoryImpl implements IProgrammeEnrolmentListFactory {

    public ArrayList<ProgrammeEnrolment> newArrayList() {

        return new ArrayList<>();
    }
}
