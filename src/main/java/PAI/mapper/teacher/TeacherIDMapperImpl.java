package PAI.mapper.teacher;

import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.teacher.TeacherIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherIDMapperImpl implements ITeacherIDMapper {

    public TeacherID toDomain (TeacherIDDataModel teacherIDDataModel) {

        if (teacherIDDataModel == null)
            throw new IllegalArgumentException("TeacherID Data Model cannot be null.");

        String teacherAcronym = teacherIDDataModel.getTeacherAcronym();
        TeacherAcronym acronym = new TeacherAcronym(teacherAcronym);

        return new TeacherID(acronym);
    }

    public TeacherIDDataModel toDataModel (TeacherID teacherID) {

        if (teacherID == null)
            throw new IllegalArgumentException("TeacherID cannot be null.");

        TeacherAcronym teacherAcronym = teacherID.getTeacherAcronym();

        String acronym = teacherAcronym.getAcronym();

        return new TeacherIDDataModel(acronym);
    }
}
