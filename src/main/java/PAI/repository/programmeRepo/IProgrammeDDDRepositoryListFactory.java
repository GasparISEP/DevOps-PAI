package PAI.repository.programmeRepo;

import PAI.domain.programme.ProgrammeDDD;

import java.util.List;

public interface IProgrammeDDDRepositoryListFactory {
    List<ProgrammeDDD> newProgrammeArrayList();
    List<ProgrammeDDD> copyProgrammeArrayList(List<ProgrammeDDD> list);
}
