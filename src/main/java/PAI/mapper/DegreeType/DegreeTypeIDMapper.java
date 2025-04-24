package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;

public class DegreeTypeIDMapper {


    public static String toString(DegreeTypeID id) {
        return id.getDTID();
    }


    public static DegreeTypeID toDomain(String idString) {
        return new DegreeTypeID(idString);
    }
}
