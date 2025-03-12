package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;

public class ProgrammeEditionFactoryImpl implements IProgrammeEditionFactory {

    @Override
    public ProgrammeEdition createProgrammeEdition(Programme programme, SchoolYear schoolYear) throws Exception {
        return new ProgrammeEdition(programme, schoolYear);
    }
}
