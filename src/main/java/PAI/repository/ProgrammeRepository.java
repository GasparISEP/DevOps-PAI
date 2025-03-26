package PAI.repository;

import PAI.domain.*;
import PAI.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeRepository {
    private final IProgrammeFactory _I_programmeFactory;
    private final List<Programme> _programmeRepo;
    private ProgrammeRepositoryListFactory _programmeRepoListFactory;

    public ProgrammeRepository(IProgrammeFactory IProgrammeFactory, ProgrammeRepositoryListFactory programmeLisListFactory) {
        _I_programmeFactory = IProgrammeFactory;
        _programmeRepo = programmeLisListFactory.newProgrammeArrayList();
        _programmeRepoListFactory = programmeLisListFactory;
    }

    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, IProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanListFactory studyPlanListFactory, StudyPlanFactory studyPlanFactory, ICourseFactory ICourseFactory) throws Exception {

        Programme programme = _I_programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory, studyPlanListFactory, studyPlanFactory, ICourseFactory);

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

    public Optional<Programme> getProgrammeByName(String name) {
        for (Programme programme : _programmeRepo) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Programme getProgrammeByAcronym(String acronym) {
        for (Programme programme : _programmeRepo) {
            if (programme.getAcronym().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

    public List<String> getAllProgrammeNames() {

        List<String> list = new ArrayList<>();
        for (Programme programme : _programmeRepo) {
            list.add(programme.getProgrammeName());
        }
        return list;
    }
}
