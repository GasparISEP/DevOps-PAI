package PAI.domain;

import java.util.ArrayList;

public class ProgrammeEnrolmentListFactory implements ProgrammeEnrolmentListFactoryInterface {

    public ArrayList<ProgrammeEnrolment> newArrayList() {

        return new ArrayList<>();
    }
}
