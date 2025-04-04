package PAI.repository.DegreeTypeRepoDDD;
import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeTypeDDD.DegreeType_2;
import PAI.factory.IDegreeTypeListFactory_2;
import PAI.factory.IDegreeTypeFactoryInterface_2;

import java.util.List;
import java.util.Objects;

public class DegreeTypeRepository_2 implements IDegreeTypeRepository_2 {
    private final IDegreeTypeFactoryInterface_2 degreeTypeFactory;
    private final List<DegreeType_2> degreeTypeRepository;

    public DegreeTypeRepository_2(IDegreeTypeFactoryInterface_2 degreeTypeFactory, IDegreeTypeListFactory_2 degreeTypeListFactory) {
        this.degreeTypeFactory = Objects.requireNonNull(degreeTypeFactory, "Factory cannot be null");
        this.degreeTypeRepository = Objects.requireNonNull(degreeTypeListFactory, "Factory cannot be null").createDegreeType_2List();
    }

    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        DegreeType_2 degreeType = degreeTypeFactory.addNewDegreeType_2(degreeTypeID, name, maxEcts);

        if (degreeTypeRepository.contains(degreeType)) {
            return false;
        }

        return degreeTypeRepository.add(degreeType);
    }

    @Override
    public List<DegreeType_2> getAllDegreeTypes() {
        return degreeTypeRepository;
    }
}