package PAI.repository.programmeEditionRepository;

import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionListFactoryImpl implements IProgrammeEditionListFactory {

    public Set<ProgrammeEdition> createProgrammeEditionList() {
        return new HashSet<ProgrammeEdition>();
    }
}
