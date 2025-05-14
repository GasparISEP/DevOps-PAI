package PAI.domain.repositoryInterfaces.degreeType;

import PAI.VOs.DegreeTypeID;
import PAI.ddd.IRepository;
import PAI.domain.degreeType.DegreeType;

import java.util.List;

public interface IDegreeTypeRepository extends IRepository<DegreeTypeID, DegreeType> {
    List<DegreeType> getAllDegreeTypes();
}
