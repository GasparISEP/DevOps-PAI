package PAI.repository.accessMethodRepositoryDDD;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.ddd.IRepository;
import PAI.domain.accessMethodDDD.AccessMethodDDD;

import java.util.Optional;

public interface IRepositoryAccessMethodDDD extends IRepository<AccessMethodID, AccessMethodDDD> {
    boolean registerAccessMethod(NameWithNumbersAndSpecialChars accessMethodName);

    Optional<AccessMethodDDD> getAccessMethodByName (NameWithNumbersAndSpecialChars accessMethodNameToSearch);
}
