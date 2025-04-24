package PAI.mapper.DegreeType;

import PAI.VOs.DegreeTypeID;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeIDMapper implements IDegreeTypeIDMapper {

    @Override
    public String toString(DegreeTypeID id) {
        return id.getDTID();
    }

    @Override
    public DegreeTypeID toDomain(String idString) {
        return new DegreeTypeID(idString);
    }
}