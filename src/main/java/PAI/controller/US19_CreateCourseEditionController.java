package PAI.controller;

import PAI.VOs.CourseInStudyPlanID;
import PAI.VOs.ProgrammeEditionID;
import PAI.domain.*;
import PAI.repository.CourseEditionRepository;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeRepository;


import java.util.List;

public class US19_CreateCourseEditionController {
    private ProgrammeEditionRepository _programmeEditionRepository;
    private CourseEditionRepository _courseEditionRepository;
    private ProgrammeRepository _programmeList;

    public US19_CreateCourseEditionController(ProgrammeEditionRepository programmeEditionRepository, CourseEditionRepository courseEditionRepository, ProgrammeRepository programmeList){
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

    public boolean createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
            if(!_courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID))
                return false;
        _courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);
            return true;
    }
}