package PAI.factory;

import PAI.domain.Programme;
import PAI.domain.ProgrammeEdition;
import PAI.domain.SchoolYear;

public interface ProgrammeEditionFactoryInterface {

    public ProgrammeEdition createProgrammeEdition(Programme programme, SchoolYear schoolYear) throws Exception;
}
