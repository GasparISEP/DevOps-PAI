package PAI.mapper.DegreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeTypeDM;

public interface IDegreeTypeMapper {
    DegreeTypeDM toDataModel(DegreeType degreeType);
    DegreeType toDomainModel(DegreeTypeDM degreeTypeDM);
}
