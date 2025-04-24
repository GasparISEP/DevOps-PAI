package PAI.mapper.DegreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.mapper.DegreeType.IDegreeTypeIDMapper;
import PAI.persistence.datamodel.DegreeTypeDM;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeMapper implements IDegreeTypeMapper {

    private final IDegreeTypeIDMapper idMapper;

    public DegreeTypeMapper(IDegreeTypeIDMapper idMapper) {
        this.idMapper = idMapper;
    }

    @Override
    public DegreeTypeDM toDataModel(DegreeType degreeType) {
        return new DegreeTypeDM(
                idMapper.toString(degreeType.identity()),
                degreeType.getName(),
                degreeType.getMaxEcts()
        );
    }

    @Override
    public DegreeType toDomainModel(DegreeTypeDM dm) {
        return new DegreeType(
                idMapper.toDomain(dm.getId()),
                new Name(dm.getName()),
                new MaxEcts(dm.getMaxEcts())
        );
    }
}
