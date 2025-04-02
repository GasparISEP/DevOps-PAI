package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.ProgrammeEdition;
import PAI.domain.programmeEdition.IProgrammeEditionDDDFactory;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProgrammeEditionRepositoryDDDImpl implements IProgrammeEditionRepositoryDDD{

    private final Set<ProgrammeEdition> _programmeEditions;
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
    public boolean createProgrammeEdition(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        return false;
    }

    @Override
    public Optional<ProgrammeEditionDDD> findProgrammeEditionByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) {
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
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        return false;
    }
}
