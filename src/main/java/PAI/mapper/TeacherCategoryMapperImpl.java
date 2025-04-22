package PAI.mapper;

import PAI.VOs.Name;
import PAI.domain.TeacherCategory;
import PAI.persistence.datamodel.TeacherCategoryDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherCategoryMapperImpl implements ITeacherCategoryMapper {

    private final ITeacherCategoryIDMapper idMapper;

    @Autowired
    public TeacherCategoryMapperImpl(ITeacherCategoryIDMapper idMapper) {
        this.idMapper = idMapper;
    }

    @Override
    public TeacherCategoryDataModel toDataModel(TeacherCategory domain) {
        if (domain == null) {
            throw new IllegalArgumentException("Domain object cannot be null");
        }

        return new TeacherCategoryDataModel(
                idMapper.toDataModel(domain.identity()),
                domain.getName().getName()
        );
    }

    @Override
    public TeacherCategory toDomainModel(TeacherCategoryDataModel data) {
        if (data == null || data.getId() == null || data.getName() == null) {
            throw new IllegalArgumentException("DataModel fields cannot be null");
        }

        return new TeacherCategory(
                idMapper.toDomainModel(data.getId()),
                new Name(data.getName())
        );
    }
}
