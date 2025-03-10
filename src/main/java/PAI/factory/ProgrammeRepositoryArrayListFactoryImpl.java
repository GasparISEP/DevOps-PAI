package PAI.factory;

import PAI.domain.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeRepositoryArrayListFactoryImpl implements ProgrammeRepositoryArrayListFactory {
    public List<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme> copyProgrammeArrayList(List<Programme> list) {
        return List.copyOf(list);
    }

}