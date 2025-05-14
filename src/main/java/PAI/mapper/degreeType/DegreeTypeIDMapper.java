package PAI.mapper.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.persistence.datamodel.degreeType.DegreeTypeIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class DegreeTypeIDMapper implements IDegreeTypeIDMapper {

    @Override
    public DegreeTypeIDDataModel toDataModel(DegreeTypeID id) {
        if (id == null) {
            throw new IllegalArgumentException("DegreeTypeID cannot be null");
        }
        return new DegreeTypeIDDataModel(id.getDTID());
    }

    @Override
    public DegreeTypeID toDomain(DegreeTypeIDDataModel dataModel) {
        if (dataModel == null) {
            throw new IllegalArgumentException("DegreeTypeIDDataModel cannot be null");
        }
        return new DegreeTypeID(dataModel.getDegreeTypeID());
    }
}