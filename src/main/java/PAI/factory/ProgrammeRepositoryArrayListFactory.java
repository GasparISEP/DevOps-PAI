package PAI.factory;

import PAI.domain.Programme;

import java.util.List;

public interface ProgrammeRepositoryArrayListFactory {

    List<Programme> newProgrammeArrayList();
    List<Programme> copyProgrammeArrayList(List<Programme> list);
}
