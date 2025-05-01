package PAI.service.programmeEdition;

import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeEditionService implements IProgrammeEditionService {
    private final IProgrammeEditionFactory programmeEditionFactory;
    private final IProgrammeEditionRepository programmeEditionRepository;

    public ProgrammeEditionService (IProgrammeEditionFactory programmeEditionFactory, IProgrammeEditionRepository programmeEditionRepository) {
        if(programmeEditionFactory == null){
            throw new IllegalArgumentException("ProgrammeEditionFactory cannot be null!");
        }
        this.programmeEditionFactory = programmeEditionFactory;

        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("ProgrammeEditionRepository cannot be null!");
        }
        this.programmeEditionRepository = programmeEditionRepository;
    }

    @Override
    public ProgrammeEdition createProgrammeEdition (ProgrammeID programmeID, SchoolYearID schoolYearID) throws Exception {
        return this.programmeEditionFactory.createProgrammeEdition(programmeID, schoolYearID);
    }

    @Override
    public Optional<ProgrammeEdition> saveProgrammeEdition (ProgrammeEdition programmeEdition) throws Exception {
        if (programmeEdition == null) {
            return Optional.empty();
        }

        boolean isProgrammeEditionAlreadyRegistered = this.programmeEditionRepository.containsOfIdentity(programmeEdition.identity());

        if (!isProgrammeEditionAlreadyRegistered) {
            ProgrammeEdition programmeEditionSaved = this.programmeEditionRepository.save(programmeEdition);

            //If programmeEditionSaved is null return optional empty
            return Optional.ofNullable(programmeEditionSaved);
        }

        return Optional.empty();
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception {
        if (programmeID == null) {
            return List.of();
        }

        return programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);
    }
}
