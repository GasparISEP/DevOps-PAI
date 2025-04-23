package PAI.mapper;

import PAI.VOs.TeacherCategoryID;
import PAI.persistence.datamodel.TeacherCategoryIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryIDMapper implements ITeacherCategoryIDMapper {

    @Override
    public TeacherCategoryIDDataModel toDataModel(TeacherCategoryID domainId) {
        if (domainId == null) {
            throw new IllegalArgumentException("Domain ID cannot be null");
        }
        return new TeacherCategoryIDDataModel(domainId.getValue());
    }

    @Override
    public TeacherCategoryID toDomainModel(TeacherCategoryIDDataModel dataModelId) {
        if (dataModelId == null || dataModelId.getValue() == null) {
            throw new IllegalArgumentException("Data Model ID or its value cannot be null");
        }
        return new TeacherCategoryID(dataModelId.getValue());
    }
}
