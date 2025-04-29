package PAI.service.DegreeType;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;

import java.util.List;
import java.util.Optional;

public interface IDegreeTypeService {

    boolean registerDegreeType(Name name, MaxEcts maxEcts) throws Exception;

    Optional<DegreeType> getDegreeTypeById(DegreeTypeID id);

    List<DegreeType> getAllDegreeTypes();
}