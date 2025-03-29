package PAI.repository;

import PAI.domain.AccessMethod;
import PAI.factory.IAccessMethodFactory;
import PAI.factory.IAccessMethodListFactory;

import java.util.List;
import java.util.Optional;

public class AccessMethodRepository {

    private final IAccessMethodFactory _IaccessMethodFactory;
    private final List<AccessMethod> _accessMethods;

    public AccessMethodRepository (IAccessMethodFactory iAccessMethodFactory, IAccessMethodListFactory iAccessMethodListFactory) {
        _IaccessMethodFactory = iAccessMethodFactory;
        _accessMethods = iAccessMethodListFactory.createAccessMethodList();
    }

    //register accessMethod
    public boolean registerAccessMethod (String accessMethodName) {
        try {
            AccessMethod accessMethod = _IaccessMethodFactory.createAccessMethod(accessMethodName);

            if (isAccessMethodRegistered(accessMethod))
                return false;

            _accessMethods.add(accessMethod);
            return true;

        } catch (Exception e) {
           return false;
        }
    }

    private boolean isAccessMethodRegistered(AccessMethod accessMethod) {

        return _accessMethods.contains(accessMethod);
    }

    public Optional <AccessMethod> getAccessMethodByName(String name) {
        for ( AccessMethod accessMethod : _accessMethods) {
            if ( accessMethod.hasThisAccessMethodName(name)){
                return Optional.of(accessMethod);
            }
        }
        return Optional.empty();
    }

    //might be useful in the future
    //    public Optional <AccessMethod> createAccessMethod (String accessMethodName){
//        try{
//            AccessMethod accessMethod = _accessMethodFactory.createAccessMethod(accessMethodName);
//            if(!isAccessMethodRegistered(accessMethod)){
//                _accessMethodRepository.add(accessMethod);
//                return Optional.of(accessMethod);
//            }
//
//            return Optional.empty();
//
//        } catch (InstantiationException e) {
//            return Optional.empty();
//        }
//    }
}
