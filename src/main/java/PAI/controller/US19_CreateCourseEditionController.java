package PAI.controller;
import PAI.VOs.*;
import PAI.domain.DegreeTypeDDD.DegreeType_2;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.programmeEdition.ProgrammeEditionDDD;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import PAI.repository.DegreeTypeRepoDDD.IDegreeTypeRepository_2;
import PAI.repository.ICourseEditionRepositoryDDD;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;


import java.util.List;

public class US19_CreateCourseEditionController {
    private final IDegreeTypeRepository_2 _degreeTypeRepository;
    private final IProgrammeDDDRepository _programmeRepository;
    private final IStudyPlanDDDRepository _studyPlanRepository;
    private final ICourseInStudyPlanDDDRepository _courseInStudyPlanRepository;
    private final IProgrammeEditionRepositoryDDD _programmeEditionRepository;
    private final ICourseEditionRepositoryDDD _courseEditionRepository;

    public US19_CreateCourseEditionController(IDegreeTypeRepository_2 degreeTypeRepository,
                                              IProgrammeDDDRepository programmeRepository,
                                              IStudyPlanDDDRepository studyPlanRepository,
                                              ICourseInStudyPlanDDDRepository courseInStudyPlanRepository,
                                              IProgrammeEditionRepositoryDDD programmeEditionRepository,
                                              ICourseEditionRepositoryDDD courseEditionRepository){
        if (degreeTypeRepository == null) {
            throw new IllegalArgumentException("degreeTypeRepository cannot be null");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("programmeRepository cannot be null");
        }
        if (studyPlanRepository == null) {
            throw new IllegalArgumentException("studyPlanRepository cannot be null");
        }
        if (courseInStudyPlanRepository == null) {
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
        _courseInStudyPlanRepository = courseInStudyPlanRepository;
        _programmeEditionRepository = programmeEditionRepository;
        _courseEditionRepository = courseEditionRepository;
    }

    public List<DegreeType_2> getAllDegreeTypes() {
        return _degreeTypeRepository.getAllDegreeTypes();
    }

    public List<ProgrammeDDD> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception {
        return _programmeRepository.getProgrammesByDegreeTypeID(degreeTypeID);
    }

    public List<CourseInStudyPlanDDD> getCoursesInStudyPlanByProgrammeID (ProgrammeID programmeID) {
        StudyPlanID studyPlanID = _studyPlanRepository.getLatestStudyPlanIDByProgrammeID(programmeID);
        return _courseInStudyPlanRepository.getCoursesInStudyPlanByStudyPlanID(studyPlanID);
    }

    public List<ProgrammeEditionDDD> getProgrammeEditionsByProgrammeID (ProgrammeID programmeID) {
        return _programmeEditionRepository.getProgrammeEditionsByProgrammeID(programmeID);

    }

//    public List<ProgrammeEdition> getAllProgrammeEditions() {
//        return _programmeEditionRepository.getAllProgrammeEditions();
//    }
//
//    public List<Course> getCoursesInProgramme(ProgrammeEdition programmeEdition) {
//        Programme programme = _programmeEditionRepository.findProgrammeInProgrammeEdition(programmeEdition);
//        return _programmeList.getCourseList(programme);
//    }
//
//    public boolean createCourseEdition (CourseInStudyPlanID courseInStudyPlanID, ProgrammeEditionID programmeEditionID) {
//            if(!_courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID))
//                return false;
//        _courseEditionRepository.createAndSaveCourseEdition(courseInStudyPlanID, programmeEditionID);
//            return true;
//    }
}