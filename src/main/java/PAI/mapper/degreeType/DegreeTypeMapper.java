package PAI.mapper.degreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.persistence.datamodel.degreeType.DegreeTypeDataModel;
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
                degreeType.getName().getName(),
                degreeType.getMaxEcts().getMaxEcts()
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