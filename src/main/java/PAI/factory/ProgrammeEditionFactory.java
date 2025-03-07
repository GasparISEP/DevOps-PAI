package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;

public class ProgrammeEditionFactory implements ProgrammeEditionFactoryInterface {

    @Override
    public ProgrammeEdition createProgrammeEdition(Programme programme, SchoolYear schoolYear) throws Exception {
        return new ProgrammeEdition(programme, schoolYear);
    }
}
