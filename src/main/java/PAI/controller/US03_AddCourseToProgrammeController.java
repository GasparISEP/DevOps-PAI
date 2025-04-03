package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.CourseDDD;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDRepository;
import PAI.repository.courseRepositoryDDD.ICourseRepositoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;


public class US03_AddCourseToProgrammeController {

    private final IProgrammeDDDRepository iProgrammeDDDRepository;
    private final ICourseRepositoryDDD iCourseRepository;
    private final IStudyPlanDDDRepository iStudyPlanRepository;
    private final ICourseInStudyPlanDDDRepository iCourseInStudyPlanRepository;


    public US03_AddCourseToProgrammeController(IProgrammeDDDRepository iProgrammeDDDRepository,
                                               ICourseRepositoryDDD iCourseRepositoryDDD, IStudyPlanDDDRepository iStudyPlanRepository,
                                               ICourseInStudyPlanDDDRepository iCourseInStudyPlanRepository) throws Exception {
        if(iProgrammeDDDRepository == null) {
            throw new IllegalArgumentException("ProgrammeList cannot be null.");
        }
        if (iCourseRepositoryDDD == null) {
            throw new IllegalArgumentException("CourseRepository cannot be null.");
        }
        if (iStudyPlanRepository == null) {
            throw new IllegalArgumentException("StudyPlanRepository cannot be null.");
        }
        if(iCourseInStudyPlanRepository == null) {
            throw new IllegalArgumentException("CourseInStudyPlanRepository cannot be null.");
        }
        this.iProgrammeDDDRepository = iProgrammeDDDRepository;
        this.iCourseRepository = iCourseRepositoryDDD;
        this.iStudyPlanRepository = iStudyPlanRepository;
        this.iCourseInStudyPlanRepository = iCourseInStudyPlanRepository;
    }

    public Iterable<ProgrammeDDD> getAllProgrammes() {
        return iProgrammeDDDRepository.findAll();
    }

    public Iterable<CourseDDD> getAllCourses() {
        return iCourseRepository.findAll();
    }

    public Iterable<StudyPlanDDD> getAllStudyPlansByProgrammeId(ProgrammeID programmeID) {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null.");
        }
        return iStudyPlanRepository.getAllStudyPlansByProgrammeId(programmeID);
    }

    public boolean addCourseToProgramme(Semester semester, CurricularYear curricularYear, CourseDDD courseDDD, StudyPlanDDD studyPlanDDD) throws Exception {
        if (courseDDD == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (studyPlanDDD == null) {
            throw new IllegalArgumentException("StudyPlanDDD cannot be null.");
        }
        if(curricularYear == null) {
            throw new IllegalArgumentException("Curricular Year cannot be null.");
        }
        if(semester == null) {
            throw new IllegalArgumentException("Semester cannot be null.");
        }
        CourseID courseID = courseDDD.identity();
        StudyPlanID studyPlanID = studyPlanDDD.identity();
        return iCourseInStudyPlanRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
    }
}
