package PAI.mapper;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeTypeDM;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeMapper {

    public DegreeTypeDM toDataModel(DegreeType degreeType) {
        return new DegreeTypeDM(
                degreeType.identity().getDTID(),
                degreeType.getName(),
                degreeType.getMaxEcts()
        );
    }

    public DegreeType toDomainModel(DegreeTypeDM dm) {
        return new DegreeType(
                new DegreeTypeID(dm.getId()),
                new Name(dm.getName()),
                new MaxEcts(dm.getMaxEcts())
        );
    }
}
