package PAI.persistence.mem.courseEditionEnrolment;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;

import java.util.Set;

public interface ICourseEditionEnrolmentListFactory {

    Set<CourseEditionEnrolment> getCourseEditionEnrolmentList();
}
