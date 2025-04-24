package PAI.mapper.DegreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeMapper implements IDegreeTypeMapper {

    private final IDegreeTypeIDMapper idMapper;

    public DegreeTypeMapper(IDegreeTypeIDMapper idMapper) {
        this.idMapper = idMapper;
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
        return new DegreeType(
                idMapper.toDomain(dm.getId()),
                new Name(dm.getName()),
                new MaxEcts(dm.getMaxEcts())
        );
    }
}
