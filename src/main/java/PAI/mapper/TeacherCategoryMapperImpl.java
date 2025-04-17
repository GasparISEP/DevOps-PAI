package PAI.mapper;

import PAI.VOs.Name;
import PAI.VOs.TeacherCategoryID;
import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;

import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryMapperImpl implements ITeacherCategoryMapper {

    @Override
    public TeacherCategoryDataModel toDataModel(TeacherCategory domain) {
        return new TeacherCategoryDataModel(
                domain.identity().getValue(),
                domain.getName().getName()
        );
    }

    @Override
    public TeacherCategory toDomainModel(TeacherCategoryDataModel data) {
        return new TeacherCategory(
                new TeacherCategoryID(data.getId()),
                new Name(data.getName())
        );
    }
}
