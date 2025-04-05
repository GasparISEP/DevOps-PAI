package PAI.repository.programmeEditionRepository;

import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.Set;

public interface IProgrammeEditionListFactory {

    Set<ProgrammeEdition> createProgrammeEditionList();
}
