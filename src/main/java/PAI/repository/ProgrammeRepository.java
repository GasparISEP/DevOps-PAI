package PAI.repository;

import PAI.VOs.Acronym;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.QuantEcts;
import PAI.VOs.QuantSemesters;
import PAI.domain.*;
import PAI.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeRepository {
    private final IProgrammeFactory _I_programmeFactory;
    private final List<Programme> _programmeRepo;
    private IProgrammeRepositoryListFactory _programmeRepoListFactory;

    public ProgrammeRepository(IProgrammeFactory IProgrammeFactory, IProgrammeRepositoryListFactory programmeLisListFactory) {
        _I_programmeFactory = IProgrammeFactory;
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
        _programmeRepoListFactory = programmeLisListFactory;
    }

    public boolean registerProgramme(NameWithNumbersAndSpecialChars name, Acronym acronym, QuantEcts quantityOfEcts, QuantSemesters quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, ICourseInStudyPlanFactory ICourseInStudyPlanFactory, IStudyPlanListFactory IStudyPlanListFactory, IStudyPlanFactory IStudyPlanFactory, ICourseFactory ICourseFactory) throws Exception {

        Programme programme = _I_programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, ICourseInStudyPlanFactory, IStudyPlanListFactory, IStudyPlanFactory, ICourseFactory);

        if (_programmeRepo.contains(programme))
            return false;

        _programmeRepo.add(programme);
        return true;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(Programme programme, Teacher newDirector) throws Exception {

        return programme.newProgrammeDirector(newDirector);

    }

    public List<Programme> getAllProgrammes() {
        return _programmeRepoListFactory.copyProgrammeArrayList(_programmeRepo);
    }

    public List<Course> getCourseList(Programme programme) {
        return programme.getCourseList();
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
            if (programme.getAcronymm().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

    public List<NameWithNumbersAndSpecialChars> getAllProgrammeNames() {

        List<NameWithNumbersAndSpecialChars> list = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            list.add(programme.getProgrammeNameWithNumbersAndSpecialChars());
        }
        return list;
    }
}
