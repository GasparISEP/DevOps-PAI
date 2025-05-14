package PAI.mapper.degreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.persistence.datamodel.degreeType.DegreeTypeDataModel;

public interface IDegreeTypeMapper {
    DegreeTypeDataModel toDataModel(DegreeType degreeType);
    DegreeType toDomainModel(DegreeTypeDataModel degreeTypeDataModel);
}
