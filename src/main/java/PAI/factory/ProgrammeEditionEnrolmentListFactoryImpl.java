package PAI.factory;

import PAI.domain.ProgrammeEditionEnrolment;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionEnrolmentListFactoryImpl implements IProgrammeEditionEnrolmentListFactory {

    public Set<ProgrammeEditionEnrolment> newListProgrammeEditionEnrolment() {
        return new HashSet<>();
    }
}
