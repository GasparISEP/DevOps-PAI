package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;

public interface IDegreeTypeIDMapper {

    DegreeTypeIDDataModel toDataModel(DegreeTypeID id);

    DegreeTypeID toDomain(DegreeTypeIDDataModel dataModel);
}