package PAI.factory;

import PAI.domain.CourseEditionEnrollment;

import java.util.HashSet;
import java.util.Set;

public class CourseEditionEnrollmentListFactoryImpl implements ICourseEditionEnrollmentListFactory {

    public Set<CourseEditionEnrollment> getCourseEditionEnrollmentList(){
        return new HashSet<>();
    }
}
