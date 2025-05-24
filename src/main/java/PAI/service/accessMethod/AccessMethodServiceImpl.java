package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.assembler.accessMethod.IAccessMethodAssembler;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.domain.repositoryInterfaces.accessMethod.IRepositoryAccessMethod;
import PAI.dto.accessMethod.RegisterAccessMethodCommand;
import PAI.dto.accessMethod.AccessMethodResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessMethodServiceImpl implements IAccessMethodService {

    private final IAccessMethodFactory accessMethodFactory;
    private final IRepositoryAccessMethod repositoryAccessMethod;
    private final IAccessMethodAssembler assembler;

    public AccessMethodServiceImpl(IAccessMethodFactory accessMethodFactory, IRepositoryAccessMethod repositoryAccessMethod,
                                   IAccessMethodAssembler assembler) {

        if(accessMethodFactory == null) {
            throw new IllegalArgumentException("accessMethodFactory cannot be null");
        }
        if(repositoryAccessMethod == null) {
            throw new IllegalArgumentException("repositoryAccessMethod cannot be null");
        }
        if(assembler == null) {
            throw new IllegalArgumentException("assembler cannot be null");
        }
        this.accessMethodFactory = accessMethodFactory;
        this.repositoryAccessMethod = repositoryAccessMethod;
        this.assembler = assembler;
    }


    @Override
    public Optional<AccessMethodResponseDTO> configureAccessMethod(RegisterAccessMethodCommand command) {
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(command.name());

        if (repositoryAccessMethod.getAccessMethodByName(nameVO).isPresent()) {
            return Optional.empty();
        }

        AccessMethod newAccessMethod = accessMethodFactory.createAccessMethod(nameVO);
        Optional<AccessMethod> saved = repositoryAccessMethod.saveAccessMethod(newAccessMethod);

        return saved.map(assembler::toDto);
    }
}

