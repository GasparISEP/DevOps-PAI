package PAI.service.degreeType;

import PAI.domain.degreeType.DegreeType;
import PAI.domain.degreeType.IDegreeTypeFactory;
import PAI.domain.repositoryInterfaces.degreeType.IDegreeTypeRepository;
import PAI.dto.degreeType.RegisterDegreeTypeCommand;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

@Service
public class DegreeTypeRegistrationServiceImpl implements IDegreeTypeRegistrationService{

    private final IDegreeTypeFactory _degreeTypeFactory;
    private final IDegreeTypeRepository _degreeTypeRepository;

    public DegreeTypeRegistrationServiceImpl (IDegreeTypeFactory degreeTypeFactory, IDegreeTypeRepository degreeTypeRepository) {
        if(degreeTypeFactory == null) throw new IllegalArgumentException("DegreeTypeFactory cannot be null.");
        if(degreeTypeRepository == null) throw new IllegalArgumentException("DegreeTypeRepository cannot be null.");

        _degreeTypeFactory = degreeTypeFactory;
        _degreeTypeRepository = degreeTypeRepository;
    }

    @Override
    public DegreeType createAndSaveDegreeType(RegisterDegreeTypeCommand requestCommand) throws Exception {
        if (requestCommand.name() == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }

        if (requestCommand.maxEcts() == null) {
            throw new IllegalArgumentException("MaxEcts cannot be null.");
        }

        DegreeType degreeType = _degreeTypeFactory.create(requestCommand.name(), requestCommand.maxEcts());

        if(degreeType == null) {
            throw new IllegalStateException("Failed to create DegreeType.");
        }

        if (_degreeTypeRepository.containsOfIdentity(degreeType.identity())) {
            throw new BusinessRuleViolationException("DegreeType with this identity already exists");
        }

        _degreeTypeRepository.save(degreeType);

        return degreeType;
    }

    @Override
    public Iterable<DegreeType> getAllDegreeTypes() {
        return _degreeTypeRepository.findAll();
    }

}
