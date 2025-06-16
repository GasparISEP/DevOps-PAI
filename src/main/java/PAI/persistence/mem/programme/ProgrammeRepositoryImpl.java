package PAI.persistence.mem.programme;

import PAI.VOs.*;
import PAI.domain.programme.Programme;
import PAI.domain.repositoryInterfaces.programme.IProgrammeRepository;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProgrammeRepositoryImpl implements IProgrammeRepository {

    private final List<Programme> _programmeRepo;

    public ProgrammeRepositoryImpl(IProgrammeRepositoryListFactory programmeListFactory) {
        _programmeRepo = programmeListFactory.newProgrammeArrayList();
    }

    @Override
    public Programme save(Programme entity) {
        _programmeRepo.removeIf(p -> p.identity().equals(entity.identity()));
        _programmeRepo.add(entity);
        return entity;
    }

    @Override
    public Iterable<Programme> findAll() {
        return _programmeRepo;
    }

    @Override
    public Optional<Programme> ofIdentity(ProgrammeID id) {
        for (Programme existingProgramme : _programmeRepo) {
            if (existingProgramme.identity().equals(id)) {
                return Optional.of(existingProgramme);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(ProgrammeID id) {
        return ofIdentity(id).isPresent();
    }

    public List<ProgrammeID> findProgrammesIdByDepartmentId(DepartmentID departmentID){
        List<ProgrammeID> programmesWithDepartment = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            if(programme.isInDepartment(departmentID)){
                programmesWithDepartment.add(programme.identity());
            }
        }
        return programmesWithDepartment;
    }

    @Override
    public boolean existsByName(NameWithNumbersAndSpecialChars name) {
        for (Programme programme : _programmeRepo) {
            if (programme.getProgrammeName().getNameWithNumbersAndSpecialChars().equals(name.getNameWithNumbersAndSpecialChars())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByAcronym(Acronym acronym) {
        for (Programme programme : _programmeRepo) {
            if (programme.getAcronym().getAcronym().equals(acronym.getAcronym())) {
                return true;
            }
        }
        return false;
    }
}