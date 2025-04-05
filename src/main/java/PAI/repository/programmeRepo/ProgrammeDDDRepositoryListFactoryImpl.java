package PAI.repository.programmeRepo;

import PAI.domain.programme.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeDDDRepositoryListFactoryImpl implements IProgrammeDDDRepositoryListFactory {
    public List<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme> copyProgrammeArrayList(List<Programme> list) {
        return new ArrayList<>(list);
    }

}
