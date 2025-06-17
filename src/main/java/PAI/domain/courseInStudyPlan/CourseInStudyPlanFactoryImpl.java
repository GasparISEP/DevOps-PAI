package PAI.domain.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;
import org.springframework.stereotype.Component;

import static PAI.utils.ValidationUtils.validateNotNull;

@Component
public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                  DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID) {

        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);
        CourseInStudyPlanGeneratedID generatedID = CourseInStudyPlanGeneratedID.randomID();

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);
    }

    public CourseInStudyPlan newCourseInStudyPlanFromDataModel(CourseInStudyPlanID courseInStudyPlanID, CourseInStudyPlanGeneratedID generatedID, Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID) {

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts, generatedID, programmeID);

    }

    @Override
    public CourseInStudyPlan newCourseInStudyPlan(CourseInStudyPlanCommand command) throws Exception {
        validateNotNull(command, "Command");

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
        ProgrammeID programmeID = new ProgrammeID(new Acronym(command.programmeAcronym().getAcronym()));

        return new CourseInStudyPlan(
                semester,
                curricularYear,
                courseID,
                studyPlanID,
                new CourseInStudyPlanID(courseID, studyPlanID),
                durationOfCourse,
                quantityOfCreditsEcts,
                generatedID,
                programmeID
        );
    }
}