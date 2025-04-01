package PAI.repository.DegreeTypeRepoDDD;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;

public interface IDegreeTypeRepository_2 {
    boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;
}
