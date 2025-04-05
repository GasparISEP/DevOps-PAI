package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.StudyPlan;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanDDDRepository;
import PAI.repository.courseRepository.ICourseRepository;
import PAI.repository.programmeRepository.IProgrammeDDDRepository;
import PAI.repository.studyPlanRepository.IStudyPlanDDDRepository;


public class US03_AddCourseToProgrammeController {

    private final IProgrammeDDDRepository iProgrammeDDDRepository;
    private final ICourseRepository iCourseRepository;
    private final IStudyPlanDDDRepository iStudyPlanRepository;
    private final ICourseInStudyPlanDDDRepository iCourseInStudyPlanRepository;


    public US03_AddCourseToProgrammeController(IProgrammeDDDRepository iProgrammeDDDRepository,
                                               ICourseRepository iCourseRepository, IStudyPlanDDDRepository iStudyPlanRepository,
                                               ICourseInStudyPlanDDDRepository iCourseInStudyPlanRepository) throws Exception {
        if(iProgrammeDDDRepository == null) {
            throw new IllegalArgumentException("ProgrammeList cannot be null.");
        }
        if (iCourseRepository == null) {
            throw new IllegalArgumentException("CourseRepository cannot be null.");
        }
        if (iStudyPlanRepository == null) {
            throw new IllegalArgumentException("StudyPlanRepository cannot be null.");
        }
        if(iCourseInStudyPlanRepository == null) {
            throw new IllegalArgumentException("CourseInStudyPlanRepository cannot be null.");
        }
        this.iProgrammeDDDRepository = iProgrammeDDDRepository;
        this.iCourseRepository = iCourseRepository;
        this.iStudyPlanRepository = iStudyPlanRepository;
        this.iCourseInStudyPlanRepository = iCourseInStudyPlanRepository;
    }

    public Iterable<Programme> getAllProgrammes() {
        return iProgrammeDDDRepository.findAll();
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
        return iCourseInStudyPlanRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
    }
}
