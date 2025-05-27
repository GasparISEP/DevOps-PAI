package PAI.service.accessMethod;

import PAI.assembler.accessMethod.IAccessMethodControllerAssembler;
import PAI.assembler.accessMethod.IAccessMethodServiceAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.exception.AlreadyRegisteredException;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import static PAI.utils.ValidationUtils.validateNotNull;

@Service
public class AccessMethodServiceImpl implements IAccessMethodService {

    private final IAccessMethodFactory accessMethodFactory;
    private final IRepositoryAccessMethod repositoryAccessMethod;
    private final IAccessMethodServiceAssembler assembler;

    public AccessMethodServiceImpl(IAccessMethodFactory accessMethodFactory,
                                   IRepositoryAccessMethod repositoryAccessMethod,
                                   IAccessMethodServiceAssembler assembler) {
        this.accessMethodFactory = validateNotNull(accessMethodFactory, "AccessMethodFactory");
        this.repositoryAccessMethod = validateNotNull(repositoryAccessMethod, "RepositoryAccessMethod");
        this.assembler = validateNotNull(assembler, "AccessMethodServiceAssembler");
    }

    @Override
    public AccessMethodServiceDTO configureAccessMethod(RegisterAccessMethodCommand command) {
        if (repositoryAccessMethod.getAccessMethodByName(command.name()).isPresent()) {
            throw new BusinessRuleViolationException("Access method already exists.");
        }

        AccessMethod newAccessMethod = accessMethodFactory.createAccessMethod(command.name());
        return repositoryAccessMethod.saveAccessMethod(newAccessMethod)
                .map(assembler::toDTO)
                .orElseThrow(() -> new AlreadyRegisteredException("Access Method"));
    }

}

