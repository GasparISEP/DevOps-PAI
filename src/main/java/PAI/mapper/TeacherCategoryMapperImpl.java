package PAI.mapper;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryMapperImpl implements ITeacherCategoryMapper {

    // This class is responsible for mapping between the domain model and the data model
    @Override
    public TeacherCategoryDataModel toDataModel(TeacherCategory domain) {
        return new TeacherCategoryDataModel(
                TeacherCategoryIDMapper.toDataModel(domain.identity()),
                domain.getName().getName()
        );
    }
    // This method is responsible for mapping from the data model to the domain model
    @Override
    public TeacherCategory toDomainModel(TeacherCategoryDataModel data) {
        return new TeacherCategory(
                TeacherCategoryIDMapper.toDomainModel(data.getId()),
                new Name(data.getName())
        );
    }
}
