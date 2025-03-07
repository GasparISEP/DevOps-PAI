package PAI.domain;

import PAI.factory.ProgrammeEditionEnrollmentListFactoryInterface;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionEnrolmentListFactory implements ProgrammeEditionEnrollmentListFactoryInterface {

    public Set<ProgrammeEditionEnrollment> newListProgrammeEditionEnrollment() {
        return new HashSet<>();
    }
}
