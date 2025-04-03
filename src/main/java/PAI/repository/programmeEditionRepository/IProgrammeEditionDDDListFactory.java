package PAI.repository.programmeEditionRepository;

import PAI.domain.programmeEdition.ProgrammeEditionDDD;

import java.util.Set;

public interface IProgrammeEditionDDDListFactory {

    Set<ProgrammeEditionDDD> createProgrammeEditionList();
}
