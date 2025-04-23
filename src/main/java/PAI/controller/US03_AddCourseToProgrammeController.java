/*
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.StudyPlan;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
import PAI.repository.courseRepository.ICourseRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;


public class US03_AddCourseToProgrammeController {

    private final IProgrammeRepository iProgrammeRepository;
    private final ICourseRepository iCourseRepository;
    private final IStudyPlanRepository iStudyPlanRepository;
    private final ICourseInStudyPlanService iCourseInStudyPlanService;


    public US03_AddCourseToProgrammeController(IProgrammeRepository iProgrammeRepository,
                                               ICourseRepository iCourseRepository, IStudyPlanRepository iStudyPlanRepository,
                                               ICourseInStudyPlanService iCourseInStudyPlanService) throws Exception {
        if(iProgrammeRepository == null) {
            throw new IllegalArgumentException("ProgrammeList cannot be null.");
        }
        if (iCourseRepository == null) {
            throw new IllegalArgumentException("CourseRepository cannot be null.");
        }
        if (iStudyPlanRepository == null) {
            throw new IllegalArgumentException("StudyPlanRepository cannot be null.");
        }
        if(iCourseInStudyPlanService == null) {
            throw new IllegalArgumentException("CourseInStudyPlanRepository cannot be null.");
        }
        this.iProgrammeRepository = iProgrammeRepository;
        this.iCourseRepository = iCourseRepository;
        this.iStudyPlanRepository = iStudyPlanRepository;
        this.iCourseInStudyPlanService = iCourseInStudyPlanService;
    }

    public Iterable<Programme> getAllProgrammes() {
        return iProgrammeRepository.findAll();
    }

    public Iterable<Course> getAllCourses() {
        return iCourseRepository.findAll();
    }

    public Iterable<StudyPlan> getAllStudyPlansByProgrammeId(ProgrammeID programmeID) {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null.");
        }
        return iStudyPlanRepository.getAllStudyPlansByProgrammeId(programmeID);
    }

    public boolean addCourseToProgramme(Semester semester, CurricularYear curricularYear, Course course, StudyPlan studyPlan) throws Exception {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (studyPlan == null) {
            throw new IllegalArgumentException("StudyPlanDDD cannot be null.");
        }
        if(curricularYear == null) {
            throw new IllegalArgumentException("Curricular Year cannot be null.");
        }
        if(semester == null) {
            throw new IllegalArgumentException("Semester cannot be null.");
        }
        CourseID courseID = course.identity();
        StudyPlanID studyPlanID = studyPlan.identity();
        return iCourseInStudyPlanService.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID);
    }
}
*/
