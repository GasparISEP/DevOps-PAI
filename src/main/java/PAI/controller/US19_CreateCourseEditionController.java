package PAI.controller;

import PAI.domain.*;


import java.util.List;

public class US19_CreateCourseEditionController {
    private ProgrammeEditionRepository _programmeEditionRepository;
    private CourseEditionRepository _courseEditionRepository;
    private ProgrammeList _programmeList;

    public US19_CreateCourseEditionController(ProgrammeEditionRepository programmeEditionRepository, CourseEditionRepository courseEditionRepository, ProgrammeList programmeList){
        _programmeEditionRepository = programmeEditionRepository;
        _courseEditionRepository = courseEditionRepository;
        _programmeList = programmeList;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public List<Course> getCoursesInProgramme(ProgrammeEdition programmeEdition) {
        Programme programme = _programmeEditionRepository.findProgrammeInProgrammeEdition(programmeEdition);
        return _programmeList.getCourseList(programme);
    }

    public boolean createCourseEdition (Course course, ProgrammeEdition programmeEdition) {
            if(!_courseEditionRepository.createCourseEdition(course, programmeEdition))
                return false;
        _courseEditionRepository.createCourseEdition(course, programmeEdition);
            return true;
    }
}