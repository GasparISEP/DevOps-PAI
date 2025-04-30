package PAI.factory;

import PAI.domain.ProgrammeEditionEnrolment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ProgrammeEditionEnrolmentListFactoryImpl implements IProgrammeEditionEnrolmentListFactory {

    public Set<ProgrammeEditionEnrolment> newListProgrammeEditionEnrolment() {
        return new HashSet<>();
    }
}
