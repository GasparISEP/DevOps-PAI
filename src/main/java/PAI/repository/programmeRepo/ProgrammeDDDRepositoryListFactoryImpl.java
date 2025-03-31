package PAI.repository.programmeRepo;

import PAI.domain.programme.ProgrammeDDD;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeDDDRepositoryListFactoryImpl implements IProgrammeDDDRepositoryListFactory {
    public List<ProgrammeDDD> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<ProgrammeDDD> copyProgrammeArrayList(List<ProgrammeDDD> list) {
        return new ArrayList<>(list);
    }

}
