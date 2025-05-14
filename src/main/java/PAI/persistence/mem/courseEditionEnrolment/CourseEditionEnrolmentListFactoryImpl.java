package PAI.persistence.mem.courseEditionEnrolment;

import PAI.domain.courseEditionEnrolment.CourseEditionEnrolment;

import java.util.HashSet;
import java.util.Set;

public class CourseEditionEnrolmentListFactoryImpl implements ICourseEditionEnrolmentListFactory {

    public Set<CourseEditionEnrolment> getCourseEditionEnrolmentList(){
        return new HashSet<>();
    }
}
