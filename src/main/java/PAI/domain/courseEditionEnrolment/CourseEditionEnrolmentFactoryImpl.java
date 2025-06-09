package PAI.domain.courseEditionEnrolment;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseEditionEnrolmentFactoryImpl implements ICourseEditionEnrolmentFactory {

    public CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId) {
        return new CourseEditionEnrolment(studentId, courseEditionId);
    }

    @Override
    public CourseEditionEnrolment createWithEnrolmentDateAndUUID(CourseEditionEnrolmentGeneratedID uuid, StudentID studentID, CourseEditionID courseEditionID, Date enrolmentDate, EnrolmentStatus active) {
        return new CourseEditionEnrolment(uuid, studentID,courseEditionID, enrolmentDate, active);
    }
}
