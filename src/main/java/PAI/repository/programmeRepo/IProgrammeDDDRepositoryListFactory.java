package PAI.repository.programmeRepo;

import PAI.domain.programme.Programme;

import java.util.List;

public interface IProgrammeDDDRepositoryListFactory {
    List<Programme> newProgrammeArrayList();
    List<Programme> copyProgrammeArrayList(List<Programme> list);
}
