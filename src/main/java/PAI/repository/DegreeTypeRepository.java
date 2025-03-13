package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.DegreeTypeFactoryInterface;
import PAI.factory.DegreeTypeListFactoryInterface;

import java.util.List;
import java.util.Objects;

public class DegreeTypeRepository {
    private final DegreeTypeFactoryInterface _degreeTypeFactoryInterface;
    private final List<DegreeType> _degreeTypeRepository;

    public DegreeTypeRepository(DegreeTypeFactoryInterface degreeTypeFactoryInterface, DegreeTypeListFactoryInterface degreeTypeListFactoryInterface) {
        _degreeTypeFactoryInterface = Objects.requireNonNull(degreeTypeFactoryInterface,"Factory cannot be null");
        if (degreeTypeListFactoryInterface == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _degreeTypeRepository = degreeTypeListFactoryInterface.createDegreeTypeList();
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {
        DegreeType degreeType = _degreeTypeFactoryInterface.addNewDegreeType(name, maxEcts);

        for (DegreeType dt : _degreeTypeRepository) {
            if (dt.equals(degreeType)) {
                return false;
            }
        }

        _degreeTypeRepository.add(degreeType);
        return true;
    }
}

