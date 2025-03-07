package PAI.domain;

import PAI.factory.CourseEditionEnrollmentListFactoryInterface;

import java.util.HashSet;
import java.util.Set;

public class CourseEditionEnrollmentListFactory implements CourseEditionEnrollmentListFactoryInterface {

    public Set<CourseEditionEnrollment> getCourseEditionEnrollmentList(){
        return new HashSet<>();
    }
}
