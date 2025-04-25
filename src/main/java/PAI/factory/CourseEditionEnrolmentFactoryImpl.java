package PAI.factory;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import PAI.domain.CourseEditionEnrolment;

import java.time.LocalDate;


public class CourseEditionEnrolmentFactoryImpl implements ICourseEditionEnrolmentFactory {

    public CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId) {
        return new CourseEditionEnrolment(studentId, courseEditionId);
    }

    @Override
    public CourseEditionEnrolment createWithEnrolmentDate(StudentID studentID, CourseEditionID courseEditionID, LocalDate enrolmentDate, boolean active) {
        return new CourseEditionEnrolment(studentID,courseEditionID);
    }
}
