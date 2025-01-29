package PAI.controller;

import PAI.domain.*;

import java.util.ArrayList;
import java.util.List;

public class US19_CreateCourseEditionController {
    private ProgrammeEditionRepository _programmeEditionRepository;
    private CourseEditionRepository _courseEditionRepository;

    public US19_CreateCourseEditionController(ProgrammeEditionRepository programmeEditionRepository, CourseEditionRepository courseEditionRepository){
        _programmeEditionRepository = programmeEditionRepository;
        _courseEditionRepository = courseEditionRepository;
    }

    public List<ProgrammeEdition> getAllProgrammeEditions() {
        return _programmeEditionRepository.getAllProgrammeEditions();
    }

    public List<Course> getCoursesInProgramme(ProgrammeEdition programmeEdition) {
        Programme programme = programmeEdition.findProgrammeInProgrammeEdition();
        return programme.getCourseList();
    }

    public boolean createCourseEdition (Course course, ProgrammeEdition programmeEdition) throws Exception {
        try {
            CourseEdition courseEdition = new CourseEdition(course, programmeEdition);
            _courseEditionRepository.createCourseEdition(course, programmeEdition);
            return true;

        } catch (Exception exception) {
            return false;
        }
    }
}