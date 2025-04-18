package PAI.service.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;

import java.util.Optional;

public interface IAccessMethodService {
    Optional<AccessMethod> registerAccessMethodInMemoryRepository(NameWithNumbersAndSpecialChars accessMethodName);
}
