package PAI.service.accessMethod;

import PAI.VOs.AccessMethodID;
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

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import static PAI.utils.ValidationUtils.validateNotBlank;
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
        validateNotNull(command, "RegisterAccessMethodCommand");

        if (repositoryAccessMethod.getAccessMethodByName(command.name()).isPresent()) {
            throw new AlreadyRegisteredException("Access method");
        }

        AccessMethod newAccessMethod = accessMethodFactory.createAccessMethod(command.name());
        return repositoryAccessMethod.saveAccessMethod(newAccessMethod)
                .map(assembler::toDTO)
                .orElseThrow(() -> new AlreadyRegisteredException("Access Method"));
    }

    @Override
    public AccessMethodServiceDTO getAccessMethodById(String id) {
        validateNotBlank(id, "AccessMethod ID");

        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format for AccessMethod ID.");
        }

        AccessMethodID accessMethodId = new AccessMethodID(uuid);

        return repositoryAccessMethod.ofIdentity(accessMethodId)
                .map(assembler::toDTO)
                .orElseThrow(() -> new BusinessRuleViolationException("Access method not found with ID " + id));
    }

    @Override
    public List<AccessMethodServiceDTO> getAllAccessMethods() {
        Iterable<AccessMethod> iterable = repositoryAccessMethod.findAll();

        List<AccessMethod> accessMethods = StreamSupport.stream(iterable.spliterator(), false)
                .toList();
        if (accessMethods.isEmpty()) {
            throw new BusinessRuleViolationException("No access methods found.");
        }
        return assembler.toDTOList(accessMethods);
    }
}

