package PAI.repository.accessMethodRepository;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.ddd.IRepository;
import PAI.domain.accessMethod.AccessMethod;

import java.util.Optional;

public interface IRepositoryAccessMethod extends IRepository<AccessMethodID, AccessMethod> {
    Optional<AccessMethod> saveAccessMethod(AccessMethod accessMethod);
    Optional<AccessMethod> getAccessMethodByName (NameWithNumbersAndSpecialChars accessMethodNameToSearch);
}
