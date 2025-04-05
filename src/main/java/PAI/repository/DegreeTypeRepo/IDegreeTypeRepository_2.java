package PAI.repository.DegreeTypeRepo;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeTypeDDD.DegreeType_2;

import java.util.List;

public interface IDegreeTypeRepository_2 {
    boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;

    List<DegreeType_2> getAllDegreeTypes();
}
