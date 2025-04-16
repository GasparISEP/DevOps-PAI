package PAI.service;

import PAI.VOs.DegreeTypeID;
import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import PAI.domain.degreeType.DegreeType;
import PAI.factory.DegreeTypeFactory.IDegreeTypeFactory;
import PAI.repository.degreeTypeRepository.IDegreeTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DegreeTypeService {

    private final IDegreeTypeRepository repository;
    private final IDegreeTypeFactory factory;

    public DegreeTypeService(IDegreeTypeRepository repository, IDegreeTypeFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    public boolean registerDegreeType(Name name, MaxEcts maxEcts) throws Exception {
        DegreeType degreeType = factory.create(name, maxEcts);
        return repository.registerDegreeType(
                degreeType.identity(),
                name,
                maxEcts
        );
    }

    public Optional<DegreeType> getDegreeTypeById(DegreeTypeID id) {
        return repository.ofIdentity(id);
    }

    public List<DegreeType> getAllDegreeTypes() {
        return repository.getAllDegreeTypes();
    }
}