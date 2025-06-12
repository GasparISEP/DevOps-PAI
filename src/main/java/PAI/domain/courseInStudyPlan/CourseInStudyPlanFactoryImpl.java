package PAI.domain.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                  DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseInStudyPlanGeneratedID generatedID = CourseInStudyPlanGeneratedID.randomID();

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID);
    }

    public CourseInStudyPlan newCourseInStudyPlanFromDataModel(CourseInStudyPlanID courseInStudyPlanID, CourseInStudyPlanGeneratedID generatedID, Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID);

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
                new ProgrammeID(command.programmeAcronym()),
                command.studyPlanDate()
        );
        DurationCourseInCurricularYear durationOfCourse = command.duration();
        CourseQuantityCreditsEcts quantityOfCreditsEcts = command.credits();
        CourseInStudyPlanGeneratedID generatedID = CourseInStudyPlanGeneratedID.randomID();

        return new CourseInStudyPlan(
                semester,
                curricularYear,
                courseID,
                studyPlanID,
                new CourseInStudyPlanID(courseID, studyPlanID),
                durationOfCourse,
                quantityOfCreditsEcts,
                generatedID
        );
    }
}