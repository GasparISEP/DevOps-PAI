package PAI.controller;
import PAI.VOs.*;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;


import java.util.List;

public class US19_CreateCourseEditionController {
    private final IDegreeTypeRepository _degreeTypeRepository;
    private final IProgrammeRepository _programmeRepository;
    private final IStudyPlanRepository _studyPlanRepository;
    private final ICourseInStudyPlanService _courseInStudyPlanService;
    private final IProgrammeEditionRepository _programmeEditionRepository;
    private final ICourseEditionRepository _courseEditionRepository;

    public US19_CreateCourseEditionController(IDegreeTypeRepository degreeTypeRepository,
                                              IProgrammeRepository programmeRepository,
                                              IStudyPlanRepository studyPlanRepository,
                                              ICourseInStudyPlanService courseInStudyPlanService,
                                              IProgrammeEditionRepository programmeEditionRepository,
                                              ICourseEditionRepository courseEditionRepository){
        if (degreeTypeRepository == null) {
            throw new IllegalArgumentException("degreeTypeRepository cannot be null");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("programmeRepository cannot be null");
        }
        if (studyPlanRepository == null) {
            throw new IllegalArgumentException("studyPlanRepository cannot be null");
        }
        if (courseInStudyPlanService == null) {
            throw new IllegalArgumentException("courseInStudyPlanRepository be cannot null");
        }
        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("programmeEditionRepository be cannot null");
        }
        if (courseEditionRepository == null) {
            throw new IllegalArgumentException("courseEditionRepository be cannot null");
        }

        _degreeTypeRepository = degreeTypeRepository;
        _programmeRepository = programmeRepository;
        _studyPlanRepository = studyPlanRepository;
        _courseInStudyPlanService = courseInStudyPlanService;
        _programmeEditionRepository = programmeEditionRepository;
        _courseEditionRepository = courseEditionRepository;
    }

    public List<DegreeType> getAllDegreeTypes() {
        return _degreeTypeRepository.getAllDegreeTypes();
    }

//    public List<Programme> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception {
//        return _programmeRepository.getProgrammesByDegreeTypeID(degreeTypeID);
//    }

    public List<CourseInStudyPlan> getCoursesInStudyPlanByProgrammeID (ProgrammeID programmeID) throws Exception {
        StudyPlanID studyPlanID = _studyPlanRepository.getLatestStudyPlanIDByProgrammeID(programmeID);
        return _courseInStudyPlanService.getCoursesByStudyPlanId(studyPlanID);
    }

    public List<ProgrammeEdition> getProgrammeEditionsByProgrammeID (ProgrammeID programmeID) {
        return _programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);

    }

//    public boolean createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
//        return _courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);
//    }
}