package PAI.persistence.mem.programme;

import PAI.domain.programme.Programme;

import java.util.List;

public interface IProgrammeRepositoryListFactory {
    List<Programme> newProgrammeArrayList();
    List<Programme> copyProgrammeArrayList(List<Programme> list);
}
