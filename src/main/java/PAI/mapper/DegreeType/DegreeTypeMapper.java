package PAI.mapper.DegreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeMapper implements IDegreeTypeMapper {

    private final IDegreeTypeIDMapper idMapper;
    private final IDegreeTypeFactory factory;

    public DegreeTypeMapper(IDegreeTypeIDMapper idMapper,  IDegreeTypeFactory factory) {
        this.idMapper = idMapper;
        this.factory = factory;
    }

    @Override
    public DegreeTypeDataModel toDataModel(DegreeType degreeType) {
        return new DegreeTypeDataModel(
                idMapper.toDataModel(degreeType.identity()),
                degreeType.getName(),
                degreeType.getMaxEcts()
        );
    }

    @Override
    public DegreeType toDomainModel(DegreeTypeDataModel dm) {
        return factory.recreate(
                idMapper.toDomain(dm.getId()),
                new Name(dm.getName()),
                new MaxEcts(dm.getMaxEcts())
        );
    }
}