package PAI.domain.courseEditionEnrolment;

import PAI.VOs.CourseEditionID;
import PAI.VOs.StudentID;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface ICourseEditionEnrolmentFactory {

    CourseEditionEnrolment createCourseEditionEnrolment(StudentID studentId, CourseEditionID courseEditionId);

    CourseEditionEnrolment createWithEnrolmentDate(StudentID studentID, CourseEditionID courseEditionID, LocalDate enrolmentDate, boolean active);
}
