package PAI.repository;

import PAI.domain.*;
import PAI.factory.ProgrammeCourseListFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.factory.ProgrammeListArrayListFactory;
import PAI.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammeList {
    private final ProgrammeFactory _programmeFactory;
    private final List<Programme> _programmeList;
    private ProgrammeListArrayListFactory _programmeListArrayListFactory;

    public ProgrammeList(ProgrammeFactory programmeFactory, ProgrammeListArrayListFactory programmeListArrayListFactory) {
        _programmeFactory = programmeFactory;
        _programmeList = programmeListArrayListFactory.newProgrammeArrayList();
        _programmeListArrayListFactory = programmeListArrayListFactory;
    }

    public boolean registerProgramme(String name, String acronym, int quantityOfEcts, int quantityOfSemesters, DegreeType degreeType, Department department, Teacher programmeDirector, ProgrammeCourseListFactory programmeCourseListFactory, CourseInStudyPlanFactory courseInStudyPlanFactory, StudyPlanArrayListFactory studyPlanArrayListFactory, StudyPlanFactory studyPlanFactory, CourseFactory courseFactory) throws Exception {

        Programme programme = _programmeFactory.registerProgramme(name, acronym, quantityOfEcts, quantityOfSemesters, degreeType, department, programmeDirector, programmeCourseListFactory, courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactory);

        if (_programmeList.contains(programme))
            return false;

        _programmeList.add(programme);
        return true;
    }

    // Change ProgrammeDirector
    public boolean changeProgrammeDirector(Programme programme, Teacher newDirector) throws Exception {
        if (newDirector == null) {
            return false;
        }

        programme.newProgrammeDirector(newDirector);
        return true;
    }

    public List<Programme> getAllProgrammes() {
        return _programmeListArrayListFactory.copyProgrammeArrayList(_programmeList);
    }

    public List<Course> getCourseList(Programme programme) {
        return programme.getCourseList();
    }

    public Optional<Programme> getProgrammeByName(String name) {
        for (Programme programme : _programmeList) {
            if (programme.hasThisProgrammeName(name)) {
                return Optional.of(programme);
            }
        }
        return Optional.empty();
    }

    public Programme getProgrammeByAcronym(String acronym) {
        for (Programme programme : _programmeList) {
            if (programme.getAcronym().equals(acronym)) {
                return programme;
            }
        }
        return null;
    }

    public List<String> getAllProgrammeNames() {

        List<String> list = new ArrayList<>();
        for (Programme programme : _programmeList) {
            list.add(programme.getProgrammeName());
        }
        return list;
    }
}
