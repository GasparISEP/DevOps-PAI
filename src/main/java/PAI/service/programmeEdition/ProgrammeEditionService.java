package PAI.service.programmeEdition;

import PAI.domain.programmeEdition.IProgrammeEditionFactory;
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

}
