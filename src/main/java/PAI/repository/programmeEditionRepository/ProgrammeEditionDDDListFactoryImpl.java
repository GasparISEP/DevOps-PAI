package PAI.repository.programmeEditionRepository;

import PAI.domain.ProgrammeEdition;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionDDDListFactoryImpl implements IProgrammeEditionDDDListFactory {

    public Set<ProgrammeEdition> createProgrammeEditionList() {
        return new HashSet<ProgrammeEdition>();
    }
}
