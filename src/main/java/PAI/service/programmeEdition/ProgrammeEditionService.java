package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;

//TODO @Service and Implements IProgrammeEditionService
public class ProgrammeEditionService {
    private final IProgrammeEditionFactory _programmeEditionFactory;
    private final IProgrammeEditionRepository _programmeEditionRepository;

    public ProgrammeEditionService (IProgrammeEditionFactory programmeEditionFactory, IProgrammeEditionRepository programmeEditionRepository) {
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null!");
        }
        this._programmeEditionFactory = programmeEditionFactory;

        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null!");
        }
        this._programmeEditionRepository = programmeEditionRepository;
    }

    public ProgrammeEdition createProgrammeEdition (ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        return this._programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
    }

}
