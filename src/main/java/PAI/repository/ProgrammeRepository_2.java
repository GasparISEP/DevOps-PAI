package PAI.repository;

import PAI.VOs.*;
import PAI.domain.*;
import PAI.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeRepository_2 {

    private final IProgrammeFactory_2 _I_programmeFactory;
    private final List<Programme_2> _programmeRepo;
    private IProgrammeRepositoryListFactory_2 _programmeRepoListFactory;

    public ProgrammeRepository_2(IProgrammeFactory_2 IProgrammeFactory, IProgrammeRepositoryListFactory_2 programmeLisListFactory) {
        _I_programmeFactory = IProgrammeFactory;
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
        _programmeRepoListFactory = programmeLisListFactory;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType_ID degreeTypeID, Department department, TeacherID programmeDirectorID) throws Exception {

        Programme_2 programme_2 = _I_programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeTypeID, department, programmeDirectorID);

        if (_programmeRepo.contains(programme_2))
            return false;

        _programmeRepo.add(programme_2);
        return true;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(Programme_2 programme, TeacherID newDirectorID) throws Exception {

        return programme.newProgrammeDirector(newDirectorID);

    }

    public List<Programme_2> getAllProgrammes() {
        return _programmeRepoListFactory.copyProgrammeArrayList(_programmeRepo);
    }

    public Optional<Programme_2> getProgrammeByName(NameWithNumbersAndSpecialChars name) {
        for (Programme_2 programme : _programmeRepo) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Programme_2 getProgrammeByAcronym(Acronym acronym) {
        for (Programme_2 programme : _programmeRepo) {
            if (programme.getAcronym().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames() {

        List<NameWithNumbersAndSpecialChars> list = new ArrayList<>();
        for (Programme_2 programme : _programmeRepo) {
            list.add(programme.getProgrammeName());
        }
        return list;
    }

}
