package PAI.factory;

import PAI.domain.Programme;

import java.util.List;

public interface ProgrammeRepositoryListFactory {

    List<Programme> newProgrammeArrayList();
    List<Programme> copyProgrammeArrayList(List<Programme> list);
}
