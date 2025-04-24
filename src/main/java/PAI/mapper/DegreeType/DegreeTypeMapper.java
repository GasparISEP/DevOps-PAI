package PAI.mapper.DegreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeTypeDM;
import org.springframework.stereotype.Component;

import static PAI.mapper.DegreeType.DegreeTypeIDMapper.toDomain;

@Component
public class DegreeTypeMapper {

    public DegreeTypeDM toDataModel(DegreeType degreeType) {
        return new DegreeTypeDM(
                DegreeTypeIDMapper.toString(degreeType.identity()),  // ← usa o mapper centralizado
                degreeType.getName(),
                degreeType.getMaxEcts()
        );
    }

    public DegreeType toDomainModel(DegreeTypeDM dm) {
        return new DegreeType(
                toDomain(dm.getId()),              // ← usa o mapper centralizado
                new Name(dm.getName()),
                new MaxEcts(dm.getMaxEcts())
        );
    }
}
