package PAI.mapper.Course;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.persistence.datamodel.CourseDataModel;
import PAI.persistence.datamodel.CourseIDDataModel;


public class CourseMapperImpl implements ICourseMapper {

    private final CourseFactoryImpl courseFactory;

    public CourseMapperImpl(CourseFactoryImpl courseFactory) {
        if (courseFactory == null) throw new IllegalArgumentException("Factory cannot be null");
        this.courseFactory = courseFactory;
    }

    @Override
    public Course toDomain(CourseDataModel courseDataModel) throws Exception {
        if (courseDataModel == null) {
            throw new NullPointerException("courseDataModel cannot be null");
        }
        Name name = new Name(courseDataModel.get_name());
        Acronym acronym = new Acronym(courseDataModel.get_acronym());
        CourseID courseID = new CourseID(acronym, name);
        CourseQuantityCreditsEcts quantityCreditsEcts = new CourseQuantityCreditsEcts(courseDataModel.get_quantityCreditsEcts());
        DurationCourseInCurricularYear durationCourseInSemester = new DurationCourseInCurricularYear(courseDataModel.get_duration());

        return courseFactory.createCourse(name, acronym, quantityCreditsEcts, durationCourseInSemester);
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
                acronym,
                course.getCourseQuantityCreditsEcts().getQuantity(),
                course.getDurationCourseInCurricularYear().getDuration()
        );
    }

}