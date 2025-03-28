package PAI.factory;

import PAI.domain.Programme_2;
import java.util.ArrayList;
import java.util.List;

public class ProgrammeRepositoryListFactoryImpl_2 implements IProgrammeRepositoryListFactory_2{
    public List<Programme_2> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme_2> copyProgrammeArrayList(List<Programme_2> list) {
        return new ArrayList<>(list);
    }

}
