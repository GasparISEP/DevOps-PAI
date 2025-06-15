package PAI.persistence.mem.programmeEdition;

import PAI.VOs.Date;
import PAI.VOs.ProgrammeEditionID;
import PAI.VOs.ProgrammeID;
import PAI.VOs.SchoolYearID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.domain.repositoryInterfaces.programmeEdition.IProgrammeEditionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProgrammeEditionRepositoryImpl implements IProgrammeEditionRepository {

    private final Set<ProgrammeEdition> programmeEditions;

    public ProgrammeEditionRepositoryImpl(IProgrammeEditionListFactory programmeEditionDDDListFactory) throws Exception {

        if (programmeEditionDDDListFactory == null)
            throw new Exception("Programme Edition ListFactory cannot be null");

        programmeEditions = programmeEditionDDDListFactory.createProgrammeEditionList();
    }

    @Override
    public ProgrammeEdition save(ProgrammeEdition programmeEdition) {
        if (programmeEdition == null){
            throw new IllegalArgumentException("Programme Edition cannot be null");
        }
        programmeEditions.add(programmeEdition);

        return programmeEdition;
    }

    @Override
    public Iterable<ProgrammeEdition> findAll() {
        return programmeEditions;
    }

    @Override
    public Optional<ProgrammeEdition> ofIdentity(ProgrammeEditionID id) {
        for(ProgrammeEdition check : programmeEditions){
            if (check.identity().equals(id))
                return Optional.of(check);
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeEditionID id) {
        for(ProgrammeEdition check : programmeEditions){
            if (check.identity().equals(id))
                return true;
        }
        return false;
    }

    @Override
    public Optional<ProgrammeEditionID> findProgrammeEditionIDByProgrammeIDAndSchoolYearID(ProgrammeID programmeid, SchoolYearID schoolYearid) {
        for(ProgrammeEdition check : programmeEditions) {
            if (check.findProgrammeIDInProgrammeEdition().equals(programmeid) && check.findSchoolYearIDInProgrammeEdition().equals(schoolYearid))
                return Optional.of(check.identity());
        }
        return Optional.empty();
    }

    @Override
    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeid) {
        List<ProgrammeEdition> programmeEditions = new ArrayList<>();
        for (ProgrammeEdition programmeEdition : this.programmeEditions) {
            if (programmeEdition.findProgrammeIDInProgrammeEdition().equals(programmeid))
                programmeEditions.add(programmeEdition);
        }
        return programmeEditions;
    }

    @Override
    public List<ProgrammeEdition> findByProgrammeIDs(List<ProgrammeID> programmeIDs) {
        List<ProgrammeEdition> result = new ArrayList<>();
        for (ProgrammeEdition edition : this.programmeEditions) {
            if (programmeIDs.contains(edition.findProgrammeIDInProgrammeEdition())) {
                result.add(edition);
            }
        }
        return result;
    }
    public SchoolYearID getSchoolYearIDByProgrammeEdition (ProgrammeEdition programmeEdition) {
        return programmeEdition.findSchoolYearIDInProgrammeEdition();
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionIDsByProgrammeIDAndStartDateAfter(ProgrammeID programmeID, LocalDate date) {
        return null;
    }

    @Override
    public List<ProgrammeEditionID> findProgrammeEditionIDsBySchoolYearIDAndProgrammeIDs(SchoolYearID schoolYearID, List<ProgrammeID> programmeIDs) {
        return null;
    }
}