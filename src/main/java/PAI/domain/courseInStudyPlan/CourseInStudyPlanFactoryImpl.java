package PAI.domain.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                  DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
    }

    public CourseInStudyPlan newCourseInStudyPlanFromDataModel(CourseInStudyPlanID courseInStudyPlanID, Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

    }

    @Override
    public CourseInStudyPlan newCourseInStudyPlan(CourseInStudyPlanCommand command) throws Exception {
        if (command == null) {
            throw new IllegalArgumentException("Command cannot be null");
        }

        Semester semester = command.semester();
        CurricularYear curricularYear = command.curricularYear();
        CourseID courseID = new CourseID(command.courseAcronym(), command.courseName());
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(command.programmeName(), command.programmeAcronym()),
                command.studyPlanDate()
        );
        DurationCourseInCurricularYear durationOfCourse = command.duration();
        CourseQuantityCreditsEcts quantityOfCreditsEcts = command.credits();

        return new CourseInStudyPlan(
                semester,
                curricularYear,
                courseID,
                studyPlanID,
                new CourseInStudyPlanID(courseID, studyPlanID),
                durationOfCourse,
                quantityOfCreditsEcts
        );
    }
}