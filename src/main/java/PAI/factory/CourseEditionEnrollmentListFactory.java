package PAI.factory;

import PAI.domain.CourseEditionEnrollment;

import java.util.HashSet;
import java.util.Set;

public class CourseEditionEnrollmentListFactory implements CourseEditionEnrollmentListFactoryInterface {

    public Set<CourseEditionEnrollment> getCourseEditionEnrollmentList(){
        return new HashSet<>();
    }
}
