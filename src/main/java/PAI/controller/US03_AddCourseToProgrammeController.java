
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.StudyPlan;
import PAI.service.StudyPlan.IStudyPlanService;
import PAI.service.course.ICourseService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;


public class US03_AddCourseToProgrammeController {

    private final IProgrammeService programmeService;
    private final ICourseService courseService;
    private final IStudyPlanService studyPlanService;
    private final ICourseInStudyPlanService courseInStudyPlanService;

    public US03_AddCourseToProgrammeController(IProgrammeService programmeService, ICourseService courseService, IStudyPlanService studyPlanService, ICourseInStudyPlanService courseInStudyPlanService) {

        if (programmeService == null) {
            throw new IllegalArgumentException("Programme Service cannot be null.");
        }
        if (courseService == null) {
            throw new IllegalArgumentException("Course Service cannot be null.");
        }
        if (studyPlanService == null) {
            throw new IllegalArgumentException("Study Plan Service cannot be null.");
        }
        if (courseInStudyPlanService == null) {
            throw new IllegalArgumentException("Course In Study Plan Service cannot be null.");
        }
        this.programmeService = programmeService;
        this.courseService = courseService;
        this.studyPlanService = studyPlanService;
        this.courseInStudyPlanService = courseInStudyPlanService;
    }

    public Iterable<Programme> getAllProgrammes() {
        return programmeService.findAll();
    }

    public Iterable<Course> getAllCourses() {
        return courseService.findAll();
    }

    public StudyPlanID getLatestStudyPlanByProgrammeId(ProgrammeID programmeID) {
        if (programmeID == null) {
            throw new IllegalArgumentException("ProgrammeID cannot be null.");
        }
        return studyPlanService.getLatestStudyPlanIDByProgrammeID(programmeID);
    }

    public boolean addCourseToProgramme(int semesterInt, int curricularYearInt, Course course, StudyPlan studyPlan, int duration, double quantEcts) throws Exception {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (studyPlan == null) {
            throw new IllegalArgumentException("StudyPlan cannot be null.");
        }
            CourseID courseID = course.identity();
            StudyPlanID studyPlanID = studyPlan.identity();
            Semester semester = new Semester(semesterInt);
            CurricularYear curricularYear = new CurricularYear(curricularYearInt);
            DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(duration);
            CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(quantEcts);

            return courseInStudyPlanService.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts);
        }
    }


