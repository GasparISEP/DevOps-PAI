package PAI.service.accessMethod;

import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.AccessMethodServiceDTO;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.exception.BusinessRuleViolationException;
import org.springframework.stereotype.Service;

import static PAI.utils.ValidationUtils.validateNotNull;

@Service
public class AccessMethodServiceImpl implements IAccessMethodService {

    private final IAccessMethodFactory accessMethodFactory;
    private final IRepositoryAccessMethod repositoryAccessMethod;
    private final IAccessMethodAssembler assembler;

    public AccessMethodServiceImpl(IAccessMethodFactory accessMethodFactory, IRepositoryAccessMethod repositoryAccessMethod,
                                   IAccessMethodAssembler assembler) {


        this.accessMethodFactory = validateNotNull(accessMethodFactory, "AccessMethodFactory");
        this.repositoryAccessMethod = validateNotNull(repositoryAccessMethod, "RepositoryAccessMethod");
        this.assembler = validateNotNull(assembler, "AccessMethodAssembler");
    }


    @Override
    public AccessMethodServiceDTO configureAccessMethod(RegisterAccessMethodCommand command) {
        if (repositoryAccessMethod.getAccessMethodByName(command.name()).isPresent()) {
            throw new BusinessRuleViolationException("Access method already exists.");
        }

        AccessMethod newAccessMethod = accessMethodFactory.createAccessMethod(command.name());
        return repositoryAccessMethod.saveAccessMethod(newAccessMethod)
                .map(assembler::toDto)
                .orElseThrow(() -> new BusinessRuleViolationException("Failed to save access method."));
    }

}

