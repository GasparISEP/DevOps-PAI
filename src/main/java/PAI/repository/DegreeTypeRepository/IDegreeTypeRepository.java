package PAI.repository.DegreeTypeRepository;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeType.DegreeType;

import java.util.List;

public interface IDegreeTypeRepository {
    boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;

    List<DegreeType> getAllDegreeTypes();
}
