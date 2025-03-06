package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactory;
import PAI.factory.DegreeTypeListFactory;

import java.util.List;

public class DegreeTypeRepository {
    private final DegreeTypeFactory _degreeTypeFactory;
    private final List<DegreeType> _degreeTypeRepository;

    public DegreeTypeRepository(DegreeTypeFactory degreeTypeFactory, DegreeTypeListFactory degreeTypeListFactory) {
        _degreeTypeFactory = degreeTypeFactory;
        _degreeTypeRepository = degreeTypeListFactory.createDegreeTypeList();
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {
        DegreeType degreeType = _degreeTypeFactory.addNewDegreeType(name, maxEcts);

        for (DegreeType dt : _degreeTypeRepository) {
            if (dt.equals(degreeType)) {
                return false;
            }
        }

        _degreeTypeRepository.add(degreeType);
        return true;
    }
}

