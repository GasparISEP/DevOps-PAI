package PAI.persistence.springdata.accessMethod;

import PAI.VOs.AccessMethodID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.mapper.accessMethod.IAccessMethodMapper;
import PAI.persistence.datamodel.accessMethod.AccessMethodDataModel;
import PAI.repository.accessMethodRepository.IRepositoryAccessMethod;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccessMethodRepositorySpringDataImpl implements IRepositoryAccessMethod {

    private final IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData;
    private final IAccessMethodMapper iAccessMethodMapper;


    public AccessMethodRepositorySpringDataImpl(IAccessMethodRepositorySpringData iAccessMethodRepositorySpringData, IAccessMethodMapper iAccessMethodMapper) {
        if(iAccessMethodRepositorySpringData == null) {
            throw new IllegalArgumentException("iAccessMethodRepositorySpringData must not be null");
        }
        if(iAccessMethodMapper == null) {
            throw new IllegalArgumentException("iAccessMethodMapper must not be null");
        }
        this.iAccessMethodRepositorySpringData = iAccessMethodRepositorySpringData;
        this.iAccessMethodMapper = iAccessMethodMapper;
    }

    @Override
    public Optional<AccessMethod> saveAccessMethod(AccessMethod accessMethod) {
        AccessMethod savedAccessMethod = save(accessMethod);
        return Optional.ofNullable(savedAccessMethod);
}

    @Override
    public Optional<AccessMethod> getAccessMethodByName(NameWithNumbersAndSpecialChars accessMethodNameToSearch) {
        String accessMethodName = accessMethodNameToSearch.toString();
        Optional<AccessMethodDataModel> accessMethodDataModelOptional = iAccessMethodRepositorySpringData.findAccessMethodDataModelByName(accessMethodName);
        if(accessMethodDataModelOptional.isPresent()) {
            AccessMethodDataModel accessMethodDataModel = accessMethodDataModelOptional.get();
            return iAccessMethodMapper.toDomain(accessMethodDataModel);
        }
        return Optional.empty();
    }

    @Override
    public AccessMethod save(AccessMethod entity) {
        Optional<AccessMethodDataModel> accessMethodDataModel = iAccessMethodMapper.toDataModel(entity);
        if(accessMethodDataModel.isPresent()) {
            iAccessMethodRepositorySpringData.save(accessMethodDataModel.get());
            Optional<AccessMethod> accessMethod = iAccessMethodMapper.toDomain(accessMethodDataModel.get());
            if(accessMethod.isPresent()) {
                return accessMethod.get();
            }
        }
        return null;
    }

    @Override
    public Iterable<AccessMethod> findAll() {
        List<AccessMethod> allAccessMethods = new ArrayList<>();
        List<AccessMethodDataModel> allAccessMethodDataModels = iAccessMethodRepositorySpringData.findAll();
        for(AccessMethodDataModel accessMethodDataModel : allAccessMethodDataModels) {
            Optional<AccessMethod> accessMethodOptional = iAccessMethodMapper.toDomain(accessMethodDataModel);
            accessMethodOptional.ifPresent(allAccessMethods::add);
        }
        return allAccessMethods;
    }

    @Override
    public Optional<AccessMethod> ofIdentity(AccessMethodID id) {
        Optional<AccessMethodDataModel> accessMethodDataModel = iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(id.getAccessMethodID());
        if(accessMethodDataModel.isPresent()) {
            return iAccessMethodMapper.toDomain(accessMethodDataModel.get());
        }
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(AccessMethodID id) {
        Optional<AccessMethodDataModel> accessMethodDataModel = iAccessMethodRepositorySpringData.findAccessMethodDataModelByAccessMethodID(id.getAccessMethodID());
        return accessMethodDataModel.isPresent();
    }

}
