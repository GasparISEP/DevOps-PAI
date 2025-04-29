package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.*;
import PAI.persistence.datamodel.*;
import java.util.UUID;

public class TeacherCareerProgressionIDImplMapper implements ITeacherCareerProgressionIDMapper {

    public TeacherCareerProgressionIDDataModel domainToDataModel(TeacherCareerProgressionID teacherCareerProgressionID) {

        UUID id = teacherCareerProgressionID.getIDValue();

        if (id == null){
            throw new NullPointerException("Id can not be null!");
        }

        return new TeacherCareerProgressionIDDataModel(id);
    }

    public TeacherCareerProgressionID dataModelToDomain(TeacherCareerProgressionIDDataModel teacherCareerProgressionIDDataModel) {

        UUID idFromDataModel = teacherCareerProgressionIDDataModel.getIDValue();

        return new TeacherCareerProgressionID(idFromDataModel);
    }
}
