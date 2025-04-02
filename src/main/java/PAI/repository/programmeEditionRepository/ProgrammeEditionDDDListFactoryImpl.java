package PAI.repository.programmeEditionRepository;

import PAI.domain.programmeEdition.ProgrammeEditionDDD;

import java.util.HashSet;
import java.util.Set;

public class ProgrammeEditionDDDListFactoryImpl implements IProgrammeEditionDDDListFactory {

    public Set<ProgrammeEditionDDD> createProgrammeEditionList() {
        return new HashSet<ProgrammeEditionDDD>();
    }
}
