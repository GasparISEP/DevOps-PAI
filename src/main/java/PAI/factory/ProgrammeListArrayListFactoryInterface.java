package PAI.factory;

import PAI.domain.Programme;

import java.util.List;

public interface ProgrammeListArrayListFactoryInterface {

    List<Programme> newProgrammeArrayList();
    List<Programme> copyProgrammeArrayList(List<Programme> list);
}
