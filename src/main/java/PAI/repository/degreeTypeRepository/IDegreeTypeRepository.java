package PAI.repository.degreeTypeRepository;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.ddd.IRepository;
import PAI.domain.degreeType.DegreeType;

import java.util.List;

public interface IDegreeTypeRepository extends IRepository<DegreeTypeID, DegreeType> {
    boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception;

    List<DegreeType> getAllDegreeTypes();
}
