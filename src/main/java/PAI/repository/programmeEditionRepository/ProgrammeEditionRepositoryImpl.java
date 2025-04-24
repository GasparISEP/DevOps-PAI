package PAI.repository.programmeEditionRepository;

import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEdition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProgrammeEditionRepositoryImpl implements IProgrammeEditionRepository {

    private final Set<ProgrammeEdition> _programmeEditions;

    public ProgrammeEditionRepositoryImpl(IProgrammeEditionListFactory programmeEditionDDDListFactory) throws Exception {

        if (programmeEditionDDDListFactory == null)
            throw new Exception("Programme Edition ListFactory cannot be null");

        _programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();
    }

    @Override
    public ProgrammeEdition save(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        _programmeEditions.add(programmeEdition);

        return programmeEdition;
    }

    @Override
    public Iterable<ProgrammeEdition> findAll() {
        return _programmeEditions;
    }

    @Override
    public Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id) {
        for(ProgrammeEdition check : _programmeEditions){
            if (check.identity().equals(id))
                return Optional.of(check);
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        for(ProgrammeEdition check : _programmeEditions){
            if (check.identity().equals(id))
                return true;
        }
        return false;
    }

    @Override
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        for(ProgrammeEdition check : _programmeEditions) {
            if (check.findProgrammeIDInProgrammeEdition().equals(programmeid) && check.findSchoolYearIDInProgrammeEdition().equals(schoolYearid))
                return Optional.of(check.identity());
        }
        return Optional.empty();
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid) {
        List<ProgrammeEdition> programmeEditions = new ArrayList<>();
        for (ProgrammeEdition programmeEdition : _programmeEditions) {
            if (programmeEdition.findProgrammeIDInProgrammeEdition().equals(programmeid))
                programmeEditions.add(programmeEdition);
        }
        return programmeEditions;
    }

    public SchoolYearID getSchoolYearIDByProgrammeEdition (ProgrammeEdition programmeEdition) {
        return programmeEdition.findSchoolYearIDInProgrammeEdition();
    }
}