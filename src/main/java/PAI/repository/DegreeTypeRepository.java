package PAI.repository;

import PAI.domain.DegreeType;
import PAI.factory.IDegreeTypeListFactory;
import PAI.factory.IDegreeTypeFactoryInterface;

import java.util.List;
import java.util.Objects;

public class DegreeTypeRepository {
    private final IDegreeTypeFactoryInterface _I_degreeTypeFactoryInterface;
    private final List<DegreeType> _degreeTypeRepository;

    public DegreeTypeRepository(IDegreeTypeFactoryInterface IDegreeTypeFactoryInterface, IDegreeTypeListFactory IDegreeTypeListFactory) {
        _I_degreeTypeFactoryInterface = Objects.requireNonNull(IDegreeTypeFactoryInterface,"Factory cannot be null");
        if (IDegreeTypeListFactory == null) {
            throw new IllegalArgumentException("Factory cannot be null!");

        }
        _degreeTypeRepository = IDegreeTypeListFactory.createDegreeTypeList();
    }

    public boolean registerDegreeType(String name, int maxEcts) throws Exception {
        DegreeType degreeType = _I_degreeTypeFactoryInterface.addNewDegreeType(name, maxEcts);

        for (DegreeType dt : _degreeTypeRepository) {
            if (dt.equals(degreeType)) {
                return false;
            }
        }

        _degreeTypeRepository.add(degreeType);
        return true;
    }
}

