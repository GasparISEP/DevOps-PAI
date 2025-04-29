package PAI.persistence.mem.programmeEdition;

import PAI.domain.programme.Programme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProgrammeRepositoryListFactoryImpl implements IProgrammeRepositoryListFactory {
    public List<Programme> newProgrammeArrayList() {
        return new ArrayList<>();
    }

    @Override
    public List<Programme> copyProgrammeArrayList(List<Programme> list) {
        return new ArrayList<>(list);
    }

}
