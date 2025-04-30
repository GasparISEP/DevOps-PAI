package PAI.controller;
import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.service.DegreeType.IDegreeTypeService;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.courseEdition.ICourseEditionService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;
import PAI.service.programmeEdition.IProgrammeEditionService;

import java.util.List;

public class US19_CreateCourseEditionController {
    private final IDegreeTypeService degreeTypeService;
    private final IProgrammeService programmeService;
    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanService courseInStudyPlanService;
    private final IProgrammeEditionService programmeEditionService;
    private final ICourseEditionService courseEditionService;

    public US19_CreateCourseEditionController(IDegreeTypeService degreeTypeService,
                                              IProgrammeService programmeService,
                                              IStudyPlanService studyPlanService,
                                              ICourseInStudyPlanService courseInStudyPlanService,
                                              IProgrammeEditionService programmeEditionService,
                                              ICourseEditionService courseEditionService){
        if (degreeTypeService == null) {
            throw new IllegalArgumentException("degreeTypeService cannot be null");
        }
        if (programmeService == null) {
            throw new IllegalArgumentException("programmeService cannot be null");
        }
        if (studyPlanService == null) {
            throw new IllegalArgumentException("studyPlanService cannot be null");
        }
        if (courseInStudyPlanService == null) {
            throw new IllegalArgumentException("courseInStudyPlanService cannot be null");
        }
        if (programmeEditionService == null) {
            throw new IllegalArgumentException("programmeEditionService cannot be null");
        }
        if (courseEditionService == null) {
            throw new IllegalArgumentException("courseEditionService cannot be null");
        }

        this.degreeTypeService = degreeTypeService;
        this.programmeService = programmeService;
        this.studyPlanService = studyPlanService;
        this.courseInStudyPlanService = courseInStudyPlanService;
        this.programmeEditionService = programmeEditionService;
        this.courseEditionService = courseEditionService;
    }

    public List<DegreeType> getAllDegreeTypes() {
        return degreeTypeService.getAllDegreeTypes();
    }

    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception {
        if (degreeTypeID == null)
            return List.of();
        return programmeService.getProgrammesByDegreeTypeID(degreeTypeID);
    }

    public List<CourseInStudyPlan> getCoursesInStudyPlanByProgrammeID (ProgrammeID programmeID) throws Exception {
        if (programmeID == null)
            return List.of();
        StudyPlanID studyPlanID = studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID);
        if (studyPlanID == null)
            return List.of();
        return courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID);
    }

    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID(ProgrammeID programmeID) throws Exception{
        if (programmeID == null)
            return List.of();
        return programmeEditionService.getProgrammeEditionsByProgrammeID(programmeID);
    }


    public boolean createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
        return false;
    }
}