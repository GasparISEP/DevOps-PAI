package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.EnrolmentStatus;
import PAI.VOs.StudentID;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Primary
public class CourseEditionEnrolmentFactoryImpl implements ICourseEditionEnrolmentFactory {

    public CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId) {
        return new CourseEditionEnrolment(studentId, courseEditionId);
    }

    @Override
    public CourseEditionEnrolment createWithEnrolmentDate(StudentID studentID, CourseEditionID courseEditionID, Date enrolmentDate, EnrolmentStatus active) {
        return new CourseEditionEnrolment(studentID,courseEditionID, enrolmentDate, active);
    }
}
