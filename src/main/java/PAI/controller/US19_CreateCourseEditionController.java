package PAI.controller;
import PAI.VOs.*;
import PAI.domain.courseEdition.CourseEdition;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.courseEdition.ICreateCourseEditionService;
import PAI.service.degreeType.IDegreeTypeService;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEdition.IProgrammeEditionService;

import java.util.List;

public class US19_CreateCourseEditionController {
    private final ICreateCourseEditionService courseEditionService;

    public US19_CreateCourseEditionController(ICreateCourseEditionService courseEditionService) {
        if (courseEditionService == null) {
            throw new IllegalArgumentException("courseEditionService cannot be null");
        }
        this.courseEditionService = courseEditionService;
    }

    public List<DegreeType> getAllDegreeTypes() {
        return courseEditionService.getAllDegreeTypes();
    }

    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception {
        if (degreeTypeID == null)
            return List.of();
        return courseEditionService.getProgrammesByDegreeTypeID(degreeTypeID);
    }

    public List<CourseInStudyPlan> getCoursesInStudyPlanByProgrammeID (ProgrammeID programmeID) throws Exception {
        if (programmeID == null)
            return List.of();
        StudyPlanID studyPlanID = courseEditionService.getLatestStudyPlanIDByProgrammeID(programmeID);
        if (studyPlanID == null)
            return List.of();
        return courseEditionService.getCoursesByStudyPlanId(studyPlanID);
    }

    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception{
        if (programmeID == null)
            return List.of();
        return courseEditionService.getProgrammeEditionsByProgrammeID(programmeID);
    }

    public boolean createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        if (courseInStudyPlanID == null)
            return false;

        if (programmeEditionID == null)
            return false;
        CourseEdition result = courseEditionService.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);
        return result != null;
    }
}