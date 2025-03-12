package PAI.factory;

import PAI.domain.ProgrammeEdition;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeEditionListFactoryImpl implements ProgrammeEditionListFactoryInterface {

    @Override
    public List<ProgrammeEdition> createProgrammeEditionArrayList() {
        return new ArrayList<>();
    }
}
