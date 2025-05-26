package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import PAI.utils.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ServiceResponse<AccessMethodResponseDTO> configureAccessMethod(RegisterAccessMethodCommand command) {
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(command.name());

        if (repositoryAccessMethod.getAccessMethodByName(nameVO).isPresent()) {
            return ServiceResponse.failure("Access method already exists.");
        }

        AccessMethod newAccessMethod = accessMethodFactory.createAccessMethod(nameVO);
        Optional<AccessMethod> saved = repositoryAccessMethod.saveAccessMethod(newAccessMethod);

        return saved
                .map(am -> {
                    AccessMethodResponseDTO dto = assembler.toDto(am);
                    return ServiceResponse.success(dto);
                })
                .orElseGet(() -> ServiceResponse.failure("Failed to save access method."));
    }

}

