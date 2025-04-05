package PAI.repository.degreeTypeRepository;
import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public DegreeType save(DegreeType degreeType) {
        degreeTypeRepository.add(degreeType);
        return degreeType;
    }

    @Override
    public Iterable<DegreeType> findAll() {
        return degreeTypeRepository;
    }

    @Override
    public Optional<DegreeType> ofIdentity(DegreeTypeID id) {
        for (DegreeType existingDegreeType : degreeTypeRepository) {
            if (existingDegreeType.identity().equals(id)) {
                return Optional.of(existingDegreeType);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(DegreeTypeID id) {
        return ofIdentity(id).isPresent();
    }
}