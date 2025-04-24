package PAI.mapper.DegreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.DegreeType.DegreeTypeDataModel;

public interface IDegreeTypeMapper {
    DegreeTypeDataModel toDataModel(DegreeType degreeType);
    DegreeType toDomainModel(DegreeTypeDataModel degreeTypeDataModel);
}
