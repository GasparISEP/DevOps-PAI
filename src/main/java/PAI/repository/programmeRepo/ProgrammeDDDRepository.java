package PAI.repository.programmeRepo;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.domain.programme.IProgrammeDDDFactory;
import PAI.domain.programme.ProgrammeDDD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeDDDRepository {

    private final IProgrammeDDDFactory _I_programmeFactory;
    private final List<ProgrammeDDD> _programmeRepo;
    private IProgrammeDDDRepositoryListFactory _programmeRepoListFactory;

    public ProgrammeDDDRepository(IProgrammeDDDFactory IProgrammeFactory, IProgrammeDDDRepositoryListFactory programmeLisListFactory) {
        _I_programmeFactory = IProgrammeFactory;
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
        _programmeRepoListFactory = programmeLisListFactory;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) throws Exception {

        ProgrammeDDD programme_DDD = _I_programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);

        if (_programmeRepo.contains(programme_DDD))
            return false;

        _programmeRepo.add(programme_DDD);
        return true;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(ProgrammeDDD programme, TeacherID newDirectorID) throws Exception {
        return programme.newProgrammeDirector(newDirectorID);
    }

    public List<ProgrammeDDD> getAllProgrammes() {
        return _programmeRepoListFactory.copyProgrammeArrayList(_programmeRepo);
    }

    public Optional<ProgrammeDDD> getProgrammeByName(NameWithNumbersAndSpecialChars name) {
        for (ProgrammeDDD programme : _programmeRepo) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public ProgrammeDDD getProgrammeByAcronym(Acronym acronym) {
        for (ProgrammeDDD programme : _programmeRepo) {
            if (programme.getAcronym().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames() {
        List<NameWithNumbersAndSpecialChars> list = new ArrayList<>();
        for (ProgrammeDDD programme : _programmeRepo) {
            list.add(programme.getProgrammeName());
        }
        return list;
    }

    public Optional<ProgrammeDDD> getProgrammeByID(ProgrammeID programmeID) {
        for (ProgrammeDDD programmeDDD : _programmeRepo) {
            if (programmeDDD.getProgrammeID().equals(programmeID)) {
                return Optional.of(programmeDDD);
            }
        }
        return Optional.empty();
    }
}