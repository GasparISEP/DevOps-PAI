package PAI.mapper.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.persistence.datamodel.DepartmentIDDataModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentIDMapperImplTest {

    @Test
    void shouldMapDepartmentIDDataModelToDepartmentID() {
        //arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        DepartmentID idDomain = mock(DepartmentID.class);
        when(idDomain.getAcronym()).thenReturn(acronym);

        DepartmentIDMapperImpl mapper = new DepartmentIDMapperImpl();
        //act
        DepartmentIDDataModel idDataModel = mapper.toDataModel(idDomain);
        //assert
        assertEquals("DEI", idDataModel.getDepartmentID());
    }

    @Test
    void shouldNotMapDepartmentIDDataModelToDepartmentIDIfInputNull(){
        //arrange
        DepartmentIDMapperImpl mapper = new DepartmentIDMapperImpl();
        //act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mapper.toDataModel(null)
        );

        assertEquals("DepartmentID cannot be null", exception.getMessage());
    }

    @Test
    void shouldMapDepartmentIDToDepartmentIDDataModel() {
        DepartmentIDDataModel idDataModel = mock(DepartmentIDDataModel.class);
        when(idDataModel.getDepartmentID()).thenReturn("DEI");

        DepartmentIDMapperImpl mapper= new DepartmentIDMapperImpl();
        //act
        DepartmentID idDomain = mapper.toDomainModel(idDataModel);

        //assert
        assertEquals("DEI", idDomain.getAcronym().getAcronym());
    }

    @Test
    void shouldNotMapDepartmentIDToDepartmentIDDataModelIfInputNull() {
        //arrange
        DepartmentIDMapperImpl mapper = new DepartmentIDMapperImpl();
        //act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                mapper.toDomainModel(null)
        );

        assertEquals("DepartmentIDDataModel cannot be null", exception.getMessage());
    }
}