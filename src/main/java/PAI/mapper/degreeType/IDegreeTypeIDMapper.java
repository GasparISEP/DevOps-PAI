package PAI.mapper.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;

public interface IDegreeTypeIDMapper {

    DegreeTypeIDDataModel toDataModel(DegreeTypeID id);

    DegreeTypeID toDomain(DegreeTypeIDDataModel dataModel);
}