package PAI.factory;

import PAI.domain.ProgrammeEditionEnrollment;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionEnrollmentListFactoryImpl implements IProgrammeEditionEnrollmentListFactory {

    public Set<ProgrammeEditionEnrollment> newListProgrammeEditionEnrollment() {
        return new HashSet<>();
    }
}
