package PAI.controller;
import PAI.VOs.DegreeTypeID;
import PAI.domain.programme.ProgrammeDDD;
import PAI.repository.CourseEditionRepository;
import PAI.repository.DegreeTypeRepoDDD.IDegreeTypeRepository_2;
import PAI.repository.ICourseEditionRepositoryDDD;
import PAI.repository.ProgrammeEditionRepository;
import PAI.repository.ProgrammeRepository;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;


import java.util.List;

public class US19_CreateCourseEditionController {
    private final IDegreeTypeRepository_2 _degreeTypeRepository;
    private final IProgrammeDDDRepository _programmeRepository;
    private final ICourseInStudyPlanDDDRepository _courseInStudyPlanRepository;
    private final IProgrammeEditionRepositoryDDD _programmeEditionRepository;
    private final ICourseEditionRepositoryDDD _courseEditionRepository;

    public US19_CreateCourseEditionController(IDegreeTypeRepository_2 degreeTypeRepository,
                                              IProgrammeDDDRepository programmeRepository,
                                              ICourseInStudyPlanDDDRepository courseInStudyPlanRepository,
                                              IProgrammeEditionRepositoryDDD programmeEditionRepository,
                                              ICourseEditionRepositoryDDD courseEditionRepository){
        if (degreeTypeRepository == null) {
            throw new IllegalArgumentException("degreeTypeRepository cannot null");
        }
        if (programmeRepository == null) {
            throw new IllegalArgumentException("programmeRepository cannot null");
        }
        if (courseInStudyPlanRepository == null) {
            throw new IllegalArgumentException("courseInStudyPlanRepository cannot null");
        }
        if (programmeEditionRepository == null) {
            throw new IllegalArgumentException("programmeEditionRepository cannot null");
        }
        if (courseEditionRepository == null) {
            throw new IllegalArgumentException("courseEditionRepository cannot null");
        }

        _degreeTypeRepository = degreeTypeRepository;
        _programmeRepository = programmeRepository;
        _courseInStudyPlanRepository = courseInStudyPlanRepository;
        _programmeEditionRepository = programmeEditionRepository;
        _courseEditionRepository = courseEditionRepository;
    }

    public List<ProgrammeDDD> getProgrammesByDegreeTypeID(DegreeTypeID degreeTypeID) throws Exception {
        return _programmeRepository.getProgrammesByDegreeTypeID(degreeTypeID);
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