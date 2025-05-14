package PAI.domain.programmeEnrolment;

import java.util.ArrayList;

public class ProgrammeEnrolmentListFactoryImpl implements IProgrammeEnrolmentListFactory {

    public ArrayList<ProgrammeEnrolment> newArrayList() {

        return new ArrayList<>();
    }
}
