package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.Programme_2;

import java.util.List;

public interface IProgrammeRepositoryListFactory_2 {
    List<Programme_2> newProgrammeArrayList();
    List<Programme_2> copyProgrammeArrayList(List<Programme_2> list);
}
