package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;

public interface IDegreeTypeIDMapper {
    String toString(DegreeTypeID id);
    DegreeTypeID toDomain(String idString);
}
