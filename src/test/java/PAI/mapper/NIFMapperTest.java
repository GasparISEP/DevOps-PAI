package PAI.mapper;

import PAI.VOs.Country;
import PAI.VOs.NIF;
import PAI.persistence.datamodel.NIFDataModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NIFMapperTest {

    @Test
    void testDomainToDataModel() {
        // Arrange
        Country countryMock = mock(Country.class);
        NIF nifMock = mock(NIF.class);

        String nifValue = "123456789";
        String countryValue = "Portugal";

        // Mock NIF behavior
        when(nifMock.getNIF()).thenReturn(nifValue);
        when(nifMock.getCountry()).thenReturn(countryMock);
        when(countryMock.toString()).thenReturn(countryValue);

        NIFMapper nifMapper = new NIFMapper();

        // Act
        NIFDataModel dataModel = nifMapper.domainToDataModel(nifMock);

        // Assert
        assertNotNull(dataModel);
        assertEquals(nifValue, dataModel.getNIF());
        assertEquals(countryValue, dataModel.getCountry());
    }

    @Test
    void testDataModelToDomain() throws Exception {
        // Arrange
        String nifValue = "123456789";
        String countryValue = "Portugal";
        NIFDataModel nifDataModelMock = mock(NIFDataModel.class);


        when(nifDataModelMock.getNIF()).thenReturn(nifValue);
        when(nifDataModelMock.getCountry()).thenReturn(countryValue);

        NIFMapper nifMapper = new NIFMapper();

        // Act
        NIF nif = nifMapper.dataModelToDomain(nifDataModelMock);

        // Assert
        assertNotNull(nif);
        assertEquals(nifValue, nif.getNIF());
        assertEquals(countryValue, nif.getCountry().toString());
    }

}