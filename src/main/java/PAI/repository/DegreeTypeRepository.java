package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactoryImpl;
import PAI.factory.DegreeTypeListFactory;

import java.util.List;

public class DegreeTypeRepository {
    private final DegreeTypeFactoryImpl _degreeTypeFactoryImpl;
    private final List<DegreeType> _degreeTypeRepository;

    public DegreeTypeRepository(DegreeTypeFactoryImpl degreeTypeFactoryImpl, DegreeTypeListFactory degreeTypeListFactory) {
        _degreeTypeFactoryImpl = degreeTypeFactoryImpl;
        _degreeTypeRepository = degreeTypeListFactory.createDegreeTypeList();
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {
        DegreeType degreeType = _degreeTypeFactoryImpl.addNewDegreeType(name, maxEcts);

        for (DegreeType dt : _degreeTypeRepository) {
            if (dt.equals(degreeType)) {
                return false;
            }
        }

        _degreeTypeRepository.add(degreeType);
        return true;
    }
}

