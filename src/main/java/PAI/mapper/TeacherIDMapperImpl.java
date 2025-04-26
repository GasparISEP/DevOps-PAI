package PAI.mapper;

import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import PAI.persistence.datamodel.TeacherIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherIDMapperImpl implements ITeacherIDMapper {

    public TeacherID toDomain (TeacherIDDataModel teacherIDDataModel) {

        if (teacherIDDataModel == null)
            return null;

        String teacherAcronym = teacherIDDataModel.getTeacherAcronym();
        TeacherAcronym acronym = new TeacherAcronym(teacherAcronym);

        return new TeacherID(acronym);
    }

    public TeacherIDDataModel toDataModel (TeacherID teacherID) {

        if (teacherID == null)
            return null;

        TeacherAcronym teacherAcronym = teacherID.getTeacherAcronym();

        String acronym = teacherAcronym.getAcronym();

        return new TeacherIDDataModel(acronym);
    }
}
