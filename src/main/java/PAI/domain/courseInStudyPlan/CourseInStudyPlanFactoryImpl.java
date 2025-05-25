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

        Semester semester = new Semester(command.semester());
        CurricularYear curricularYear = new CurricularYear(command.curricularYear());
        CourseID courseID = new CourseID(new Acronym(command.courseAcronym()), new Name(command.courseName()));
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(
                        new NameWithNumbersAndSpecialChars(command.programmeName()),
                        new Acronym(command.programmeAcronym())
                ),
                new PAI.VOs.Date(command.studyPlanDate())
        );
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(command.duration());
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(command.credits());

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