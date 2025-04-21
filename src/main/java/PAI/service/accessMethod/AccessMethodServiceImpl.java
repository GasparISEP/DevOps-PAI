package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessMethodServiceImpl implements IAccessMethodService {

    private final IAccessMethodFactory accessMethodFactory;
    private final IRepositoryAccessMethod repositoryAccessMethod;

    public AccessMethodServiceImpl(IAccessMethodFactory accessMethodFactory, IRepositoryAccessMethod repositoryAccessMethod) {
        if(accessMethodFactory == null) {
            throw new IllegalArgumentException("accessMethodFactory cannot be null");
        }
        if(repositoryAccessMethod == null) {
            throw new IllegalArgumentException("repositoryAccessMethod cannot be null");
        }
        this.accessMethodFactory = accessMethodFactory;
        this.repositoryAccessMethod = repositoryAccessMethod;
    }
    @Override
    public Optional<AccessMethod> registerAccessMethod(NameWithNumbersAndSpecialChars accessMethodName) {
        if (repositoryAccessMethod.getAccessMethodByName(accessMethodName).isPresent()) {
            return Optional.empty();
        }
        AccessMethod accessMethod = accessMethodFactory.createAccessMethod(accessMethodName);
        return repositoryAccessMethod.saveAccessMethod(accessMethod);
    }
}
