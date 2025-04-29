package PAI.persistence.mem.programmeEdition;

import PAI.VOs.*;
import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.repository.programmeRepository.IProgrammeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProgrammeRepositoryImpl implements IProgrammeRepository {

    private final IProgrammeFactory _I_programmeFactory;
    private final List<Programme> _programmeRepo;
    private IProgrammeRepositoryListFactory _programmeRepoListFactory;

    public ProgrammeRepositoryImpl(IProgrammeFactory IProgrammeFactory, IProgrammeRepositoryListFactory programmeLisListFactory) {
        _I_programmeFactory = IProgrammeFactory;
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
        _programmeRepoListFactory = programmeLisListFactory;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeTypeID degreeTypeID, DepartmentID departmentID, TeacherID programmeDirectorID) throws Exception {

        Programme programme_DDD = _I_programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, departmentID, programmeDirectorID);

        if (_programmeRepo.contains(programme_DDD))
            return false;

        _programmeRepo.add(programme_DDD);
        return true;
    }

    @Override
    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) {
        List <Programme> programmeList = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            if (programme.getDegreeTypeID().equals(degreeTypeID))
                programmeList.add(programme);
        }
        return programmeList;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(ProgrammeID programmeID, TeacherID newDirectorID) throws Exception {
        Optional<Programme> programmeDDD = ofIdentity(programmeID);
        if (programmeDDD.isPresent()) {
            return programmeDDD.get().newProgrammeDirector(newDirectorID);
        }
        return false;
    }

    public List<Programme> getAllProgrammes() {
        return _programmeRepoListFactory.copyProgrammeArrayList(_programmeRepo);
    }

    public Optional<Programme> getProgrammeByName(NameWithNumbersAndSpecialChars name) {
        for (Programme programme : _programmeRepo) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Programme getProgrammeByAcronym(Acronym acronym) {
        for (Programme programme : _programmeRepo) {
            if (programme.getAcronym().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

//    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames() {
//        List<NameWithNumbersAndSpecialChars> list = new ArrayList<>();
//        for (Programme programme : _programmeRepo) {
//            list.add(programme.getProgrammeName());
//        }
//        return list;
//    }

    @Override
    public Programme save(Programme entity) {
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

    public Optional<ProgrammeID> findProgrammeIdByProgramme (Programme programme) {
        for (Programme existingProgramme : _programmeRepo) {
            if (existingProgramme.sameAs(programme)) {
                return Optional.of(programme.identity());
            }
        }
        return Optional.empty();
    }

    public List<ProgrammeID> getAllProgrammesIDs() {
        List<ProgrammeID> programmeIDs = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            programmeIDs.add(programme.getProgrammeID());
        }
        return programmeIDs;
    }

    public List<ProgrammeID> findProgrammeByDepartment(DepartmentID departmentID){
        List<ProgrammeID> programmesWithDepartment = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            if(programme.isInDepartment(departmentID)){
                programmesWithDepartment.add(programme.identity());
            }
        }
        return programmesWithDepartment;
    }
}