package PAI.factory;

import PAI.domain.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeRepositoryListFactoryImpl implements IProgrammeRepositoryListFactory {
    public List<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme> copyProgrammeArrayList(List<Programme> list) {
        return new ArrayList<>(list);
    }

}