package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import java.util.Optional;
import java.util.Set;

public class ProgrammeEditionRepositoryDDDImpl implements IProgrammeEditionRepositoryDDD{

    private final Set<ProgrammeEditionDDD> _programmeEditions;
    private final IProgrammeEditionDDDFactory _programmeEditionDDDFactory;

    public ProgrammeEditionRepositoryDDDImpl(IProgrammeEditionDDDListFactory programmeEditionDDDListFactory, IProgrammeEditionDDDFactory programmeEditionDDDFactory) throws Exception {

        if (programmeEditionDDDListFactory == null)
            throw new Exception("Programme Edition ListFactory cannot be null");
        if (programmeEditionDDDFactory == null)
            throw new Exception("Programme Edition Factory cannot be null");

        _programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();
        _programmeEditionDDDFactory = programmeEditionDDDFactory;
    }

    @Override
    public boolean createProgrammeEdition(ProgrammeID programmeID, SchoolYearID schoolYearID) {
        try {
            ProgrammeEditionDDD pE = _programmeEditionDDDFactory.createProgrammeEdition(programmeID, schoolYearID);
            if (_programmeEditions.add(pE))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        return Optional.empty();
    }

    @Override
    public ProgrammeEditionDDD save(ProgrammeEditionDDD entity) {
        return null;
    }

    @Override
    public Iterable<ProgrammeEditionDDD> findAll() {
        return null;
    }

    @Override
    public Optional<ProgrammeEditionDDD> ofIdentity(ProgrammeEditionID id) {
        for(ProgrammeEditionDDD check : _programmeEditions){
            if (check.identity().equals(id))
                return Optional.of(check);
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        for(ProgrammeEditionDDD check : _programmeEditions){
            if (check.identity().equals(id))
                return true;
        }
        return false;
    }
}