package PAI.factory;

import PAI.domain.ProgrammeEnrolment;

import java.util.ArrayList;

public class ProgrammeEnrolmentListFactoryImpl implements IProgrammeEnrolmentListFactory {

    public ArrayList<ProgrammeEnrolment> newArrayList() {

        return new ArrayList<>();
    }
}
