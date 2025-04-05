package PAI.repository.DegreeTypeRepository;
import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.DegreeType.DegreeType;
import PAI.domain.DegreeType.IDegreeTypeFactory;

import java.util.List;
import java.util.Objects;

public class DegreeTypeRepositoryImpl implements IDegreeTypeRepository {
    private final IDegreeTypeFactory degreeTypeFactory;
    private final List<DegreeType> degreeTypeRepository;

    public DegreeTypeRepositoryImpl(IDegreeTypeFactory degreeTypeFactory, IDegreeTypeListFactory degreeTypeListFactory) {
        this.degreeTypeFactory = Objects.requireNonNull(degreeTypeFactory, "Factory cannot be null");
        this.degreeTypeRepository = Objects.requireNonNull(degreeTypeListFactory, "Factory cannot be null").createDegreeType_2List();
    }

    public boolean registerDegreeType(DegreeTypeID degreeTypeID, Name name, MaxEcts maxEcts) throws Exception {
        DegreeType degreeType = degreeTypeFactory.addNewDegreeType_2(degreeTypeID, name, maxEcts);

        if (degreeTypeRepository.contains(degreeType)) {
            return false;
        }

        return degreeTypeRepository.add(degreeType);
    }

    @Override
    public List<DegreeType> getAllDegreeTypes() {
        return degreeTypeRepository;
    }
}