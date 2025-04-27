package PAI.mapper.courseID;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.persistence.datamodel.course.CourseIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class CourseIDMapperImpl implements ICourseIDMapper {

    public CourseID toDomain(CourseIDDataModel courseIDDataModel) {
        if (courseIDDataModel == null) {
            throw new IllegalArgumentException("courseIDDataModel cannot be null");
        }
        Acronym acronym = new Acronym(courseIDDataModel.getAcronym());
        Name name = new Name(courseIDDataModel.getName());
        return new CourseID(acronym, name);
    }

    public CourseIDDataModel toDataModel(CourseID courseID) {
        if (courseID == null) {
            throw new IllegalArgumentException("courseID cannot be null");
        }
        String acronym = courseID.getAcronym().getAcronym();
        String name = courseID.getName().getName();
        return new CourseIDDataModel(acronym, name);
    }
}
