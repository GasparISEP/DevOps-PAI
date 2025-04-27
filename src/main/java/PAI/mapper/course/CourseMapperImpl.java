package PAI.mapper.course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapperImpl implements ICourseMapper {

    private final ICourseFactory courseFactory;

    public CourseMapperImpl(ICourseFactory courseFactory) {
        if (courseFactory == null) throw new IllegalArgumentException("Factory cannot be null");
        this.courseFactory = courseFactory;
    }

    @Override
    public Course toDomain(CourseDataModel courseDataModel) {
        if (courseDataModel == null) {
            throw new NullPointerException("courseDataModel cannot be null");
        }
        Name name = new Name(courseDataModel.getName());
        Acronym acronym = new Acronym(courseDataModel.getAcronym());

        return courseFactory.createCourse(name, acronym);
    }

    @Override
    public CourseDataModel toDataModel(Course course) {
        if (course == null) {
            throw new NullPointerException("course cannot be null");
        }

        CourseID courseID = course.identity();
        String acronym = courseID.getAcronym().getAcronym();
        String name = courseID.getName().getName();

        CourseIDDataModel courseIDDataModel = new CourseIDDataModel(acronym, name);

        return new CourseDataModel(
                courseIDDataModel,
                name,
                acronym
        );
    }

    public Iterable<Course> toDomain(Iterable<CourseDataModel> listDataModel) {
        List<Course> listDomain = new ArrayList<>();
        for (CourseDataModel courseDataModel : listDataModel) {
            Course course = toDomain(courseDataModel);
            listDomain.add(course);
        }
        return listDomain;
    }
}