package PAI.factory;

import PAI.domain.CourseEditionEnrolment;

import java.util.HashSet;
import java.util.Set;

public class CourseEditionEnrolmentListFactoryImpl implements ICourseEditionEnrolmentListFactory {

    public Set<CourseEditionEnrolment> getCourseEditionEnrollmentList(){
        return new HashSet<>();
    }
}
