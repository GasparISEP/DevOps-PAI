package PAI.mapper;

import PAI.VOs.NIF;
import PAI.persistence.datamodel.NIFDataModel;

public interface INIFMapper {

    NIFDataModel domainToDataModel(NIF nif);
    NIF dataModelToDomain (NIFDataModel nifDataModel) throws Exception;
}
