package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.Date;
import PAI.VOs.EnrolmentStatus;
import PAI.VOs.StudentID;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId);

    CourseEditionEnrolment createWithEnrolmentDate(StudentID studentID, CourseEditionID courseEditionID, Date enrolmentDate, EnrolmentStatus active);
}
