package PAI.mapper;

import PAI.VOs.Country;
import PAI.VOs.NIF;
import PAI.persistence.datamodel.NIFDataModel;
import org.springframework.stereotype.Component;

@Component
public class NIFMapper implements INIFMapper {

    public NIFDataModel domainToDataModel(NIF NIF) {

        String _NIF = NIF.getNIF();
        String country = String.valueOf(NIF.getCountry());

        return new NIFDataModel (_NIF, country);
    }

    public NIF dataModelToDomain (NIFDataModel nifDataModel) throws Exception {

        String NIF = nifDataModel.getNifNumber();
        String countryValue = nifDataModel.getNifCountry();

        Country country = new Country(countryValue);

        return new NIF( NIF, country);
    }
}

