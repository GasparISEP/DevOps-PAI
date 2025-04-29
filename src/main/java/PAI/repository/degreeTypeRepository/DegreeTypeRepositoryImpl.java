package PAI.repository.degreeTypeRepository;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.factory.DegreeTypeFactory.IDegreeTypeListFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DegreeTypeRepositoryImpl implements IDegreeTypeRepository {
    private final List<DegreeType> degreeTypeRepository;

    public DegreeTypeRepositoryImpl(IDegreeTypeListFactory degreeTypeListFactory) {
        this.degreeTypeRepository = Objects.requireNonNull(degreeTypeListFactory, "Factory cannot be null").createEmptyList();
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
        return degreeTypeRepository.stream()
                .filter(dt -> dt.identity().equals(id))
                .findFirst();
    }

    @Override
    public boolean containsOfIdentity(DegreeTypeID id) {
        return ofIdentity(id).isPresent();
    }
}