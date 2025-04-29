package PAI.mapper.course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.ICourseFactory;
import PAI.mapper.courseID.ICourseIDMapper;
import PAI.persistence.datamodel.course.CourseDataModel;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapperImpl implements ICourseMapper {

    private final ICourseFactory courseFactory;
    private final ICourseIDMapper courseIDMapper;

    public CourseMapperImpl(ICourseFactory courseFactory, ICourseIDMapper courseIDMapper) {
        if (courseFactory == null || courseIDMapper == null) throw new IllegalArgumentException("Factory and Mapper cannot be null");
        this.courseFactory = courseFactory;
        this.courseIDMapper = courseIDMapper;
    }

    @Override
    public Course toDomain(CourseDataModel courseDataModel) {
        if (courseDataModel == null) {
            throw new NullPointerException("courseDataModel cannot be null");
        }
        CourseID courseID = courseIDMapper.toDomain(courseDataModel.getCourseID());
        Name name = new Name(courseDataModel.getName());
        Acronym acronym = new Acronym(courseDataModel.getAcronym());

        return courseFactory.createCourse(courseID, name, acronym);
    }

    @Override
    public CourseDataModel toDataModel(Course course) {
        if (course == null) {
            throw new NullPointerException("course cannot be null");
        }

        String acronym = course.getAcronym().getAcronym();
        String name = course.getName().getName();

        CourseIDDataModel courseIDDataModel = courseIDMapper.toDataModel(course.identity());

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