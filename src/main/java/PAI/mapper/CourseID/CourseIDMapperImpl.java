package PAI.mapper.CourseID;

import PAI.VOs.Acronym;
import PAI.VOs.CourseID;
import PAI.VOs.Name;
import PAI.persistence.datamodel.CourseIDDataModel;

public class CourseIDMapperImpl implements ICourseIDMapper {

    public CourseID toDomain(CourseIDDataModel courseIDDataModel) {
        if (courseIDDataModel == null) {
            throw new NullPointerException("courseIDDataModel cannot be null");
        }
        Acronym acronym = new Acronym(courseIDDataModel.getAcronym());
        Name name = new Name(courseIDDataModel.getName());
        return new CourseID(acronym, name);
    }

    public CourseIDDataModel toDataModel(CourseID courseID) {
        if (courseID == null) {
            throw new NullPointerException("courseID cannot be null");
        }
        String acronym = courseID.getAcronym().toString();
        String name = courseID.getName().toString();
        return new CourseIDDataModel(acronym, name);
    }
}
